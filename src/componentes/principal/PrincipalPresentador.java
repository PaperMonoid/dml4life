/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes.principal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import modelos.gestor.generico.IActualizacion;
import modelos.gestor.generico.IBaseDeDatos;
import modelos.gestor.generico.ICampo;
import modelos.gestor.generico.IConsulta;
import modelos.gestor.generico.IEliminacion;
import modelos.gestor.generico.IGestor;
import modelos.gestor.generico.IInsersion;
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
    private Map<Map<String, String>, Map<String, String>> cambios;

    public PrincipalPresentador(IPrincipalVista vista) {
        this.vista = vista;
    }
    
    public void setGestor(IGestor gestor) {
        cambios = null;
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
            this.cambios = new HashMap<>();
        } catch (Exception exception) {
            exception.printStackTrace();
            this.vista.consultaInvalida();
        }
    }
    
    public void registrarCambio(Map<String, String> registro) {
        try {
            List<ICampo> llavesPrimarias = tabla.getLlavesPrimarias();
            Map<String, String> id = new HashMap<>();
            for (ICampo llavePrimaria : llavesPrimarias) {
                String nombre = llavePrimaria.getNombre();
                String valor = registro.get(nombre);
                id.put(nombre, valor);
            }
            cambios.put(id, registro);
        } catch (Exception exception) {
            exception.printStackTrace();
            this.vista.consultaInvalida();
        }
    }

    public void ejecutar(String comando) {
        try {
            if (comando.toLowerCase().startsWith("delete")) {
                IEliminacion eliminacion = tabla.eliminacion();
                for (String subcomando : comando.split(";\n")) {
                    eliminacion.setComando(subcomando);
                    eliminacion.eliminar();
                }
                this.vista.eliminacionExitosa();
                IConsulta consulta = tabla.consulta();
                this.vista.cambioConsulta(consulta.toString());
                this.vista.cambioResultado(consulta.consultar());
                this.cambios = new HashMap<>();
            } else if (comando.toLowerCase().startsWith("update")) {
                IActualizacion actualizacion = tabla.actualizacion();
                for (String subcomando : comando.split(";\n")) {
                    actualizacion.setComando(subcomando);
                    actualizacion.actualizar();
                }
                this.vista.actualizacionExitosa();
                IConsulta consulta = tabla.consulta();
                this.vista.cambioConsulta(consulta.toString());
                this.vista.cambioResultado(consulta.consultar());
                this.cambios = new HashMap<>();
            } else if (comando.toLowerCase().startsWith("insert")) {
                IInsersion insersion = tabla.insersion();
                for (String subcomando : comando.split(";\n")) {
                    insersion.setComando(subcomando);
                    insersion.insertar();
                }
                this.vista.insersionExitosa();
                IConsulta consulta = tabla.consulta();
                this.vista.cambioConsulta(consulta.toString());
                this.vista.cambioResultado(consulta.consultar());
                this.cambios = new HashMap<>();
            } else {
                IConsulta consulta = tabla.consulta();
                consulta.setComando(comando);
                this.vista.cambioConsulta(consulta.toString());
                this.vista.cambioResultado(consulta.consultar());
                this.cambios = new HashMap<>();
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

    public void actualizar() {
        try {
            StringBuilder comando = new StringBuilder();
            List<ICampo> campos = tabla.getCampos();
            for (Map<String, String> registro : cambios.values()) {
                IActualizacion actualizacion = tabla.actualizacion();
                for (ICampo campo : campos) {
                    actualizacion.agregarCampo(campo, 
                            registro.get(campo.getNombre()));
                }
                comando.append(actualizacion.toString()).append(";\n");
            }
            this.vista.cambioConsulta(comando.toString());
        } catch (Exception exception) {
            exception.printStackTrace();
            this.vista.consultaInvalida();
        }
    }

    public void insertar() {
        try {
            StringBuilder comando = new StringBuilder();
            List<ICampo> campos = tabla.getCampos();
            for (Map<String, String> registro : cambios.values()) {
                IInsersion insersion = tabla.insersion();
                for (ICampo campo : campos) {
                    insersion.agregarCampo(campo, 
                            registro.get(campo.getNombre()));
                }
                comando.append(insersion.toString()).append(";\n");
            }
            this.vista.cambioConsulta(comando.toString());
        } catch (Exception exception) {
            exception.printStackTrace();
            this.vista.consultaInvalida();
        }
    }

}
