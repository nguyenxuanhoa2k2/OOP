public class Student extends User {
    private String studentID;

    public Student(String name, String studentID, String dob, String address, String username, String password) {
        super(username, password, name, dob, address);
        this.studentID = studentID;
    }

    public Student(String studentID) {
        super("", "", "", "", "");
        this.studentID = studentID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    @Override
    public String toString() {
        return "Student [studentID=" + studentID + ", name=" + getName() + ", dob=" + getDob() + ", address=" + getAddress() + ", username=" + getUsername() + ", password=" + getPassword() + "]";
    }
}