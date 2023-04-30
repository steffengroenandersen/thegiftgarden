package com.thegiftgarden.repository;

import com.thegiftgarden.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {


    // DATABASE CONNECTION VARIABLES
    //@Value("${spring.datasource.url}")
    private String DB_HOSTNAME = "jdbc:mysql://thegiftgardendatabase.mysql.database.azure.com:3306/thegiftgardendatabase";

    //@Value("${spring.datasource.username}")
    private String DB_USERNAME = "thegiftgardenuser";

    //@Value("${spring.datasource.password}")
    private String DB_PASSWORD = "Sesame80";


    // FETCH ALL USERS FROM DATABASE
    public List<User> getAllUsers(){
        List userList = new ArrayList();
        
        try{
            // Connect to database
            Connection connection = DriverManager.getConnection(DB_HOSTNAME, DB_USERNAME, DB_PASSWORD);
            Statement statement = connection.createStatement();
            
            // Create preparedStatement query
            final String SQL_QUERY = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(SQL_QUERY);
            
            // Get data from database and add to ArrayList
            while(resultSet.next()){
                int userID = resultSet.getInt(1);
                String userEmail = resultSet.getString(2);
                String userPassword = resultSet.getString(3);
                String userFirstName = resultSet.getString(4);
                String userLastName = resultSet.getString(5);
                
                User user = new User(userID, userEmail, userPassword, userFirstName, userLastName);
                userList.add(user);
            }
            
        } catch(SQLException e){
            System.out.println("Error: Could not connect to database and getAllUsers.");
            e.printStackTrace();
        }
        
        return userList;
    }

    // ADD NEW USERS TO DATABASE
    public void addUser(User newUser){
        try{
            // Connect to database
            Connection connection = DriverManager.getConnection(DB_HOSTNAME, DB_USERNAME, DB_PASSWORD);
            final String CREATE_QUERY = "INSERT INTO users" +
                    "(user_email, user_password, user_firstname, user_lastname)" +
                    "VALUES (?, ?, ?, ?)";
            
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY);

            preparedStatement.setString(1, newUser.getEmail());
            preparedStatement.setString(2, newUser.getPassword());
            preparedStatement.setString(3, newUser.getFirstName());
            preparedStatement.setString(4, newUser.getLastName());

            preparedStatement.executeUpdate();
            
        } catch(SQLException e){
            System.out.println("Error: Could not connect to database and addUser.");
            e.printStackTrace();
        }
    }
}
