package gupao.abstr;

import factory.gupao.Milk;

public abstract class AbstractFactory {

    //公共的逻辑
    //方便于统一管理

    /**
     * 获得一个蒙牛品牌的牛奶
     * @return
     */
    public  abstract Milk getMengniu();

    /**
     * 获得一个伊利品牌的牛奶
     * @return
     */
    public abstract  Milk getYili();

    /**
     * 获得一个特仑苏品牌的牛奶
     * @return
     */
    public  abstract  Milk getTelunsu();

    public abstract Milk getSanlu();

}