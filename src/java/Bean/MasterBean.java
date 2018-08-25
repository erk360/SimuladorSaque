package Bean;

import DAO.MasterDAO;
import Entities.Cliente;
import Entities.Conta;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


public abstract class MasterBean<E, D extends MasterDAO> {
    
    private E entity;
    private List<E> entities;
    private boolean viewMode=true;
    
    
    public E getEntity() {
        return this.entity;
    }

    public void setEntity(E entity) {
        this.entity = entity;
    }

    public List<E> getEntities() {
        return this.entities;
    }
    
     public void novo()
    {
        this.entity=createNewBean();
        this.viewMode=false;
        message("Novo Cliente Criado!", FacesMessage.SEVERITY_INFO);
    }
     
    public void salvar()
    {
        try {  
            if(this.validationEntity()){
                this.getNewDAO().salvar(this.entity);
                this.entities=this.getNewDAO().pesquisar();
                this.viewMode=true;
                message("Cliente salvo com Sucesso", FacesMessage.SEVERITY_INFO);
            }
            else
                message(this.getErrorValidation(), FacesMessage.SEVERITY_WARN);
        } catch (Exception ex) {
            message(ex.getMessage(), FacesMessage.SEVERITY_ERROR);            
        }
    } 
    
    public void pesquisar()
    { 
        if(this.viewMode == false){
           this.viewMode=true;
        }else{
       
            try {
                this.entities = this.getNewDAO().pesquisar();
                if(this.entities== null || this.entities.size() < 1){
                    message("Não Existem Clientes Cadastrados!", FacesMessage.SEVERITY_WARN);
                }
            } catch (Exception ex) {
                message("Erro ao pesquisar Clientes!", FacesMessage.SEVERITY_WARN);
            }
            
        }
        this.viewMode=true;
    }
     
    public void editar(E entity)
    {
        this.entity = entity;
        this.viewMode=false;
    }
    
    public void deletar(E entity)
    {
        try {
            getNewDAO().deletar(entity);
            this.entities.remove(entity);
            message("Cliente deletado com sucesso!", FacesMessage.SEVERITY_INFO);
        } catch (Exception ex) {
            message(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    
    }
    
    public boolean isEdit(){ return this.viewMode;}
    
    public abstract D getNewDAO();
    public abstract E createNewBean();
    public abstract boolean validationEntity();
    public abstract String getErrorValidation();
    
    public void message(String mensagem, FacesMessage.Severity tipoErro)
    {
        FacesMessage fm = new FacesMessage(tipoErro, mensagem, null);
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }
    
    
}
