/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes.principal;

import componentes.conector.IConectorModelo;
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
        conexion.close();
        return basesDeDatos;
    }
    
}
