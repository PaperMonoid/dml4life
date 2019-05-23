/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.gestor.mysql;

import modelos.gestor.generico.IInsersion;
import modelos.gestor.generico.ITabla;
import modelos.gestor.generico.IConsulta;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelos.gestor.generico.ICampo;

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
    public IInsersion insersion() throws Exception {
        return new InsersionMysql(conexion, baseDeDatos, nombre);
    }
    
    @Override
    public IConsulta consulta() throws Exception {
        return new ConsultaMysql(conexion, baseDeDatos, nombre);
    }

    @Override
    public List<ICampo> getCampos() throws Exception {
        List<ICampo> campos;
        Statement comando;
        ResultSet resultados;

        comando = conexion.createStatement();
        comando.execute(String.format("USE `%s`", baseDeDatos));
        comando.close();

        comando = conexion.createStatement();
        resultados = comando.executeQuery(
                String.format("DESCRIBE `%s`", nombre));
        campos = new ArrayList<>();
        while (resultados.next()) {
            campos.add(new CampoMysql(resultados.getString(0), 
                    resultados.getString(1), 
                    "PRI".equals(resultados.getString(3))));
        }
        resultados.close();
        comando.close();
        
        return campos;
    }

    @Override
    public ICampo getCampo(String nombre) throws Exception {
        Statement comando;
        ResultSet resultados;

        comando = conexion.createStatement();
        comando.execute(String.format("USE `%s`", baseDeDatos));
        comando.close();

        comando = conexion.createStatement();
        resultados = comando.executeQuery(
                String.format("DESCRIBE `%s`", this.nombre));
        ICampo campo = null;
        while (resultados.next()) {
            if (nombre.equals(resultados.getString(0))) {
                campo = new CampoMysql(resultados.getString(0), 
                        resultados.getString(1),
                        "PRI".equals(resultados.getString(3)));
            }
        }
        resultados.close();
        comando.close();
        
        if (campo == null) {
            throw new RuntimeException("No se encontró el campo");
        }
        
        return campo;
    }

    @Override
    public List<ICampo> getLlavesPrimarias() throws Exception {
        List<ICampo> campos;
        Statement comando;
        ResultSet resultados;

        comando = conexion.createStatement();
        comando.execute(String.format("USE `%s`", baseDeDatos));
        comando.close();

        comando = conexion.createStatement();
        resultados = comando.executeQuery(
                String.format("DESCRIBE `%s`", nombre));
        campos = new ArrayList<>();
        while (resultados.next()) {
            if ("PRI".equals(resultados.getString(3))) {
                campos.add(new CampoMysql(resultados.getString(0), 
                        resultados.getString(1), true));
            }
        }
        resultados.close();
        comando.close();
        
        return campos;
    }
}
