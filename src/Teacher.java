public class Teacher {
    private String name;
    private String teacherID;
    private String dob;
    private String address;
    private String qualification;
    private String teachingSubject;
    private String username;
    private String password;

    public Teacher(String name, String teacherID, String dob, String address, String qualification, String teachingSubject, String username, String password) {
        this.name = name;
        this.teacherID = teacherID;
        this.dob = dob;
        this.address = address;
        this.qualification = qualification;
        this.teachingSubject = teachingSubject;
        this.username = username;
        this.password = password;
    }

    public Teacher(String teacherID) {
        this.teacherID = teacherID;
    }

    public String getName() {
        return name;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public String getDob() {
        return dob;
    }

    public String getAddress() {
        return address;
    }

    public String getQualification() {
        return qualification;
    }

    public String getTeachingSubject() {
        return teachingSubject;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public void setTeachingSubject(String teachingSubject) {
        this.teachingSubject = teachingSubject;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Teacher [name=" + name + ", teacherID=" + teacherID + ", dob=" + dob + ", address=" + address + ", qualification=" + qualification + ", teachingSubject=" + teachingSubject + ", username=" + username + ", password=" + password + "]";
    }
}