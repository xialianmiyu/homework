package pattern7_template;

public class Process {


    private StepFirst first;
    private StepSecond second;
    private StepThird third;

    private void before(){
        System.out.println("doSomething before");
    }
    private void otherSetp(){
        System.out.println("其他步骤");
    }

    public void doSomething(){
        before();
        first.first();
        second.second();
        third.third();
        otherSetp();
    }
    public StepFirst getFirst() {
        return first;
    }

    public void setFirst(StepFirst first) {
        this.first = first;
    }

    public StepSecond getSecond() {
        return second;
    }

    public void setSecond(StepSecond second) {
        this.second = second;
    }

    public StepThird getThird() {
        return third;
    }

    public void setThird(StepThird third) {
        this.third = third;
    }
}
