package gupao.simple;

import factory.gupao.Mengniu;
import factory.gupao.Milk;
import factory.gupao.Telunsu;
import factory.gupao.Yili;

public class SimpleFactory {
	// 小作坊的简单工厂模式，什么型号的产品都能生产，告诉工厂需要什么，只要工厂能生产，就能返回具体的bean。
	public Milk getMilk(String name) {
		if ("特仑苏".equals(name)) {
			return new Telunsu();
		} else if ("伊利".equals(name)) {
			return new Yili();
		} else if ("蒙牛".equals(name)) {
			return new Mengniu();
		} else {
			System.out.println("不能生产您所需的产品");
			return null;
		}
	}

}