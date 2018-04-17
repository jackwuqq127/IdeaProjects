package person.jack.db.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Component
public class OracltToMysql {

    private BlockingQueue tableBq;
    private BlockingQueue<String> tableBqForValue=new ArrayBlockingQueue(10);
    private int tableSize;
    private Map<String, String> insertMap = new HashMap<>();
    private JdbcTemplate msqlTemplate;
    private JdbcTemplate oracleTemplate;

    private CountDownLatch cdl = new CountDownLatch(2);

    public void init(JdbcTemplate oracleTemplate,JdbcTemplate msqlTemplate,String...tables){
        this.oracleTemplate=oracleTemplate;
        this.msqlTemplate=msqlTemplate;
        setTableBq(tables);
    }

    public void setTableBq(String...tables) {
        String condition="";
        if(tables.length>0){
            StringBuffer sb = new StringBuffer(" where table_name in(");
            for (String name : tables) {
                sb.append("'"+name.toUpperCase()+"',");
            }
            sb.deleteCharAt(sb.lastIndexOf(","));
            sb.append(")");
            condition=sb.toString();
        }
        String sql="select table_name from user_all_tables "+condition;
        List<Map<String, Object>> list = oracleTemplate.queryForList(sql);
        if(list.size()==0){
            return;
        }
        this.tableBq = new ArrayBlockingQueue(list.size());
        try {
            for (Map<String, Object> map : list) {
                tableBq.put(map.get("table_name"));
                this.tableSize++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void start(){
        ExecutorService threadPool = Executors.newCachedThreadPool();
        JdbcTemplate msqlTemplate=this.msqlTemplate;
        Runnable taskCreateTable=new Runnable() {
            @Override
            public void run() {
                while (tableBq!=null&&tableBq.size() > 0) {
                    String name = null;
                    try {
                        name = tableBq.take().toString();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    String createSql = setCreateTableSql(name);
                    msqlTemplate.execute(createSql);
                    try {
                        tableBqForValue.put(name);
                        System.out.println("成功建表："+name);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                cdl.countDown();
            }
        };

        Runnable taskInsert=new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        String tableName = tableBqForValue.take();
                        System.out.println(tableName);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        threadPool.execute(taskCreateTable);
        threadPool.execute(taskInsert);

        try {
            threadPool.shutdown();
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String setCreateTableSql(String name) {
        String sql = "select c.COLUMN_NAME,c.DATA_TYPE,c.DATA_LENGTH,c.DATA_PRECISION,c.DATA_SCALE " +
                "from USER_TAB_COLUMNS c where  TABLE_NAME='" + name + "'";
        List<Map<String, Object>> list = oracleTemplate.queryForList(sql);
        StringBuffer sb = new StringBuffer("create table if not exists " + name + "(\n");
        StringBuffer insertBuffer = new StringBuffer("insert into "+name+"(");
        StringBuffer valueBuffer = new StringBuffer(" values(");

        Object dataType, dataLen, dataPrecision, data_scale,columeName;
        for (Map<String, Object> map : list) {
            columeName=map.get("column_name").toString();
            dataType = map.get("data_type").toString();
            dataLen = map.get("data_length").toString();
            dataPrecision = map.get("data_precision");
            data_scale = map.get("data_scale");
            String columnType = setMysqlColumnType(dataType, dataLen, dataPrecision, data_scale).toLowerCase();
            sb.append("\t" + map.get("column_name") + " " + columnType + ",\n");
            insertBuffer.append(columeName+",");
            if(columnType.startsWith("varchar")|| columnType.startsWith("decimal")||columnType.startsWith("datetime")){
                valueBuffer.append("?,");
            }
        }
        insertBuffer.replace(insertBuffer.lastIndexOf(","),insertBuffer.length(),")");
        valueBuffer.replace(valueBuffer.lastIndexOf(","),valueBuffer.length(),")");
        insertMap.put(name, insertBuffer.append(valueBuffer).toString().toLowerCase());
        sb.deleteCharAt(sb.lastIndexOf(",")).append(")");
        return sb.toString().toLowerCase();
    }

    private String setMysqlColumnType(Object dataType, Object dataLen, Object dataPrecision, Object dataScale) {
        String typeStr = null;
        if (dataType.equals("NUMBER")) {
            if(dataPrecision==null&&dataScale==null){
                typeStr = "decimal";
                return typeStr;
            }
            typeStr = "decimal(" + dataPrecision + "," + dataScale + ")";
        } else if (dataType.equals("VARCHAR2")||dataType.equals("NCHAR")) {
            typeStr = "varchar(" + dataLen + ")";
        }else if(dataType.equals("DATE")){
            typeStr="datetime";
        }
        return typeStr.toLowerCase();
    }

}
