package com.clone.amazon.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

   private final AmazonUserRepository userRepository ;
   private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    public MyUserDetailService(AmazonUserRepository userRepository  ){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String mailId) throws UsernameNotFoundException {

        var amazonUser = userRepository.findByEmail(mailId);

        if(amazonUser == null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("no user is there in this name");
        }
        return new UserPrincipal(amazonUser);
    }


}
