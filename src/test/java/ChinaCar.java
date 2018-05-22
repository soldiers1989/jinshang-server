import org.springframework.stereotype.Component;

/**
 * ChinaCar
 *
 * @author xiazy
 * @create 2018-05-16 15:28
 **/
@Component
public class ChinaCar  implements Car{
    @Override
    public void name() {
        System.out.println("This is China Car");
    }
}
