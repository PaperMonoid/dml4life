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

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;


public class PrincipalModelo implements IPrincipalModelo 
{

    private IConectorModelo conector;

    public PrincipalModelo(IConectorModelo conector) {
        this.conector = conector;
    }

    @Override
    public List<BaseDeDatos> getBasesDeDatos() throws SQLException,
            IllegalStateException 
    {
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
        conexion.close();
        return basesDeDatos;
    }

    @Override
    public DefaultTableModel consulta(String bdt, String ntbla)
    {DefaultTableModel modelo = new DefaultTableModel();
        try {
            Connection conexion = conector.getConexion();
            Statement comando;
            ResultSet resultados;
            comando = conexion.createStatement();
            resultados = comando.executeQuery(String.format("USE %s", bdt));
            comando = conexion.createStatement();
            resultados = comando.executeQuery(String.format("Select * From %s", ntbla));
            ResultSetMetaData rsmt = resultados.getMetaData();
            int col = rsmt.getColumnCount();
            
            for (int i = 1; 1 <= col; i++) {
                modelo.addColumn(rsmt.getColumnLabel(i));
            }
            while (resultados.next())
            {
                String fila[] = new String[col];
                for (int j = 0; j < col; j++) 
                {
                    fila[j] = resultados.getString(j + 1);
                    modelo.addRow(fila);
                }
            }
         } 
        catch (SQLException ex) 
        {
           // Logger.getLogger(PrincipalModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return modelo;
   }
}