In command prompt terminal
cd add path like in - cd C:\Users\Admin\OneDrive\Documents\vscode\practice2
then, javac *.java to compile folder
then, java Main to run the program

you can login as student which already login are-      email              password
                                                      akshay              chauhan
                                                      lakshay             chauhan
                                                      bhavya              chauhan


for professor already login are-                       akshay              chauhan
                                                       Bn                  Jain
                                                       Pravesh             Biyani
                                                       Rajiv               Shah

for admin which is fixed is-                           akshay              chauhan

#Assumption 
initially no course register to any student


then after it is easy to run enter the number of option which you wnat to do in system

OOPS concept-

For Classes & Interfaces
My core classes entities are Student, Professor, Administrator, Coursen which itself includes various classes.
Student, Professor, and Administrator classes can implement the UserRole interface

For Inheritance,
Person is a base class which encapsulating common attributes such as name, email, and password. Student, Professor, and Administrator classes inherit from Person
each of these classes are then defining additional role-specific attributes like registerCourse() for students or assignGrades() for professors

For Polymorphism,
like UserRole reference holds a Student object, calling viewMenu() on it will invoke the viewMenu() method specific to the Student class. 
The same applies to Professor and Administrator ensuring dynamic adaptability without needing to write separate logic for each role

For Encapsulation,
In my project, sensitive data such as password, email, and registeredCourses is protected by marking fields as private or protected. 
This prevents direct access from outside the class. I provide public getter and setter methods, which control how the data is accessed and modified. 
For example, you might validate a password before changing it or ensure that a course registration is valid before adding it to a student’s course list.

For Abstraction,
In my project, complex operations such as managing courses, registering students, and handling complaints are abstracted behind simple method calls 
like registerCourse(), assignGrade(), and handleComplaint(). Users like students don’t need to know how the course registration or grade assignment is implemented. 
Because it handles the complexity internally
      

MY UML link is https://lucid.app/lucidchart/e4a3766f-c32d-4226-8b6f-bec26ba83c8d/edit?page=0_0&invitationId=inv_cff9d991-5244-4099-9051-e8d2671ca531#