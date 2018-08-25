package DAO;

import Entities.Cliente;
import Entities.Conta;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteDAO implements MasterDAO<Cliente> {

    
    @Override
    public void salvar(Cliente cliente) throws Exception  
    {
        try {          
            Connection conexao = ConexaoDAO.getConexao();
            PreparedStatement ps;
            this.ConfigContaDAO(cliente.getConta(),conexao);
            if(cliente.getIdCliente() == 0){
                ps = conexao.prepareStatement("INSERT INTO cliente (cpf,nome,idConta) VALUES (?,?,?)");
                cliente.getConta().setIdConta(this.contaDAO.getMaxIdConta());
            } else {
                ps = conexao.prepareStatement("UPDATE cliente set cpf=?, nome=?,  idConta=? where idCliente=?");
                ps.setInt(4, cliente.getIdCliente());
            }
            ps.setString(1, cliente.getCpf());
            ps.setString(2, cliente.getNome());
            ps.setInt(3, cliente.getConta().getIdConta());
            ps.execute();
            ConexaoDAO.fecharConexao();
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new Exception("Erro: ", ex);
        }
      
    }

    @Override
    public void deletar(Cliente cliente) throws Exception {
        try {
            Connection conexao = ConexaoDAO.getConexao();

            PreparedStatement ps  = conexao.prepareStatement("DELETE FROM cliente WHERE idCliente=?");
            ps.setInt(1, cliente.getIdCliente());
            ps.execute();
            (new ContaDAO()).deletar(cliente.getConta(),conexao);
            ConexaoDAO.fecharConexao();
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage(), ex);
        }
    }

    @Override
    public List<Cliente> pesquisar() throws Exception 
    {
        List<Cliente> clientes = new ArrayList<>();
        try
        {
            Connection conexao = ConexaoDAO.getConexao();
            PreparedStatement ps = conexao.prepareStatement("SELECT c.cpf,c.nome,c.idCliente,ct.saldo,ct.idConta FROM cliente c LEFT JOIN conta ct ON ct.idConta=c.idConta;");
            ResultSet resultSet = ps.executeQuery();
             
            while(resultSet.next()){
                Cliente cliente = new Cliente();
                Conta conta = new Conta();
                conta.setIdConta(resultSet.getInt("idConta"));
                conta.setSaldo(resultSet.getFloat("saldo"));
                
                cliente.setIdCliente(resultSet.getInt("idCliente"));
                cliente.setCpf(resultSet.getString("cpf"));
                cliente.setNome(resultSet.getString("nome"));
                cliente.setConta(conta);
                clientes.add(cliente);
            }
            ConexaoDAO.fecharConexao();       
        } catch (SQLException ex) {
            throw new Exception("Erro de consulta na base de dados! ", ex);
        } catch (Exception ex) {
            throw new Exception("Erro: ", ex);
        }
        return clientes;
    }
    
    public void atualizaSaldoCliente(Cliente cliente) throws Exception
    {
        ConfigContaDAO(cliente.getConta(),ConexaoDAO.getConexao());
        ConexaoDAO.fecharConexao();
    }
    
    public Cliente getClienteCPF(String cpf) throws Exception
    {
        Cliente cliente=null;
        try
        {
            Connection conexao = ConexaoDAO.getConexao();
            PreparedStatement ps = conexao.prepareStatement("SELECT c.cpf,c.nome,c.idCliente,ct.saldo,ct.idConta FROM cliente c LEFT JOIN conta ct ON ct.idConta=c.idConta where c.cpf=?;");
            ps.setString(1, cpf);
            ResultSet resultSet = ps.executeQuery();          
            
            if(resultSet.next()){
                cliente = new Cliente();
                Conta conta = new Conta();
                conta.setIdConta(resultSet.getInt("idConta"));
                conta.setSaldo(resultSet.getFloat("saldo"));
                
                cliente.setIdCliente(resultSet.getInt("idCliente"));
                cliente.setCpf(resultSet.getString("cpf"));
                cliente.setNome(resultSet.getString("nome"));
                cliente.setConta(conta);
                
            }
            
            ConexaoDAO.fecharConexao();
        } catch (SQLException ex) {
            throw new Exception("Erro de consulta na base de dados! ", ex);
        } catch (Exception ex) {
            throw new Exception("Erro: ", ex);
        }
        return cliente;
    }
    
    
    private ContaDAO contaDAO;
    private ContaDAO ConfigContaDAO(Conta conta, Connection conexao) throws Exception {
        if(this.contaDAO==null)
            this.contaDAO= new ContaDAO();
        this.contaDAO.setConnection(conexao);
        this.contaDAO.salvar(conta);
        return this.contaDAO;
    }
  
    
}
