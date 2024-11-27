import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/hospital"; // Database URL
        String username = "root"; // Database username
        String password = "mysql@6578"; // Database password

        // SQL statements to create tables
        String createPatientsTable = "CREATE TABLE IF NOT EXISTS patients (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(255) NOT NULL, " +
                "age INT NOT NULL, " +
                "gender VARCHAR(10) NOT NULL);";

        String createDoctorsTable = "CREATE TABLE IF NOT EXISTS doctors (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(255) NOT NULL, " +
                "specialization VARCHAR(255) NOT NULL);";

        String createAppointmentsTable = "CREATE TABLE IF NOT EXISTS appointments (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "patient_id INT NOT NULL, " +
                "doctor_id INT NOT NULL, " +
                "appointment_date DATE NOT NULL, " +
                "FOREIGN KEY (patient_id) REFERENCES patients(id), " +
                "FOREIGN KEY (doctor_id) REFERENCES doctors(id));";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {

            // Execute the SQL statements
            statement.execute(createPatientsTable);
            System.out.println("Patients table created successfully!");

            statement.execute(createDoctorsTable);
            System.out.println("Doctors table created successfully!");

            statement.execute(createAppointmentsTable);
            System.out.println("Appointments table created successfully!");

        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
    }
}