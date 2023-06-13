
package br.com.garage.DAO;

import br.com.garage.model.Pessoa;
import br.com.garage.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class PessoaDAOImpl {
    private Connection conn;

    public PessoaDAOImpl() throws Exception {
        try {
            this.conn = ConnectionFactory.getConnection();
            System.out.println("Conectado com sucesso!");
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
    public Integer cadastrar(Object object) throws ParseException, SQLException {

        PreparedStatement stmt = null;
        Pessoa pessoa = (Pessoa) object;
        ResultSet rs = null;
        Integer idPessoa = null;

        String sql = "insert into pessoa(nomepessoa, cpfcnpjpessoa, rgiepessoa, datanascimentopessoa) VALUES (?, ?, ?, ?) returning idpessoa";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, pessoa.getNomePessoa());
            stmt.setString(2, pessoa.getCpfCnpjPessoa());
            stmt.setString(3, pessoa.getRgIePessoa());
            stmt.setDate(4, new java.sql.Date(new SimpleDateFormat("yyyy-mm-dd").parse(pessoa.getDataNascimento()).getTime()));
            rs = stmt.executeQuery();
            while (rs.next()) {
                idPessoa = rs.getInt("idpessoa");
            }
        } catch (SQLException ex) {
            System.out.println("Problemas na DAO ao cadastrar Pessoa! Erro: " + ex.getMessage());
        }finally
        {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar parâmetros de conexão! Erro " + ex.getMessage());
            }
        }
        return idPessoa;

    }
 
    public Boolean excluir(int idObject){
        PreparedStatement stmt = null;
        String sql = "delete from pessoa where idpessoa = ?; ";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            stmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("Problemas na DAO ao excluir pessoa! Erro: " + ex.getMessage());
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar os parÂmetros de conexão! Erro: " + ex.getMessage());
            }
        }
    }
    
     public boolean alterar(Pessoa pessoa) throws ParseException{
     
       PreparedStatement stmt =null;
       Integer idPessoa = null;
       String sql = "update pessoa set nomepessoa = ?, cpfcnpjpessoa = ?, rgiepessoa= ?, datanascimentopessoa = ? where idpessoa = ?;";
       
       try{
           stmt = conn.prepareStatement(sql);
            stmt.setString(1, pessoa.getNomePessoa());
            stmt.setString(2, pessoa.getCpfCnpjPessoa());
            stmt.setString(3, pessoa.getRgIePessoa());
            stmt.setDate(4, new java.sql.Date(new SimpleDateFormat("yyyy-mm-dd").parse(pessoa.getDataNascimento()).getTime()));
          stmt.setInt(5, pessoa.getIdPessoa());
           stmt.executeUpdate();
                                
           return true;
       }catch(Exception ex){
           System.out.println("Problemas na DAO ao alterar pessoa! Erro: " + ex.getMessage());
           return false;
    }finally{
           try{
               ConnectionFactory.closeConnection(conn, stmt);
           }catch(Exception ex){
               System.out.println("Problemas ao fechars os parametros de conexão! Erro: " + ex.getMessage());
    }
       }
     }
}



    
    

