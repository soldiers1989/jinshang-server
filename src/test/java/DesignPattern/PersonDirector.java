package DesignPattern;

/**
 * PersonDirector
 *
 * @author xiazy
 * @create 2018-06-08 17:24
 **/
public class PersonDirector {
    public Person  constructPerson(PersonBuilder pb){
        pb.buildBody();
        pb.buildFoot();
        pb.buildHead();
        return pb.buildPerson();
    }

}
