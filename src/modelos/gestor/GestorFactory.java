/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.gestor;

/**
 *
 * @author tritiummonoid
 */
public class GestorFactory {
    public IGestor create(String gestor, String servidor, String usuario, 
            String clave, String baseDeDatos) throws Exception {
        switch(gestor) {
            case "MySQL":
                return new GestorMysql(servidor, usuario, clave, baseDeDatos);
            default:
                throw new IllegalStateException("Gestor no soportado");
        }
    }
}
