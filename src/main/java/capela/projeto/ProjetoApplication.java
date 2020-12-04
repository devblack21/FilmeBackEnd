package capela.projeto;

import capela.projeto.web.config.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan(basePackageClasses = {WebConfig.class})
@SpringBootApplication
public class ProjetoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProjetoApplication.class, args);
    }
}
