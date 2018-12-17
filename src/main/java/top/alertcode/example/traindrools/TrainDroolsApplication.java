package top.alertcode.example.traindrools;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("top.alertcode.example.traindrools.drools.mapper")
public class TrainDroolsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrainDroolsApplication.class, args);
    }
    @Bean
    public KieContainer kieContainer() {
        return KieServices.Factory.get().getKieClasspathContainer();
    }

}

