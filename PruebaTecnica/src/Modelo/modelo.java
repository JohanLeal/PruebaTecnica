package Modelo;
    import java.sql.Connection;
    import java.sql.DriverManager;
public class modelo { 
    //Conexion a la base de datos
    Connection con;
    public void conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "");
        } catch (Exception e) {
            System.err.println("Erro: "+e);
        }
    }
    
    
}
