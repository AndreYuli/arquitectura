package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Configuración de la conexión a SQL Server
    private static final String SERVER_NAME = "localhost"; // Cambiar si el servidor está en otra ubicación
    private static final String PORT = "1433"; // Puerto estándar de SQL Server
    private static final String DATABASE_NAME = "VideojuegosDB";
    private static final String URL = "jdbc:sqlserver://" + SERVER_NAME + ":" + PORT + ";databaseName=" + DATABASE_NAME + ";encrypt=true;trustServerCertificate=true;";
    
    // Credenciales - Cambiar por tus credenciales reales
    private static final String USER = "sa"; // Cambiar por tu usuario
    private static final String PASSWORD = "YourStrongPassword"; // Cambiar por tu contraseña

    private static Connection connection;

    // Método para obtener la conexión
    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Registrar el driver JDBC de SQL Server (asegúrate de tener la dependencia en tu proyecto)
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                
                // Establecer la conexión
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexión exitosa a SQL Server.");
            } catch (ClassNotFoundException e) {
                System.err.println("Error: No se encontró el driver JDBC de SQL Server.");
                e.printStackTrace();
            } catch (SQLException e) {
                System.err.println("Error al conectar a SQL Server: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return connection;
    }

    // Método para cerrar la conexión
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null; // Establecer a null para permitir reconexión
                System.out.println("Conexión cerrada correctamente.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}