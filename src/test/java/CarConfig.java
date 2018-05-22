import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Carconfig
 *
 * @author xiazy
 * @create 2018-05-16 15:35
 **/
@Configuration
@ComponentScan(basePackageClasses = Car.class)
public class CarConfig {
}
