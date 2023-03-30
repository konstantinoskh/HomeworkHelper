public class MainMenu {
    private String welcomeMessage;
    private Account loggedInUser;

    public MainMenu(Account loggedInUser){
        this.loggedInUser = loggedInUser;
        this.welcomeMessage = "Welcome, " + loggedInUser.getUsername() + "\n";
    }

    public void displayWelcomeMessage(){
        System.out.println(welcomeMessage);
    }
}
