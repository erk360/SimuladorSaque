/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;

public interface MasterDAO<E> 
{    
    public void salvar(E entity) throws Exception;
    public void deletar(E entity) throws Exception;
    public List<E> pesquisar() throws Exception;
}
