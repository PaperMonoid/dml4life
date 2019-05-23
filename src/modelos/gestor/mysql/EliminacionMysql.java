/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.gestor.mysql;

import java.sql.Connection;
import java.sql.Statement;
import modelos.gestor.generico.IEliminacion;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.delete.Delete;

/**
 *
 * @author papermonoid
 */
public class EliminacionMysql implements IEliminacion {

    private Connection conexion;
    private String baseDeDatos;
    private String tabla;
    private Delete delete;

    public EliminacionMysql(Connection conexion, String baseDeDatos, 
            String tabla) throws Exception {
        this.conexion = conexion;
        this.baseDeDatos = baseDeDatos;
        this.tabla = tabla;
    }

    @Override
    public String toString() {
        Delete delete = this.delete;
        if (delete == null) {
            delete = new Delete();
            delete.setTable(new Table(tabla));
        }
        return delete.toString();
    }

    @Override
    public IEliminacion agregarCampo(String campo, String valor) 
            throws Exception {
        String condicion = String.format("%s = %s", campo, valor);
        if (delete == null) {
            delete = new Delete();
            delete.setTable(new Table(tabla));
            delete.setWhere(CCJSqlParserUtil.parseCondExpression(condicion));
        } else {
            Expression expression = delete.getWhere();
            expression = new AndExpression(expression, 
                    CCJSqlParserUtil.parseCondExpression(condicion));
            delete.setWhere(expression);
        }
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
    public void setComando(String comando) throws Exception {
        delete = (Delete) CCJSqlParserUtil.parse(comando);
    }
    
}
