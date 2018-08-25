
package Bean;


import java.util.Map;
import java.util.TreeMap;
import junit.framework.TestCase;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class CaixaBeanTest extends TestCase 
{
    private CaixaBean caixaBean;
    
    @Before
    public void setUp() 
    {
        this.caixaBean=new CaixaBean();
    }
    
    @Test
    public void testSaque()
    {
        int valor=30;
        float saldo=90;
        int[] notasDisponiveis={10,20,50,100};
         //Map<Notas,Quantidade>
        Map<Integer,Integer> notasEsperadas = new TreeMap<Integer, Integer>()      
        {{put(10,1); put(20,1);}};
        
        String expectedResult=this.caixaBean.convertToNotasQuantidade(notasEsperadas);
       
       assertEquals(expectedResult, this.caixaBean.sacarValor(saldo, notasDisponiveis, valor));
    }
    
    
    
}
