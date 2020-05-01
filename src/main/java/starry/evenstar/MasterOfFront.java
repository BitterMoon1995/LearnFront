package starry.evenstar;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"starry.evenstar.mapper"})
public class MasterOfFront {

    public static void main(String[] args) {
        SpringApplication.run(MasterOfFront.class, args);
    }

}
