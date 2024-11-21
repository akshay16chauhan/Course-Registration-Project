import java.util.*;

public class Professor extends Person {
    Scanner scanner = new Scanner(System.in);
    public Professor(String email, String password) {
        super(email, password);
    }

    public void manageCourse(Course course) {
        
        boolean exit = false;

        while (!exit) {
            System.out.println("Manage Course Menu:");
            System.out.println("1. Add Timing");
            System.out.println("2. Add Office Hour");
            System.out.println("3. Update Credits");
            System.out.println("4. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter new timing for the course: ");
                    String timing = scanner.nextLine();
                    course.setTiming(timing);
                    System.out.println("Timing updated to: " + timing);
                    break;
                case 2:
                    System.out.print("Enter office hour for the course: ");
                    String officeHour = scanner.nextLine();
                    course.setOfficeHour(officeHour);
                    System.out.println("Office hour updated to: " + officeHour);
                    break;
                case 3:
                    System.out.print("Enter new credits for the course: ");
                    int credits = scanner.nextInt();
                    scanner.nextLine();
                    course.setCredits(credits);
                    System.out.println("Credits updated to: " + credits);
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public void viewCourseCatalog(List<Course> courses) {
        System.out.println("Viewing course catalog:");
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    public void viewEnrolledStudents(Course course) {
        System.out.println("Viewing students for course: " + course.getTitle());
        List<Student> students = course.getEnrolledStudents(); 
    
        if (students.isEmpty()) {
            System.out.println("No students register for this course.");
        } else {
            for (Student student : students) {
                System.out.println("Student Email: " + student.getEmail() + ", Semester: " + student.getSemester());
            }
        }
    }
    
    @Override
    public void logout() {
        System.out.println("Logging out...");
        System.out.println("You are logged out.");
    }
}
