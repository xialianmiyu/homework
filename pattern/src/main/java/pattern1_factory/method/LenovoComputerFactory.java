package pattern1_factory.method;

import pattern1_factory.Computer;
import pattern1_factory.Lenovo;

public class LenovoComputerFactory extends ComputerFactory {
    public Computer createComputer() {
        return new Lenovo();
    }
}
