package car;

//public static void main(String[] args) {
//    var car = new Car();
//    car.move(new Go());
//    car.move(new Stop());
//}

public class Condition {

    boolean isMovable() {
        return false;
    }
}

class Stop extends Condition {

    @Override
    boolean isMovable() {
        return false;
    }
}

class Go extends Condition {

    @Override
    boolean isMovable() {
        return true;
    }
}