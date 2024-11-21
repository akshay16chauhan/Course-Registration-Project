import java.util.ArrayList;
import java.util.List;

class Course {
    private String professor;
    private String code;
    private String title;
    private List<Student> enrolledStudents;
    private int credits;
    private int semester;
    private String officeHour;
    private String timing;

    public Course(String code, String title, String professor, int credits, int semester) {
        this.professor = professor;
        this.semester = semester;
        this.code = code;
        this.title = title;
        this.credits = credits;
        this.timing = "Not set";
        this.officeHour = "Not set";
        this.enrolledStudents = new ArrayList<>();
    }

    public void setOfficeHour(String officeHour) {
        this.officeHour = officeHour;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code + ": " + title + " (" + professor + ", " + credits + " credits, Timing: " + timing + ", Office Hour: " + officeHour + ")";
    }

    public String getTitle() {
        return title;
    }

    public String getCourseCode() {
        return code;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }
    

    public int getCredits() {
        return credits;
    }

    public String getOfficeHour() {
        return officeHour;
    }

    public String getTiming() {
        return timing;
    }

    public int getSemester() {
        return semester;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void enrollStudent(Student student) {
        if (!enrolledStudents.contains(student)) {
            enrolledStudents.add(student);
        }
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

}

