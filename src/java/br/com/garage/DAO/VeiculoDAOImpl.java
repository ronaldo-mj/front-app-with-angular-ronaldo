
package br.com.garage.DAO;

import br.com.garage.model.Marca;
import br.com.garage.model.Veiculo;
import br.com.garage.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAOImpl implements GenericDAO{
    private Connection conn;
    
    public VeiculoDAOImpl() throws Exception{
        try{
            this.conn = ConnectionFactory.getConnection();
            System.out.println("Conectado sucesso!");
        } catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public Boolean cadastrar(Object object) {
        Veiculo veiculo = (Veiculo) object;
        PreparedStatement stmt = null;
        String sql = "INSERT INTO veiculo (nomeveiculo, anofabricacaoveiculo, valorveiculo, idmarca) VALUES(?, ?, ?, ?);";     
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, veiculo.getNomeVeiculo());
            stmt.setInt(2, veiculo.getAnoFabricacaoVeiculo());
            stmt.setDouble(3, veiculo.getValorVeiculo());
            stmt.setInt(4, veiculo.getMarca().getIdMarca());
            stmt.execute();
            
            return true;
        } catch (SQLException ex){
            System.out.println("Problemas ao cadastrar Veículo! Erro: " + ex.getMessage());
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
        String sql = "SELECT v.*, m.nomemarca FROM veiculo v, marca m WHERE v.idmarca = m.idmarca;";
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Veiculo veiculo = new Veiculo();
                veiculo.setIdVeiculo(rs.getInt("idveiculo"));
                veiculo.setNomeVeiculo(rs.getString("nomeveiculo"));
                veiculo.setAnoFabricacaoVeiculo(rs.getInt("anofabricacaoveiculo"));
                veiculo.setValorVeiculo(rs.getDouble("valorveiculo"));
                veiculo.setMarca(new Marca(rs.getInt("idmarca"), rs.getString("nomemarca")));
                resultado.add(veiculo);
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao listar Veiculo! Erro: " + ex.getMessage());
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
        String sql = "DELETE FROM veiculo WHERE idveiculo = ?;";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Problemas ao excluir Veículo! Erro: " + ex.getMessage());
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
        Veiculo veiculo = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql="SELECT * FROM veiculo WHERE idveiculo = ?;";
        try{
            stmt=conn.prepareStatement(sql);
            stmt.setInt(1,idObject);
            rs=stmt.executeQuery();
            if(rs.next()){
                veiculo = new Veiculo();
                veiculo.setIdVeiculo(rs.getInt("idveiculo"));
                veiculo.setNomeVeiculo(rs.getString("nomeveiculo"));
                veiculo.setAnoFabricacaoVeiculo(rs.getInt("anofabricacaoveiculo"));
                veiculo.setValorVeiculo(rs.getDouble("valorveiculo"));
            }
        } catch(SQLException ex) {
            System.out.println("Problemas ao carregar Veículo! Erro: "+ex.getMessage());
            ex.printStackTrace();
        } finally{
            try{
                ConnectionFactory.closeConnection(conn,stmt,rs);
            }catch(Exception ex){
                System.out.println("Problemas ao fechar conexão! Erro: "+ex.getMessage());
                ex.printStackTrace();
            }
        }  
        return veiculo;
    }

    @Override
    public Boolean alterar(Object object) {
        Veiculo veiculo = (Veiculo) object;
        PreparedStatement stmt = null;
        String sql="UPDATE veiculo SET nomeveiculo = ?, anofabricacaoveiculo = ?, valorveiculo = ? WHERE idveiculo = ?;";
        try{
            stmt=conn.prepareStatement(sql);
            stmt.setString(1,veiculo.getNomeVeiculo());
            stmt.setInt(2,veiculo.getAnoFabricacaoVeiculo());
            stmt.setDouble(3,veiculo.getValorVeiculo());
            stmt.setInt(4,veiculo.getIdVeiculo());
            stmt.executeUpdate();
            return true;
        }catch (SQLException ex){
            System.out.println("Problemas ao alterar Veiculo! Erro: "+ex.getMessage());
            ex.printStackTrace();
            return false;     
        }finally{
            try{
                ConnectionFactory.closeConnection(conn,stmt);
            }catch(Exception ex){
                System.out.println("Problemas ao fechar conexão! Erro: "+ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
    
}