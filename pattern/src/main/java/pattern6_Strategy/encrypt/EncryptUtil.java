package pattern6_Strategy.encrypt;

import java.util.HashMap;
import java.util.Map;

public class EncryptUtil {

    public static final String MD5 = "MD5";
    public static final String SM2 = "SM2";
    private static Map<String,Encrypt> encryptStrategy = new HashMap<String,Encrypt>();
    static {
        encryptStrategy.put(MD5,new MD5Encrypt());
        encryptStrategy.put(SM2,new SM2Encrypt());
    }

    public static String deal(String encryptType,Object info){
        if(encryptStrategy.containsKey(encryptType)){
            return encryptStrategy.get(encryptType).encrypt(info);
        }
        return new Encrypt().encrypt(info);
    }
}
