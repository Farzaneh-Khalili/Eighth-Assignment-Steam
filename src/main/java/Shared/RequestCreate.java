package Shared;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class RequestCreate extends Request implements Serializable{

    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String  dateOfBirth;

    public RequestCreate(String username, String password, String dateOfBirth) {
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }

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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return username + " " + password + " " + dateOfBirth;
    }
}
