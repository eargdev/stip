package br.com.quiz.controller;

import br.com.quiz.dao.PrincipalDAO;
import br.com.quiz.model.Jogador;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class PrincipalController {
    
    private List<Jogador> ranking;

    public PrincipalController() {
        ranking = new ArrayList<>();
    }

    public List<Jogador> listarRanking() {
        
        PrincipalDAO pdao = new PrincipalDAO();
        
        return pdao.listarRanking();
    }
    
    public Integer listarQtdJogosRealizados() {
        
        PrincipalDAO pdao = new PrincipalDAO();
        
        return pdao.listarQtdJogosRealizados();
    }
    
    public Integer listarQtdJogadores() {
        
        PrincipalDAO pdao = new PrincipalDAO();
        
        return pdao.listarQtdJogadores();
    }
}