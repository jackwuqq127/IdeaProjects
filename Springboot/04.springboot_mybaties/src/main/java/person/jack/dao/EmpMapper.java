package person.jack.dao;

import person.jack.bean.Emp;

import java.util.List;

/**
 * EmpMapper class
 *
 * @author wuchao
 * @date 2018/2/9
 */
public interface EmpMapper {

    /**
     * 根据员工（主键）编号删除员工
     * @param empno 员工编号
     * @return 返回受影响的行数
     */
    int deleteByPrimaryKey(Integer empno);

    int insert(Emp record);

    int insertSelective(Emp record);

    Emp selectByPrimaryKey(Integer empno);

    int updateByPrimaryKeySelective(Emp record);

    int updateByPrimaryKey(Emp record);

    List<Emp> selectAll();
}