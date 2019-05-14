/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.gestor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author tritiummonoid
 */
public class TablaMysql implements ITabla {

    private Connection conexion;
    private String baseDeDatos;
    private String nombre;

    public TablaMysql(Connection conexion, String baseDeDatos, String nombre) {
        this.conexion = conexion;
        this.baseDeDatos = baseDeDatos;
        this.nombre = nombre;
    }

    @Override
    public String getNombre() {
        return this.nombre;
    }

    @Override
    public TableModel consultar() throws Exception {
        DefaultTableModel modelo = new DefaultTableModel();
        Statement comando;
        ResultSet resultados;
        ResultSetMetaData metadatos;

        comando = conexion.createStatement();
        comando.execute(String.format("USE %s", baseDeDatos));
        comando.close();

        comando = conexion.createStatement();
        resultados = comando.executeQuery(
            String.format("SELECT * FROM %s", nombre)
        );

        metadatos = resultados.getMetaData();
        int columnas = metadatos.getColumnCount();
        for (int i = 1; i < columnas; i++) {
            modelo.addColumn(metadatos.getColumnLabel(i));
        }

        while (resultados.next()) {
            String[] fila = new String[columnas];
            for (int i = 0, j = 1; j < columnas; i++, j++) {
                fila[i] = resultados.getString(j);
            }
            modelo.addRow(fila);
        }

        resultados.close();
        comando.close();

        return modelo;
    }
}
