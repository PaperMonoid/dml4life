/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes.conector;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author tritiummonoid
 */
public interface IConectorModelo {

    void conectar() throws SQLException;

    Connection getConexion() throws IllegalStateException;
}
