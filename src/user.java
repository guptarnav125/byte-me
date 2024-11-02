public abstract class user {
    private String name;
    private String password;
    private int userid;
    private static int counter=1;

    public user(String name,String password) {
        this.name = name;
        this.password = password;
        this.userid = counter++;
    }

    public abstract void mainMenu();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserid() {
        return userid;
    }
}
