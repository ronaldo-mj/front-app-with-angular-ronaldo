
package br.com.garage.DAO;

import br.com.garage.model.Marca;
import br.com.garage.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MarcaDAOImpl implements GenericDAO{
    private Connection conn;

    public MarcaDAOImpl() throws Exception{
        try{
            this.conn = ConnectionFactory.getConnection();
            System.out.println("Conectado sucesso!");
        } catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
    
    @Override
    public Boolean cadastrar(Object object) {
        Marca marca = (Marca) object;
        PreparedStatement stmt = null;
        String sql = "INSERT INTO marca(nomemarca, descricaomarca) VALUES(?,?);";     
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, marca.getNomeMarca());
            stmt.setString(2, marca.getDescricaoMarca());
            stmt.execute();
            
            return true;
        } catch (SQLException ex){
            System.out.println("Problemas ao cadastrar Marca! Erro: " + ex.getMessage());
            ex.printStackTrace();
            
            return false;
        } finally{
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception ex){
                System.out.println("problemas ao fechar conexão! Erro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM marca;";
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Marca marca = new Marca();
                marca.setIdMarca(rs.getInt("idmarca"));
                marca.setNomeMarca(rs.getString("nomemarca"));
                marca.setDescricaoMarca(rs.getString("descricaomarca"));
                resultado.add(marca);
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao listar Marca! Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar conexão! Erro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return resultado;
    }

    @Override
    public void excluir(int idObject) {
        PreparedStatement stmt = null;
        String sql = "DELETE FROM marca WHERE idmarca = ?;";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Problemas ao excluir Marca! Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar conexão! Erro: " + ex.getMessage());
            }
        }
    }

    @Override
    public Object carregar(int idObject) {
        Marca marca = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql="SELECT * FROM marca WHERE idmarca = ?;";
        try{
            stmt=conn.prepareStatement(sql);
            stmt.setInt(1,idObject);
            rs=stmt.executeQuery();
            if(rs.next()){
                marca = new Marca();
                marca.setIdMarca(rs.getInt("idmarca"));
                marca.setNomeMarca(rs.getString("nomemarca"));
                marca.setDescricaoMarca(rs.getString("descricaomarca"));
            }
        } catch(SQLException ex) {
            System.out.println("Problemas ao carregar Marca! Erro: "+ex.getMessage());
            ex.printStackTrace();
        } finally{
            try{
                ConnectionFactory.closeConnection(conn,stmt,rs);
            }catch(Exception ex){
                System.out.println("Problemas ao fechar conexão! Erro: "+ex.getMessage());
                ex.printStackTrace();
            }
        }  
        return marca;
    }

    @Override
    public Boolean alterar(Object object) {
       Marca marca = (Marca) object;
        PreparedStatement stmt = null;
        String sql="UPDATE marca SET nomemarca = ?, descricaomarca = ? WHERE idmarca = ?;";
        try{
            stmt=conn.prepareStatement(sql);
            stmt.setString(1,marca.getNomeMarca());
            stmt.setString(2,marca.getDescricaoMarca());
            stmt.setInt(3,marca.getIdMarca());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex){
            System.out.println("Problemas ao alterar Marca! Erro: "+ex.getMessage());
            ex.printStackTrace();
            return false;     
        } finally{
            try{
                ConnectionFactory.closeConnection(conn,stmt);
            } catch(Exception ex){
                System.out.println("Problemas ao fechar conexão! Erro: "+ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
    
}
