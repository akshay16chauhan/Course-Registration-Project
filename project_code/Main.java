import java.util.*;

public class Main {
    static List<Professor> professors = new ArrayList<>();
    static List<Complaint> complaints = new ArrayList<>();
    static List<Course> courses = new ArrayList<>();
    static List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        Data();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        

        while (!exit) {
            System.out.println("Indraprastha Institute Of Information Technology Delhi\n");
            System.out.println("Welcome to Registration System");
            System.out.println("Login as:");
            System.out.println("1. Student");
            System.out.println("2. Professor");
            System.out.println("3. Administrator");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = 0;
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Enter a number: ");
                scanner.next();  
            }
            choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    Student student = verify(scanner, Student.class);
                    if (student != null) {
                        Student_handle(student, scanner);
                    } else {
                        System.out.println("Invalid login. Try again.\n");
                    }
                    break;
                case 2:
                    Professor professor = verify(scanner, Professor.class);
                    if (professor != null) {
                        Professor_handle(professor, scanner);
                    } else {
                        System.out.println("Invalid login. Try again.\n");
                    }
                    break;
                case 3:
                    Administrator admin = verify_Admin(scanner);
                    if (admin != null) {
                        admin.setStudents(students); 
                        Admin_handle(admin, scanner);
                    } else {
                        System.out.println("Invalid login.\n");
                    }
                    break;
                    case 4:
                        System.out.println("Exiting the system. Goodbye!");
                        exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.\n");
                    break;
            }
        }
        scanner.close();
    }
    
    private static <T extends Person> T signUp(Scanner scanner, Class<T> type) {
        System.out.print("Enter email: ");
        String email = scanner.nextLine(); 
        System.out.print("Enter password: ");
        String password = scanner.nextLine(); 
    
        if (type.equals(Student.class)) {
            System.out.print("Enter semester: ");
            int semester = 0;
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Enter a number: ");
                scanner.next();  
            }
            semester = scanner.nextInt();  
            scanner.nextLine();  
            Student student = new Student(email, password, semester);
            students.add(student);
            System.out.println("Account created successfully.");
            return type.cast(student);
        }
        else if (type.equals(Professor.class)) {
            Professor professor = new Professor(email, password);
            professors.add(professor);
            System.out.println("Account created successfully.");
            return type.cast(professor);
        }
        return null;
    }
    
    private static <T extends Person> T login(Scanner scanner, Class<T> type) {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
    
        if (type.equals(Student.class)) {
            for (Student student : students) {
                if (student.getEmail().equals(email) && student.getPassword().equals(password)) {
                    return type.cast(student);
                }
            }
        } else if (type.equals(Professor.class)) {
            for (Professor professor : professors) {
                if (professor.getEmail().equals(email) && professor.getPassword().equals(password)) {
                    return type.cast(professor);
                }
            }
        }
        System.out.println("Invalid email or password.");
        return null;
    }
    
    private static <T extends Person> T verify(Scanner scanner, Class<T> type) {
        System.out.println("1. For Sign Up (If new)");
        System.out.println("2. For Login");
    
        int choice = 0;
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Enter a number: ");
            scanner.next(); 
        }
        choice = scanner.nextInt();
        scanner.nextLine();  
    
        switch (choice) {
            case 1:
                return signUp(scanner, type);
            case 2:
                return login(scanner, type);
            default:
                System.out.println("Invalid choice.\n");
                return null;
        }
    }
    

    private static Administrator verify_Admin(Scanner scanner) {
        System.out.print("Enter administrator email: ");
        String email = scanner.nextLine();
        System.out.print("Enter administrator password: ");
        String password = scanner.nextLine();
    
        if ("akshay".equals(email) && "chauhan".equals(password)) {
            return new Administrator(email, password);
        }
        return null;
    }
    

    private static void Student_handle(Student student, Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            student.displayMenu();
            System.out.print("Choose an option: ");
            int choice = 0;
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Enter a number: ");
                scanner.next();  
            }
            choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    student.viewAvailableCourses(courses);
                    break;
                case 2:
                    System.out.print("For register enter course code : ");
                    String courseCode = scanner.nextLine();
                    Course course = CourseByCode(courseCode);
                    if (course != null) {
                        student.registerCourse(course);
                    } 
                    else {
                        System.out.println("Course not available.");
                        System.out.println("Try for another course.");
                    }
                    break;
                case 3:
                    student.viewSchedule();
                    break;
                case 4:
                    student.trackAcademicProgress();
                    break;
                case 5:
                    System.out.print("For drop enter course code ; ");
                    courseCode = scanner.nextLine();
                    course = CourseByCode(courseCode);
                    if (course != null) {
                        student.dropCourse(course);
                    } else {
                        System.out.println("Course not available.\n");
                    }
                    break;
                case 6:
                    System.out.print("Write Complaint here : ");
                    String complaintDesc = scanner.nextLine();
                    student.submitComplaint(complaintDesc, complaints);
                    break;
                case 7:
                    exit = true;
                    student.logout();
                    break;
                default:
                    System.out.println("Invalid choice\n");
            }
        }
    }

    private static void Professor_handle(Professor professor, Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.println("Professor Menu:");
            System.out.println("1. Manage or View Courses");
            System.out.println("2. View Enrolled Students");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");

            int choice = 0;
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Enter a number: ");
                scanner.next();  
            }
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.println("1. View Course Catalog");
                    System.out.println("2. Manage Course");
                    int subChoice = 0;
                    while (!scanner.hasNextInt()) {
                        System.out.print("Invalid input. Enter a number: ");
                        scanner.next();  
                    }
                    subChoice = scanner.nextInt();
                    scanner.nextLine();  
                    switch (subChoice) {
                        case 1:
                            professor.viewCourseCatalog(courses);
                            break;
                        case 2:
                            System.out.print("For managing course enter course code : ");
                            String courseCode = scanner.nextLine();
                            Course course = CourseByCode(courseCode);
                            if (course != null) {
                                professor.manageCourse(course);
                            } else {
                                System.out.println("Course not available.\n");
                            }
                            break;
                        default:
                            System.out.println("Invalid choice. Try Later.\n");
                    }
                    break;
                case 2:
                    System.out.print("For seeing enrolled students enter course code : ");
                    String courseCode = scanner.nextLine();
                    Course course = CourseByCode(courseCode);
                    if (course != null) {
                        professor.viewEnrolledStudents(course);
                    } else {
                        System.out.println("Course not available.\n");
                    }
                    break;
                case 3:
                    exit = true;
                    professor.logout();
                    break;
                default:
                    System.out.println("Invalid choice.\n");
            }
        }
    }

    private static void Admin_handle(Administrator admin, Scanner scanner) {
        boolean adminexit = false;
        while (!adminexit) {
            System.out.println("Administrator Menu:");
            System.out.println("1. Manage Course Catalog");
            System.out.println("2. Manage Student Records");
            System.out.println("3. Assign Professors to Courses");
            System.out.println("4. Handle Complaints");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");

            int choice = 0;
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Enter a number: ");
                scanner.next();  
            }
            choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    System.out.println("1. view Course");
                    System.out.println("2. Add Course");
                    System.out.println("3. Remove Course");

                    int subChoice = 0;
                    while (!scanner.hasNextInt()) {
                        System.out.print("Invalid input. Enter a number: ");
                        scanner.next();  
                    }
                    subChoice = scanner.nextInt();
                    scanner.nextLine(); 

                    if(subChoice == 1){
                        admin.viewCourseCatalog(courses);
                    }
                    else if (subChoice == 2) {
                        System.out.print("Enter course code: ");
                        String code = scanner.nextLine();
                        System.out.print("Enter course title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter professor email: ");
                        String professor = scanner.nextLine();
                        System.out.print("Enter course credits: ");
                        int credits = scanner.nextInt();
                        scanner.nextLine();  
                        System.out.print("Enter course semester: ");
                        int semester = scanner.nextInt();
                        scanner.nextLine();  

                        Course course = new Course(code, title, professor, credits, semester);
                        admin.manageCourseCatalog(course, true, courses);
                    } 
                    else if (subChoice == 3) {
                        System.out.print("To remove enter course code : ");
                        String courseCode = scanner.nextLine();
                        Course course = CourseByCode(courseCode);
                        if (course != null) {
                            admin.manageCourseCatalog(course, false, courses);
                        } else {
                            System.out.println("Course not available.\n");
                        }
                    } 
                     else {
                        System.out.println("Try later! Invalid choice.\n");
                    }
                    break;
                case 2:
                    admin.manageStudentRecords(scanner);
                    break;
                case 3:
                    System.out.println("Available Course are : CS101, ECE101, HCI, COM, MTH101, CS201, ECE201, MB, IIS, MTH201");
                    System.out.print("Enter course code to assign professor to: ");
                    String courseCode = scanner.nextLine();
                    Course course = CourseByCode(courseCode);
                    if (course != null) {
                        System.out.println("Available Professor are : Bn, Pravesh, Rajiv, Payal, Subhajeet");
                        System.out.print("Enter professor : ");
                        String email = scanner.nextLine();
                        Professor professor = Professor_Name(email);
                        if (professor != null) {
                            admin.assignProfessor(course, professor);
                        } else {
                            System.out.println("Professor not found.");
                        }
                    } else {
                        System.out.println("Course not available.\n");
                    }
                    break;
                case 4:
                    admin.handleComplaints(complaints, scanner);
                    break;
                case 5:
                    admin.logout();
                    adminexit = true;
                    break;
                default:
                    System.out.println("Try Later! Invalid choice.\n");
            }
        }
    }

    private static Professor Professor_Name(String email) {
        for (Professor professor : professors) {
            if (professor.getEmail().equals(email)) {
                return professor;
            }
        }
        return null;
    }

    private static Course CourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }

    private static void Data() {
        
        courses.add(new Course("CS101", "Intro to CS", "Bn Jain", 4, 1));
        courses.add(new Course("ECE101", "Digital Circuits", "Pravesh Biyani", 4, 1));
        courses.add(new Course("HCI", "Human & Computer", "Rajiv Shah", 4, 1));
        courses.add(new Course("COM", "Communication", "Payal Mukherjii", 4, 1));
        courses.add(new Course("MTH101", "Calculus I", "Subhajeet Choudhary", 4, 1)); 
        courses.add(new Course("CS201", "Data Structure & Algorithm", "Bn Jain", 4, 2));
        courses.add(new Course("ECE201", "Computer Organization", "Pravesh Biyani", 4, 2));
        courses.add(new Course("MB", "Money & banking", "Rajiv Shah", 4, 2));
        courses.add(new Course("IIS", "Intelligent System", "Payal Mukherjii", 4, 2));
        courses.add(new Course("MTH201", "Probability & Statistics", "Subhajeet Choudhary", 4, 2));

        professors.add(new Professor("akshay", "chauhan"));
        professors.add(new Professor("Bn", "Jain"));
        professors.add(new Professor("Pravesh", "Biyani"));
        professors.add(new Professor("Rajiv", "Shah"));
        professors.add(new Professor("Payal", "Mukherjii"));
        professors.add(new Professor("Subhajeet", "Choudhary"));

        students.add(new Student("akshay", "chauhan", 1));
        students.add(new Student("lakshay", "chauhan", 1));
        students.add(new Student("bhavya", "chauhan", 2));
    }
}



