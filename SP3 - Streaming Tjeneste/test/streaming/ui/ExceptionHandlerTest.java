package streaming.ui;

import streaming.exceptions.InValidPasswordException;
import streaming.exceptions.InValidUsername;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ExceptionHandlerTest {

    @org.junit.jupiter.api.Test
    void catchException() {
        ExceptionHandler ged = new ExceptionHandler();
        Exception ie = new InValidPasswordException("Password wrong");
        Exception ie2 = new InValidUsername("Username issue");
        Exception ie3 = new InValidPasswordException("Password wrong");
        Exception ie4 = new FileNotFoundException();
        ged.catchException(ie);
        ged.catchException(ie2);
        ged.catchException(ie3);
        ged.catchException(ie4);

    }
}