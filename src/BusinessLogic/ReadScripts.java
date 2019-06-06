/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel Abrego
 */
public class ReadScripts {
    AppLog log;
    BufferedReader bufferedReader;
    FileReader fileReader;
    String query;
    String[] queryStrings;
    double procent;
    
    /**
    * @return retorna un array de String con todos los scripts del .txt
    */
    public String[] readScripsTxt(){
        int i = 0;
        try {
            log = new AppLog();
            queryStrings = new String[200];
            fileReader = new FileReader("/migrador/script.txt");
            bufferedReader = new BufferedReader(fileReader);
            while ((query = bufferedReader.readLine()) != null) {
                if (query != null) {
                    log.escribirLog("Leyendo archivo script.txt: QUERY:"+query, false);
                    queryStrings[i]= query;
                    i++;
                }
            }
            procent = 1/i;
        } catch (FileNotFoundException ex) {
            log.escribirLog("No se encontro el archivo script.txt en la ruta '/migrador/script.txt'", true);
            Logger.getLogger(ReadScripts.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            log.escribirLog("Error: "+ex, true);
            System.out.println("Error: " + ex);
            Logger.getLogger(ReadScripts.class.getName()).log(Level.SEVERE, null, ex);
        }
        return queryStrings;
    }
    
    /**
     * Metodo progressStep()
     * @return procentaje de avance para saber cuanto avanzara el ProgressBar
     */
    public double progressStep(){
        return procent;
    }
}
