package gupao.method;

import factory.gupao.Milk;

public interface Factory {
//工厂必然具有生产产品技能，统一的产品出口
    Milk getMilk();
}