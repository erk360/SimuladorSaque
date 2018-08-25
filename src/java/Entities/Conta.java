package Entities;
public class Conta {
    
    private int idConta=0;
    private float saldo;
    
    
    public int getIdConta()
    {
        return this.idConta;
    }
    public void setIdConta(int idConta)
    {
        this.idConta=idConta;
    }
    public float getSaldo()
    {
        return this.saldo;
    }
    public void setSaldo(float saldo)
    {
        this.saldo=saldo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.idConta;
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
        final Conta other = (Conta) obj;
        if (this.idConta != other.idConta) {
            return false;
        }
        return true;
    }
}
