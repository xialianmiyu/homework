package pattern1_factory.abstr;

public class LenovoComputerFactory extends   AbstractComputerFactory{
    public Cpu getCpu() {
        return new LenovoCpu("LenovoCpu");
    }

    public Cache getCache() {
        return new LenovoCache("LenovoCache");
    }

    public Board getBoard() {
        return new LenovoBoard("LenovoBoard");
    }


}
