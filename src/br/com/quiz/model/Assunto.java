package br.com.quiz.model;

import java.util.List;

public class Assunto {
    
    private Integer id;
    private String descricao;
    private Integer nivelAssunto;
    private Integer pontuacaoAssunto;
    private Boolean questionario;
    private List<Referencia> referencias;

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

    public Boolean getQuestionario() {
        return questionario;
    }

    public void setQuestionario(Boolean questionario) {
        this.questionario = questionario;
    }

    public List<Referencia> getReferencias() {
        return referencias;
    }

    public void setReferencias(List<Referencia> referencias) {
        this.referencias = referencias;
    }
}