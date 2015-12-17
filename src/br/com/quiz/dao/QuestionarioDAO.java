package br.com.quiz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.quiz.factory.ConnectionFactory;
import br.com.quiz.model.Alternativa;
import br.com.quiz.model.Assunto;
import br.com.quiz.model.Jogador;
import br.com.quiz.model.Pergunta;
import br.com.quiz.model.Referencia;

public class QuestionarioDAO {

    private Connection conexao = null;

    public List<Pergunta> carregarPerguntas() {

        conexao = ConnectionFactory.getConnection();

        String sql = "select p.id as idpergunta, p.pergunta, p.nivel, p.resposta_certa "
            + "as idresposta, a.resposta, a.id as idalternativa, g.explicacao, "
            + "ass.id as idassunto, ass.descricao as descassunto from quiz.pergunta p "
            + "join quiz.alternativa_pergunta ap on ap.id_pergunta = p.id "
            + "join quiz.alternativa a on a.id = ap.id_alternativa "
            + "join quiz.assunto ass on ass.id = p.tipo_pergunta "
            + "left join quiz.gabarito g on g.id_pergunta = p.id "
            + "where p.resposta_certa = a.id";

        List<Pergunta> lista = new ArrayList<>();

        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Pergunta p = new Pergunta();
                p.setId(rs.getInt("idpergunta"));
                p.setPergunta(rs.getString("pergunta"));
                p.setNivel(rs.getInt("nivel"));
                p.setExplicacao(rs.getString("explicacao"));
                p.getAssunto().setId(rs.getInt("idassunto"));
                p.getAssunto().setDescricao(rs.getString("descassunto"));
                p.getAlternativaCorreta().setId(rs.getInt("idalternativa"));
                p.getAlternativaCorreta().setDescricao(rs.getString("resposta"));

                lista.add(p);
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
    
    // EMERSON
    public List<Pergunta> carregarPerguntas(Jogador j, Assunto a) {

        conexao = ConnectionFactory.getConnection();

        String sql = "select p.id as idpergunta, p.pergunta, p.nivel, p.resposta_certa "
            + "as idresposta, at.resposta, at.id as idalternativa, g.explicacao, "
            + "ass.id as idassunto, ass.descricao as descassunto "
            + "from quiz.pergunta p "
            + "join quiz.assunto ass on ass.id = p.tipo_pergunta "
            + "join quiz.jogador_assunto_nivel jan on jan.id_assunto = ass.id "
            + "join quiz.jogador j on j.id = jan.id_jogador "
            + "join quiz.alternativa at on at.id = p.resposta_certa "
            + "left join quiz.gabarito g on g.id_pergunta = p.id "
            + "where p.nivel = ? and j.id = ? and ass.id = ?";

        List<Pergunta> lista = new ArrayList<>();

        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, a.getNivelAssunto());
            ps.setInt(2, j.getId());
            ps.setInt(3, a.getId());
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Pergunta p = new Pergunta();
                p.setId(rs.getInt("idpergunta"));
                p.setPergunta(rs.getString("pergunta"));
                p.setNivel(rs.getInt("nivel"));
                p.setExplicacao(rs.getString("explicacao"));
                p.getAssunto().setId(rs.getInt("idassunto"));
                p.getAssunto().setDescricao(rs.getString("descassunto"));
                p.getAlternativaCorreta().setId(rs.getInt("idalternativa"));
                p.getAlternativaCorreta().setDescricao(rs.getString("resposta"));

                lista.add(p);
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
    
    public List<Pergunta> carregarPerguntasVerif() {

        conexao = ConnectionFactory.getConnection();

        String sql = "select p.id as idpergunta, p.pergunta, p.nivel, p.resposta_certa "
            + "as idresposta, a.resposta, a.id as idalternativa, g.explicacao, "
            + "ass.id as idassunto, ass.descricao as descassunto from quiz.pergunta p "
            + "join quiz.alternativa_pergunta ap on ap.id_pergunta = p.id "
            + "join quiz.alternativa a on a.id = ap.id_alternativa "
            + "join quiz.assunto ass on ass.id = p.tipo_pergunta "
            + "left join quiz.gabarito g on g.id_pergunta = p.id "
            + "where p.resposta_certa = a.id and p.nivel = 0";

        List<Pergunta> lista = new ArrayList<>();

        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Pergunta p = new Pergunta();
                p.setId(rs.getInt("idpergunta"));
                p.setPergunta(rs.getString("pergunta"));
                p.setNivel(rs.getInt("nivel"));
                p.setExplicacao(rs.getString("explicacao"));
                p.getAssunto().setId(rs.getInt("idassunto"));
                p.getAssunto().setDescricao(rs.getString("descassunto"));
                p.getAlternativaCorreta().setId(rs.getInt("idalternativa"));
                p.getAlternativaCorreta().setDescricao(rs.getString("resposta"));

                lista.add(p);
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
    
    public List<Alternativa> carregarAlternativas(Integer idPergunta) {

        conexao = ConnectionFactory.getConnection();

        String sql = "select a.id, a.resposta from quiz.alternativa a "
            + "join quiz.alternativa_pergunta ap on ap.id_alternativa = a.id "
            + "where ap.id_pergunta = ?";

        List<Alternativa> lista = new ArrayList<>();

        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, idPergunta);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Alternativa a = new Alternativa();
                a.setId(rs.getInt("id"));
                a.setDescricao(rs.getString("resposta"));

                lista.add(a);
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
    
    public String carregarExplicação(Integer idPergunta) {

        conexao = ConnectionFactory.getConnection();

        String sql = "select explicacao from quiz.gabarito where id_pergunta = ?";

        String exp = "";

        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, idPergunta);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                exp = rs.getString("explicacao");
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
        return exp;
    }
    
    public void atualizaEstatisticas() {

        conexao = ConnectionFactory.getConnection();

        String sql = "select total_partidas from quiz.estatistica where id = 1";

        Integer total = 0;
        
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                total = rs.getInt("total_partidas");
            }
            
            total = total + 1;
            
            sql = "update quiz.estatistica set total_partidas = ? where id = 1";
            
            ps = conexao.prepareStatement(sql);
            ps.setInt(1, total);
            ps.executeUpdate();
            
            conexao.commit();
            
        } catch(SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                conexao.close();
            } catch(SQLException ex) {
               ex.printStackTrace();
            }
        }
    }
    
    public List<Assunto> carregarAssuntosQuestionario() {

        conexao = ConnectionFactory.getConnection();
        
        String sql = "select * from quiz.assunto where questionario = true";
        
        List<Assunto> lista = new ArrayList<>();
        
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                Assunto a = new Assunto();
                a.setId(rs.getInt("id"));
                a.setDescricao(rs.getString("descricao"));
                a.setQuestionario(rs.getBoolean("questionario"));
                lista.add(a);
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
    
    public Assunto carregarreferenciasAssunto(Integer idAssunto) {

        conexao = ConnectionFactory.getConnection();
        
        String sql = "select re.id, re.link_referencia, ass.id as idassunto, "
            + "ass.descricao as descassunto from quiz.referencia re "
            + "join quiz.assunto_referencia ar on ar.id_referencia = re.id "
            + "join quiz.assunto ass on ass.id = ar.id_assunto where ass.id = ?";
        
        Assunto as = new Assunto();
        List<Referencia> lista = new ArrayList<>();
        
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, idAssunto);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                Referencia r = new Referencia();
                r.setId(rs.getInt("id"));
                r.setLinkReferencia(rs.getString("link_referencia"));
                
                lista.add(r);
            }
            
            as.setReferencias(lista);
            
            ps = conexao.prepareStatement(sql);
            ps.setInt(1, idAssunto);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                as.setId(rs.getInt("idassunto"));
                as.setDescricao(rs.getString("descassunto"));
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
        return as;
    }
}