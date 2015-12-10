package br.com.quiz.controller;

public class Teste {

    public static void main(String[] args) {
        
        String matAux = "121212-8";
        
        String[] vetorAux = matAux.split("-");
        
        for(int i = 0; i < vetorAux.length; i++) {
            System.out.println(vetorAux[i]);
        }
        
        String mat = vetorAux[0];
        String digito = vetorAux[1];
        
        System.out.println(mat);
        System.out.println(digito);
    }
}