import java.util.Scanner;
public class SignupScreen {

Scanner sc = new Scanner(System.in);

    public Account SignUp() throws Exception {

        Account account = null;
        System.out.println("Press 1 to see options\n" + "Press 2 to create account\n" + "Press 3 to log in\n" + "Press 4 to quit \n");
        int option = 0;
        boolean valid = false;

        while(option != 4 && !Database.isLoggedIn()) {
            do {
                try {
                    System.out.print("Option: ");
                    option = sc.nextInt();
                    valid = true;
                }catch (java.util.InputMismatchException e){
                    System.out.println("Invalid option\n");
                    valid = false;
                    sc.next();
                }
            }while(!valid);

            switch(option){
                case 1: System.out.println("Press 1 to see options\n" + "Press 2 to create account\n" + "Press 3 to log in\n" + "Press 4 to quit \n");
                    break;
                case 2:
                    Database.createAccount();
                    break;
                case 3:
                    account = Database.logIn();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid option\n");
            }
        }
        return account;
    }
}