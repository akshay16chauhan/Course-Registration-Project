import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Administrator extends Person {
    private Map<String, Student> students = new HashMap<>(); 

    public Administrator(String email, String password) {
        super(email, password);
        students.put("akshay", new Student("akshay", "chauhan", 1));
    }

    public void setStudents(List<Student> students) {
        for (Student student : students) {
            this.students.put(student.getEmail(), student);
        }
    }
    
    public void manageCourseCatalog(Course course, boolean add, List<Course> courses) {
        if (add) {
            courses.add(course);
            System.out.println("Added course: " + course.getTitle());
        } else {
            courses.remove(course);
            System.out.println("Removed course: " + course.getTitle());
        }
    }

    public void manageStudentRecords(Scanner scanner) {
        while (true) {
            System.out.println("\nManage Student Records:");
            System.out.println("1) View Student Records ");
            System.out.println("2) Update Student Records ");
            System.out.println("3) Back to Main Menu ");
            int option = scanner.nextInt();
            scanner.nextLine();
            if (option == 1) {
                viewStudentRecords(scanner);
            } else if (option == 2) {
                updateStudentRecords(scanner);
            } else if (option == 3) {
                break;
            } else {
                System.out.println("Invalid option.");
            }
        }
    }
    
    
    public void viewStudentRecords(Scanner scanner) {
        System.out.println("Enter student email to view records:");
        String email = scanner.nextLine();
        
        Student student = students.get(email); 
        if (student == null) {
            System.out.println("No student found with email: " + email);
            return;
        }
        
        System.out.println("\nStudent Records for " + email + ":");
        System.out.println("Current Semester: " + student.getSemester());
        List<Course> registeredCourses = student.registeredCourses;

        if (registeredCourses.isEmpty()) {
            System.out.println("No courses registered.");
        } else {
            System.out.println("Registered Courses:");
            for (Course course : registeredCourses) {
                System.out.println(course);
            }
        }
    }
    
    public void updateStudentRecords(Scanner scanner) {
        System.out.println("Enter student email to update records:");
        String email = scanner.nextLine();
        
        Student student = students.get(email); 
        if (student == null) {
            System.out.println("No student found with email: " + email);
            return;
        }
    
        while (true) {
            System.out.println("\nUpdate Student Records for " + email + ":");
            System.out.println("1) Unregister from a Course");
            System.out.println("2) Assign Grade to a Course");
            System.out.println("3) Back to Main Menu");
            
            int option = scanner.nextInt();
            scanner.nextLine();
            
            if (option == 3) {
                break; 
            }
    
            switch (option) {
                case 1:
                    removeCourseFromStudent(scanner, student);
                    break;
                case 2:
                    assignGradeToStudent(scanner, student);
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
    
    public void removeCourseFromStudent(Scanner scanner, Student student) {
        List<Course> registeredCourses = student.registeredCourses;
    
        if (registeredCourses.isEmpty()) {
            System.out.println("No courses registered to remove.");
            return;
        }
    
        System.out.println("Enter the course code to remove:");
        String courseCode = scanner.nextLine();
    
        Course courseToRemove = null;
        for (Course course : registeredCourses) {
            if (course.getCode().equals(courseCode)) {
                courseToRemove = course;
                break;
            }
        }
    
        if (courseToRemove != null) {
            student.dropCourse(courseToRemove);
            System.out.println("Course " + courseCode + " removed successfully.");
        } else {
            System.out.println("No course found with code: " + courseCode);
        }
    }
    
    public void assignGradeToStudent(Scanner scanner, Student student) {
        List<Course> registeredCourses = student.registeredCourses;
    
        if (registeredCourses.isEmpty()) {
            System.out.println("No courses registered yet.");
            return;
        }
    
        System.out.println("Enter the course code to assign a grade:");
        String courseCode = scanner.nextLine();
    
        Course courseToAssign = null;
        for (Course course : registeredCourses) {
            if (course.getCode().equals(courseCode)) {
                courseToAssign = course;
                break;
            }
        }
    
        if (courseToAssign != null) {
            System.out.println("Enter grade (A, B, C, D, F):");
            String grade = scanner.nextLine();
            student.setGrade(courseCode, grade);
            System.out.println("Grade assigned for course " + courseCode);
            System.out.println("Thanks!!");
        } else {
            System.out.println("No course available with code: " + courseCode);
        }
    }

    public void logout() {
        System.out.println("Logging out...");
        System.out.println("You are logged out.");
    }
    
    public void viewCourseCatalog(List<Course> courses) {
        System.out.println("Viewing course catalog:");
        for (Course course : courses) {
            System.out.println(course);
        }
    }
    
    public void assignProfessor(Course course, Professor professor) {
        course.setProfessor(professor.getEmail());
        System.out.println("Assigned " + professor.getEmail() + " to " + course.getTitle());
    }

    public void handleComplaints(List<Complaint> complaints, Scanner scanner) {
        System.out.println("Handling complaints...");
        boolean found = false;
        for (Complaint complaint : complaints) {
            System.out.println("Complaint: " + complaint.getDescription());
            System.out.println("Status: " + complaint.getStatus());
    
            System.out.println("Change Status to: ");
            System.out.println("1. Pending");
            System.out.println("2. Resolved");
    
            int choice = 0;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.\n");
                scanner.nextLine();
                continue;
            }
    
            switch (choice) {
                case 1:
                    complaint.setStatus(Complaint.ComplaintStatus.PENDING);
                    break;
                case 2:
                    complaint.setStatus(Complaint.ComplaintStatus.RESOLVED);
                    break;
                default:
                    System.out.println("Invalid choice. Status is not updated.\n");
                    break;
            }
            found = true;
        }
        if (!found) {
            System.out.println("No complaints yet.\n");
        }
    }
}

