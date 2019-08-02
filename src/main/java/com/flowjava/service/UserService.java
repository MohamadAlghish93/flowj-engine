package com.flowjava.service;

import com.flowjava.dao.UserDao;
import com.flowjava.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.flowjava.shared.ConstantsApp.DEFAULT_ROLE;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public UserEntity createUser(UserEntity userEntity) {
        if (userEntity.getPassword() != null && userEntity.getPassword() != ""){
            userEntity.setPassword(encoder.encode(userEntity.getPassword()));
        }
        return this.userDao.save(userEntity);
    }

    public Optional<UserEntity> findByEmail(String email) {

        return this.userDao.findByEmail(email);
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Optional<UserEntity> currentUser = this.userDao.findByEmail(s);

        if(currentUser.isPresent()) {
            // Remember that Spring needs roles to be com this format: "ROLE_" + userRole (i.e. "ROLE_ADMIN")

            // The "User" class is provided by Spring and represents a model class for user to be returned by UserDetailsService
            // And used by auth manager to verify and check user authentication.
            return new User(currentUser.get().getEmail(), currentUser.get().getPassword(), getAuthority(currentUser.get()));
        }

        // If user not found. Throw this exception.
        throw new UsernameNotFoundException("Email: " + s + " not found");
    }

    private Set<SimpleGrantedAuthority> getAuthority(UserEntity user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
//        if (user.getRoles().size() > 0) {
//            user.getRoles().forEach(role -> {
//                authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
//            });
//        } else {
//            authorities.add(new SimpleGrantedAuthority("ROLE_" + DEFAULT_ROLE));
//        }
        authorities.add(new SimpleGrantedAuthority("ROLE_" + DEFAULT_ROLE));

        return authorities;
    }
}
