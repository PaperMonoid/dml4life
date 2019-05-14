/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.gestor;

import javax.swing.table.TableModel;

/**
 *
 * @author tritiummonoid
 */
public interface ITabla {
    String getNombre();
    TableModel consultar() throws Exception;
}
