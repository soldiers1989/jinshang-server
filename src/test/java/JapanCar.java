import org.springframework.stereotype.Component;

/**
 * JapanCar
 *
 * @author xiazy
 * @create 2018-05-16 15:30
 **/
@Component
public class JapanCar implements  Car{
    @Override
    public void name() {
        System.out.println("This is JapanCar");
    }
}
