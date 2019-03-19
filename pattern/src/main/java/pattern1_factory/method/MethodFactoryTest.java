package pattern1_factory.method;

import pattern1_factory.Computer;

public class MethodFactoryTest {
    public static void main(String[] args) {
        ComputerFactory factory=new DellComputerFactory();
        Computer computer=factory.createComputer();
        System.out.println(computer.getBrand());
    }
}
