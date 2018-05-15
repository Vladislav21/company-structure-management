package com.altarix.exercisetwo.companystructuremanagement;

import com.altarix.exercisetwo.companystructuremanagement.config.DataConfig;
import com.altarix.exercisetwo.companystructuremanagement.config.JacksonConfig;
import com.altarix.exercisetwo.companystructuremanagement.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CompanyStructureManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(new Class[]{
                CompanyStructureManagementApplication.class,
                DataConfig.class,
                JacksonConfig.class,
                SwaggerConfig.class
        }, args);
    }


}
