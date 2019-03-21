package pattern10_observer;

import com.google.common.eventbus.EventBus;

public class Test {

    public static void main(String[] args) {

        Teacher tom = new Teacher("Tom");
        Teacher mic = new Teacher("Mic");

        EventBus eventBus = new EventBus();

        eventBus.register(tom);
        eventBus.register(mic);

        Question question = new Question();
        question.setUserName("小明");
        question.setContent("观察者设计模式适用于哪些场景？");

        eventBus.post(question);




    }

}
