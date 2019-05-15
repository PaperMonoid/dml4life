/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes.principal;

import javax.swing.table.TableModel;
import javax.swing.tree.TreeModel;

/**
 *
 * @author tritiummonoid
 */
public interface IPrincipalVista {
    void cambioBasesDeDatos(TreeModel modelo);
    void cambioTabla(String nombre, TableModel modelo);
    void consultaInvalida();
    void conexionFallida();
}
