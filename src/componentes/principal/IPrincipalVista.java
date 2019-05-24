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
    void cambioServidor(TreeModel modelo);
    void cambioTabla(String nombreBaseDeDatos, String nombreTabla, 
            String consulta);
    void cambioConsulta(String consulta);
    void cambioResultado(TableModel tabla);
    void consultaInvalida();
    void conexionFallida();
    void eliminacionExitosa();
    void actualizacionExitosa();
}
