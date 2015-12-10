package br.com.quiz.model;

import java.util.List;

public class Questionario {

    private List<Pergunta> perguntas;

    private String jogador;
    private Integer pontuacao;
    private Integer tempo;

    private Pergunta p1;
    private Pergunta p2;
    private Pergunta p3;
    private Pergunta p4;
    private Pergunta p5;
    private Pergunta p6;
    private Pergunta p7;
    private Pergunta p8;
    private Pergunta p9;
    private Pergunta p10;

    public Questionario() {
        p1 = new Pergunta();
        p2 = new Pergunta();
        p3 = new Pergunta();
        p4 = new Pergunta();
        p5 = new Pergunta();
        p6 = new Pergunta();
        p7 = new Pergunta();
        p8 = new Pergunta();
        p9 = new Pergunta();
        p10 = new Pergunta();
    }

    public List<Pergunta> getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(List<Pergunta> perguntas) {
        this.perguntas = perguntas;
    }

    public String getJogador() {
        return jogador;
    }

    public void setJogador(String jogador) {
        this.jogador = jogador;
    }

    public Integer getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }

    public Integer getTempo() {
        return tempo;
    }

    public void setTempo(Integer tempo) {
        this.tempo = tempo;
    }

    public Pergunta getP1() {
        return p1;
    }

    public void setP1(Pergunta p1) {
        this.p1 = p1;
    }

    public Pergunta getP2() {
        return p2;
    }

    public void setP2(Pergunta p2) {
        this.p2 = p2;
    }

    public Pergunta getP3() {
        return p3;
    }

    public void setP3(Pergunta p3) {
        this.p3 = p3;
    }

    public Pergunta getP4() {
        return p4;
    }

    public void setP4(Pergunta p4) {
        this.p4 = p4;
    }

    public Pergunta getP5() {
        return p5;
    }

    public void setP5(Pergunta p5) {
        this.p5 = p5;
    }

    public Pergunta getP6() {
        return p6;
    }

    public void setP6(Pergunta p6) {
        this.p6 = p6;
    }

    public Pergunta getP7() {
        return p7;
    }

    public void setP7(Pergunta p7) {
        this.p7 = p7;
    }

    public Pergunta getP8() {
        return p8;
    }

    public void setP8(Pergunta p8) {
        this.p8 = p8;
    }

    public Pergunta getP9() {
        return p9;
    }

    public void setP9(Pergunta p9) {
        this.p9 = p9;
    }

    public Pergunta getP10() {
        return p10;
    }

    public void setP10(Pergunta p10) {
        this.p10 = p10;
    }
}