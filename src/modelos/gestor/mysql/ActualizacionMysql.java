/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.gestor.mysql;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import modelos.gestor.generico.IActualizacion;
import modelos.gestor.generico.ICampo;

/**
 *
 * @author tritiummonoid
 */
public class ActualizacionMysql implements IActualizacion {

    private Connection conexion;
    private String baseDeDatos;
    private String tabla;
    private Map<ICampo, String> campos;
    private String comando;

    public ActualizacionMysql(Connection conexion, String baseDeDatos, 
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
        StringBuilder actualizaciones, condiciones;
        Iterator<ICampo> iterador;
        List<ICampo> llavesPrimarias, ordinarios;
        llavesPrimarias = new ArrayList<>();
        ordinarios = new ArrayList<>();
        for (ICampo campo : campos.keySet()) {
            if (campo.llavePrimaria()) {
                llavesPrimarias.add(campo);
            } else {
                ordinarios.add(campo);
            }
        }
        actualizaciones = new StringBuilder();
        condiciones = new StringBuilder();
        if (!llavesPrimarias.isEmpty() && !ordinarios.isEmpty()) {
            iterador = ordinarios.iterator();
            while (iterador.hasNext()) {
                ICampo campo = iterador.next();
                String nombre = campo.getNombre();
                String valor = campos.get(campo);
                String expresion;
                if (campo.getTipo().contains("int") || valor == null) {
                    actualizaciones.append(String.format("`%s` = %s", 
                            nombre, valor));
                } else {
                    actualizaciones.append(String.format("`%s` = '%s'", 
                            nombre, valor));
                }
                if (iterador.hasNext()) {
                    actualizaciones.append(", ");
                }
            }
            iterador = llavesPrimarias.iterator();
            while (iterador.hasNext()) {
                ICampo campo = iterador.next();
                String nombre = campo.getNombre();
                String valor = campos.get(campo);
                String expresion;
                if (campo.getTipo().contains("int") || valor == null) {
                    condiciones.append(String.format("`%s` = %s", 
                            nombre, valor));
                } else {
                    condiciones.append(String.format("`%s` = '%s'", 
                            nombre, valor));
                }
                if (iterador.hasNext()) {
                    condiciones.append(" AND ");
                }
            }
            return String.format("UPDATE `%s` SET %s WHERE %s", tabla, 
                    actualizaciones, condiciones);
        }
        return "";
    }
    
    @Override
    public IActualizacion agregarCampo(ICampo campo, String valor) {
        this.campos.put(campo, valor);
        return this;
    }

    @Override
    public void setComando(String comando) {
        this.comando = comando;
    }

    @Override
    public void actualizar() throws Exception {
        Statement comando;
        
        comando = conexion.createStatement();
        comando.execute(String.format("USE %s", baseDeDatos));
        comando.close();

        comando = conexion.createStatement();
        comando.executeUpdate(toString());
        comando.close();
    }
    
}
