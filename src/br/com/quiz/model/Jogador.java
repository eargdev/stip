package br.com.quiz.model;

public class Jogador {
    
    private Integer id;
    private String nome;
    private String email;
    private String senha;
    private Integer pontuacaoAtual;
    private Integer pontuacaoTotal;
    private Integer nivel;
    private Boolean ativo;
    
    // CONTADOR TABELA
    private Integer colocacao;
    
    public Jogador() {
        pontuacaoAtual = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getPontuacaoAtual() {
        return pontuacaoAtual;
    }

    public void setPontuacaoAtual(Integer pontuacaoAtual) {
        this.pontuacaoAtual = pontuacaoAtual;
    }

    public Integer getPontuacaoTotal() {
        return pontuacaoTotal;
    }

    public void setPontuacaoTotal(Integer pontuacaoTotal) {
        this.pontuacaoTotal = pontuacaoTotal;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Integer getColocacao() {
        return colocacao;
    }

    public void setColocacao(Integer colocacao) {
        this.colocacao = colocacao;
    }
}