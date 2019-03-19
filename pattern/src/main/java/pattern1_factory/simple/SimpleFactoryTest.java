package pattern1_factory.simple;


import pattern1_factory.Computer;

public class SimpleFactoryTest {

    public static void main(String[] args) {
        SimpleFactory factory=new SimpleFactory();
        Computer computer =factory.getComputer("dell");
        System.out.println(computer.getBrand());
    }
}
