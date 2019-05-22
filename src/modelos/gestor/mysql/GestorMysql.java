/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.gestor.mysql;

import modelos.gestor.generico.IGestor;
import modelos.gestor.generico.IBaseDeDatos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author tritiummonoid
 */
public class GestorMysql implements IGestor {

    private Connection conexion;
    private Map<String, IBaseDeDatos> basesDeDatos;

    public GestorMysql(String servidor, String usuario,
            String clave, String baseDeDatos) throws SQLException {
        String string = String.format(
                "jdbc:mysql://%s/%s?user=%s&password=%s&serverTimezone=UTC",
                servidor, baseDeDatos, usuario, clave);
        DriverManager.setLoginTimeout(10);
        conexion = DriverManager.getConnection(string);        
        
        Statement comando = conexion.createStatement();
        ResultSet resultados = comando.executeQuery("SHOW DATABASES");

        basesDeDatos = new HashMap<>();
        while (resultados.next()) {
            String nombre = resultados.getString(1);
            basesDeDatos.put(nombre, new BaseDeDatosMysql(conexion, nombre));
        }
        
        resultados.close();
        comando.close();
    }

    @Override
    public Map<String, IBaseDeDatos> getBasesDeDatos() {
        return this.basesDeDatos;
    }

}
