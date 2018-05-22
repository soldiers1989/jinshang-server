import org.springframework.stereotype.Component;

/**
 * USACar
 *
 * @author xiazy
 * @create 2018-05-16 15:29
 **/
@Component
public class USACar implements Car {

    @Override
    public void name() {
        System.out.println("This is USA Car.");
    }
}
