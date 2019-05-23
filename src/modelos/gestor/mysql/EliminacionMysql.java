/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.gestor.mysql;

import java.sql.Connection;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import modelos.gestor.generico.ICampo;
import modelos.gestor.generico.IEliminacion;

/**
 *
 * @author papermonoid
 */
public class EliminacionMysql implements IEliminacion {

    private Connection conexion;
    private String baseDeDatos;
    private String tabla;
    private Map<ICampo, String> campos;
    private String comando;

    public EliminacionMysql(Connection conexion, String baseDeDatos, 
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
        Iterator<Entry<ICampo, String>> iterador;
        builder = new StringBuilder("");
        if (!campos.isEmpty()) {
            builder.append(String.format("DELETE FROM `%s` WHERE", tabla));
            iterador = campos.entrySet().iterator();
            while (iterador.hasNext()) {
                Entry<ICampo, String> campo = iterador.next();
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
    public IEliminacion agregarCampo(ICampo campo, String valor) {
        this.campos.put(campo, valor);
        return this;
    }

    @Override
    public void eliminar() throws Exception {
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
