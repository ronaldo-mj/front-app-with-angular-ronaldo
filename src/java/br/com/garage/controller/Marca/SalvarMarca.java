
package br.com.garage.controller.Marca;

import br.com.garage.DAO.GenericDAO;
import br.com.garage.DAO.MarcaDAOImpl;
import br.com.garage.model.Marca;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SalvarMarca", urlPatterns = {"/SalvarMarca"})
public class SalvarMarca extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mensagem = null;
        String nomeMarca = request.getParameter("nomemarca");
        String descricaoMarca = request.getParameter("descricaomarca");
        
        Marca marca = new Marca();
        marca.setNomeMarca(nomeMarca);
        marca.setDescricaoMarca(descricaoMarca);
        
        try{
            GenericDAO dao = new MarcaDAOImpl();
            if (request.getParameter("idmarca").equals("")){
                if(dao.cadastrar(marca)){
                    mensagem="Marca cadastrada com sucesso!";
                } else{
                    mensagem="Problemas ao cadastrar Marca!";
                }
            } else{
                marca.setIdMarca(Integer.parseInt(request.getParameter("idmarca")));
                if(dao.alterar(marca)){
                    mensagem="Marca alterada com sucesso!";
                } else{
                    mensagem="Problemas ao alterar Marca!";
                }
            }
            request.setAttribute("mensagem",mensagem);
            request.getRequestDispatcher("/Dashboard/marca/salvar.jsp").forward(request, response);
        } catch(Exception ex){
            System.out.println("Problemas ao cadastrar Marca! Erro: "+ex.getMessage());
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
