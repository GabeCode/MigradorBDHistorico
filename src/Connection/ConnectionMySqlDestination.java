/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import BusinessLogic.AppLog;
import Properties.ObtProperties;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Gabriel Abrego
 * @author Rafael Belloso
 */
public class ConnectionMySqlDestination {
    private Connection conn;    
    private String driver = null;
    private String user = null;
    private String password = null;
    private String url = null;    
    private static Properties p = new Properties();


    public ConnectionMySqlDestination() throws IOException {

        ObtProperties op = new ObtProperties();
        p = op.getP();
        driver = p.getProperty("driverD");
        user = p.getProperty("userD");
        password = p.getProperty("passwordD");
        url = p.getProperty("urlD");
        
        //Se crea un objeto para poder escribir en el log
        AppLog bt = new AppLog();
        conn = null;        
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,password);
            bt.escribirLog("Base de Datos Destino conectada", false);
            
        } catch (ClassNotFoundException | SQLException e) {
            bt.escribirLog("Imposible conectarse con Base de datos Destino..." + e, true);
            System.out.println("Error: " + e);
            System.exit(0);
        }
        
    }
    
    public Connection getConnection(){
        return conn;
    }
    
    public void desconectar() throws SQLException{
        conn.close();
    }
}
