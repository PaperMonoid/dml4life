/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes.conector;

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
            this.vista.conexionExitosa();
        } catch (Exception exception) {
            this.vista.conexionFallida();
        }
    }
}
