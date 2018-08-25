
package DAO;

import Entities.Cliente;
import Entities.Conta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ContaDAO implements MasterDAO<Conta>{
    
    @Override
    public void salvar(Conta conta) throws Exception {
        try {           
            PreparedStatement ps;
            if(conta.getIdConta() == 0){
                ps = conexao.prepareStatement("INSERT INTO conta (saldo) VALUES (?)");
            } else {
                ps = conexao.prepareStatement("UPDATE conta SET saldo=? where idConta=?");
                ps.setInt(2, conta.getIdConta());
            }
            ps.setFloat(1, conta.getSaldo());
            ps.execute();
        } catch (SQLException ex) {
            throw new Exception("Erro ao tentar inserir no banco! ", ex);
        } catch (Exception ex) {
            throw new Exception("Erro: ", ex);
        }
    }

    @Override
    public void deletar(Conta conta) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Conta> pesquisar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void deletar(Conta conta, Connection conexao) throws Exception {
        try {
            
            PreparedStatement ps  = conexao.prepareStatement("delete from Conta where idConta = ?");
            ps.setInt(1, conta.getIdConta());
            ps.execute();
            
        } catch (SQLException ex) {
            throw new Exception("Erro ao deletar um cliente!", ex);
        }
    }
    
    public Integer getMaxIdConta() throws Exception
    {
        Integer lastIdConta=0;
        try {
            conexao = ConexaoDAO.getConexao();
            
            PreparedStatement ps = conexao.prepareStatement("SELECT MAX(idConta) from Conta;");
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next())
                lastIdConta= resultSet.getInt(1); 
        } catch (Exception ex) {
            throw new Exception("Erro ao obter o Id da conta:", ex);
        }           
        return lastIdConta;
    }
    
    
    private Connection conexao;
    public void setConnection(Connection conexao){
        this.conexao=conexao;
    }
    
}
