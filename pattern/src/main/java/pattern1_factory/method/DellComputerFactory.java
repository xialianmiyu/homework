package pattern1_factory.method;

import pattern1_factory.Computer;
import pattern1_factory.Dell;

public class DellComputerFactory extends ComputerFactory{


    public Computer createComputer() {
        return new Dell();
    }
}
