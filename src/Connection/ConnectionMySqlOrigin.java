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
 * 
 */
public class ConnectionMySqlOrigin {
    private Connection conn;    
    private String driver = null;
    private String user = null;
    private String password = null;
    private String url = null;    
    private static Properties p = new Properties();        

    public ConnectionMySqlOrigin() throws IOException {

        ObtProperties op = new ObtProperties();
        p = op.getP();
        driver = p.getProperty("driver");
        user = p.getProperty("user");
        password = p.getProperty("password");
        url = p.getProperty("url");
        
        //Se crea un objeto para poder escribir en el log
        AppLog bt = new AppLog();
        conn = null;        
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,password);
            bt.escribirLog("Base de Datos de Origen conectada", false);

            
        } catch (ClassNotFoundException | SQLException e) {
            bt.escribirLog("Imposible conectarse con Base de datos de Origen..." + e , true);
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
