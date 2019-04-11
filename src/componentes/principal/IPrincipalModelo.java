/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes.principal;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author tritiummonoid
 */
public interface IPrincipalModelo {
    List<String> getBasesDeDatos() throws SQLException, IllegalStateException;
}
