package com.springservices.springserv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.springservices.springserv.repositories.UserRepository;
import com.springservices.springserv.entity.User;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import com.springservices.springserv.util.TokenGenerator;

@Service
public class UserService {

    @Autowired  // This will inject the UserRepository
    private UserRepository userRepository;
    @Autowired
    private GeoCodingService geoCodingService;


    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User createUser(String name, String email, Double latitude, Double longitude, String locationName, String password) {

        Optional <User> existingUser = userRepository.findByEmail(email);
        if(existingUser.isPresent()){
            throw new RuntimeException("User with email " + email + " already exists try with another unused email");
        }
        String token = TokenGenerator.generateToken();
        String hashedPassword = passwordEncoder.encode( password);

        if(locationName == null || locationName.isEmpty()){
            locationName = geoCodingService.getLocationNameFromCordinates(latitude, longitude);
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(hashedPassword);
        user.setLatitude(latitude);
        user.setLongitude(longitude);
        user.setLocationName(locationName);
        user.setToken(token);
        user.setCreatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }


    public User updateUser(Long id, User userDetails){
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(userDetails.getName());
                    user.setEmail(userDetails.getEmail());
                    return userRepository.save(user);
                })
                .orElse(null);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


}
