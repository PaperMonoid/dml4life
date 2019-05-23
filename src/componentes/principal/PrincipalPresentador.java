/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes.principal;

import java.util.List;
import java.util.Map;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import modelos.gestor.generico.IBaseDeDatos;
import modelos.gestor.generico.ICampo;
import modelos.gestor.generico.IConsulta;
import modelos.gestor.generico.IEliminacion;
import modelos.gestor.generico.IGestor;
import modelos.gestor.generico.ITabla;

/**
 *
 * @author tritiummonoid
 */
public class PrincipalPresentador {
    
    private IPrincipalVista vista;
    private IGestor gestor;
    private IBaseDeDatos baseDeDatos;
    private ITabla tabla;

    public PrincipalPresentador(IPrincipalVista vista) {
        this.vista = vista;
    }
    
    public void setGestor(IGestor gestor) {
        this.gestor = gestor;
        try {
            DefaultMutableTreeNode raiz = 
                    new DefaultMutableTreeNode("Bases de datos");
            for (IBaseDeDatos baseDeDatos : gestor.getBasesDeDatos()) {
                DefaultMutableTreeNode nodoBaseDeDatos = 
                        new DefaultMutableTreeNode(baseDeDatos.getNombre());
                raiz.add(nodoBaseDeDatos);
                for (ITabla tabla: baseDeDatos.getTablas()) {
                    DefaultMutableTreeNode nodoTabla = 
                            new DefaultMutableTreeNode(tabla.getNombre());
                    nodoBaseDeDatos.add(nodoTabla);
                }
            }
            DefaultTreeModel modelo = new DefaultTreeModel(raiz);
            this.vista.cambioServidor(modelo);

        } catch (Exception exception) {
            exception.printStackTrace();
            this.vista.conexionFallida();
        }
    }

    public void seleccionarTabla(String baseDeDatos, String tabla) {
        try {
            this.baseDeDatos = this.gestor.getBaseDeDatos(baseDeDatos);
            this.tabla = this.baseDeDatos.getTabla(tabla);
            IConsulta consulta = this.tabla.consulta();
            this.vista.cambioTabla(baseDeDatos, tabla, consulta.toString());
        } catch (Exception exception) {
            exception.printStackTrace();
            this.vista.consultaInvalida();
        }
    }

    public void ejecutar(String comando) {
        try {
            if (comando.toLowerCase().contains("delete")) {
                IEliminacion eliminacion = tabla.eliminacion();
                for (String subcomando : comando.split(";\n")) {
                    eliminacion.setComando(subcomando);
                    eliminacion.eliminar();
                }
                this.vista.eliminacionExitosa();
                IConsulta consulta = tabla.consulta();
                this.vista.cambioConsulta(consulta.toString());
                this.vista.cambioResultado(consulta.consultar());
            } else {
                IConsulta consulta = tabla.consulta();
                consulta.setComando(comando);
                this.vista.cambioConsulta(consulta.toString());
                this.vista.cambioResultado(consulta.consultar());
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            this.vista.consultaInvalida();
        }
    }

    public void eliminar(List<Map<String, String>> registros) {
        try {
            StringBuilder comando = new StringBuilder();
            List<ICampo> llavesPrimarias = tabla.getLlavesPrimarias();
            for (Map<String, String> registro : registros) {
                IEliminacion eliminacion = tabla.eliminacion();
                for (ICampo llavePrimaria : llavesPrimarias) {
                    eliminacion.agregarCampo(llavePrimaria, 
                            registro.get(llavePrimaria.getNombre()));
                }
                comando.append(eliminacion.toString()).append(";\n");
            }
            this.vista.cambioConsulta(comando.toString());
        } catch (Exception exception) {
            exception.printStackTrace();
            this.vista.consultaInvalida();
        }
    }

}
