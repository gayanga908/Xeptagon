package com.test.xeptagon;

import com.test.xeptagon.entities.User;
import com.test.xeptagon.enums.UserType;
import com.test.xeptagon.repositories.UserRepository;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class XeptagonApplication {

    public static void main(String[] args) {
        SpringApplication.run(XeptagonApplication.class, args);
    }
    @Bean
    CommandLineRunner init (UserRepository userRepository){
        return args -> {
            User admin = userRepository.findUserByUserName("admin");
            if (admin == null) {
                User adminUser = new User();
                adminUser.setUserName("admin");
                adminUser.setPassword(DigestUtils.sha256Hex("1234"));
                adminUser.setUserType(UserType.ADMINISTRATOR.toString());
                userRepository.save(adminUser);
            }
        };
    }

    @Bean
    public OpenAPI myOpenAPI() {
        Contact contact = new Contact();
        contact.setEmail("gayanga908@gmail.com");
        contact.setName("Tharindu Gayanga");

        Server localServer = new Server();
        localServer.setUrl("http://localhost:8080");
        localServer.setDescription("Server URL in Local environment");

        Info info = new Info()
                .title("Xeptagon Coding Assignment")
                .contact(contact)
                .version("1.0")
                .description("This is an Education system API that exposes endpoints for user creation, " +
                        "class creation, view class modules and execute class modules");

        return new OpenAPI()
                .info(info)
                .servers(List.of(localServer));
    }

}
