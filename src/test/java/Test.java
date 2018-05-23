import DesignPattern.Country;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import mizuki.project.core.restserver.util.OtherUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import project.jinshang.common.utils.GsonUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
//        String str= OtherUtil.get32UUID();
//        System.out.println(str);
//        Gson gson = new Gson();
//        //language=JSON
//        String json="[{\"name\":\"xiazhenyu\",\"age\":27,\"country\":\"China\"},{\"name\":\"John\",\"age\":22,\"country\":\"USA\"}]";
////        Map<String, JsonObject> map = gson.fromJson(json, new TypeToken<Map<String,Student>>(){}.getType());
////        System.out.println(new TypeToken<Map<String,Student>>(){}.getType());
//        List<Student> student=GsonUtils.toList(json,Student.class);
//        System.out.println(student.toString());
//        BigDecimal one=new BigDecimal(-980.00);
//        BigDecimal two=new BigDecimal(21.00);
//        BigDecimal three=one.add(two);
//        System.out.println(three.doubleValue());

//        new Thread(() -> System.out.println("In Java8,lambda expression")).start();;
        List<String> lists= Arrays.asList("John","Bush","Steve","Lina","Amda");
//        lists.forEach(str-> System.out.println(str));
//        lists.forEach(System.out::print);
//        filter(lists,(str)->str.startsWith("J"));

//        Predicate<String> startsWith=(n) -> n.startsWith("A");
//        Predicate<String> endWith=(n)->n.endsWith("a");
//        lists.stream().filter(startsWith.and(endWith)).forEach(n-> System.out.println(n));

//        List<Long> coutBeforeTax=Arrays.asList(100l,200l,300l,400l,500l);
//        coutBeforeTax.stream().map(n->n+0.12*n).forEach(System.out::println);
//        List<String> filtered=lists.stream().filter(x->x.length()>4).collect(Collectors.toList());
//        System.out.printf("Original List is:%s,filtered list is :%s %n",lists,filtered);
//        String upCase=lists.stream().map(x->x.toUpperCase()).collect(Collectors.joining(","));
//        System.out.println(upCase);
//        List<Integer> numbers=Arrays.asList(1,2,3,4,5,2);
//        List<Integer> sortnumbers=numbers.stream().map(n->n).distinct().collect(Collectors.toList());
//        System.out.printf("Original List is :%s,Add Without duplicates :%s %n",numbers,sortnumbers);
//        IntSummaryStatistics stats=numbers.stream().mapToInt(x->x).summaryStatistics();
//        System.out.println("Highest prime number in List:"+stats.getMax());
//        System.out.println("Lowest prime number in List:"+stats.getMin());
//        System.out.println("Sum of all prime numbers:"+stats.getSum());
//        System.out.println("Average of all prime numbers:"+stats.getAverage());
//        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(CarConfig.class);
//         Dessert dessert = ac.getBean(Dessert.class);
//         Car car=ac.getBean(Car.class);
//         car.name();
//        SingletonInstance singletonInstance=SingletonInstance.getInstance();
//        String songname=singletonInstance.Sing();
//        SingletonInstance3 singletonInstance3=SingletonInstance3.getInstance();
//        System.out.println(singletonInstance3.Sing());
//        SingletonInstance5 instance5=SingletonInstance5.getInstance5();
//        System.out.println(instance5.Song());
//        Dynasty dynasty=Dynasty.HAN;
//        System.out.println(dynasty.toString());

//        for (Food.Dessert dessert:Food.Dessert.values()){
//            System.out.println(dessert+" ");
//        }
//        for (Food.Coffee coffee:Food.Coffee.values()){
//            System.out.println(coffee+" ");
//        }
//        for (Noodel noodel:Noodel.values()){
//            System.out.println(noodel+" ");
//        }

//        Food.Coffee coffee=Food.Coffee.CAPPUCCINO;
//        switch (coffee){
//            case BLACK_COFFEE:
//                System.out.println(coffee.toString());
//                break;
//            case CAPPUCCINO:
//                System.out.println(coffee.toString());
//                break;
//            case DECAF_COFFEE:
//                System.out.println(coffee.toString());
//                break;
//            case LATTE:
//                System.out.println(coffee.toString());
//                break;
//        }

//        short a=null;
        Country country=new Country.Builder().setName("China").setArea("Asia").setPopulation(14).build();
        System.out.println(country.getArea()+":"+country.getName()+":"+country.getPopulation());

    }
    public static void filter(List<String> names,Predicate condition){
        for (String name:names
                ) {
            if (condition.test(name)){
                System.out.println(name +" ");
            }
        }

    }
}
