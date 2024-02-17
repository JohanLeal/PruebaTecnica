import java.sql.Connection;
import java.sql.DriverManager;

public class Main{

    Connection con;

    public void conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "");
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        Main uwu = new Main();

        uwu.conexion();
    }
}
