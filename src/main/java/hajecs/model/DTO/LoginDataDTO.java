package hajecs.model.DTO;

/**
 * Created by lucjan on 21.05.15.
 */
public class LoginDataDTO {
    private String username;
    private String password;

    public LoginDataDTO() {
    }

    public LoginDataDTO(String username, String password) {
        this.username = username;
        this.password = password;
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

    @Override
    public String toString() {
        return "LoginDataDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
