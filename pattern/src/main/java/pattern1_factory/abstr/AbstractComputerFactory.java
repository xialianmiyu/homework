package pattern1_factory.abstr;

public abstract class AbstractComputerFactory {

    public abstract Cpu getCpu();

    public abstract Cache getCache();

    public abstract Board getBoard();

    public void packagComputer() {
        System.out.println(getCpu().getName());

        System.out.println(getCache().getName());
        System.out.println(getBoard().getName());
    }
}
