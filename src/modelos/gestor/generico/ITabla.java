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
public interface ITabla {
    String getNombre();
    List<ICampo> getCampos() throws Exception;
    ICampo getCampo(String nombre) throws Exception;
    List<ICampo> getLlavesPrimarias() throws Exception;
    IInsersion insersion();
    IConsulta consulta();
}
