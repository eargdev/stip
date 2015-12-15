package br.com.quiz.model;

public class Assunto {
    
    private Integer id;
    private String descricao;
    private Integer nivelAssunto;
    private Integer pontuacaoAssunto;

    public Assunto() {
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getNivelAssunto() {
        return nivelAssunto;
    }

    public void setNivelAssunto(Integer nivelAssunto) {
        this.nivelAssunto = nivelAssunto;
    }

    public Integer getPontuacaoAssunto() {
        return pontuacaoAssunto;
    }

    public void setPontuacaoAssunto(Integer pontuacaoAssunto) {
        this.pontuacaoAssunto = pontuacaoAssunto;
    }
}