package br.com.quiz.dao;

import br.com.quiz.factory.ConnectionFactory;
import br.com.quiz.model.Jogador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrincipalDAO {
    
    private Connection conexao = null;

    public List<Jogador> listarRanking() {

        conexao = ConnectionFactory.getConnection();

        String sql = "select j.id, j.nome, p.pontuacao from quiz.pontuacao p "
            + "join quiz.jogador j on j.id = p.id_jogador "
            + "order by pontuacao desc limit 10";

        List<Jogador> lista = new ArrayList<>();
        Integer count = 1;

        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Jogador j = new Jogador();
                j.setId(rs.getInt("id"));
                j.setNome(rs.getString("nome"));
                j.setPontuacaoTotal(rs.getInt("pontuacao"));
                j.setColocacao(count);
                
                lista.add(j);
                count++;
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                conexao.close();
            } catch(SQLException ex) {
               ex.printStackTrace();
            }
        }
        return lista;
    }
    
    public Integer listarQtdJogosRealizados() {

        conexao = ConnectionFactory.getConnection();

        String sql = "select total_partidas from quiz.estatistica where id = 1";

        Integer qtd = 0;

        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                qtd = rs.getInt("total_partidas");
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                conexao.close();
            } catch(SQLException ex) {
               ex.printStackTrace();
            }
        }
        return qtd;
    }
    
    public Integer listarQtdJogadores() {

        conexao = ConnectionFactory.getConnection();

        String sql = "select count(id) as qtd from quiz.pontuacao";

        Integer qtd = 0;

        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                qtd = rs.getInt("qtd");
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                conexao.close();
            } catch(SQLException ex) {
               ex.printStackTrace();
            }
        }
        return qtd;
    }
}