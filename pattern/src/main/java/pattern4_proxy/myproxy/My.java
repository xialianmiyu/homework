package pattern4_proxy.myproxy;

public class My implements Person{

    @Override
    public void findJob() {
        System.out.println("my own money");
    }
}
