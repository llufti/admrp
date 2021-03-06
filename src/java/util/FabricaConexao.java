
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FabricaConexao {
    
    private static Connection conexao;
    private static final String URL_CONEXAO = "jdbc:mysql://localhost:3306/adserrana";
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    public static Connection getConexao()throws ErroSistema{
        if(conexao == null){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conexao = DriverManager.getConnection(URL_CONEXAO, USUARIO, SENHA);
            } catch (SQLException ex) {
               throw new ErroSistema("Nao foi possivel conectar",ex);
            } catch (ClassNotFoundException ex) {
               throw new ErroSistema("driver nao encontrado",ex); 
            }
        }
        return conexao;
    }
    
    public static void fecharConexao() throws ErroSistema{
        if(conexao != null){
            try {
                conexao.close();
                conexao = null;
            } catch (SQLException ex) {
                throw new ErroSistema("Erro ao fechar conexo",ex);
            }
        }
    }
    
    
}
