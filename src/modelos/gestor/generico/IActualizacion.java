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
public interface IActualizacion {
    IActualizacion agregarCampo(ICampo campo, String valor);
    void setComando(String comando);
    void insertar() throws Exception;
}
