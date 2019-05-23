/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.gestor.mysql;

import modelos.gestor.generico.ITabla;
import modelos.gestor.generico.IBaseDeDatos;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tritiummonoid
 */
public class BaseDeDatosMysql implements IBaseDeDatos {

    private Connection conexion;
    private String nombre;
    
    public BaseDeDatosMysql(Connection conexion, String nombre) {
        this.conexion = conexion;
        this.nombre = nombre;
    }

    @Override
    public String getNombre() {
        return this.nombre;
    }

    @Override
    public List<ITabla> getTablas() throws Exception {
        Statement comando;
        ResultSet resultados;
        List<ITabla> tablas;

        comando = conexion.createStatement();
        resultados = comando.executeQuery(String.format("USE `%s`;", nombre));
        resultados.close();
        comando.close();

        comando = conexion.createStatement();
        resultados = comando.executeQuery("SHOW TABLES;");
        tablas = new ArrayList<>();
        while (resultados.next()) {
            String tabla = resultados.getString(1);
            tablas.add(new TablaMysql(conexion, nombre, tabla));
        }
        resultados.close();
        comando.close();
    
        return tablas;
    }

    @Override
    public ITabla getTabla(String nombre) throws Exception {
        return new TablaMysql(conexion, this.nombre, nombre);
    }
}
