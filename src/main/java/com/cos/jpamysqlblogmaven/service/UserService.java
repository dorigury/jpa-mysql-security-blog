package com.cos.jpamysqlblogmaven.service;

import com.cos.jpamysqlblogmaven.model.RoleType;
import com.cos.jpamysqlblogmaven.model.User;
import com.cos.jpamysqlblogmaven.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;

    @Transactional
    public int save(User user) {
        System.out.println("User Service: save 호출됨");
        String rawPassword = user.getPassword();
        String encPassword = encoder.encode(rawPassword);
        user.setPassword(encPassword);
        user.setRole(RoleType.USER);
        try {
            userRepository.save(user);
            return 1; // 성공
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // 실패
        }
    }


}
