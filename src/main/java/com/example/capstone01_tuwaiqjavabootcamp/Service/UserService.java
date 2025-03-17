package com.example.capstone01_tuwaiqjavabootcamp.Service;

import com.example.capstone01_tuwaiqjavabootcamp.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    ArrayList<User> users = new ArrayList<>();

    public ArrayList<User> getUsers() {
        return users;
    }

    public String addUser(User user) {
        for (User u : users) {
            if (u.getId().equals(user.getId()))
                return "id";
            else if (u.getUsername().equals(user.getUsername()))
                return "username";
            else if (u.getEmail().equals(user.getEmail()))
                return "email";
        }
        users.add(user);
        return "";
    }

    public String updateUser(String id, User user) {
        for (User u : users) {
            if (u.getId().equals(id)) {
                for (User u2 : users) {
                    if (u2.getUsername().equals(user.getUsername()))
                        return "username";
                    if (u2.getEmail().equals(user.getEmail()))
                        return "email";
                }
                users.set(users.indexOf(u), user);
                return "update";
            }
        }
        return "";
    }

    public boolean deleteUser(String id){
        for (User u:users){
            if (u.getId().equals(id)){
                users.remove(u);
                return true;
            }
        }
        return false;
    }


}
