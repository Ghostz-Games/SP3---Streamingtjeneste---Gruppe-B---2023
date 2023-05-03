package streaming.ui;

import streaming.mediaHandler.Media;
import streaming.users.User;

public interface UI {
    public String getUserInput(String msg);
    void loginOrRegister();
    void login();
    void registerUser();
    void library();
    void mainMenu();
    void viewMovie(Media media);
    public void settings(User user);

}
