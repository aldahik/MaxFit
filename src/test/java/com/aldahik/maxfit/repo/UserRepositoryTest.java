package com.aldahik.maxfit.repo;
import com.aldahik.user.User;
import com.aldahik.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.test.autoconfigure.*;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByUsername_shouldReturnUser(){
        User user = new User("test", "test", "test", 99);
        User savedUser = userRepository.save(user);
        Optional<User> result = userRepository.findByUsername(savedUser.getUsername());
        assertThat(result).isPresent();
        assertThat(result.get().getUsername()).isEqualTo(savedUser.getUsername());
    }


}
