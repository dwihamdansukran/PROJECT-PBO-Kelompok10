public class Akun {
    protected String id;
    protected String password;

    public Akun() {
        this.id = "id";
        this.password = "password";
    }

    public Akun(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String newId) {
        this.id = newId;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
}
