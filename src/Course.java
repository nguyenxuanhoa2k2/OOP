public class Course {
    private String courseName;
    private String courseCode;
    private String subject;
    private String teacherID;
    private String schedule;
    private int maxStudents;
    private int remainingStudents;

    public Course(String courseName, String courseCode, String subject, String teacherID, String schedule, int maxStudents, int remainingStudents) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.subject = subject;
        this.teacherID = teacherID;
        this.schedule = schedule;
        this.maxStudents = maxStudents;
        this.remainingStudents = remainingStudents;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
    }

    public int getRemainingStudents() {
        return remainingStudents;
    }

    public void setRemainingStudents(int remainingStudents) {
        this.remainingStudents = remainingStudents;
    }

    @Override
    public String toString() {
        return "Course [courseName=" + courseName + ", courseCode=" + courseCode + ", subject=" + subject + ", teacherID=" + teacherID + ", schedule=" + schedule + ", maxStudents=" + maxStudents + ", remainingStudents=" + remainingStudents + "]";
    }
}