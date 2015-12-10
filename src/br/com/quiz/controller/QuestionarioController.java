package br.com.quiz.controller;

import br.com.quiz.dao.JogadorDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.faces.bean.ManagedBean;
import br.com.quiz.dao.QuestionarioDAO;
import br.com.quiz.model.Alternativa;
import br.com.quiz.model.Jogador;
import br.com.quiz.model.Pergunta;
import br.com.quiz.model.Questionario;
import br.com.quiz.util.JSFMessageUtil;
import br.com.quiz.util.SessionUtil;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;

@SessionScoped
@ManagedBean
public class QuestionarioController {

    private Questionario questionario;
    
    private Jogador jogador;
    private Jogador jogadorLogado;
    
    /* ====================================================================== */
    
    // LISTA DAS PERGUNTAS DA VERIFICAÇÃO
    private List<Pergunta> perguntasVerif = new ArrayList();
    // PERGUNTA ATUAL DA VERIFICAÇÃO
    private Pergunta perguntaVerif = new Pergunta();
    // CONTROLE DA PERGUNTA ATUAL VERIF
    private Integer indexVerif = 0;
    // CONTROLE DA RODADA ATUAL VERIF
    private Boolean fimRodadaVerif = false;
    // PERGUNTAS RESPONDIDAS VERIF
    private List<Pergunta> perguntasRespondidasVerif = new ArrayList<>();
    private Integer indiceVerif = 1;
    // RESPOSTA DA PERGUNTA ATUAL
    private Integer respostaVerif = 0;
    // PONTUAÇÃO JOGADOR
    private Integer pontuacaoVerif = 0;
    
    /* ====================================================================== */
    
    // O VALOR FICA 1 QUANDO INICIA O JOGO
    private Integer pronto = 0;
    // LISTA DAS PERGUNTAS DA RODADA
    private List<Pergunta> perguntas = new ArrayList();
    // PERGUNTA ATUAL
    private Pergunta pergunta = new Pergunta();
    // RESPOSTA DA PERGUNTA ATUAL
    private Integer resposta = 0;
    // PONTUAÇÃO JOGADOR
    private Integer pontuacao = 0;
    
    /* ====================================================================== */
    
    // CONTROLE DA PERGUNTA ATUAL
    private Integer index = 0;
    // CONTROLE DA RODADA ATUAL
    private Boolean fimRodada = false;
    // PERGUNTAS RESPONDIDAS
    private List<Pergunta> perguntasRespondidas = new ArrayList<>();
    private Integer indice = 1;
    private String explicacao = "";

    public String finalizarJogo() {
        index = 0;
        pronto = 0;
        jogador = new Jogador();
        questionario = new Questionario();
        fimRodada = false;
        resposta = 0;
        pontuacao = 0;
        perguntas = null;
        perguntas = new ArrayList<>();
        pergunta = new Pergunta();
        
        RequestContext.getCurrentInstance().execute("PF('dlgFimRodada').hide();");
        
        SessionUtil.getSession().invalidate();
        
        JogoController jc = new JogoController();
        
        perguntasRespondidas = new ArrayList<>();
        indice = 1;
        explicacao = "";
        
        return jc.finalizarJogo();
    }
    
    public String finalizarJogo2() {
        index = 0;
        pronto = 0;
        jogador = new Jogador();
        questionario = new Questionario();
        fimRodada = false;
        resposta = 0;
        pontuacao = 0;
        perguntas = null;
        perguntas = new ArrayList<>();
        pergunta = new Pergunta();
        
        SessionUtil.getSession().invalidate();
        
        JogoController jc = new JogoController();
        
        perguntasRespondidas = new ArrayList<>();
        indice = 1;
        explicacao = "";
        
        return jc.finalizarJogo();
    }
    
    public void carregarExplicacao(Integer idPergunta) {
        QuestionarioDAO qd = new QuestionarioDAO();
        explicacao = qd.carregarExplicação(idPergunta);
    }
    
    public void cadastrarJogadorQuest() {
        RequestContext.getCurrentInstance().execute("PF('dlgJogar').hide();");
        RequestContext.getCurrentInstance().execute("PF('dlgCadastroQ').show();");
    }
    
    public QuestionarioController() {
        System.out.println("CONSTRUTOR");
        
        if(questionario == null) {
            questionario = new Questionario();
        }
        
        if(jogador == null) {
            jogador = new Jogador();
        }
    }
    
    public boolean verificaNivelJogador() {
        JogadorDAO jd = new JogadorDAO();
        return jd.verificaNivelJogador(jogadorLogado.getId());
    }
    
    public void login() {
        
        JogadorDAO jd = new JogadorDAO();
        Boolean existe = jd.verificaJogador(jogador.getNome());
        
        if(existe) {
            
            Jogador jogadorAux = jd.login(jogador);
            
            if(jogadorAux != null) {
                if(jogadorAux.getAtivo() == true) {
                    
                    jogadorLogado = jogadorAux;
                    
                    RequestContext.getCurrentInstance().execute("PF('dlgJogar').hide();");
                    
                    Boolean nivelZero = verificaNivelJogador();
                    System.out.println("NÍVEL ZERO: " + nivelZero);
                    
                    if(nivelZero) {
                        carregarQuestoesVerif();
                        carregarPerguntaVerif();
                        RequestContext.getCurrentInstance().execute("PF('dlgAvisoVerifNivel').show();");
                    } else {
                        pronto = 1;
                        carregarQuestoes();
                        carregarPergunta();
                    }
                } else {
                    JSFMessageUtil mu = new JSFMessageUtil();
                    mu.alerta("Esta conta foi suspensa!");
                }
            } else {
                JSFMessageUtil mu = new JSFMessageUtil();
                mu.alerta("Senha incorreta!");
            }
        } else {
            JSFMessageUtil mu = new JSFMessageUtil();
            mu.alerta("Este jogador não existe!");
        }
    }

    private void carregarQuestoes() {
        
        QuestionarioDAO qdao = new QuestionarioDAO();
        
        List<Pergunta> listaPerguntasAux = qdao.carregarPerguntas();
        List<Pergunta> listaFiltrada = new ArrayList<>();
        
        List<Pergunta> listaFacil = new ArrayList<>();
        List<Pergunta> listaMedio = new ArrayList<>();
        List<Pergunta> listaDificil = new ArrayList<>();

        System.out.println("QTD PERGUNTAS:" + listaPerguntasAux.size());

        for(Pergunta p : listaPerguntasAux) {
            if (p.getNivel().equals(1)) {
                listaFacil.add(p);
            }

            if (p.getNivel().equals(2)) {
                listaMedio.add(p);
            }

            if (p.getNivel().equals(3)) {
                listaDificil.add(p);
            }
        }

        System.out.println("LISTA FACIL: " + listaFacil.size());
        System.out.println("LISTA MÉDIO: " + listaMedio.size());
        System.out.println("LISTA DIFÍCIL: " + listaDificil.size());

        Random r = new Random();

        for(int i = 0; i <= 0; i++) {
            Integer posicao = r.nextInt(listaFacil.size());
            Pergunta pa = listaFacil.get(posicao);

            listaFiltrada.add(pa);
            listaFacil.remove(pa);
        }

        for(int i = 0; i <= 0; i++) {
            Integer posicao = r.nextInt(listaMedio.size());
            Pergunta pa = listaMedio.get(posicao);

            listaFiltrada.add(pa);
            listaMedio.remove(pa);
        }

        for(int i = 0; i <= 0; i++) {
            Integer posicao = r.nextInt(listaDificil.size());
            Pergunta pa = listaDificil.get(posicao);

            listaFiltrada.add(pa);
            listaDificil.remove(pa);
        }
        
        perguntas = listaFiltrada;
    }
    
    private void carregarQuestoesVerif() {
        
        QuestionarioDAO qdao = new QuestionarioDAO();
        perguntasVerif = qdao.carregarPerguntasVerif();

        System.out.println("QTD PERGUNTAS VERIF:" + perguntasVerif.size());
    }
    
    private void carregarPergunta() {
        
        pergunta = perguntas.get(index);
        
        QuestionarioDAO qdao = new QuestionarioDAO();
        List<Alternativa> alternativasAux = qdao.carregarAlternativas(pergunta.getId());
        pergunta.setAlternativas(alternativasAux);
    }
    
    private void carregarPerguntaVerif() {
        
        perguntaVerif = perguntasVerif.get(indexVerif);
        
        QuestionarioDAO qdao = new QuestionarioDAO();
        List<Alternativa> alternativasAux = qdao.carregarAlternativas(perguntaVerif.getId());
        perguntaVerif.setAlternativas(alternativasAux);
    }

    public void responderQuestao() {
        
        if(pergunta.getAlternativaCorreta().getId().equals(resposta)) {
            pergunta.setAcertou(true);
            pergunta.setAlternativaMarcada(resposta);
            System.out.println("QUESTÃO " + (index + 1) + " - " + pergunta.isAcertou());
            pergunta.setIndice(indice);
            perguntasRespondidas.add(pergunta);
            indice++;
        } else {
            pergunta.setAcertou(false);
            pergunta.setAlternativaMarcada(resposta);
            System.out.println("QUESTÃO " + (index + 1) + " - " + pergunta.isAcertou());
            pergunta.setIndice(indice);
            perguntasRespondidas.add(pergunta);
            indice++;
        }
        
        verificaRodada();
    }
    
    public void responderQuestaoVerif() {
        
        if(perguntaVerif.getAlternativaCorreta().getId().equals(respostaVerif)) {
            perguntaVerif.setAcertou(true);
            perguntaVerif.setAlternativaMarcada(respostaVerif);
            System.out.println("QUESTÃO " + (indexVerif + 1) + " - " + perguntaVerif.isAcertou());
            perguntaVerif.setIndice(indiceVerif);
            perguntasRespondidasVerif.add(perguntaVerif);
            indiceVerif++;
        } else {
            perguntaVerif.setAcertou(false);
            perguntaVerif.setAlternativaMarcada(respostaVerif);
            System.out.println("QUESTÃO " + (indexVerif + 1) + " - " + perguntaVerif.isAcertou());
            perguntaVerif.setIndice(indiceVerif);
            perguntasRespondidasVerif.add(perguntaVerif);
            indiceVerif++;
        }
        
        verificaRodadaVerif();
    }
    
    private void verificaRodadaVerif() {
        
        Integer size = perguntasVerif.size();
        
        indexVerif++;
        
        if(size.equals(indexVerif)) {
            System.out.println("I " + indexVerif + " / S " + size + " - FIM");
            fimRodadaVerif = true;
            
            gerarPontuacaoVerif();
            atualizarNivelJogador();
            
            RequestContext.getCurrentInstance().execute("PF('dlgFimRodada').show();");
        } else {
            carregarPerguntaVerif();
            System.out.println("I " + indexVerif + " / S " + size + " - CONTINUA");
        }
    }
    
    public void gerarPontuacaoVerif() {
        
        Integer qtdCorretas = 0;
        Integer pontosPergunta = 0;
        
        for(Pergunta p : perguntasVerif) {
            
            System.out.println("NÍVEL PERGUNTA: " + p.getNivel());
            
            if(p.isAcertou()) {
                pontosPergunta = 2;
                qtdCorretas++;
            } else {
                pontosPergunta = 1;
            }
            
            System.out.println("PONTUAÇÃO PERGUNTA ATUAL: " + pontosPergunta);
            
            pontuacaoVerif += pontosPergunta;
        }
        
        Integer nivel = 0;
        
        if(pontuacaoVerif == 10) {
            nivel = 3;
        } else {
            if(pontuacaoVerif > 5) {
                nivel = 2;
            } else {
                nivel = 1;
            }
        }
        
        jogador.setId(jogadorLogado.getId());
        jogador.setNivel(nivel);
        
        System.out.println("JOGADOR: " + jogadorLogado.getNome());
        System.out.println("PONTUAÇÃO ATUAL: " + pontuacaoVerif);
        System.out.println("PERGUNTAS x ACERTOS: " + perguntasVerif.size() + " / " + qtdCorretas);
        
        System.out.println("NÍVEL CONQUISTADO: " + nivel);
    }
    
    public void atualizarNivelJogador() {
        
        JogadorDAO jd = new JogadorDAO();
        jd.alterarNivelJogador(jogador);
    }
    
    private void verificaRodada() {
        
        Integer size = perguntas.size();
        
        index++;
        
        if(size.equals(index)) {
            System.out.println("I " + index + " / S " + size + " - FIM");
            fimRodada = true;
            
            gerarPontuacao();
            gravarPontuação();
            atualizarEstatisticas();
            
            RequestContext.getCurrentInstance().execute("PF('dlgFimRodada').show();");
        } else {
            carregarPergunta();
            System.out.println("I " + index + " / S " + size + " - CONTINUA");
        }
    }
    
    public void atualizarEstatisticas() {
        QuestionarioDAO qd = new QuestionarioDAO();
        qd.atualizaEstatisticas();
    }

    public void gerarPontuacao() {
        
        Integer qtdCorretas = 0;
        Integer pontosPergunta = 0;
        
        for(Pergunta p : perguntas) {
            
            System.out.println("NÍVEL PERGUNTA: " + p.getNivel());
            
            if(p.isAcertou()) {
                pontosPergunta = (100 * (p.getNivel() * 2)) / 10;
                qtdCorretas++;
            } else {
                pontosPergunta = (50 * (p.getNivel() * 2)) / 10;
            }
            
            System.out.println("PONTUAÇÃO PERGUNTA ATUAL: " + pontosPergunta);
            
            pontuacao += pontosPergunta;
        }
        
        Integer pontuacaoTotal = (pontuacao + jogadorLogado.getPontuacaoTotal());
        
        jogador.setId(jogadorLogado.getId());
        jogador.setPontuacaoAtual(pontuacao);
        jogador.setPontuacaoTotal(pontuacaoTotal);
        
        System.out.println("JOGADOR: " + jogadorLogado.getNome());
        System.out.println("PONTUAÇÃO ATUAL: " + pontuacao);
        System.out.println("PERGUNTAS x ACERTOS: " + perguntas.size() + " / " + qtdCorretas);
        
        System.out.println("LISTA PR: " + perguntasRespondidas.size());
    }
    
    public void gravarPontuação() {
        
        JogadorDAO jd = new JogadorDAO();
        jd.alterarPontucaoJogador(jogador);
    }

    public void finalizarRodada() {
        
        JogoController jc = new JogoController();
        jc.finalizarJogo();
    }

    public Questionario getQuestionario() {
        return questionario;
    }

    public void setQuestionario(Questionario questionario) {
        this.questionario = questionario;
    }
    
    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public Jogador getJogadorLogado() {
        return jogadorLogado;
    }

    public void setJogadorLogado(Jogador jogadorLogado) {
        this.jogadorLogado = jogadorLogado;
    }

    public Integer getPronto() {
        return pronto;
    }

    public void setPronto(Integer pronto) {
        this.pronto = pronto;
    }

    public List<Pergunta> getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(List<Pergunta> perguntas) {
        this.perguntas = perguntas;
    }

    public Pergunta getPergunta() {
        return pergunta;
    }

    public void setPergunta(Pergunta pergunta) {
        this.pergunta = pergunta;
    }

    public Integer getResposta() {
        return resposta;
    }

    public void setResposta(Integer resposta) {
        this.resposta = resposta;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Boolean getFimRodada() {
        return fimRodada;
    }

    public void setFimRodada(Boolean fimRodada) {
        this.fimRodada = fimRodada;
    }

    public Integer getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }

    public List<Pergunta> getPerguntasRespondidas() {
        return perguntasRespondidas;
    }

    public void setPerguntasRespondidas(List<Pergunta> perguntasRespondidas) {
        this.perguntasRespondidas = perguntasRespondidas;
    }

    public String getExplicacao() {
        return explicacao;
    }

    public void setExplicacao(String explicacao) {
        this.explicacao = explicacao;
    }

    public List<Pergunta> getPerguntasVerif() {
        return perguntasVerif;
    }

    public void setPerguntasVerif(List<Pergunta> perguntasVerif) {
        this.perguntasVerif = perguntasVerif;
    }

    public Pergunta getPerguntaVerif() {
        return perguntaVerif;
    }

    public void setPerguntaVerif(Pergunta perguntaVerif) {
        this.perguntaVerif = perguntaVerif;
    }

    public Integer getIndexVerif() {
        return indexVerif;
    }

    public void setIndexVerif(Integer indexVerif) {
        this.indexVerif = indexVerif;
    }

    public Boolean getFimRodadaVerif() {
        return fimRodadaVerif;
    }

    public void setFimRodadaVerif(Boolean fimRodadaVerif) {
        this.fimRodadaVerif = fimRodadaVerif;
    }

    public List<Pergunta> getPerguntasRespondidasVerif() {
        return perguntasRespondidasVerif;
    }

    public void setPerguntasRespondidasVerif(List<Pergunta> perguntasRespondidasVerif) {
        this.perguntasRespondidasVerif = perguntasRespondidasVerif;
    }

    public Integer getIndiceVerif() {
        return indiceVerif;
    }

    public void setIndiceVerif(Integer indiceVerif) {
        this.indiceVerif = indiceVerif;
    }

    public Integer getIndice() {
        return indice;
    }

    public void setIndice(Integer indice) {
        this.indice = indice;
    }

    public Integer getRespostaVerif() {
        return respostaVerif;
    }

    public void setRespostaVerif(Integer respostaVerif) {
        this.respostaVerif = respostaVerif;
    }

    public Integer getPontuacaoVerif() {
        return pontuacaoVerif;
    }

    public void setPontuacaoVerif(Integer pontuacaoVerif) {
        this.pontuacaoVerif = pontuacaoVerif;
    }
}