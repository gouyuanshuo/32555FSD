## 🧩 1. Project Overview  

**Project Name:** CLIUniApp & GUIUniApp  

This project is a university application system that provides two subsystems:  

- **CLIUniApp:** Command-Line Interface version  
- **GUIUniApp:** JavaFX-based Graphical User Interface version  

The system allows **students** and **admins** to interact with the same data source (`students.data`).

### ✨ Features

#### 👩‍🎓 Student Subsystem
- Register (with regex validation)
- Login
- Enrol / Remove / Show subjects
- Change password
- Data persistence via file (`students.data`)

#### 👨‍💼 Admin Subsystem
- Show all students
- Group students by grade
- Partition Pass/Fail
- Remove student by ID
- Clear database

#### 🪟 GUI Subsystem
- Student login via FXML  
- Subject enrolment & management  
- Error handling via popup windows (Exception window)  
- Data source shared with CLI system  

---

## 💻 2. System Requirements  

| Component | Version / Requirement |
|------------|----------------------|
| **JDK** | 17 or later (tested on JDK 21 / 24) |
| **JavaFX** | 21.0.1 |
| **Maven** | 3.8+ |
| **OS** | Windows / macOS / Linux |

---

## ⚙️ 3. Installation & Setup  

### 🧰 Option 1: Run inside IntelliJ IDEA
1. Import this project as a **Maven project**  
2. Ensure **Java SDK = 17** or **21+**  
3. Rebuild Project  
4. Run the main class:  
   ```bash
   edu.uts.uniapp.view.GUIUniApp
   ```
   or run the CLI version:
   ```bash
   edu.uts.uniapp.Main
   ```
### 💡 Option 2: Run via Maven
```bash
   mvn clean javafx:run
   ```
This command compiles and runs the JavaFX GUI automatically.

---

## 🔧 4. Configurations

* **Dependencies** are managed through `pom.xml`.
* `students.data` will be created automatically in the project root.
* No external libraries are required beyond **Maven dependencies**.

---

## ▶️ 5. How to Run & Use the Software

---

### **CLI Version (Command Line Interface)**

1.  **Clean and Compile:** Execute the following command in your project's root directory:
    ```bash
    mvn clean compile
    ```
2.  **Run the Main Class:** Launch the application using the compiled classes:
    ```bash
    java -cp target/classes edu.uts.uniapp.Main
    ```
3.  **Usage:** Follow the on-screen menu options to Add, Show, or Exit.
    * **Data Storage:** All student/admin data is persisted in the `students.data` file.

---

### **GUI Version (Graphical User Interface)**

1.  **Run the JavaFX Application:** Use the Maven JavaFX plugin to launch the GUI:
    ```bash
    mvn javafx:run
    ```
2.  **Usage:** Use the displayed **login window** to access the enrolment system features.
3.  **Error Handling:** If **invalid input** is entered, an **Exception Window** will display the specific error message.


