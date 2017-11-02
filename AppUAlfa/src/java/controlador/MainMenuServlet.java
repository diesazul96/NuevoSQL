/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import dao.CarritoDAO;
import dao.ProductoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import vo.ProductoVO;

/**
 *
 * @author Carlos Alberto
 */
@WebServlet(name = "MainMenuServlet", urlPatterns = {"/MainMenuServlet"})
public class MainMenuServlet extends HttpServlet {

    private CarritoDAO carritoDAO;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {

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
        try (PrintWriter out = response.getWriter()) {
            //processRequest(request, response);
            response.setContentType("application/json");

            CarritoDAO Mateo = new CarritoDAO();
            
            HttpSession sesion = request.getSession();
            ArrayList<ProductoVO> CarroSesion = (ArrayList) sesion.getAttribute("carrito");
//            String CarroSesion = (String) sesion.getAttribute("carrito");
//             ArrayList<ProductoVO> Carro = new Gson().fromJson(CarroSesion, ArrayList.class);
            

            String P = "";

            P = request.getParameter("opcion");

            System.out.println("InfoCarro:"+P);
            
            if (P.equals("1")) {

                
                ArrayList<ProductoVO> Arreglo = new ArrayList();

                JSONArray array = new JSONArray();
                

                //Arreglo= Mateo.infoCheckOut();
                
                if (CarroSesion == null) {
                    System.out.println("paila, esta nulo");
                    JSONObject json = new JSONObject();
//                        json.put("ID", "Papitas2");
//                        json.put("nombre", "Papitas");
//                        json.put("cantidad", "2");
//                        json.put("precio", "100");
//                        array.put(json);

                } else if (CarroSesion != null) {
                    ProductoVO productos = new ProductoVO();
                        System.out.println("No esta nulo");
                    for (int i = 0; i < CarroSesion.size(); i++) {
                        System.out.println(CarroSesion.get(i));
                        JSONObject json = new JSONObject();
                        //productos = Carro.get(i);
                        json.put("ID", CarroSesion.get(i).getID());
                        json.put("nombre", CarroSesion.get(i).getNombre());
                        json.put("cantidad", String.valueOf(CarroSesion.get(i).getCantidad()));
                        json.put("precio", String.valueOf(CarroSesion.get(i).getPrecio()));
                        System.out.println("PRUEBA JSON NOMBRE: "+json.get("nombre"));
                        array.put(json);

                    }
                       
                    //Envia Precio y cantidad
//                    String x = Mateo.PrecioTotal(CarroSesion);
//                    JSONObject total = new JSONObject();
//
//                    total.put("Total", x);
//
//                    array.put(total);

                    JSONObject fin = new JSONObject();
                    fin.put("Productos", array);
                    out.print(fin);

                }
            }

            if (P.equals("2")) {
                ArrayList<ProductoVO> Arreglo = new ArrayList();

                //Arreglo = CarroSesion;
                String ID = request.getParameter("idprod");

                Mateo.borrar(ID, CarroSesion);

            }

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            //processRequest(request, response);

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
