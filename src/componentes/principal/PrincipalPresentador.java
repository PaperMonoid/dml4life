/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes.principal;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import modelos.gestor.IBaseDeDatos;
import modelos.gestor.IConsulta;
import modelos.gestor.IGestor;
import modelos.gestor.ITabla;

/**
 *
 * @author tritiummonoid
 */
public class PrincipalPresentador {

    private IPrincipalVista vista;
    private IGestor gestor;

    public PrincipalPresentador(IGestor gestor) {
        this.gestor = gestor;
    }

    public void setVista(IPrincipalVista vista) {
        this.vista = vista;
        try {
            DefaultMutableTreeNode raiz = 
                    new DefaultMutableTreeNode("Bases de datos");
            for (IBaseDeDatos baseDeDatos : gestor.getBasesDeDatos().values()) {
                DefaultMutableTreeNode nodoBaseDeDatos = 
                        new DefaultMutableTreeNode(baseDeDatos.getNombre());
                raiz.add(nodoBaseDeDatos);
                for (ITabla tabla: baseDeDatos.getTablas().values()) {
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
            IConsulta consulta = this.gestor
                    .getBasesDeDatos().get(baseDeDatos)
                    .getTablas().get(tabla)
                    .consulta();
            this.vista.cambioTabla(
                    baseDeDatos, 
                    tabla, 
                    consulta.toString(), 
                    consulta.consultar()
            );
        } catch (Exception exception) {
            exception.printStackTrace();
            this.vista.consultaInvalida();
        }
    }

}
