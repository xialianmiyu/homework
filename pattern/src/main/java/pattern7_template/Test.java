package pattern7_template;

public class Test {

    public static void main(String[] args) {
        Process process=new Process();
        process.setFirst(new StepFirstImpl1());
        process.setSecond(new StepSecondImpl2());
        process.setThird(new StepThirdImpl2());
        process.doSomething();
    }
}
