package com.kokn.paperround.auth;

import com.kokn.paperround.advisor.ConflictException;
import com.kokn.paperround.dto.SignUpDto;
import com.kokn.paperround.entity.User;
import com.kokn.paperround.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService implements UserDetailsService {

    private final UserRepository userRepository;

    public void signup(SignUpDto dto) {


        if (isDuplicated(dto.getEmail())){
            throw new ConflictException("already exist user");
        }

        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(dto, User.class);


        userRepository.save(user);

    }

    private boolean isDuplicated(String email){
        Optional<User> userData = Optional.ofNullable(userRepository.findByEmail(email));
        return userData.isPresent() ? true : false;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("\n\n\n!!!!loadUserByUsername!!!!\n\n\n");
        return null;
    }
}
