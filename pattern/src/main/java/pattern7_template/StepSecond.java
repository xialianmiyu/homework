package pattern7_template;

public abstract class StepSecond {
    abstract void  second();
}

class StepSecondImpl1 extends StepSecond{

    @Override
    void second() {
        System.out.println("StepSecond实现方式一");
    }
}

class StepSecondImpl2 extends StepSecond{

    @Override
    void second() {
        System.out.println("StepSecond实现方式二");
    }
}

