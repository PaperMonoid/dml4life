/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.gestor.generico;

import modelos.gestor.generico.IBaseDeDatos;
import java.util.Map;

/**
 *
 * @author tritiummonoid
 */
public interface IGestor {
    Map<String, IBaseDeDatos> getBasesDeDatos();
}
