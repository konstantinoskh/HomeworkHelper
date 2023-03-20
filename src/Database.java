import java.io.File;
import java.util.ArrayList;

public class Database {
    public static ArrayList<Account> listOfAccounts = new ArrayList<>();
    public static File users;
    public Database(){
        users = new File("users.txt");

    }
    public void addToTextDatabase(String filename, Account account,boolean append){
        String username = account.getUsername();
        String password = account.getPassword();
        FileUtilities.writeToFile(filename,  username + "\n"  + password + "\n \n", append );
        Account newAccount = new Account(username, password);
        listOfAccounts.add(newAccount);
    }

    public boolean checkName(String username){
        for (Account account : listOfAccounts){
            if (account.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }

    public boolean checkUsername(String username, String filename){
        return FileUtilities.checkString(username, filename);
    }

    public String getName(){
        return users.getName();
    }
}

