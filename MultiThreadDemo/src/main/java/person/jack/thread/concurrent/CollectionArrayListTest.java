package person.jack.thread.concurrent;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class CollectionArrayListTest {
    public static void main(String[] args) {
        //Collection<String> collection =  new ArrayList<String>();
        Collection<String> collection = new CopyOnWriteArrayList<>(); ;
        collection.add("吴超");
        collection.add("周俊");
        collection.add("蒲小强");

        Iterator<String> iterator = collection.iterator();
        while (iterator.hasNext()) {
            String str=iterator.next();
            if(str.equals("蒲小强")){
                collection.remove(str);
            }else{
                System.out.println(str);
            }
        }
    }
}
