package gupao.method;

import factory.gupao.Milk;
import factory.gupao.Yili;

public class YiliFactory implements Factory {
    @Override
    public Milk getMilk() {
        return new Yili();
    }
}