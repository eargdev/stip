package br.com.quiz.model;

import java.util.List;

public class Pergunta {

    private Integer id;
    private String pergunta;
    private Integer nivel;
    private List<Alternativa> alternativas;
    private Alternativa alternativaCorreta;
    private Integer alternativaMarcada;
    private boolean acertou;
    private byte[] imagem;
    private String explicacao;
    
    private Assunto assunto;

    private Integer indice;

    public Pergunta() {
        alternativaCorreta = new Alternativa();
        assunto = new Assunto();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public List<Alternativa> getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(List<Alternativa> alternativas) {
        this.alternativas = alternativas;
    }

    public Alternativa getAlternativaCorreta() {
        return alternativaCorreta;
    }

    public void setAlternativaCorreta(Alternativa alternativaCorreta) {
        this.alternativaCorreta = alternativaCorreta;
    }

    public Integer getAlternativaMarcada() {
        return alternativaMarcada;
    }

    public void setAlternativaMarcada(Integer alternativaMarcada) {
        this.alternativaMarcada = alternativaMarcada;
    }

    public boolean isAcertou() {
        return acertou;
    }

    public void setAcertou(boolean acertou) {
        this.acertou = acertou;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public String getExplicacao() {
        return explicacao;
    }

    public void setExplicacao(String explicacao) {
        this.explicacao = explicacao;
    }

    public Integer getIndice() {
        return indice;
    }

    public void setIndice(Integer indice) {
        this.indice = indice;
    }

    public Assunto getAssunto() {
        return assunto;
    }

    public void setAssunto(Assunto assunto) {
        this.assunto = assunto;
    }
}