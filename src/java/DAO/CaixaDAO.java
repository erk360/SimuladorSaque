
package DAO;

import Entities.Caixa;
import Entities.Cliente;
import Entities.Conta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;


public class CaixaDAO implements MasterDAO<Caixa> {

    @Override
    public void salvar(Caixa entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletar(Caixa entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Caixa> pesquisar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Cliente pesquisarCliente(String cpf) throws Exception
    {
        Cliente cliente=null;
        try {
            Connection conexao = ConexaoDAO.getConexao();
            
            PreparedStatement ps = conexao.prepareStatement("SELECT c.cpf,c.nome,c.idCliente,ct.saldo,ct.idConta FROM cliente c LEFT JOIN conta ct ON ct.idConta=c.idConta where c.cpf=?");
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
        } catch (Exception ex) {
            throw new Exception("Erro ao pesquisar cliente: ", ex);
        }           
        return cliente;
    }
    
    
    public String getNotas() throws Exception
    {
        String notas="";
        try {
            Connection conexao = ConexaoDAO.getConexao();            
            PreparedStatement ps = conexao.prepareStatement("Select * from caixaeletronico order by notas");
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                notas+=resultSet.getInt("Notas")+";";
            }
            notas=notas.length()>2 ? notas.substring(0, notas.length()-1):"";
            ConexaoDAO.fecharConexao(); 
        } catch (Exception ex) {
            throw new Exception("Erro ao pesquisar cliente: ", ex);
        }           
        return notas;
    }
    
}
