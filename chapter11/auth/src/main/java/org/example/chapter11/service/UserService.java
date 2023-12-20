package org.example.chapter11.service;

import org.example.chapter11.model.Otp;
import org.example.chapter11.model.User;
import org.example.chapter11.repository.OtpRepository;
import org.example.chapter11.repository.UserRepository;
import org.example.chapter11.util.GenerateCodeUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final OtpRepository otpRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, OtpRepository otpRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.otpRepository = otpRepository;
    }

    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void auth(User user) {
        User userFromDB = userRepository.findUserByUsername(user.getUsername())
                .orElseThrow(() -> new BadCredentialsException("Bad Credentials."));
        if (passwordEncoder.matches(user.getPassword(), userFromDB.getPassword())) {
            renewOtp(userFromDB);
        } else {
            throw new BadCredentialsException("Bad Credentials.");
        }
    }

    public boolean check(Otp otpToValidate) {
        Optional<Otp> userOtp = otpRepository.findOtpByUsername(otpToValidate.getUsername());

        if (userOtp.isPresent()) {
            Otp otp = userOtp.get();
            return otpToValidate.getCode().equals(otp.getCode());
        }
        return false;
    }

    private void renewOtp(User user) {
        // OTP를 위한 임의의 수 생성
        String code = GenerateCodeUtil.generateCode();

        // 사용자 이름으로 OTP 검색
        Optional<Otp> otpFromDB = otpRepository.findOtpByUsername(user.getUsername());
        if (otpFromDB.isPresent()) {
            Otp otp = otpFromDB.get();
            otp.setCode(code);
        } else {
            Otp otp = new Otp(user.getUsername(), code);
            otpRepository.save(otp);
        }
    }
}
