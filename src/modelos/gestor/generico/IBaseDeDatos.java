/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.gestor.generico;

import java.util.List;

/**
 *
 * @author tritiummonoid
 */
public interface IBaseDeDatos {
    String getNombre();
    List<ITabla> getTablas() throws Exception;
    ITabla getTabla(String nombre) throws Exception;
}
