package pattern7_template;

public abstract class StepThird {
    abstract void  third();
}

class StepThirdImpl1 extends StepThird{

    @Override
    void third() {
        System.out.println("StepSecond实现方式一");
    }
}

class StepThirdImpl2 extends StepThird{

    @Override
    void third() {
        System.out.println("StepSecond实现方式二");
    }
}

