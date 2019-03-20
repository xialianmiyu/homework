package pattern6_Strategy.encrypt;

public class SM2Encrypt extends Encrypt{

    @Override
    public String encrypt(Object obj) {


        return obj.toString()+"国密密文";
    }
}
