package br.com.quiz.controller;

import br.com.quiz.dao.JogadorDAO;
import br.com.quiz.dao.QuestionarioDAO;
import br.com.quiz.model.Assunto;
import br.com.quiz.model.Jogador;
import br.com.quiz.util.JSFMessageUtil;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

@ViewScoped
@ManagedBean
public class JogoController {
    
    private Jogador jogadorCad;

    public JogoController() {
        jogadorCad = new Jogador();
    }
    
    public String questionarioNivel() {
        return "/quiz/questionarioNivel.faces?faces-redirect=true";
    }
    
    public String exibirRespostas() {
        return "/quiz/resposta.faces?faces-redirect=true";
    }
    
    public String iniciarJogo() {
        return "/quiz/jogo.faces?faces-redirect=true";
    }
    
    public String finalizarJogo() {
        return "/quiz/principal.faces?faces-redirect=true";
    }
    
    public String cadastrarJogador() {
        
        System.out.println("CADASTRO");
        
        String urlRetorno = "";
        
        JogadorDAO jd = new JogadorDAO();
        Boolean verifNome = jd.verificaJogador(jogadorCad.getNome());
        Boolean verifEmail = jd.verificaEmail(jogadorCad.getEmail());
        
        if(!verifNome && !verifEmail) {
            
            QuestionarioDAO qd = new QuestionarioDAO();
            List<Assunto> listaAssQuest = qd.carregarAssuntosQuestionario();
            
            Boolean gravou = jd.cadastrarJogador(jogadorCad, listaAssQuest);

            if(gravou) {
                
                limparDados();
                
                RequestContext.getCurrentInstance().execute("PF('dlgCadastro').show();");
                
                urlRetorno = "/quiz/principal.faces?faces-redirect=true";
            } else {
                JSFMessageUtil mu = new JSFMessageUtil();
                mu.alerta("Erro ao realizar cadastro.");
            }
        } else {
            if(verifNome) {
                JSFMessageUtil mu = new JSFMessageUtil();
                mu.alerta("O nome já está em uso.");
            }

            if(verifEmail) {
                JSFMessageUtil mu = new JSFMessageUtil();
                mu.alerta("O email já está em uso.");
            }
        }
        return urlRetorno;
    }
    
    public void limparDados() {
        jogadorCad = new Jogador();
    }

    public Jogador getJogadorCad() {
        return jogadorCad;
    }

    public void setJogadorCad(Jogador jogadorCad) {
        this.jogadorCad = jogadorCad;
    }
}