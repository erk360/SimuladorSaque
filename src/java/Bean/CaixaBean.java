package Bean;

import DAO.CaixaDAO;
import DAO.ClienteDAO;
import Entities.Caixa;
import Entities.Cliente;
import com.mysql.jdbc.StringUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@SessionScoped
@ManagedBean
public class CaixaBean 
{
    private Caixa caixa= new Caixa();
    private int valorSolicitado;
    private int valorSacado;
    private String cedulas;
    private boolean notasLiberadas=false;
  
    public void message(String mensagem, FacesMessage.Severity tipoErro)
    {
        if( FacesContext.getCurrentInstance()!=null){
            FacesMessage fm = new FacesMessage(tipoErro, mensagem, null);
            FacesContext.getCurrentInstance().addMessage(null, fm);
        }
        
    }
    
    public String sacarValor(float saldo, int[] notasDisponiveis, int valor)
    {
        int aSacar=0;
        String strNotasAEntregar="";
        if(saldo>=notasDisponiveis[0])
        {
            if(valor<=saldo)
            {
                if(valor>=notasDisponiveis[0])
                {
                    for ( Map.Entry<Integer, Integer> m : contaNotas(notasDisponiveis,valor).entrySet() )
                    { 
                        aSacar+=(m.getKey()*m.getValue());
                        strNotasAEntregar+="[ "+ m.getValue() +"x "+  m.getKey()+ " ]";
                    }
                    if(aSacar==valor){
                        this.valorSacado=aSacar;
                        this.notasLiberadas=true;
                    }
                    else{
                        strNotasAEntregar="";
                        message("Nota indisponível para este valor.", FacesMessage.SEVERITY_ERROR);
                    }
                    
                }
                else
                    message("Nota indisponível para este valor.", FacesMessage.SEVERITY_ERROR);
            }
            else
                message("Saldo Insuficiente para este valor!", FacesMessage.SEVERITY_ERROR);
        }
        else
            message("Saldo insuficiente para saque", FacesMessage.SEVERITY_ERROR);
        return strNotasAEntregar;
    }
    
    private Map<Integer, Integer>  contaNotas(int[] notas, int valor)
    {
        Map<Integer, Integer> mapNotas = new Hashtable<Integer, Integer>();
        int nrNotas=0;
        for(int i=notas.length-1;i>=0;i--)
        {
            nrNotas=(int)(valor/notas[i]);
            if(nrNotas>0)
                mapNotas.put(notas[i], nrNotas);
            valor= valor - (nrNotas * notas[i]);
        }
        return mapNotas;
    }
        
    private int[] stringToIntArray(String str, String pattern)
    {
        String[] sArray=str.split(pattern);
        int[] iArray=new int[sArray.length];
        for (int i=0;i<sArray.length;i++)
            iArray[i]=Integer.parseInt(sArray[i]);
        Arrays.sort(iArray);      
        return iArray;
    }  
    
    public void entregarNotas()
    {    
        this.cedulas="";
        this.notasLiberadas=false;
        try {
            float newSaldo=this.caixa.getCliente().getConta().getSaldo()-this.valorSacado;
            if(newSaldo>=0){
                this.stringToIntArray(this.getCaixa().getNotasDisponiveis(), ";");
                this.cedulas=this.sacarValor(this.getCaixa().getCliente().getConta().getSaldo(), this.stringToIntArray(this.getCaixa().getNotasDisponiveis(), ";"), this.valorSolicitado);
                if(this.notasLiberadas){
                    this.caixa.getCliente().getConta().setSaldo(newSaldo);
                    (new ClienteDAO()).atualizaSaldoCliente(this.caixa.getCliente());
                }
                else
                    this.cedulas="";
            }
            else
                message("Não foi possível realizar o saque. Seu saldo ficará negativo;", FacesMessage.SEVERITY_WARN);
        } catch (Exception ex) {
            message("Erro ao atualizar conta do cliente:"+ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }             
    }
    
    public void onload()
    {
        try {
            if(this.caixa!=null){
                if(StringUtils.isNullOrEmpty(this.caixa.getNotasDisponiveis())){
                        this.caixa.setNotasDisponiveis((new CaixaDAO()).getNotas());
                }
            }
        } catch (Exception ex) 
        {
                message("Erro ao obter notas:", FacesMessage.SEVERITY_INFO); 
        }
        
    }
    
    public void verificaCliente()
    {
        try {
            if(!StringUtils.isNullOrEmpty(this.caixa.getCliente().getCpf()))
            {
                Cliente cliente=(new CaixaDAO()).pesquisarCliente(this.caixa.getCliente().getCpf());
                if(cliente!=null)
                {
                    this.caixa.setCliente(cliente);
                    message("Bem Vindo "+ cliente.getNome()+" !", FacesMessage.SEVERITY_INFO);
                }
                else
                    message("Cliente não encontrado!", FacesMessage.SEVERITY_WARN);
          }
        } catch (Exception ex) {
             message("Não foi possível consutar este cliente;", FacesMessage.SEVERITY_ERROR); 
        }
    }
    
    public String convertToNotasQuantidade(Map<Integer,Integer> map)
    {
        String s="";
        for ( Map.Entry<Integer, Integer> m : map.entrySet() )
            s+="[ "+ m.getValue() +"x "+  m.getKey() + " ]";
        
        return s;
    }

    public Caixa getCaixa() {
        return caixa;
    }

    public void setCaixa(Caixa caixa) {
        this.caixa = caixa;
    }

    public int getValorSolicitado() {
        return valorSolicitado;
    }

    public void setValorSolicitado(int valorSolicitado) {
        this.valorSolicitado = valorSolicitado;
    }

    public int getValorSacado() {
        return valorSacado;
    }

    public void setValorSacado(int valorSacado) {
        this.valorSacado = valorSacado;
    }

    public String getCedulas() {
        return cedulas;
    }

    public void setCedulas(String cedulas) {
        this.cedulas = cedulas;
    }
    
    
    
}
