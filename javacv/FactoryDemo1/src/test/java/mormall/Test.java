package mormall;

import person.jack.bean.Person;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        HashMap map = new HashMap();
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        map.put("5", "4");

        System.out.println(map.get("1"));

    }
}

class Name {
    private String id;
    public Name(String id) {
        this.id = id;
    }

    public String toString(){
        return this.id;
    }
    public boolean equals(Object obj) {
        if (obj instanceof Name) {
            Name name = (Name) obj;
            return (id.equals(name.id));
        }
        return hashCode()==obj.hashCode();
    }

    public int hashCode() {
        Name name = (Name) this;
        System.out.println("Hash>>:" + id.hashCode());
        return id.hashCode();
    }
}
class Name2{
    private String id;

    public Name2(String id) {
        this.id=id;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return hashCode()==obj.hashCode();
    }
}
