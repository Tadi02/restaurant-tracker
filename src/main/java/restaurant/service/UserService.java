package restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import restaurant.auth.UserRole;
import restaurant.domain.User;
import restaurant.dto.RegisteringUser;
import restaurant.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User getUser(long id) {
        return userRepository.findOne(id);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void removeUser(long id) {
        userRepository.delete(id);
    }

    public boolean isEmailAvailable(String email) {
        if (getUserByEmail(email) != null) {
            return false;
        }
        return true;
    }

    public void registerUser(RegisteringUser registeringUser) {
        User user = new User();
        user.setEmail(registeringUser.getEmail());
        user.setName(registeringUser.getName());
        user.setPassword(registeringUser.getPassword());
        user.setPermissionLevel(UserRole.ROLE_USER);
        saveUser(user);
    }

    public Page<User> getUsersPage(int page) {
        return userRepository.findAll(new PageRequest(page, 5, new Sort(Sort.Direction.ASC, "email")));
    }
}
