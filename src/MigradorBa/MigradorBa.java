/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MigradorBa;

import BusinessLogic.AppLog;
import BusinessLogic.ExecuteQuerys;
import java.io.IOException;
import java.util.Calendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gebbl
 */
public class MigradorBa {
    public static boolean a = true;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Calendar anActual = Calendar.getInstance();
        Thread executeQuerys;
        AppLog log = new AppLog();

        log.escribirLog("******* Migracion histórico BA *******",false);
        
        // TODO code application logic here
        while (a) {
            System.out.println("Ingresar año desde el cual se desea migrar:");
            String year = "";
            Scanner scanner = new Scanner(System.in);
            year = scanner.nextLine();
            try {
                if (!year.equals("")) {
                    int year2 = Integer.parseInt(year);
                    if (year.length() == 4 & year2 > 2000 & year2 <= anActual.get(Calendar.YEAR)) {
                        System.out.println("Migrando datos......");
                        executeQuerys = new ExecuteQuerys(year);
                        executeQuerys.start();
                    } else if (year.length() != 4) {
                        System.out.println("Ingresar año valido");
                    } else if (year2 < 2013) {
                        System.out.println("Ingresar año mayor a 2000");
                    } else if (year2 > anActual.get(Calendar.YEAR)) {
                        System.out.println("Ingresar año menor o igual al actual");
                    }
                } else {
                    System.out.println("Ingresar datos en el campo año");
                }

            } catch (Exception e) {

            }
        }

    }

}
