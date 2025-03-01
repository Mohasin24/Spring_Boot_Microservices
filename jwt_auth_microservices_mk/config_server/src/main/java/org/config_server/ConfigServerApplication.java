package org.config_server;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.core.env.Environment;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

@SpringBootApplication
//@EnableConfigServer
@EnableDiscoveryClient
public class ConfigServerApplication {

    private final Environment environment;

    public ConfigServerApplication(Environment environment) {
        this.environment = environment;
    }

    private static String jwtSecretKey;

    @PostConstruct
    public void generateJwtSecret() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            SecretKey secretKey = keyGenerator.generateKey();
            jwtSecretKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());

            System.out.println("Generated JWT Secret Key: " + jwtSecretKey);

//            environment.getProperty()
        } catch (Exception e) {
            throw new RuntimeException("Error generating JWT secret key", e);
        }
    }

    public static String getJwtSecretKey() {
        return jwtSecretKey;
    }


    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class,args);
    }
}