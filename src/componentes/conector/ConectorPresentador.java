/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes.conector;

import componentes.principal.PrincipalPresentador;
import java.sql.SQLException;

/**
 *
 * @author tritiummonoid
 */
public class ConectorPresentador {
    
    private IConectorVista vista;
    
    public ConectorPresentador(IConectorVista vista) {
        this.vista = vista;
    }
    
    public void conectar(String servidor, String usuario, String clave, 
            String baseDeDatos) {
        try {
            IConectorModelo modelo = 
                    new ConectorModelo(servidor, usuario, clave, baseDeDatos);
            modelo.conectar();
            this.vista.conexionExitosa(new PrincipalPresentador(modelo));
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
            this.vista.conexionFallida();
        }
    }
}
