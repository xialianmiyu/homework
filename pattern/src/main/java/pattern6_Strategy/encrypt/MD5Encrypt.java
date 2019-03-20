package pattern6_Strategy.encrypt;

public class MD5Encrypt extends Encrypt{

    @Override
    public String encrypt(Object obj) {


        return obj.toString()+"md5密文";
    }
}
