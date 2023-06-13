
package br.com.garage.controller.Veiculo;

import br.com.garage.DAO.GenericDAO;
import br.com.garage.DAO.MarcaDAOImpl;
import br.com.garage.DAO.VeiculoDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CarregarVeiculo", urlPatterns = {"/CarregarVeiculo"})
public class CarregarVeiculo extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idVeiculo = Integer.parseInt(request.getParameter("idveiculo"));
        try{
                GenericDAO daoMarca = new MarcaDAOImpl();
                GenericDAO dao = new VeiculoDAOImpl();
                request.setAttribute("marcas", daoMarca.listar());
                request.setAttribute("veiculo", dao.carregar(idVeiculo));
                request.getRequestDispatcher("Dashboard/veiculo/salvar.jsp").forward(request, response);
         } catch(Exception ex){
                System.out.println("Problemas ao carregar Veículo! Erro: "+ex.getMessage());
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
