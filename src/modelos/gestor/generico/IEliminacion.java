/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.gestor.generico;

/**
 *
 * @author papermonoid
 */
public interface IEliminacion {
    IEliminacion agregarCampo(ICampo campo, String valor);
    void setComando(String comando);
    void eliminar() throws Exception;
}
