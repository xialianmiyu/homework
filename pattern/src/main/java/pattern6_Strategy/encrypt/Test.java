package pattern6_Strategy.encrypt;

public class Test {

    public static void main(String[] args) {
        String code=EncryptUtil.deal(EncryptUtil.MD5,"hello Strategy");
        System.out.println(code);
    }
}
