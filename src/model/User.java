package model;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {


    private String userName ,password , name ,lastname;
    private int point ;
    public static ArrayList<User> users = new ArrayList<>();

    public User(String userName , String password , int point , String name , String lastname){

        this.password = password;
        this.userName = userName;
        this.point = point;
        this.name = name;
        this.lastname = lastname;

    }

    public User(){

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getLastname() {return lastname;}

    public void setLastname(String lastname) {this.lastname = lastname;}
}
