/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes.principal;

import java.sql.SQLException;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tritiummonoid
 */
public interface IPrincipalModelo {
    List<BaseDeDatos> getBasesDeDatos() throws SQLException, 
            IllegalStateException;

    public DefaultTableModel consulta(String ntbla, String bdt);

   
}
