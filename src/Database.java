import org.json.JSONObject;

import java.io.*;
import java.nio.file.NoSuchFileException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Database {

    //storing accounts in a pre-determined, static file
    private static final File users = new File("users.txt");
    private static boolean loggedIn; //checks if a user is logged in
    private static Scanner sc = new Scanner(System.in);

    //adding an account into the database
    public static void addToTextDatabase(String filename, Account account, boolean append) {
        String username = account.getUsername();
        String password = account.getPassword();

        //writes "username: password" in the database
        FileUtilities.writeToFile(filename, username + ": " + password + "\n", append);
    }

    public static boolean checkUsername(String filename, String username) {
        String checkedUsername;

        //creates a new buffered reader for the file
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            //reads the first line
            String line = br.readLine();

            //read while there is text in the file
            while (line != null) {
                //contains the first part of a line before ":" (the username)
                checkedUsername = line.split(":")[0].strip();
                //checks if the username matches a username in the database
                if (checkedUsername.equals(username)) {
                    return true;
                } else {
                    //continue reading
                    line = br.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //if the inputted username doesn't match an existing one return false
        return false;
    }

    //prompts user to create an account
    public static void createAccount() throws Exception {
        //this ensures that the file isn't null if it doesn't exist
        FileUtilities.writeToFile(users.getName(), "", true);
        Scanner sc = new Scanner(System.in);
        String username;
        String password = "";

        //decrypts encrypted file so that the checkUsername method can be used
        FileDecryption.decryptFile();

        //keeps asking to enter a username until a valid one is inputted
        while (true) {
            System.out.print("Enter a username: ");
            //stores inputted username
            username = sc.next().strip();
            //check if the username already exists
            if (checkUsername(users.getName(), username)) {
                System.out.println("Account already exists\n");
            } else {
                //break out of loop
                break;
            }
        }

        int length = 0;
        //keeps looping until inputted password is 8 letters long
        while (length < 8) {
            System.out.print("Enter a password: ");
            //stores inputted password
            password = sc.next().strip();
            length = password.length();
            //compares if its length is under 8 letters
            if (length < 8) {
                System.out.println("Password needs to be at least 8 characters\n");
            }
        }

        String confirmedPassword;
        do {
            System.out.print("Confirm password: ");
            //stores the inputted confirmed password
            confirmedPassword = sc.next().strip();
            if (!password.equals(confirmedPassword)) {
                System.out.println("Password doesn't match\n");
            }
            //keep prompting the user to confirm until the confirmed password matches
        } while (!password.equals(confirmedPassword));

        //create an account with the inputted username and password
        Account newAccount = new Account(username, password);
        //add it to the account database
        addToTextDatabase(users.getName(), newAccount, true);
        //encrypt the file so that someone can't read its contents
        FileEncryption.encryptFile();
        System.out.println("Account successfully created!\n");
    }

    public static Account logIn() throws Exception {
        String username;
        String password;

        System.out.print("Enter a username: ");
        username = sc.next();
        try {
            //decrypt encrypted file so that the checkUsername method can be used
            FileDecryption.decryptFile();
            //check if username exists in database
            if (!checkUsername(users.getName(), username)) {
                System.out.println("Account doesn't exist, please create an account \n");
                //encrypt file before exiting method
                FileEncryption.encryptFile();
                //if it doesn't exist stop the logIn method
                return null;
            }
            //catch the exception to the database file not existing
        }catch (NoSuchFileException e){
            //print helpful message
            System.out.println("Account doesn't exist, please create an account \n");
            return null;
        }

        //keep prompting for password if it is incorrect
        while (true) {
            System.out.print("Enter a password: ");
            password = sc.next();
            //check if the password corresponds to username
            if (!checkUsernameAndPassword(users.getName(), username, password)) {
                System.out.println("Password is incorrect, check the password \n");
            } else {
                loggedIn = true; //show that someone is logged in
                System.out.println("You have successfully logged in \n");
                //encrypt the database so that its contents are not visible
                FileEncryption.encryptFile();

                ArrayList<String> subjects = new ArrayList<>();
                while(true){
                    System.out.println("Enter subject that you do: ");
                    String subject = sc.next();
                    if (subject.equalsIgnoreCase("stop")){
                        break;
                    }
                    subjects.add(subject);
                }

                    for (String subject: subjects){
                        Folder folder = new Folder(subject);
                    }

                // return an Account object with inputted username and password
                return new Account(username, password);
            }
        }
    }

    //getter method for logged in field
    public static boolean isLoggedIn() {
        return loggedIn;
    }
    //method checking that the username matches the password
    public static boolean checkUsernameAndPassword(String filename, String username, String password){
        return FileUtilities.checkStringsTogether(filename, username, password);
    }
}
