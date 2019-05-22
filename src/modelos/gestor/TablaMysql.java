/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.gestor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tritiummonoid
 */
public class TablaMysql implements ITabla {

    private Connection conexion;
    private String baseDeDatos;
    private String nombre;
    private Map<String, String> campos;

    public TablaMysql(Connection conexion, String baseDeDatos, String nombre) throws SQLException {
        this.conexion = conexion;
        this.baseDeDatos = baseDeDatos;
        this.nombre = nombre;
        this.campos = new HashMap<>();
        
        Statement comando;
        ResultSet resultados;
        ResultSetMetaData metadatos;

        comando = conexion.createStatement();
        comando.execute(String.format("USE %s", baseDeDatos));
        comando.close();

        comando = conexion.createStatement();
        resultados = comando.executeQuery(String.format("SELECT * FROM `%s`", nombre));

        metadatos = resultados.getMetaData();
        int columnas = metadatos.getColumnCount();
        for (int i = 1; i <= columnas; i++) {
            this.campos.put(metadatos.getColumnLabel(i), metadatos.getColumnTypeName(i));
        }

        resultados.close();
        comando.close();

    }

    @Override
    public String getNombre() {
        return this.nombre;
    }

    @Override
    public IInsersion insersion() {
        return new InsersionMysql(conexion, baseDeDatos, nombre);
    }

    @Override
    public IConsulta consulta() {
        return new ConsultaMysql(conexion, baseDeDatos, nombre);
    }

    @Override
    public Map<String, String> getCampos() {
        return this.campos;
    }
}
