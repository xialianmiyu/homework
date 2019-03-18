package gupao.method;

import factory.gupao.Milk;
import factory.gupao.Sanlu;

/**
 * Created by Tom on 2018/3/4.
 */
public class SanluFactory implements  Factory {
    @Override
    public Milk getMilk() {
        return new Sanlu();
    }
}
