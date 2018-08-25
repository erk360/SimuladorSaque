package Entities;

public class Caixa {
    private int idCaixa;
    private String notasDisponiveis;
    private Cliente cliente;
    
    public Caixa()
    {
        cliente=new Cliente();
    }

    public String getNotasDisponiveis() {
        return notasDisponiveis;
    }

    public void setNotasDisponiveis(String notasDisponiveis) {
        this.notasDisponiveis = notasDisponiveis;
    }
    
    public void setIdCaixa(int idCaixa)
    {
        this.idCaixa=idCaixa;
    }

    public int getIdCaixa()
    {
        return this.idCaixa;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.idCaixa;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Caixa other = (Caixa) obj;
        if (this.idCaixa != other.idCaixa) {
            return false;
        }
        return true;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
