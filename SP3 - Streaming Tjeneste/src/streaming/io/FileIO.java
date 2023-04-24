package streaming.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO implements IO{
    @Override
    public ArrayList<String> readDataMedia() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Data/Media.csv"));
        ArrayList<String> output = new ArrayList<>();
        while (sc.hasNextLine()){
            output.add(sc.nextLine());
        }
        return output;
    }

    @Override
    public ArrayList<String> readDataUser(User currentUser) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Data/Users/" + currentUser.getName() + ".csv"));
        ArrayList<String> output = new ArrayList<>();
        while (sc.hasNextLine()){
            output.add(sc.nextLine());
        }
        return output;
    }

    @Override
    public void writeDataUser(User currentUser) {
        ArrayList<String> Data;
        try {
            Data = readDataUser(currentUser);
        } catch (FileNotFoundException e) {
            Data = new ArrayList<String>();
        }
        Scanner sc = new Scanner(new File("Data/Users/" + currentUser.getName() + ".csv"));
    }
}
