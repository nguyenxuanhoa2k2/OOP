public class Student {
    private String name;
    private String studentID;
    private String dob;
    private String address;
    private String username;
    private String password;

    public Student(String name, String studentID, String dob, String address, String username, String password) {
        this.name = name;
        this.studentID = studentID;
        this.dob = dob;
        this.address = address;
        this.username = username;
        this.password = password;
    }

    public Student(String studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
        return "Student [name=" + name + ", studentID=" + studentID + ", dob=" + dob + ", address=" + address + ", username=" + username + ", password=" + password + "]";
    }
}