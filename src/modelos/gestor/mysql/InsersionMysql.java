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
import java.util.Map;

/**
 *
 * @author tritiummonoid
 */
public class InsersionMysql implements IInsersion {
    
    private Connection conexion;
    private String baseDeDatos;
    private String tabla;
    private Map<String, String> campos;

    public InsersionMysql(Connection conexion, String baseDeDatos, String tabla) {
        this.conexion = conexion;
        this.baseDeDatos = baseDeDatos;
        this.tabla = tabla;
        this.campos = new HashMap<>();
    }


    @Override
    public IInsersion agregarCampo(String campo, String valor) {
        campos.put(campo, valor);
        return this;
    }

    @Override
    public void insertar() throws Exception {
    }
    
}
