package model;

import java.io.*;
import java.net.URI;
import java.util.ArrayList;

public class UFile  {


    public void writeVector(ArrayList<User> vector, String path) throws IOException {
        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(vector);
        oos.close();

    }

    public ArrayList<User> readVector(String path) throws IOException, ClassNotFoundException {

        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(fis);


        ArrayList<User> mailBoxes = new ArrayList<>();


        mailBoxes = ((ArrayList<User>) ois.readObject());

        ois.close();
        return mailBoxes;
    }


}
