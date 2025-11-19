package Repository;

import Entity.Serie;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SerieRepository {

    public boolean Incluir(Serie serie) {
        AcessoDB banco = new AcessoDB();
        
        String sql = "INSERT INTO Serie (Descricao, Turno, Situacao) ";
        sql = sql + " VALUES ('" + serie.getDescricao() + "','" + serie.getTurno() + "','" + serie.getSituacao() + "')";
        
        try
        {
            banco.conect();
            banco.Executar(sql);
            return true;
        } catch (Exception ex) {
            return false;
        } finally {
            banco.disconect();
        }        
    }
    
    public boolean Alterar(Serie serie) {
        AcessoDB banco = new AcessoDB();
        
        String sql = "UPDATE Serie SET ";
        sql = sql + " Descricao = '" + serie.getDescricao() + "',";
        sql = sql + " Turno = '" + serie.getTurno() + "',";
        sql = sql + " Situacao = '" + serie.getSituacao() + "'";
        sql = sql + " WHERE SerieId = " + Integer.toString(serie.getSerie_id());
        
        try
        {
            banco.conect();
            banco.Executar(sql);
            return true;
        } catch (Exception ex) {
            return false;
        } finally {
            banco.disconect();
        }        
    }
    
    public List<Serie> Listar() {
        AcessoDB banco = new AcessoDB();
        
        String sql = "SELECT *";
        sql = sql + "  FROM Serie";
        sql = sql + " ORDER BY Descricao";
        
        try
        {
            banco.conect();
            ResultSet resultSet = banco.query(sql);
            List<Serie> lista = new ArrayList<>();
            
            while (resultSet.next()) {
                Serie serie = new Serie();
                serie.setSerie_id(resultSet.getInt("Serie_Id"));
                serie.setDescricao(resultSet.getString("Descricao"));
                serie.setTurno(resultSet.getString("Turno"));
                serie.setSituacao(resultSet.getString("Situacao"));
                
                lista.add(serie);
            }
            return lista;
        } catch (SQLException ex) {
            return null;
        } finally {
            banco.disconect();
        }      
    }
    
    public Serie ListarId(int serieId) {
        AcessoDB banco = new AcessoDB();
        
        String sql = "SELECT *";
        sql = sql + "  FROM Serie";
        sql = sql + " WHERE Serie_Id = " + Integer.toString(serieId);
        
        try
        {
            banco.conect();
            ResultSet resultSet = banco.query(sql);
            Serie serie = new Serie();
            
            while (resultSet.next()) {                                
                serie.setSerie_id(resultSet.getInt("Serie_Id"));
                serie.setDescricao(resultSet.getString("Descricao"));
                serie.setTurno(resultSet.getString("Turno"));
                serie.setSituacao(resultSet.getString("Situacao"));                                
            }
            return serie;
        } catch (SQLException ex) {
            return null;
        } finally {
            banco.disconect();
        }      
    }
    
    public boolean Excluir(int serieId) {
        AcessoDB banco = new AcessoDB();
        
        String sql = "DELETE FROM Serie ";
        sql = sql + " WHERE Serie_Id = " + Integer.toString(serieId);
        
        try
        {
            banco.conect();
            banco.Executar(sql);
            return true;
        } catch (Exception ex) {
            return false;
        } finally {
            banco.disconect();
        }        
    }
}
