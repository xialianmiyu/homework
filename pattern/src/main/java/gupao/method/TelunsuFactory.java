package gupao.method;

import factory.gupao.Milk;
import factory.gupao.Telunsu;

public class TelunsuFactory implements Factory {
    @Override
    public Milk getMilk() {
        return new Telunsu();
    }
}