package DesignPattern;

/**
 * 实现Builder接口
 *
 * @author xiazy
 * @create 2018-06-08 17:17
 **/
public class ConcreateBuilder implements PersonBuilder {
    Person person;

    public ConcreateBuilder() {
        person=new Person();
    }

    @Override
    public void buildHead() {
        person.setHead("head");
        System.out.println("111111111");
    }

    @Override
    public void buildBody() {
        person.setBody("body");
        System.out.println("222222222222");
    }

    @Override
    public void buildFoot() {
        person.setFoot("foot");
        System.out.println("333333333333");
    }

    @Override
    public Person buildPerson() {
        return person;
    }
}
