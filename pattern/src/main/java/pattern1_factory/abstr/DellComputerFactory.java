package pattern1_factory.abstr;

public class DellComputerFactory extends   AbstractComputerFactory{
    public Cpu getCpu() {
        return new DellCpu("dellCpu");
    }

    public Cache getCache() {
        return new DellCache("dellCache");
    }

    public Board getBoard() {
        return new DellBoard("dellBoard");
    }


}
