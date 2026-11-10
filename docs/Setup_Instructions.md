
# Setup Instructions – LearnTrack

## 1. Prerequisites

Install the following tools before running the project:

- Java Development Kit (JDK) 21
- IntelliJ IDEA (or any Java IDE)
- Apache Maven
- Git (optional, for version control)

## 2. Verify Java Installation

Open the terminal and execute:

```bash
java -version
javac -version
```

Ensure that Java 21 is installed and configured correctly.

## 3. Clone the Repository

```bash
git clone <your-github-repository-url>
```

Navigate to the project directory:

```bash
cd learntrack
```

## 4. Open the Project

1. Open IntelliJ IDEA.
2. Select **Open**.
3. Choose the project directory.
4. Allow IntelliJ to load and import the Maven project.
5. Ensure the Project SDK is set to Java 21.

## 5. Build the Project

Open the terminal in the project directory and run:

```bash
mvn clean compile
```

## 6. Run the Application

Locate the `Main.java` file:

`src/main/java/com/airtribe/learntrack/ui/Main.java`

Right-click the file and select **Run 'Main.main()'**.

Alternatively, use the green Run icon next to the `main()` method.

## 7. Application Notes

- The application runs in the console.
- Student, course, and enrollment data are stored in memory using ArrayList.
- Data is not persisted after the application exits.
- No database configuration is required.
