package com.test.xeptagon;

import com.test.xeptagon.entities.User;
import com.test.xeptagon.enums.UserType;
import com.test.xeptagon.repositories.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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

}
