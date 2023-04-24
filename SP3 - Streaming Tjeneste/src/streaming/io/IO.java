package streaming.io;

import java.io.IOException;
import java.util.ArrayList;

public interface IO {
    ArrayList<String> readDataMedia() throws IOException;
    ArrayList<String> readDataSeries() throws IOException;
    ArrayList<String> readDataUser(User currentUser) throws IOException;
    void writeDataUser(User currentUser);
    ArrayList<Media> readSavedMedia(User currentUser);
    void writeSavedMedia(User currentUser);
}
