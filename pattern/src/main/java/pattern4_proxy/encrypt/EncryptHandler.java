package pattern4_proxy.encrypt;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class EncryptHandler  implements InvocationHandler {


EncryptAction encrypt;

    public EncryptHandler(EncryptAction encrypt) {
        super();
        this.encrypt = encrypt;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        Object object = null;

        System.out.println("start to encrypt");
       boolean isOk= checkData(args);
        if (isOk) {
           object = method.invoke(encrypt, args);
        }
        System.out.println("end to encrypt");

        return object;
    }

    private boolean checkData(Object obj){
        System.out.println("加密前校验");
        //check data code
        return true;
    }
}
