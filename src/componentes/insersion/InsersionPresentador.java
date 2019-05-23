/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes.insersion;

import java.util.Iterator;
import java.util.Map;
import modelos.gestor.generico.ITabla;

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
        try {
            String sql = "INSERT INTO %s(%s) VALUES(%s)";
            Iterator<String> iterador = registro.keySet().iterator();
            StringBuilder listaCampos = new StringBuilder();
            StringBuilder listaValores = new StringBuilder();
            String llave;
            while (iterador.hasNext()) {
                llave = iterador.next();
                listaCampos.append(llave);
                if (tabla.getCampo(llave).getTipo().contains("int")) {
                    listaValores.append(registro.get(llave));
                } else {
                    listaValores.append("'")
                            .append(registro.get(llave))
                            .append("'");
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
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
