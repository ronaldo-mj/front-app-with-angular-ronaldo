
package br.com.garage.controller.Veiculo;

import br.com.garage.DAO.GenericDAO;
import br.com.garage.DAO.VeiculoDAOImpl;
import br.com.garage.model.Marca;
import br.com.garage.model.Veiculo;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SalvarVeiculo", urlPatterns = {"/SalvarVeiculo"})
public class SalvarVeiculo extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
        String mensagem = null;
        String nomeVeiculo = request.getParameter("nomeveiculo");
        Integer anoFabricacaoVeiculo = Integer.parseInt(request.getParameter("anofabricacaoveiculo"));
        Double valorVeiculo = Double.parseDouble(request.getParameter("valorveiculo"));
        Integer idMarca=Integer.parseInt(request.getParameter("idmarca"));
        
        Veiculo veiculo = new Veiculo();
        veiculo.setNomeVeiculo(nomeVeiculo);
        veiculo.setAnoFabricacaoVeiculo(anoFabricacaoVeiculo);
        veiculo.setValorVeiculo(valorVeiculo);
        veiculo.setMarca(new Marca(idMarca));
        
        try{
            GenericDAO dao = new VeiculoDAOImpl();
            if (request.getParameter("idveiculo").equals("")){
                if(dao.cadastrar(veiculo)){
                    mensagem="Veículo cadastrado com sucesso!";
                } else{
                    mensagem="Problemas ao cadastrar Veículo!";
                }
            } else{
                veiculo.setIdVeiculo(Integer.parseInt(request.getParameter("idveiculo")));
                if(dao.alterar(veiculo)){
                    mensagem="Veículo alterado com sucesso!";
                } else{
                    mensagem="Problemas ao alterar Veículo!";
                }
            }
            request.setAttribute("mensagem",mensagem);
            request.getRequestDispatcher("Dashboard/veiculo/salvar.jsp").forward(request, response);
        } catch(Exception ex){
            System.out.println("Problemas ao cadastrar Veículo! Erro: "+ex.getMessage());
            ex.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
