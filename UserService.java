//SERVICE->PROCESA,VALIDA Y APLICA REGLAS
package com.myproyect.miproyect;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public User getUserByEmail(String email) {
        if (email.contains("@gmail.com")) {
            return userRepository.getUserByEmail(email);
        } else {
            return null;
        }
    }

    public List<User> getAll() {
        if (userRepository.getAll() != null) {
            return userRepository.getAll();
        } else {
            System.out.println("*ERROR->Empty collection");
            return null;
        }
    }

    public User addUser(User user) {
        if (user.getName() != null && user.getEmail() != null && user.getPassword() != null) {

            if (!userRepository.thisEmailExist(user.getEmail())) {
                return userRepository.addUser(user);
            } else {
                System.out.println("*ERROR->This email already exist!");
                return null;
            }

        } else {
            System.out.println("*ERROR->Verify this user");
            return null;
        }
    }

    // ===== NOT IMPLEMENTED =====
    /*
     * 
     * public void delete(User user) {
     * if (user != null) {
     * userRepository.delete(user);
     * }
     * }
     * 
     * public User update(User user) {
     * return userRepository.update(user);
     * }
     */
}
