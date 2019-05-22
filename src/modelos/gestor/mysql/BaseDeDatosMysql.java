/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.gestor.mysql;

import modelos.gestor.mysql.TablaMysql;
import modelos.gestor.generico.ITabla;
import modelos.gestor.generico.IBaseDeDatos;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author tritiummonoid
 */
public class BaseDeDatosMysql implements IBaseDeDatos {

    private Connection conexion;
    private String nombre;
    private Map<String, ITabla> tablas;
    
    public BaseDeDatosMysql(Connection conexion, String nombre) 
            throws SQLException {
        this.conexion = conexion;
        this.nombre = nombre;
        this.tablas = new HashMap<>();

        Statement comando;
        ResultSet resultados;

        comando = conexion.createStatement();
        resultados = comando.executeQuery(String.format("USE %s", nombre));
        resultados.close();
        comando.close();

        comando = conexion.createStatement();
        resultados = comando.executeQuery("SHOW TABLES");
        tablas = new HashMap<>();
        while (resultados.next()) {
            String tabla = resultados.getString(1);
            this.tablas.put(tabla, new TablaMysql(conexion, nombre, tabla));
        }
        resultados.close();
        comando.close();
    }

    @Override
    public String getNombre() {
        return this.nombre;
    }

    @Override
    public Map<String, ITabla> getTablas() {
        return this.tablas;
    }
}
