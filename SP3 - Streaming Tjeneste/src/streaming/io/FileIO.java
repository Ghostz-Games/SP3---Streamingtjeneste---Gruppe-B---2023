package streaming.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import streaming.users.*;

public class FileIO implements IO{
    private ArrayList<String> read(String filePath) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filePath));
        ArrayList<String> output = new ArrayList<>();
        while (sc.hasNextLine()){
            output.add(sc.nextLine());
        }
        sc.close();
        return output;
    }
    @Override
    public ArrayList<String> readDataMedia() throws FileNotFoundException {
        return read("Data/Film.csv");
    }

    @Override
    public ArrayList<String> readDataSeries() throws FileNotFoundException {
        return read("Data/serier.csv");
    }

    @Override
    public ArrayList<String> readDataUser() throws FileNotFoundException {
        return read("Data/users.csv");
    }

    @Override
    public void writeDataUser(User currentUser) {
        ArrayList<String> data;
        try {
            data = readDataUser();
        } catch (FileNotFoundException e) {
            data = new ArrayList<>();
        }
        boolean isNew = true;
        for (int i = data.size()-1; i >= 0; --i){
            if(data.get(i).split(";")[0].equals(currentUser.getUsername())){
                data.set(i, currentUser.saveUserData());
                isNew = false;
                break;
            }
        }
        if(isNew){
            data.add(currentUser.saveUserData());
        }
        StringBuilder sb = new StringBuilder();
        for(String s: data){
            sb.append(s).append("\n");
        }
        try (FileWriter fw = new FileWriter("Data/users.csv")) {
            fw.write(sb.toString());
        } catch (IOException e) {
            //Exception handler goes here.
            //Couldn't write to file.
        }
    }
}
