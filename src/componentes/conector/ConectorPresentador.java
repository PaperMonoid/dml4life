/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes.conector;

import componentes.principal.PrincipalPresentador;
import modelos.gestor.GestorFactory;
import modelos.gestor.IGestor;

/**
 *
 * @author tritiummonoid
 */
public class ConectorPresentador {

    private final IConectorVista vista;
    private final PrincipalPresentador presentador;

    public ConectorPresentador(PrincipalPresentador presentador, IConectorVista vista) {
        this.presentador = presentador;
        this.vista = vista;
    }

    public void conectar(String servidor, String usuario, String clave,
            String baseDeDatos) {
        try {
            IGestor gestor = new GestorFactory()
                    .create("MySQL", servidor, usuario, clave, baseDeDatos);
            presentador.setGestor(gestor);
            this.vista.conexionExitosa();
        } catch (Exception exception) {
            exception.printStackTrace();
            this.vista.conexionFallida();
        }
    }
}
