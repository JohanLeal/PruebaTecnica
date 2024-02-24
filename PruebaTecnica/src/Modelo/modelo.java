package Modelo;

    import Vista.Cuenta;
    import java.sql.*;

    import static java.sql.DriverManager.getConnection;

public class modelo {
    //Conexion a la base de datos
    private static String url="jdbc:mysql://localhost:3306/mydb";
    
    private static String user="root";
    
    private static String password="";
    
    private static Connection con;
    
    
    public static Connection getInstance() throws SQLException {
        if(con==null){
            con= getConnection(url,user,password);
        }
        return con;
    }

    public void pasar_a_cuentaActionPerformed(){
        Cuenta V = new Cuenta();

        V.setVisible(true);
        V.setLocationRelativeTo(null);
    }
}
