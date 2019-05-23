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
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.util.SelectUtils;

/**
 *
 * @author papermonoid
 */
public class ConsultaMysql implements IConsulta {

    private Connection conexion;
    private String baseDeDatos;
    private String tabla;
    private Select select;

    public ConsultaMysql(Connection conexion, String baseDeDatos, 
            String tabla) throws Exception {
        this.conexion = conexion;
        this.baseDeDatos = baseDeDatos;
        this.tabla = tabla;
    }
    
    @Override
    public ConsultaMysql agregarCampo(String campo) throws Exception {
        if (select == null) {
            select = SelectUtils.buildSelectFromTableAndExpressions(
                    new Table(tabla), new Column(campo));
        } else {
            SelectUtils.addExpression(select, new Column(campo));
        }
        return this;
    }

    @Override
    public String toString() {
        Select select = this.select;
        if (select == null) {
            select = SelectUtils.buildSelectFromTable(new Table(tabla));
        }
        return select.toString();
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
    public void setComando(String comando) throws Exception {
        select = (Select) CCJSqlParserUtil.parse(comando);
    }
}
