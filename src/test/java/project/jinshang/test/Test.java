package project.jinshang.test;

import DesignPattern.ConcreateBuilder;
import DesignPattern.Person;
import DesignPattern.PersonDirector;

/**
 * test
 *
 * @author xiazy
 * @create 2018-06-08 17:26
 **/
public class Test {


    public static void main(String[] args) {
        PersonDirector pd=new PersonDirector();
        Person person=pd.constructPerson(new ConcreateBuilder());
        System.out.println(person.getBody());
        System.out.println(person.getFoot());
        System.out.println(person.getHead());
    }
}
