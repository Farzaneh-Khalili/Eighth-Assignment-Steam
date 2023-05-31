package Shared;
import java.io.Serializable;

public class RequestLogin extends Request implements Serializable{
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;

    public RequestLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }

//    public RequestLogin(String username, String password) {
//        super(username, password);
//        this.username = username;
//        this.password = password;
//    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return username + " " + password;
    }
}
