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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tritiummonoid
 */
public class GestorMysql implements IGestor {

    private final Connection conexion;
    private String servidor;
    private String usuario;
    private String clave;
    private String baseDeDatos;

    public GestorMysql(String servidor, String usuario,
            String clave, String baseDeDatos) throws SQLException {
        this.servidor = servidor;
        this.usuario = usuario;
        this.clave = clave;
        this.baseDeDatos = baseDeDatos;
        String string = String.format(
                "jdbc:mysql://%s/%s?user=%s&password=%s&serverTimezone=UTC",
                servidor, baseDeDatos, usuario, clave);
        DriverManager.setLoginTimeout(10);
        conexion = DriverManager.getConnection(string);
    }

    @Override
    public String getNombre() {
        return "MySQL";
    }

    @Override
    public List<IBaseDeDatos> getBasesDeDatos() throws Exception {
        List<IBaseDeDatos> basesDeDatos;
        Statement comando;
        ResultSet resultados;
        
        comando = conexion.createStatement();
        resultados = comando.executeQuery("SHOW DATABASES;");
        basesDeDatos = new ArrayList<>();
        while (resultados.next()) {
            String nombre = resultados.getString(1);
            basesDeDatos.add(new BaseDeDatosMysql(conexion, nombre));
        }
        resultados.close();
        comando.close();
        
        return basesDeDatos;
    }

    @Override
    public IBaseDeDatos getBaseDeDatos(String nombre) throws Exception {
        return new BaseDeDatosMysql(conexion, nombre);
    }

}
