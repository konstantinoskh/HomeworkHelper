import java.util.ArrayList;

public class Account {
    private String username;
    private String password;
    private ArrayList<String> subjects;

    public Account(String username, String password, ArrayList<String> subjects) {
        this.username = username;
        this.password = password;
        this.subjects = subjects;
    }
    public Account(String username, String password){
        this.username = username;
        this.password = password;
        this.subjects = null;
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}