package pattern7_template;

public abstract class StepFirst {
    abstract void  first();
}

class StepFirstImpl1 extends StepFirst{

    @Override
    void first() {
        System.out.println("StepFirst实现方式一");
    }
}

class StepFirstImpl2 extends StepFirst{

    @Override
    void first() {
        System.out.println("StepFirst实现方式二");
    }
}

