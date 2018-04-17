package person.jack.decorator;

public class RunCar implements Car {
    public void show() {
        this.run();
    }

    public void run(){
        System.out.println("跑……");
    }
}
