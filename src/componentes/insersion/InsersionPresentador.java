/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes.insersion;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import modelos.gestor.ITabla;

/**
 *
 * @author tritiummonoid
 */
public class InsersionPresentador {
    IInsersionVista vista;
    ITabla tabla;
    
    public InsersionPresentador(IInsersionVista vista, ITabla tabla) {
        this.vista = vista;
        this.tabla = tabla;
    }
    
    public void insertar(Map<String, String> registro) {
        String sql = "INSERT INTO %s(%s) VALUES(%s)";
        Map<String, String> campos = tabla.getCampos();
        Iterator<String> iterador = registro.keySet().iterator();
        StringBuilder listaCampos = new StringBuilder();
        StringBuilder listaValores = new StringBuilder();
        String llave;
        while (iterador.hasNext()) {
            llave = iterador.next();
            System.out.println(campos.get(llave));
            listaCampos.append(llave);
            if (campos.get(llave).equals("String")) {
                listaValores.append("'").append(registro.get(llave)).append("'");
            } else {
                listaValores.append(registro.get(llave));
            }
            if (iterador.hasNext()) {
                listaCampos.append(", ");
                listaValores.append(", ");
            }
        }
        sql = String.format(
                sql, 
                tabla.getNombre(), 
                listaCampos.toString(), 
                listaValores.toString()
        );
        System.out.println(sql);
    }
}
