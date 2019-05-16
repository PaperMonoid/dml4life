/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.gestor;

import java.sql.Connection;

/**
 *
 * @author tritiummonoid
 */
public class TablaMysql implements ITabla {

    private Connection conexion;
    private String baseDeDatos;
    private String nombre;

    public TablaMysql(Connection conexion, String baseDeDatos, String nombre) {
        this.conexion = conexion;
        this.baseDeDatos = baseDeDatos;
        this.nombre = nombre;
    }

    @Override
    public String getNombre() {
        return this.nombre;
    }

    @Override
    public IConsulta consulta() {
        return new ConsultaMysql(conexion, baseDeDatos, nombre);
    }
}
