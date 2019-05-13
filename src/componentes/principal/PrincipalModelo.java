/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes.principal;

import componentes.conector.IConectorModelo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class PrincipalModelo implements IPrincipalModelo {

    private IConectorModelo conector;

    public PrincipalModelo(IConectorModelo conector) {
        this.conector = conector;
    }

    @Override
    public List<BaseDeDatos> getBasesDeDatos() throws SQLException,
            IllegalStateException {
        Connection conexion = conector.getConexion();
        Statement comando = conexion.createStatement();
        ResultSet resultados = comando.executeQuery("SHOW DATABASES");
        List<BaseDeDatos> basesDeDatos = new ArrayList<>();
        while (resultados.next()) {
            String nombre = resultados.getString(1);
            BaseDeDatos baseDeDatos = new BaseDeDatos(conexion, nombre);
            basesDeDatos.add(baseDeDatos);
        }
        resultados.close();
        comando.close();

        return basesDeDatos;
    }

    @Override
    public DefaultTableModel consulta(String baseDeDatos, String tabla)
            throws SQLException {
        DefaultTableModel modelo = new DefaultTableModel();
        Connection conexion = conector.getConexion();
        Statement comando;
        ResultSet resultados;
        ResultSetMetaData metadatos;

        comando = conexion.createStatement();
        comando.execute(String.format("USE %s", baseDeDatos));
        comando.close();

        comando = conexion.createStatement();
        resultados = comando.executeQuery(String.format("SELECT * FROM %s", tabla));

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
