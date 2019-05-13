/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes.principal;

import componentes.conector.IConectorModelo;
import java.sql.SQLException;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tritiummonoid
 */
public class PrincipalPresentador {

    private IPrincipalVista vista;
    private IPrincipalModelo modelo;

    public PrincipalPresentador(IConectorModelo modelo) {
        this.modelo = new PrincipalModelo(modelo);
    }

    public void setVista(IPrincipalVista vista) {
        this.vista = vista;
        try {
            List<BaseDeDatos> basesDeDatos = this.modelo.getBasesDeDatos();
            this.vista.cambioBasesDeDatos(basesDeDatos);

        } catch (SQLException exception) {
            exception.printStackTrace();
            this.vista.consultaInvalida();
        } catch (IllegalStateException exception) {
            exception.printStackTrace();
            this.vista.conexionFallida();
        }
    }

    public void seleccionarTabla(String bddata, String ntabla) {
        try {
            DefaultTableModel vistabala;
            vistabala = this.modelo.consulta(bddata, ntabla);
            this.vista.cambioTabla(vistabala);
        } catch (SQLException exception) {
            exception.printStackTrace();
            this.vista.consultaInvalida();
        }
    }

}
