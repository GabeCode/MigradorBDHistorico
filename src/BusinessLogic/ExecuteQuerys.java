/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

import Connection.ConnectionMySqlDestination;
import Connection.ConnectionMySqlOrigin;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel Abrego
 */
public class ExecuteQuerys extends Thread {

    ReadScripts readScripts = new ReadScripts();
    Calendar actualYear = Calendar.getInstance();
    AppLog log;
    //Variables para conexion a las Bases de Datos
    private ConnectionMySqlOrigin connectionOrigin = null;
    private ConnectionMySqlDestination connectionDestination = null;
    private PreparedStatement stmOrigin;
    private PreparedStatement stmDestin;
    private ResultSet rs;
    private String[] queryStrings;
    private int cantYears, yearReceived, countYears, insertados, totalDatos;
    private String yearString;
    String val0 = "";
    BigDecimal val1;

    /**
     * Recupera los Querys del script.txt
     */
    public void getQuerys(){
        queryStrings = readScripts.readScripsTxt();
    }

    public void ejecutaQuerys(String year) {
        try {
            log = new AppLog();
            getQuerys();
            connectionOrigin = new ConnectionMySqlOrigin();
            connectionDestination = new ConnectionMySqlDestination();
            yearReceived = Integer.parseInt(year);
            cantYears = getCantYears(yearReceived);
            countYears = yearReceived;
            totalDatos = 0;
            if (connectionOrigin.getConnection() == null) {
                System.out.println("Conexion nula");
            } else {
                for (int i = 0; i <= cantYears; i++) {
                    for (int j = 0; queryStrings[j] != null; j += 2) {
                        stmOrigin = connectionOrigin.getConnection().prepareStatement(queryStrings[j]);
                        log.escribirLog("Preparando SQL: " + queryStrings[j], false);
                        stmOrigin.setInt(1, countYears);
                        rs = stmOrigin.executeQuery();
                        log.escribirLog("Sentencia SQL Ejecutada: " + queryStrings[j], false);
                        log.escribirLog("Parametro: 1 = " + countYears, false);
                        insertados = 0;
                        stmDestin = connectionDestination.getConnection().prepareStatement(queryStrings[j + 1]);

                        while (rs.next()) {
                            int columnas = rs.getMetaData().getColumnCount();

                            for (int k = 1; k <= columnas; k++) {
                                if (rs.getMetaData().getColumnClassName(k).contains("String")) {
                                    stmDestin.setString(k, rs.getString(k));
                                } else if (rs.getMetaData().getColumnClassName(k).contains("BigDecimal")) {
                                    val1 = BigDecimal.valueOf(Double.parseDouble(rs.getString(k)));
                                    stmDestin.setBigDecimal(k, val1);
                                }
                            }
                            stmDestin.addBatch();
                            insertados++;
                        }
                        log.escribirLog("Inicio de INSERTs ....", false);
                        stmDestin.executeBatch();
                        log.escribirLog("Fin de INSERTs .... ", false);
                        log.escribirLog("Registros insertados: " + insertados , false);
                        totalDatos += insertados;
                    }
                    countYears++;
                }
                log.escribirLog("Migracion Realizada", false);
                log.escribirLog("Cantidad de datos migrados: " + totalDatos , false);
                log.escribirLog("******* FIN Migracion *******", false);
                System.exit(0);
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
            Logger.getLogger(ExecuteQuerys.class.getName()).log(Level.SEVERE, null, ex);
            log.escribirLog("Error: " + ex , true);
            System.exit(0);
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
            log.escribirLog("Error: " + ex , true);
            Logger.getLogger(ExecuteQuerys.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }
    }

    /**
     *
     * @param year recibe como parametro el año desde el que se quiere hacer la
     * migración
     * @return la cantidad de años que hay entre el año actual y el que se manda
     * como parámetro
     */
    public int getCantYears(int year) {
        return actualYear.get(Calendar.YEAR) - year;
    }

    @Override
    public void run() {

        ejecutaQuerys(yearString);

    }

    public ExecuteQuerys(String y) {
        this.yearString = y;
    }

}
