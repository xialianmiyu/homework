package gupao.method;
public class FactoryTest {
    public static void main(String[] args) {
        //System.out.println(new Factory().getMilk(););
        //货比三家
        //不知道谁好谁好谁坏
        //配置，可能会配置错
        Factory factory = new SanluFactory();
        System.out.println(factory.getMilk());//可能会配置错，因为从用户的层面不能明确知道getMilk返回的是不是真的是自己想要的，如果SanluFactory配置错，就有可能返回有问题。
    }
}