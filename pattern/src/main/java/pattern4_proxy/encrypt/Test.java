package pattern4_proxy.encrypt;

import java.lang.reflect.Proxy;

public class Test {
	public static void main(String[] args) {
		EncryptAction encrypt = new SM2EncryptAction();
		EncryptHandler handler = new EncryptHandler(encrypt);
		EncryptAction proxy = (EncryptAction) Proxy.newProxyInstance(
				ClassLoader.getSystemClassLoader(), new Class[] { EncryptAction.class },
				handler);

		String info="hello proxy";
		System.out.println("加密前数据"+info);
		Object obj =proxy.encrypt(info);
		System.out.println("加密后数据"+obj);
	}
}