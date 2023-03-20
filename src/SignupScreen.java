import java.util.Scanner;

public class SignupScreen {
private String username;
private String password;
public static Database userDatabase = new Database();

Scanner sc = new Scanner(System.in);
    public void SignUp(){
        System.out.println("Press 1 to see options\n" + "Press 2 to create account\n" + "Press 3 to quit\n");
        int option = 0;
        boolean valid = false;

        while(option != 3) {
            do {
                try {
                    System.out.print("Option: ");
                    option = sc.nextInt();
                    valid = true;
                }catch (java.util.InputMismatchException e){
                    System.out.println("Invalid option\n");
                    sc.nextLine();
                }
            }while(!valid);

            switch(option){
                case 1: System.out.println("Press 1 to see options\n" + "Press 2 to create account\n" + "Press 3 to quit\n");
                    break;
                case 2:
                    createAccount();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid option\n");
            }
        }
    }
    public void createAccount() {
    FileUtilities.writeToFile(userDatabase.getName(), "", true);
    do {
        System.out.print("Enter a username: ");
        username = sc.next().strip();
        if (userDatabase.checkName(username)){
            System.out.println("Account already exists\n");
        }
    }while(userDatabase.checkName(username));

        int length = 0;
        while (length < 8) {
            System.out.print("Enter a password: ");
            password = sc.next().strip();
            length = password.length();
            if (length < 8) {
                System.out.println("Password needs to be at least 8 characters\n");
            }
        }

        String confirmedPassword;
        do {
            System.out.print("Confirm password: ");
            confirmedPassword = sc.next().strip();
            if (!password.equals(confirmedPassword)) {
                System.out.println("Password doesn't match\n");
            }
        } while (!password.equals(confirmedPassword));

        Account newAccount = new Account(username, password);
        userDatabase.addToTextDatabase(userDatabase.getName(), newAccount, true);
        System.out.println("Account successfully created!\n");
    }
}