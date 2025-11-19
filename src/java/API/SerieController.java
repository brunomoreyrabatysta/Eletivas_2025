package API;

import Entity.Serie;
import Service.SerieService;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@WebServlet(name = "SerieController", urlPatterns = {"/Serie"})
public class SerieController extends HttpServlet {

    private JSONObject getJSONBody(BufferedReader reader) throws IOException{
        StringBuilder buffer = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        return new JSONObject(buffer.toString());
    }
    
    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        JSONObject retorno = new JSONObject();
        
        try
        {
            SerieService service = new SerieService();
            List<Serie> lista = service.Listar();
            JSONArray arr = new JSONArray();
            for(Serie s: lista) {
                JSONObject o = new JSONObject();
                o.put("SerieId", s.getSerie_id());
                o.put("Descricao", s.getDescricao());
                o.put("Situacao", s.getSituacao());
                o.put("Turno", s.getTurno());
                arr.put(o);
            }                        
            retorno.put("Dados", arr);
            retorno.put("Mensagem", "Sucesso.");
            response.getWriter().print(retorno.toString());   
        } catch(Exception ex) {
            retorno.put("Dados", "");
            retorno.put("Mensagem", "Problema - " + ex.getMessage());
            response.setStatus(500);
            response.getWriter().print(retorno.toString());
        }        
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        SerieService serieService = new SerieService();
        
        try {
            //Reading body
            JSONObject body = getJSONBody(request.getReader());
            
            Serie serie = new Serie();
        
            serie.setDescricao(body.getString("Descricao"));
            serie.setSituacao(body.getString("Sitaucao"));
            serie.setTurno(body.getString("Turno"));
        
            //serieService.Incluir(serie);
        
            //Returning tasks            
            //response.getWriter().print(file.toString());
        }catch(IOException | JSONException ex){
            response.setStatus(500);            
            //response.getWriter().print(file.toString());
        }
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
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        SerieService serieService = new SerieService();
        JSONObject retorno = new JSONObject();
        
        try {
            int serieId = Integer.parseInt(request.getParameter("serieId"));            
        
            serieService.Excluir(serieId);
        
            //Returning tasks
            retorno.put("Dados", "Série excluída com sucesso.");
            retorno.put("Mensagem", "Sucesso.");
            response.getWriter().print(retorno.toString());   
        }catch(NumberFormatException ex){
            retorno.put("Dados", "");
            retorno.put("Mensagem", "Problema - " + ex.getMessage());
            response.setStatus(500);
            response.getWriter().print(retorno.toString());
        }
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
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        SerieService serieService = new SerieService();
        
        try {
            //Reading body
            JSONObject body = getJSONBody(request.getReader());
            
            Serie serie = new Serie();
        
            serie.setSerie_id(body.getInt("Serie_id"));
            serie.setDescricao(body.getString("Descricao"));
            serie.setSituacao(body.getString("Sitaucao"));
            serie.setTurno(body.getString("Turno"));
        
            //serieService.Alterar(serie);
        
            //Returning tasks            
            //response.getWriter().print(file.toString());
        }catch( IOException | JSONException ex){
            response.setStatus(500);            
            //response.getWriter().print(file.toString());
        }
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
