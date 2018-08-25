package Bean;

import DAO.ClienteDAO;
import Entities.Cliente;
import com.mysql.jdbc.StringUtils;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ClienteBean extends MasterBean<Cliente, ClienteDAO>
{
    private ClienteDAO clienteDAO;
    private String msgError;
    
    @Override
    public ClienteDAO getNewDAO() {
        if(clienteDAO == null){
            clienteDAO = new ClienteDAO();
        }
        return this.clienteDAO;
    }

    @Override
    public Cliente createNewBean() {
        return new Cliente();
    }

    public String getMsgError() {
        return msgError;
    }

    public void setMsgError(String msgError) {
        this.msgError = msgError;
    }
    

    @Override
    public boolean validationEntity() 
    {
        boolean validation=false;
        this.setMsgError("???");
        try {
            if(!StringUtils.isNullOrEmpty(this.getEntity().getCpf()))
            {
                if(this.getNewDAO().getClienteCPF(this.getEntity().getCpf())==null)
                {
                    if(this.getEntity().getNome().length()>4)
                    {
                        if(this.getEntity().getConta().getSaldo()>10)
                           validation=true;
                        else
                            this.setMsgError("O saldo minimo deve ser de R$ 10,00!");
                    }else
                        this.setMsgError("Nome de cliente inválido");
                }else
                    this.setMsgError("CPF já cadastrado!");
            }
            else{
                this.setMsgError("CPF não pode ser vazio!");
            }
        } catch (SQLException ex) {
            this.setMsgError("Exceção SQL: "+ex.getMessage());
        } catch (Exception ex) {
             this.setMsgError("Exceção: "+ex.getMessage());
        }
        return validation;
    }

    @Override
    public String getErrorValidation() {
        return this.msgError;
    }
}
