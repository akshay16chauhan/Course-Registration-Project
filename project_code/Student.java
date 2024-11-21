import java.util.*;

public class Student extends Person {
    
    public int semester;
    public List<Course> registeredCourses = new ArrayList<>();
    public Map<String, String> courseGrades = new HashMap<>(); 

    public Student(String email, String password, int semester) {
        super(email, password);
        this.semester = semester;
    }

    public void displayMenu() {
        System.out.println("Student Menu:");
        System.out.println("1. View Available Courses");
        System.out.println("2. Register for a Course");
        System.out.println("3. View Schedule");
        System.out.println("4. Track Academic Progress");
        System.out.println("5. Drop a Course");
        System.out.println("6. Submit a Complaint");
        System.out.println("7. Logout");
    }

    public String getEmail() {
        return super.getEmail(); 
    }
    
    public String getPassword() {
        return super.getPassword(); 
    }
    
    public void registerCourse(Course course) {
        if (registeredCourses.size() >= 20) {
            System.out.println("Cannot register for more than 20 courses.");
            return;
        }
        registeredCourses.add(course);
        courseGrades.put(course.getCourseCode(), "Not Assigned"); 
        course.enrollStudent(this);
        System.out.println("Registered for course: " + course.getTitle());
    }

    public void viewAvailableCourses(List<Course> courses) {
        System.out.println("Courses available for Semester " + semester + ":");
        boolean found = false;
        for (Course course : courses) {
            if (course.getSemester() == semester) {
                System.out.println(course);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No courses available for this semester.");
            System.out.println("Please Try again later.\n");
        }
    }

    public void trackAcademicProgress() {
        System.out.println("Academic Progress:");
        boolean found = false;
        for (Course course : registeredCourses) {
            String grade = courseGrades.get(course.getCourseCode());
            System.out.println(course.getTitle() + ": " + "Grades : "+ (grade != null ? grade : "Grade not assigned yet"));
            found = true;
        }
        if (!found) {
            System.out.println("No courses registered yet.");
        }
    }

    public void viewSchedule() {
        System.out.println("Your Schedule:");
        boolean found = false;
        for (Course course : registeredCourses) {
            System.out.println(course);
            found = true;
        }
        if (found) {
            System.out.println("Timings will be given before 1 week of semester start");
        } else {
            System.out.println("No course registered yet.");
        }
    }

    public void submitComplaint(String complaintDesc, List<Complaint> complaints) {
        complaints.add(new Complaint(complaintDesc));
        System.out.println("Complaint submitted.");
        System.out.println("Thank You.");
    }
    
    public void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            registeredCourses.remove(course);
            courseGrades.remove(course.getCourseCode()); 
            System.out.println("Dropped course: " + course.getTitle());
        } else {
            System.out.println("You are not enrolled in this course.");
        }
    }

    public void logout() {
        System.out.println("Logging out.");
        System.out.println("You have been logged out");
    }

    public void setGrade(String courseCode, String grade) {
        courseGrades.put(courseCode, grade); 
        System.out.println("updating grade " + grade + " for course " + courseCode);
    }

    public int getSemester() {
        return semester;
    }
}
