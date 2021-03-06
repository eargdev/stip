package br.com.quiz.dao;

import br.com.quiz.factory.ConnectionFactory;
import br.com.quiz.model.Assunto;
import br.com.quiz.model.Jogador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JogadorDAO {
    
    private Connection conexao;
    
    public Boolean verificaJogador(String nomeJogador) {
        
        conexao = ConnectionFactory.getConnection();
        
        String sql = "select id, nome from quiz.jogador where nome = ?";
        
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, nomeJogador.toUpperCase().trim());
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                return true;
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
        return false;
    }
    
    public Boolean verificaEmail(String emailJogador) {
        
        conexao = ConnectionFactory.getConnection();
        
        String sql = "select id, email from quiz.jogador where email = ?";
        
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, emailJogador.toUpperCase().trim());
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                return true;
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
        return false;
    }
    
    public Jogador login(Jogador jogador) {
        
        conexao = ConnectionFactory.getConnection();
        
        String sql = "select j.*, p.pontuacao from quiz.jogador j "
            + "join quiz.pontuacao p on p.id_jogador = j.id "
            + "where j.nome = ? and j.senha = ?";
        
        Jogador j = null;
        
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, jogador.getNome().toUpperCase().trim());
            ps.setString(2, jogador.getSenha());
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                j = new Jogador();
                j.setId(rs.getInt("id"));
                j.setNome(rs.getString("nome"));
                j.setEmail(rs.getString("email"));
                j.setSenha(rs.getString("senha"));
                j.setQuestInicial(rs.getBoolean("quest_inicial"));
                j.setPontuacaoTotal(rs.getInt("pontuacao"));
                j.setAtivo(rs.getBoolean("ativo"));
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
        return j;
    }
    
    public boolean cadastrarJogador(Jogador jogador, List<Assunto> assuntos) {
        
        conexao = ConnectionFactory.getConnection();
        
        String sql = "insert into quiz.jogador(nome, email, senha, ativo) values (?, ?, ?, true) returning id";
        
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, jogador.getNome().toUpperCase());
            ps.setString(2, jogador.getEmail().toUpperCase());
            ps.setString(3, jogador.getSenha());
            ResultSet rs = ps.executeQuery();
            
            Integer idRetorno = 0;
            
            while(rs.next()) {                
                idRetorno = rs.getInt("id");
            }
            
            sql = "insert into quiz.jogador_assunto_nivel(id_jogador, id_assunto) values (?, ?)";
            
            for(Assunto a : assuntos) {
                ps = conexao.prepareStatement(sql);
                ps.setInt(1, idRetorno);
                ps.setInt(2, a.getId());
                ps.execute();
            }
            
            sql = "insert into quiz.pontuacao(id_jogador, pontuacao) values (?, 0)";
            
            ps = conexao.prepareStatement(sql);
            ps.setInt(1, idRetorno);
            ps.execute();
            
            conexao.commit();
            
            return true;
        } catch(SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                conexao.close();
            } catch(SQLException ex) {
               ex.printStackTrace();
            }
        }
        return false;
    }
    
    public boolean alterarPontucaoJogador(Jogador jogador) {
        
        conexao = ConnectionFactory.getConnection();
        
        String sql = "update quiz.pontuacao set pontuacao = ? where id_jogador = ?";
        
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, jogador.getPontuacaoTotal());
            ps.setInt(2, jogador.getId());
            ps.executeUpdate();
            
            conexao.commit();
            
            return true;
        } catch(SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                conexao.close();
            } catch(SQLException ex) {
               ex.printStackTrace();
            }
        }
        return false;
    }
    
    public Boolean verificaQuestIniJogador(Integer id) {
        
        conexao = ConnectionFactory.getConnection();
        
        String sql = "select quest_inicial from quiz.jogador where id = ?";
        
        Boolean questInicial = true;
        
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                questInicial = rs.getBoolean("quest_inicial");
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
        return questInicial;
    }
    
    public List<Assunto> verificaNivelAssJogador(Integer id) {
        
        conexao = ConnectionFactory.getConnection();
        
        String sql = "select an.*, a.descricao "
            + "from quiz.jogador_assunto_nivel an "
            + "join quiz.assunto a on a.id = an.id_assunto "
            + "where an.id_jogador = ?";
        
        List<Assunto> lista = new ArrayList<>();
        
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                Assunto a = new Assunto();
                a.setId(rs.getInt("id_assunto"));
                a.setDescricao(rs.getString("descricao"));
                a.setNivelAssunto(rs.getInt("nivel_assunto"));
                a.setPontuacaoAssunto(rs.getInt("pontuacao_nivel"));
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
    
    public boolean atualizaQuestIniJogador(Jogador jogador, Map<Integer, Integer> mapNivel) {
        
        conexao = ConnectionFactory.getConnection();
        
        String sql = "update quiz.jogador set quest_inicial = false where id = ?";
        
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, jogador.getId());
            ps.executeUpdate();
            
            sql = "update quiz.jogador_assunto_nivel set nivel_assunto = ? where id_jogador = ? and id_assunto = ?";
            
            ps = conexao.prepareStatement(sql);
            for(Map.Entry<Integer, Integer> map : mapNivel.entrySet()) {
                Integer key = map.getKey();
                Integer valor = map.getValue();

                ps.setInt(1, valor);
                ps.setInt(2, jogador.getId());
                ps.setInt(3, key);
                ps.executeUpdate();
            }
            
            conexao.commit();
            
            return true;
        } catch(SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                conexao.close();
            } catch(SQLException ex) {
               ex.printStackTrace();
            }
        }
        return false;
    }
    
    public boolean atualizaNivelPontQuest(Jogador jogador, Map<Integer, Assunto> mapNivel) {
        
        conexao = ConnectionFactory.getConnection();
        
        String sql = "update quiz.jogador_assunto_nivel set pontuacao_nivel = ? where id_jogador = ? and id_assunto = ?";
        
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            for(Map.Entry<Integer, Assunto> map : mapNivel.entrySet()) {
                Integer key = map.getKey();
                Assunto valor = map.getValue();

                ps.setInt(1, valor.getPontuacaoAssunto());
                ps.setInt(2, jogador.getId());
                ps.setInt(3, key);
                ps.executeUpdate();
            }
            
            conexao.commit();
            
            return true;
        } catch(SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                conexao.close();
            } catch(SQLException ex) {
               ex.printStackTrace();
            }
        }
        return false;
    }
}