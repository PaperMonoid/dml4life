/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes.principal;

import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tritiummonoid
 */
public interface IPrincipalVista {
    void cambioBasesDeDatos(List<BaseDeDatos> basesDeDatos);
    void cambioTabla(DefaultTableModel tabla);
    void consultaInvalida();
    void conexionFallida();
}
