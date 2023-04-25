package streaming.exceptions;

public class InValidUsernameOrPasswordException extends Exception{
    public InValidUsernameOrPasswordException(String errorMessage){
        super(errorMessage);
    }
}
