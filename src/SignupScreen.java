import java.util.Scanner;
public class SignupScreen {

Scanner sc = new Scanner(System.in);

    //allows user to sign up by adding an account to the database
    public Account SignUp() throws Exception {

        //initialises account
        Account account = null;

        //shows a main menu of options
        System.out.println("Press 1 to see options\n" + "Press 2 to create account\n" + "Press 3 to log in\n" + "Press 4 to quit \n");
        int option = 0;
        boolean valid;

        //if option isn't 4 and a user isn't logged in keep asking the user to enter an option
        while(option != 4 && !Database.isLoggedIn()) {
            do {
                try {
                    System.out.print("Option: ");
                    //stores user's option
                    option = sc.nextInt();
                    valid = true;

                    //if the user enters a string rather than a number, show them an error message
                }catch (java.util.InputMismatchException e){
                    System.out.println("Invalid option\n");
                    //set valid to false
                    valid = false;
                    //reset scanner
                    sc.next();
                }
                //repeat while valid is false
            }while(!valid);

            switch(option){
                //if user enters 1, show options
                case 1: System.out.println("Press 1 to see options\n" + "Press 2 to create account\n" + "Press 3 to log in\n" + "Press 4 to quit \n");
                    break;
                    // if user enters 2, create an account
                case 2:
                    Database.createAccount();
                    break;
                case 3:
                    // if user enters 3, set account to the account returned by the database's login method
                    account = Database.logIn();
                    break;
                case 4:
                    //if user enters 4, terminate the process
                    break;
                    //if user enters any other number print error message
                default:
                    System.out.println("Invalid option\n");
            }
        }
        //return Account object account
        return account;
    }
}