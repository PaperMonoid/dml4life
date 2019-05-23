/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.gestor.mysql;

import modelos.gestor.generico.IConsulta;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author papermonoid
 */
public class ConsultaMysql implements IConsulta {

    private Connection conexion;
    private String baseDeDatos;
    private String tabla;
    private List<String> campos;
    private String comando;

    public ConsultaMysql(Connection conexion, String baseDeDatos, 
            String tabla) {
        this.conexion = conexion;
        this.baseDeDatos = baseDeDatos;
        this.tabla = tabla;
        this.campos = new ArrayList<>();
    }
    
    @Override
    public ConsultaMysql agregarCampo(String campo) {
        this.campos.add(campo);
        return this;
    }

    @Override
    public String toString() {
        if (this.comando != null) {
            return this.comando;
        }
        StringBuilder builder = new StringBuilder("SELECT ");
        if (campos.isEmpty()) {
            builder.append("* ");
        } else {
            for (int i = 0; i < campos.size(); i++) {
                builder.append(campos.get(i));
                if (i + 1 < campos.size()) {
                    builder.append(", ");
                } else {
                    builder.append(" ");
                }
            }
        }
        builder.append("FROM `");
        builder.append(tabla);
        builder.append("`;");
        return builder.toString();
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
        resultados = comando.executeQuery(toString());

        metadatos = resultados.getMetaData();
        int columnas = metadatos.getColumnCount();
        for (int i = 1; i <= columnas; i++) {
            modelo.addColumn(metadatos.getColumnLabel(i));
        }

        while (resultados.next()) {
            String[] fila = new String[columnas];
            for (int i = 0, j = 1; j <= columnas; i++, j++) {
                fila[i] = resultados.getString(j);
            }
            modelo.addRow(fila);
        }

        resultados.close();
        comando.close();

        return modelo;
    }

    @Override
    public void setComando(String comando) {
        this.comando = comando;
    }
}
