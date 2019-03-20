package pattern6_Strategy.encrypt;

public class Encrypt {

    /**
     * 默认实现，不加密
     * @param obj
     * @return
     */
    public String encrypt(Object obj){

        return obj.toString();
    }
}
