package com.thegiftgarden.repository;

import com.thegiftgarden.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {


    // DATABASE CONNECTION VARIABLES
    @Value("${spring.datasource.url}")
    private String DB_HOSTNAME;

    @Value("${spring.datasource.username}")
    private String DB_USERNAME;

    @Value("${spring.datasource.password}")
    private String DB_PASSWORD;


    // FETCH ALL USERS FROM DATABASE
    public List<User> getAllUsers(){
        List userList = new ArrayList();

        return userList;
    }

    // ADD NEW USERS TO DATABASE
    public void addUser(User newUser){
        // TODO: Create a SQL statement
        
    }
}
