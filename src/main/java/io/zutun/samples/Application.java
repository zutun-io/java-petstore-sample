package io.zutun.samples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import io.zutun.samples.config.JpaConfig;
import io.zutun.samples.config.WebConfig;

@EnableConfigurationProperties
@SpringBootApplication
@Import({
        JpaConfig.class,
        WebConfig.class
})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
