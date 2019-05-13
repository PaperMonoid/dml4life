/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes.conector;

import componentes.principal.PrincipalPresentador;

/**
 *
 * @author tritiummonoid
 */
public interface IConectorVista {

    public void conexionExitosa(PrincipalPresentador presentador);

    public void conexionFallida();
}
