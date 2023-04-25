package streaming.io;

import java.io.IOException;
import java.util.ArrayList;
import streaming.users.*;

public interface IO {
    ArrayList<String> readDataMedia() throws IOException;
    ArrayList<String> readDataSeries() throws IOException;
    ArrayList<String> readDataUser(User currentUser) throws IOException;
    void writeDataUser(User currentUser);
}
