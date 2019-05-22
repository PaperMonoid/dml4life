/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.gestor.generico;

import java.util.Map;

/**
 *
 * @author tritiummonoid
 */
public interface IBaseDeDatos {
    String getNombre();
    Map<String, ITabla> getTablas();
}
