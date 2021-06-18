package model;

public class User {


    private String userName;
    private String password;
    private int point ;


    public User(String userName , String password , int point){

        this.password = password;
        this.userName = userName;
        this.point = point;

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
}
