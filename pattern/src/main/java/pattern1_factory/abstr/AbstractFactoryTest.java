package pattern1_factory.abstr;

public class AbstractFactoryTest {
    public static void main(String[] args) {
        AbstractComputerFactory factory=new DellComputerFactory();
        factory.packagComputer();
    }
}
