public class Admin extends User {
    private String adminID;

    public Admin(String name, String adminID, String dob, String address, String username, String password) {
        super(username, password, name, dob, address);
        this.adminID = adminID;
    }

    public Admin(String adminID) {
        super("", "", "", "", "");
        this.adminID = adminID;
    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    @Override
    public String toString() {
        return "Admin [adminID=" + adminID + ", name=" + getName() + ", dob=" + getDob() + ", address=" + getAddress() + ", username=" + getUsername() + ", password=" + getPassword() + "]";
    }
}