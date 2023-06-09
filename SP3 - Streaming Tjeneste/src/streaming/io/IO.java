package streaming.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import streaming.users.*;

public interface IO {
    ArrayList<String> readDataMedia() throws FileNotFoundException;
    ArrayList<String> readDataSeries() throws IOException;
    ArrayList<String> readDataUser() throws IOException;
    void writeDataUser(User currentUser);
}
