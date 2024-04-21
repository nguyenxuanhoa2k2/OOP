public class Teacher extends User {
    private String teacherID;
    private String qualification;
    private String teachingSubject;

    public Teacher(String name, String teacherID, String dob, String address, String qualification, String teachingSubject, String username, String password) {
        super(username, password, name, dob, address);
        this.teacherID = teacherID;
        this.qualification = qualification;
        this.teachingSubject = teachingSubject;
    }

    public Teacher(String teacherID) {
        super("", "", "", "", "");
        this.teacherID = teacherID;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public String getQualification() {
        return qualification;
    }

    public String getTeachingSubject() {
        return teachingSubject;
    }

    @Override
    public String toString() {
        return "Teacher [teacherID=" + teacherID + ", qualification=" + qualification + ", teachingSubject=" + teachingSubject + ", name=" + getName() + ", dob=" + getDob() + ", address=" + getAddress() + ", username=" + getUsername() + ", password=" + getPassword() + "]";
    }
}