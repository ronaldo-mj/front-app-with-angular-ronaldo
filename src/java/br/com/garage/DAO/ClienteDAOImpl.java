
package br.com.garage.DAO;

import br.com.garage.model.Cliente;
import br.com.garage.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class ClienteDAOImpl {
    private Connection conn;
    
    public ClienteDAOImpl() throws Exception {
        try {
            this.conn = ConnectionFactory.getConnection();
            System.out.println("Conectado com Sucesso");
        }catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
    
    public Boolean cadastrar(Object object){
        Cliente cliente = (Cliente) object;
        PreparedStatement stmt = null;
        String sql = "insert into cliente(email_cliente, senha_cliente, obs_cliente, idpessoa) VALUES (?, ? , ?, ?);";
        
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cliente.getEmailCliente());
            stmt.setString(2, cliente.getSenhaCliente());
            stmt.setString(3, cliente.getObsCliente());
            
            try{
                PessoaDAOImpl daoPessoa = new PessoaDAOImpl();
                stmt.setInt(4, daoPessoa.cadastrar(cliente));
            }catch (Exception ex) {
                System.out.println("Problemas ao cadastrar pessoa em: " + ex.getMessage());
            }
            stmt.execute();
            return true;
        }catch (SQLException ex) {
            System.out.println("Problemas na DAO do cliente ao cadastrar. Erro: " + ex.getMessage());
            return false;
        }finally{
            try{
                ConnectionFactory.closeConnection(conn, stmt);
            }catch (Exception ex) {
                System.out.println("Problemas ao fechar parametros de conexão! Erro: " + ex.getMessage());
            }
        }
    }

    public boolean alterar(Object object) throws Exception {
        Cliente cliente = (Cliente) object;
        PreparedStatement stmt = null;
        
        String sql = "update cliente set email_cliente = ?, senha_cliente = ?, obs_cliente = ? where idpessoa = ?";
        
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cliente.getEmailCliente());
            stmt.setString(2, cliente.getSenhaCliente());
            stmt.setString(3, cliente.getObsCliente());
            stmt.setInt(4, cliente.getIdPessoa());
            
            if(new PessoaDAOImpl().alterar(cliente)){
                stmt.executeUpdate();
                return true;
            }else{
                return false;
            }
        }catch (SQLException ex){
            System.out.println("Problemas na DAO ao alterar cliente. Erro: " + ex.getMessage());
            return false;
        }finally{
            try{
                ConnectionFactory.closeConnection(conn, stmt);
            }catch (Exception ex) {
                System.out.println("Problemas ao fechar parametros de conexão! Erro: " + ex.getMessage());
            }
        }
    }
    
    public List<Object> listar(){
        List<Object> clientes = new ArrayList<Object>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        String sql = "select p.*, c.* from pessoa p, cliente c where p.idpessoa = c.idpessoa";
        
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Cliente cliente = new Cliente ();
                cliente.setIdPessoa(rs.getInt("idpessoa"));
                cliente.setNomePessoa(rs.getString("nomepessoa"));
                cliente.setCpfCnpjPessoa(rs.getString("cpfcnpjpessoa"));
                cliente.setRgIePessoa(rs.getString("rgiepessoa"));
                cliente.setDataNascimento(new SimpleDateFormat("yyyy-mm-dd").format(rs.getDate("datanascimentopessoa")));
                cliente.setEmailCliente(rs.getString("email_cliente"));
                 cliente.setSenhaCliente(rs.getString("senha_cliente"));
                  cliente.setObsCliente(rs.getString("obs_cliente"));
                  
                  clientes.add(cliente);
            }
            }catch (SQLException ex){
            System.out.println("Problemas na DAO ao listar cliente. Erro: " + ex.getMessage());
             }finally{
            try{
                ConnectionFactory.closeConnection(conn, stmt,rs);
            }catch (Exception ex) {
                System.out.println("Problemas ao fechar parametros de conexão! Erro: " + ex.getMessage());
            }
        }
        return clientes;
    }
            
   public Boolean excluir(int idObject) throws Exception {
        PreparedStatement stmt = null;

        String sql = "delete from cliente where idpessoa = ?;";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Problemas na DAO ao excluir cliente! Erro: " + ex.getMessage());
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (SQLException ex) {
                System.out.println("Problemas ao fechar parâmetros de conexão! Erro " + ex.getMessage());
            }
        }
    }

    public Object carregar(int idObject) throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente cliente = null;
        String sql = "select p.*, c.* from pessoa p, cliente c where p.idpessoa = c.idpessoa and c.idpessoa = ?;";
        try{
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            rs = stmt.executeQuery();
            while(rs.next()){
                cliente = new Cliente();
                
                cliente.setIdPessoa(rs.getInt("idpessoa"));
                cliente.setNomePessoa(rs.getString("nomepessoa"));
                cliente.setCpfCnpjPessoa(rs.getString("cpfcnpjpessoa"));
                cliente.setRgIePessoa(rs.getString("rgiepessoa"));
                cliente.setDataNascimento(rs.getString("datanascimentopessoa"));
                cliente.setEmailCliente(rs.getString("email_cliente"));
                cliente.setSenhaCliente(rs.getString("senha_cliente"));
                cliente.setObsCliente(rs.getString("obs_cliente"));
            }            
        }catch (SQLException ex){
            System.out.println("Problemas ao carregar Cliente! Erro: " + ex.getMessage());
        }finally{
            try{
                ConnectionFactory.closeConnection(conn, stmt, rs);
            }catch (SQLException ex){
                System.out.println("Problemas ao fechar os parâmetros da conexão! Erro: " + ex.getMessage());
            }
        }
        return cliente;
    }
   
    
    
}


