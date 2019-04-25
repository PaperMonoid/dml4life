/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes.principal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tritiummonoid
 */
public class BaseDeDatos {
    
    private String nombre;
    private List<Tabla> tablas;

    public BaseDeDatos(Connection conexion, String nombre) throws SQLException {
        this.nombre = nombre;
        this.tablas = new ArrayList<>();
        
        Statement comando;
        ResultSet resultados;
        
        comando = conexion.createStatement();
        resultados = comando.executeQuery(
                String.format("USE %s", nombre));
        resultados.close();
        comando.close();
        
        comando = conexion.createStatement();
        resultados = comando.executeQuery("SHOW TABLES");
        while (resultados.next()) {
            this.tablas.add(new Tabla(resultados.getString(1)));
        }
        resultados.close();
        comando.close();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Tabla> getTablas() {
        return tablas;
    }
}
