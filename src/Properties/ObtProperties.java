/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Properties;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Rafael Belloso
 * @author Gabriel Abrego
 */
public class ObtProperties {
    private static Properties p = new Properties();        
    
    public ObtProperties() throws IOException {
        p.load(new FileReader("/migrador/config.properties"));        
    }
    
    public static Properties getP() {
        return p;
    }

    public static void setP(Properties p) {
        ObtProperties.p = p;
    }
}
