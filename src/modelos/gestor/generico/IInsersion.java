/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.gestor.generico;

/**
 *
 * @author tritiummonoid
 */
public interface IInsersion {
    IInsersion agregarCampo(String campo, String valor);
    void insertar() throws Exception;
}
