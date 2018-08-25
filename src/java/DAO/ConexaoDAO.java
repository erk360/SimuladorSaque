
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConexaoDAO 
{    
    private static Connection conexao;
     private static final String url = "jdbc:mysql://localhost:3306/SaqueNotas";
    private static final String user = "root";
    private static final String password = "abc123";
    
    public static Connection getConexao() throws Exception
    {
        if(conexao == null){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conexao = DriverManager.getConnection(url, user, password);  
            } catch (SQLException ex) {
                throw new Exception("Não foi possível conectar ao banco de dados!", ex);
            } catch (ClassNotFoundException ex) {
                throw new Exception("O driver do banco de dados não foi encontrado!", ex);
            }
        }
        return conexao;
    }
    
    public static void fecharConexao() throws Exception {
        if(conexao != null){
            try {
                conexao.close();
                conexao = null;
            } catch (SQLException ex) {
                throw new Exception("Erro ao fechar conexão com o banco de dados!");
            }
        }
    }
    
    
}
