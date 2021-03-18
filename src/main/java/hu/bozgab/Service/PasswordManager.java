package hu.bozgab.Service;

public class PasswordManager {

    private String password;

    private String newPassword;

    private String newPasswordAgain;

    public PasswordManager() {}

    public String getPassword() { return this.password; }

    public void setPassword(String password) { this.password = password; }

    public String getNewPassword() { return this.newPassword; }

    public void setNewPassword(String newPassword) { this.newPassword = newPassword; }

    public String getNewPasswordAgain() { return this.newPasswordAgain; }

    public void setNewPasswordAgain(String newPasswordAgain) { this.newPasswordAgain = newPasswordAgain; }
}
