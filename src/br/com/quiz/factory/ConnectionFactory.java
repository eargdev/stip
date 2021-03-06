package br.com.quiz.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    public static Connection getConnection() {

        Connection conexao = null;

        //String url = "jdbc:postgresql://localhost:5432/dev";
        //String usuario = "postgres";
        //String senha = "post";
        
        String url = "jdbc:postgresql://postgresurban.cugu75kgfzau.sa-east-1.rds.amazonaws.com:5432/dev";
        String usuario = "urban";
        String senha = "urban15ej";

        try {
            Class.forName("org.postgresql.Driver");
            conexao = DriverManager.getConnection(url, usuario, senha);
            conexao.setAutoCommit(false);
        } catch(Exception ex) {
            System.out.println("Erro ao criar conexão com o banco.");
            ex.printStackTrace();
        }
        return conexao;
    }
}