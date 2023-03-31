public class MainMenu {
    private String welcomeMessage;
    private Account loggedInUser;

    //when account is passed, create a welcome message based on the account's username
    public MainMenu(Account loggedInUser){
        this.loggedInUser = loggedInUser;
        this.welcomeMessage = "Welcome, " + loggedInUser.getUsername() + "\n";
    }

    public void displayWelcomeMessage(){
        System.out.println(welcomeMessage);
    }
}
