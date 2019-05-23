/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.gestor.mysql;

import modelos.gestor.generico.IInsersion;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import modelos.gestor.generico.ICampo;

/**
 *
 * @author tritiummonoid
 */
public class InsersionMysql implements IInsersion {
    
    private Connection conexion;
    private String baseDeDatos;
    private String tabla;
    private Map<ICampo, String> campos;
    private String comando;

    public InsersionMysql(Connection conexion, String baseDeDatos, 
            String tabla) {
        this.conexion = conexion;
        this.baseDeDatos = baseDeDatos;
        this.tabla = tabla;
        this.campos = new HashMap<>();
    }

    @Override
    public String toString() {
        if (this.comando != null) {
            return this.comando;
        }
        StringBuilder builder;
        Iterator<Map.Entry<ICampo, String>> iterador;
        builder = new StringBuilder("");
        if (!campos.isEmpty()) {
            builder.append(String.format("INSERT INTO `%s`() VALUES(`%s`)", tabla));
            iterador = campos.entrySet().iterator();
            while (iterador.hasNext()) {
                Map.Entry<ICampo, String> campo = iterador.next();
                String nombre = campo.getKey().getNombre();
                String valor = campo.getValue();
                if (campo.getKey().getTipo().contains("int")) {
                    builder.append(String.format("`%s` = %s", nombre, valor));
                } else {
                    builder.append(String.format("`%s` = '%s'", nombre, valor));
                }
                if (iterador.hasNext()) {
                    builder.append(" AND ");
                }
            }
        }
        return builder.toString();
    }

    @Override
    public IInsersion agregarCampo(ICampo campo, String valor) {
        campos.put(campo, valor);
        return this;
    }

    @Override
    public void insertar() throws Exception {
    }

    @Override
    public void setComando(String comando) {
        this.comando = comando;
    }
    
}
