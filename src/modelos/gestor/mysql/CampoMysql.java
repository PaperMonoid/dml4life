/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.gestor.mysql;

import modelos.gestor.generico.ICampo;

/**
 *
 * @author papermonoid
 */
public class CampoMysql implements ICampo {
    String tipo;
    String nombre;
    boolean llavePrimaria;
    
    public CampoMysql(String nombre, String tipo, boolean llavePrimaria) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.llavePrimaria = llavePrimaria;
    }

    @Override
    public String getNombre() {
        return nombre;
        
    }

    @Override
    public String getTipo() {
        return tipo;
    }

    @Override
    public boolean llavePrimaria() {
        return llavePrimaria;
    }
}
