import java.io.*;
import java.util.Scanner;

public class Database {
    private static File users = new File("users.txt");
    private static boolean loggedIn;
    private static Scanner sc = new Scanner(System.in);

    public static void addToTextDatabase(String filename, Account account, boolean append) {
        String username = account.getUsername();
        String password = account.getPassword();
        FileUtilities.writeToFile(filename, username + ": " + password + "\n", append);
    }

    public static boolean checkUsername(String filename, String username) {
        String checkedUsername;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();
            while (line != null) {
                checkedUsername = line.split(":")[0].strip();
                if (checkedUsername.equals(username)) {
                    return true;
                } else {
                    line = br.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static void createAccount() throws Exception {
        FileUtilities.writeToFile(users.getName(), "", true);
        Scanner sc = new Scanner(System.in);
        String username;
        String password = "";
        FileDecryption.decryptFile();
        while (true) {
            System.out.print("Enter a username: ");
            username = sc.next().strip();
            if (checkUsername(users.getName(), username)) {
                System.out.println("Account already exists\n");
            } else {
                break;
            }
        }

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
        addToTextDatabase(users.getName(), newAccount, true);
        FileEncryption.encryptFile();
        System.out.println("Account successfully created!\n");
    }

    public static Account logIn() throws Exception {
        String username;
        String password;

        System.out.print("Enter a username: ");
        username = sc.next();
        FileDecryption.decryptFile();
        if (!checkUsername(users.getName(), username)) {
            System.out.println("Account doesn't exist, please create an account \n");
            FileEncryption.encryptFile();
            return null;
        }

        while (true) {
            System.out.print("Enter a password: ");
            password = sc.next();
            if (!checkUsernameAndPassword(users.getName(), username, password)) {
                System.out.println("Password is incorrect, check the password \n");
            } else {
                loggedIn = true;
                System.out.println("You have successfully logged in \n");
                FileEncryption.encryptFile();
                return new Account(username, password);
            }
        }
    }

    public static boolean isLoggedIn() {
        return loggedIn;
    }
    public static boolean checkUsernameAndPassword(String filename, String username, String password){
        return FileUtilities.checkStringsTogether(filename, username, password);
    }
}
