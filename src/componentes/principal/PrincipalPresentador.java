/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes.principal;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import modelos.gestor.generico.IBaseDeDatos;
import modelos.gestor.generico.IConsulta;
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
            this.vista.cambioBasesDeDatos(modelo);

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

    public void ejecutarConsulta(String comando) {
        try {
            IConsulta consulta = this.tabla.consulta();
            this.vista.cambioConsulta(consulta.consultar(comando));
        } catch (Exception exception) {
            exception.printStackTrace();
            this.vista.consultaInvalida();
        }
    }

}
