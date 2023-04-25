package streaming.ui;

<<<<<<< HEAD
import streaming.io.FileIO;

import java.io.File;
import java.io.FileNotFoundException;

=======
>>>>>>> 241397537851f18e3791a55f90a0b06465ec0b35
public class ExceptionHandler {

    public ExceptionHandler(){

    }

<<<<<<< HEAD
    public String catchException(Exception e){

        String errorHandling = null;
        if(e instanceof FileNotFoundException){
            errorHandling = "Obi-Wan Kenobi could not find the file";
        } else if (e instanceof Streaming.Users.UserHandler) {
            errorHandling = " Obi-Wan Kenobi says the passsword or Username is wrong try again";
        }
        return errorHandling;
    }


=======
>>>>>>> 241397537851f18e3791a55f90a0b06465ec0b35
}
