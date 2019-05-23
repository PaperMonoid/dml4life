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
    IEliminacion agregarCampo(String campo, String valor) throws Exception;
    void setComando(String comando) throws Exception;
    void eliminar() throws Exception;
}
