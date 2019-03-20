package pattern4_proxy.encrypt;

public class SM2EncryptAction implements  EncryptAction{
    @Override
    public String encrypt(Object info) {

        System.out.println("国密算法加密");

        return info.toString()+"密文";
    }
}
