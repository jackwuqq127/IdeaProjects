package person.jack.decorator;

public class SwimDecorator extends CarDecorator {
    public SwimDecorator(Car car) {
        super(car);
    }

    public void show() {
        super.getCar().show();
        this.swim();
    }

    public void swim(){
        System.out.println("游……");
    }
}
