package person.jack.decorator;

public class FlyDecorator extends CarDecorator {
    public FlyDecorator(Car car) {
        super(car);
    }

    public void show() {
        this.getCar().show();
        this.fly();
    }

    public void fly(){
        System.out.println("飞……");
    }


}
