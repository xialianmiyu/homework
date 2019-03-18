package gupao.abstr;

import factory.gupao.*;

public class MilkFactory extends  AbstractFactory {


    @Override
    public Milk getMengniu() {
        return new Mengniu();
    }

    @Override
    public Milk getYili() {
        return new Yili();
    }

    @Override
    public Milk getTelunsu() {
        return new Telunsu();
    }

    @Override
    public Milk getSanlu() {
        return new Sanlu();
    }
}