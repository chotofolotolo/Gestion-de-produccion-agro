package com.myproyect.miproyect;

import java.util.List;

public interface RepositoryInterface {
    //abstract User getUserByEmail(String email);

    abstract List<User> getAll();

    abstract User addUser(User user);

    abstract User deleteUser(User user);

    abstract User updateUser(User user);

}
