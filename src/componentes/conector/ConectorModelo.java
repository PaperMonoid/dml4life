/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes.conector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author tritiummonoid
 */
public class ConectorModelo implements IConectorModelo {
    private String servidor;
    private String usuario;
    private String clave;
    private String baseDeDatos;
    
    public ConectorModelo(String servidor, String usuario, String clave, 
            String baseDeDatos) {
        this.servidor = servidor;
        this.usuario = usuario;
        this.clave = clave;
        this.baseDeDatos = baseDeDatos;
    }

    @Override
    public void conectar() throws Exception {
        String string = String.format(
                "jdbc:mysql://%s/%s?user=%s&password=%s&serverTimezone=UTC", 
                servidor, baseDeDatos, usuario, clave);
        Connection conn = DriverManager.getConnection(string);
        conn.close();
    }
    
}
