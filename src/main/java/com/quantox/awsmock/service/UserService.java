package com.quantox.awsmock.service;

import com.quantox.awsmock.domain.User;
import com.quantox.awsmock.errors.EntityNotFoundException;
import com.quantox.awsmock.repository.UserRepository;
import com.quantox.awsmock.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private static final String ENTITY = "User";

    private final UserRepository userRepository;

    public User getLoggedUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String login = SecurityUtil.getCurrentUserUsername().orElseThrow(() -> new EntityNotFoundException("logged_user", ENTITY));
        return userRepository.findUserByUsername(login).orElseThrow(() -> new EntityNotFoundException(login, ENTITY));
    }


}
