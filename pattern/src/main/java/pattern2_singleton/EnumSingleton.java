package pattern2_singleton;

public enum EnumSingleton{
    INSTANCE;
    public static EnumSingleton getInstance(){
        return INSTANCE;
    }
}
