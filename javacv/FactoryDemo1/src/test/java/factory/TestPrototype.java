package factory;

import person.jack.bean.Person;

public class TestPrototype {

    public static void main(String[] args) {
        String name = "吴";

        Person p1 = new Person();
        p1.setName(name);
        p1.setAge(18);
        p1.setSal(10.0);
        System.out.println(p1);

        name="张";
        Person p2 =p1.clone();
        p2.setName(name);
        System.out.println(p2);

        System.out.println(p1==p2);
        System.out.println(p1);
        System.out.println(p2);
    }
}
