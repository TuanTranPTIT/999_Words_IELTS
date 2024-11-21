package com.example.__words_ielts.config;

import com.example.__words_ielts.enums.UserRoleEnum;
import com.example.__words_ielts.enums.UserStatusEnum;
import com.example.__words_ielts.model.entity.User;
import com.example.__words_ielts.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ApplicationInitConfig {

    private final PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository){
        return args -> {
            if(userRepository.findByEmail("t.t.tuan.work@gmail.com").isEmpty()){
                User user = User.builder()
                        .email("t.t.tuan.work@gmail.com")
                        .hashPassword(passwordEncoder.encode("Tuan000@"))
                        .role(UserRoleEnum.ADMIN)
                        .status(UserStatusEnum.ACTIVE)
                        .build();

                userRepository.save(user);
                log.warn("Tài khoản Admin đã được tạo với default password: Tuan000@, vui lòng đổi mật khẩu!");
            }
        };
    }
}
