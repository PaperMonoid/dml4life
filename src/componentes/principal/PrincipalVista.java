/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes.principal;

import componentes.conector.ConectorVista;
import componentes.insersion.InsersionVista;
import java.awt.event.WindowEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreeSelectionModel;

/**
 *
 * @author tritiummonoid
 */
public class PrincipalVista extends javax.swing.JFrame
        implements IPrincipalVista {

    private final PrincipalPresentador presentador;    

    /**
     * Creates new form PrincipalVista
     */
    public PrincipalVista() {
        initComponents();
        this.presentador = new PrincipalPresentador(this);
        panelConexion.setVisible(false);
        panelConsulta.setVisible(false);
        
        DefaultTreeCellRenderer renderer;
        renderer = (DefaultTreeCellRenderer) treeBasesDeDatos.getCellRenderer();
        Icon closedIcon = new ImageIcon("src/resources/db-icon.png");
        Icon openIcon = new ImageIcon("src/resources/db-icon.png");
        Icon leafIcon = new ImageIcon("src/resources/table-icon.png");
        renderer.setClosedIcon(closedIcon);
        renderer.setOpenIcon(openIcon);
        renderer.setLeafIcon(leafIcon);
        
        treeBasesDeDatos.getSelectionModel()
                .setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelConexion = new javax.swing.JPanel();
        scrollBasesDeDatos = new javax.swing.JScrollPane();
        treeBasesDeDatos = new javax.swing.JTree();
        panelConsulta = new javax.swing.JPanel();
        lblBaseDeDatos = new javax.swing.JLabel();
        lblTabla = new javax.swing.JLabel();
        lblSql = new javax.swing.JLabel();
        scrollSql = new javax.swing.JScrollPane();
        txtSql = new javax.swing.JTextArea();
        btnSql = new javax.swing.JButton();
        scrollConsulta = new javax.swing.JScrollPane();
        tblConsulta = new javax.swing.JTable();
        menubar = new javax.swing.JMenuBar();
        menuArchivo = new javax.swing.JMenu();
        itemConexion = new javax.swing.JMenuItem();
        itemSalir = new javax.swing.JMenuItem();
        menuEditar = new javax.swing.JMenu();
        itemInsertar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("dml4life");
        setLocation(new java.awt.Point(400, 250));
        setPreferredSize(new java.awt.Dimension(1000, 450));

        treeBasesDeDatos.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                mostrar(evt);
            }
        });
        scrollBasesDeDatos.setViewportView(treeBasesDeDatos);

        lblBaseDeDatos.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblBaseDeDatos.setText("Base de datos");

        lblTabla.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblTabla.setText("Tabla");

        lblSql.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        lblSql.setText("Consulta:");

        txtSql.setColumns(20);
        txtSql.setRows(5);
        scrollSql.setViewportView(txtSql);

        btnSql.setText("Aceptar");
        btnSql.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSqlActionPerformed(evt);
            }
        });

        tblConsulta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblConsulta.setName("tblConsulta"); // NOI18N
        scrollConsulta.setViewportView(tblConsulta);

        javax.swing.GroupLayout panelConsultaLayout = new javax.swing.GroupLayout(panelConsulta);
        panelConsulta.setLayout(panelConsultaLayout);
        panelConsultaLayout.setHorizontalGroup(
            panelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConsultaLayout.createSequentialGroup()
                .addGroup(panelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblBaseDeDatos)
                    .addComponent(lblTabla)
                    .addComponent(lblSql))
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(scrollSql)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelConsultaLayout.createSequentialGroup()
                .addGap(0, 638, Short.MAX_VALUE)
                .addComponent(btnSql))
            .addComponent(scrollConsulta)
        );
        panelConsultaLayout.setVerticalGroup(
            panelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConsultaLayout.createSequentialGroup()
                .addComponent(lblBaseDeDatos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTabla)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSql)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollSql, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSql)
                .addGap(8, 8, 8)
                .addComponent(scrollConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelConexionLayout = new javax.swing.GroupLayout(panelConexion);
        panelConexion.setLayout(panelConexionLayout);
        panelConexionLayout.setHorizontalGroup(
            panelConexionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConexionLayout.createSequentialGroup()
                .addGap(251, 251, 251)
                .addComponent(panelConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelConexionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelConexionLayout.createSequentialGroup()
                    .addComponent(scrollBasesDeDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 911, Short.MAX_VALUE)))
        );
        panelConexionLayout.setVerticalGroup(
            panelConexionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConexionLayout.createSequentialGroup()
                .addComponent(panelConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(panelConexionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelConexionLayout.createSequentialGroup()
                    .addComponent(scrollBasesDeDatos, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        menuArchivo.setText("Archivo");

        itemConexion.setText("Nueva conexión");
        itemConexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemConexionActionPerformed(evt);
            }
        });
        menuArchivo.add(itemConexion);

        itemSalir.setText("Salir");
        itemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSalirActionPerformed(evt);
            }
        });
        menuArchivo.add(itemSalir);

        menubar.add(menuArchivo);

        menuEditar.setText("Editar");

        itemInsertar.setText("Insertar");
        itemInsertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemInsertarActionPerformed(evt);
            }
        });
        menuEditar.add(itemInsertar);

        menubar.add(menuEditar);

        setJMenuBar(menubar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelConexion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelConexion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mostrar(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_mostrar
        TreeNode nodo;
        nodo = (TreeNode) treeBasesDeDatos.getLastSelectedPathComponent();
        if (nodo != null && nodo.isLeaf()) {
            TreeNode padre = nodo.getParent();
            presentador.seleccionarTabla(padre.toString(), nodo.toString());
        }
    }//GEN-LAST:event_mostrar

    private void itemConexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemConexionActionPerformed
        new ConectorVista(presentador).setVisible(true);
    }//GEN-LAST:event_itemConexionActionPerformed

    private void btnSqlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSqlActionPerformed
        presentador.ejecutar(txtSql.getText());
    }//GEN-LAST:event_btnSqlActionPerformed

    private void itemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSalirActionPerformed
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_itemSalirActionPerformed

    private void itemInsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemInsertarActionPerformed
        //new InsersionVista().setVisible(true);
    }//GEN-LAST:event_itemInsertarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSql;
    private javax.swing.JMenuItem itemConexion;
    private javax.swing.JMenuItem itemInsertar;
    private javax.swing.JMenuItem itemSalir;
    private javax.swing.JLabel lblBaseDeDatos;
    private javax.swing.JLabel lblSql;
    private javax.swing.JLabel lblTabla;
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JMenu menuEditar;
    private javax.swing.JMenuBar menubar;
    private javax.swing.JPanel panelConexion;
    private javax.swing.JPanel panelConsulta;
    private javax.swing.JScrollPane scrollBasesDeDatos;
    private javax.swing.JScrollPane scrollConsulta;
    private javax.swing.JScrollPane scrollSql;
    private javax.swing.JTable tblConsulta;
    private javax.swing.JTree treeBasesDeDatos;
    private javax.swing.JTextArea txtSql;
    // End of variables declaration//GEN-END:variables

    @Override
    public void cambioBasesDeDatos(TreeModel modelo) {
        treeBasesDeDatos.setModel(modelo);
        panelConexion.setVisible(true);
        panelConsulta.setVisible(false);
    }

    @Override
    public void consultaInvalida() {
        JOptionPane.showMessageDialog(null, "Consulta inválida.");
    }

    @Override
    public void conexionFallida() {
        JOptionPane.showMessageDialog(null, "Conexión fallida.");
    }

    @Override
    public void cambioTabla(String nombreBaseDeDatos, String nombreTabla, 
            String consulta) {
        lblBaseDeDatos.setText(String.format("[%s]", nombreBaseDeDatos));
        lblTabla.setText(nombreTabla);
        txtSql.setText(consulta);
        panelConsulta.setVisible(true);
        presentador.ejecutar(consulta);
    }

    @Override
    public void cambioConsulta(TableModel tabla) {
        tblConsulta.setModel(tabla);
        panelConsulta.setVisible(true);
    }

}
