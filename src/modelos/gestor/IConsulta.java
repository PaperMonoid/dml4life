/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.gestor;

import javax.swing.table.TableModel;

/**
 *
 * @author papermonoid
 */
public interface IConsulta {
    IConsulta agregarCampo(String campo);
    IConsulta agregarCampo(String campo, String alias);
    TableModel consultar() throws Exception;
}
