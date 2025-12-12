import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CrearYConectarBD {
  public static void main(String[] args) {
    //URL de conexión, usuario, contraseña
    String url = "jdbc:mysql://localhost:3306/inventario?allowPublicKeyRetrieval=true&useSSL=false";
    String usuario = "root";
    String clave = "root";
    
    //Nombre de la base datos y de la tabla
    String baseDatos = "inventario";
    String tablaBaseDatos = "productos";
    
    //Generar objetos de clases de Sql que me permitan la conexión
    Connection conexion = null;
    Statement state = null;
    
    try {
      conexion = DriverManager.getConnection(url, usuario, clave);
      state = conexion.createStatement();
      //Crea la base de datos
      String sqlCrearBaseDatos = "CREATE DATABASE IF NOT EXISTS "+baseDatos;
      state.executeUpdate(sqlCrearBaseDatos);
      //Abrir la base de datos
      state.execute("USE "+baseDatos);
      //Crear tabla
      String sqlCrearTabla = "CREATE TABLE IF NOT EXISTS "+tablaBaseDatos+" ("
              +"id INT PRIMARY KEY, "
              +"nombre VARCHAR(100) NOT NULL, "
              +"descripcion VARCHAR(255), "
              +"precio DOUBLE NOT NULL, "
              +"cantidad INT NOT NULL"
              +")";
      state.executeUpdate(sqlCrearTabla);
      if(state != null) {
        state.close();
      }
      if(conexion != null) {
        conexion.close();
      } 
    } catch (SQLException ex) {
      Logger.getLogger(CrearYConectarBD.class.getName()).log(Level.SEVERE, null, ex);
      System.out.println("Imposible acceder al servidor de base de datos");
    }
    
  }
}
