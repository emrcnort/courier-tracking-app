import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.ortakciemrecan.couriertrackingapp",
})
public class CourierTrackingApp {
    public static void main(String[] args) {
        SpringApplication.run(CourierTrackingApp.class, args);
    }
}