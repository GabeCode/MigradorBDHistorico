/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

import java.io.IOException;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author Gabriel Abrego
 * @author Rafael Belloso
 */
public class AppLog {

    String rutaArchivo = "/migrador/migrador.log";

    public void escribirLog(String mensaje, boolean error) {
        Logger logger = Logger.getLogger("com.logicbig");

        FileHandler fileHandler;

        try {
            fileHandler = new FileHandler(rutaArchivo, true);
            fileHandler.setFormatter(new SimpleFormatter() {
                private static final String format = "[%1$tF %1$tT] [%2$-7s] %3$s %n";

                @Override
                public synchronized String format(LogRecord lr) {
                    return String.format(format,
                            new Date(lr.getMillis()),
                            lr.getLevel().getLocalizedName(),
                            lr.getMessage()
                    );
                }
            });
            logger.addHandler(fileHandler);
            if (error) {
                logger.warning(mensaje);
            } else {
                logger.info(mensaje);
            }
            fileHandler.close();
        } catch (IOException | SecurityException ex) {
            Logger.getLogger(AppLog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
