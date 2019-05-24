/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.gestor.mysql;

import modelos.gestor.generico.IInsersion;
import java.sql.Connection;
import java.sql.Statement;
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
        StringBuilder camposInsertar, camposValores;
        Iterator<ICampo> iterador;
        camposInsertar = new StringBuilder();
        camposValores = new StringBuilder();
        if (!campos.isEmpty()) {
            iterador = campos.keySet().iterator();
            while (iterador.hasNext()) {
                ICampo campo = iterador.next();
                String nombre = campo.getNombre();
                String valor = campos.get(campo);
                camposInsertar.append(String.format("`%s`", nombre));
                if (campo.getTipo().contains("int") || valor == null) {
                    camposValores.append(String.format("%s", valor));
                } else {
                    camposValores.append(String.format("'%s'", valor));
                }
                if (iterador.hasNext()) {
                    camposInsertar.append(", ");
                    camposValores.append(", ");
                }
            }
            return String.format("INSERT INTO `%s`(%s) VALUES(%s)", tabla, 
                    camposInsertar, camposValores);
        }
        return "";
    }

    @Override
    public IInsersion agregarCampo(ICampo campo, String valor) {
        campos.put(campo, valor);
        return this;
    }

    @Override
    public void insertar() throws Exception {
        Statement comando;
        
        comando = conexion.createStatement();
        comando.execute(String.format("USE %s", baseDeDatos));
        comando.close();

        comando = conexion.createStatement();
        comando.execute(toString());
        comando.close();
    }

    @Override
    public void setComando(String comando) {
        this.comando = comando;
    }
    
}
