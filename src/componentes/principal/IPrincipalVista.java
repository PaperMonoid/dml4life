/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes.principal;

import java.util.List;

/**
 *
 * @author tritiummonoid
 */
public interface IPrincipalVista {
    void cambioBasesDeDatos(List<String> basesDeDatos);
    void consultaInvalida();
    void conexionFallida();
}
