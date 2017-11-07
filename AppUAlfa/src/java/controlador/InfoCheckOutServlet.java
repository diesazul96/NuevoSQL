/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.google.gson.Gson;
import dao.CarritoDAO;
import dao.EnviarMail;
import dao.ProductoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;
import vo.ProductoVO;

/**
 *
 * @author Carlos Alberto
 */
@WebServlet(name = "InfoCheckOutServlet", urlPatterns = {"/InfoCheckOutServlet"})
public class InfoCheckOutServlet extends HttpServlet {

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
            throws ServletException, IOException {
        
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
       response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            CarritoDAO carrito = new CarritoDAO();
            ProductoVO productos = new ProductoVO();
            ArrayList<ProductoVO> Arreglo = new ArrayList();
            JSONArray array = new JSONArray();
            JSONObject coment = new JSONObject();
            JSONObject fin = new JSONObject();
            EnviarMail mail = new EnviarMail();
            HttpSession session = request.getSession();
            
            ArrayList<ProductoVO> CarroSesion = (ArrayList) session.getAttribute("carrito");
            // ArrayList<ProductoVO> Carro = new Gson().fromJson(CarroSesion, ArrayList.class);

            Arreglo = CarroSesion;
            //Arreglo =(ArrayList<ProductoVO>) session.getAttribute("carrito");
            
            
            
            String opcion="";

            opcion = request.getParameter("opcion1");
            String comentario = "Comentarios: "+request.getParameter("comment");
            System.out.println("UBICACION: "+request.getParameter("latitud")+" "+request.getParameter("longitud"));
//            String pos = (String) request.getParameter("pos");

            System.out.println(CarroSesion.get(0).getNombre());
            System.out.println("opcion1:"+opcion);
            if (opcion.equals("3")) {
                for (int i = 0; i < Arreglo.size(); i++) {
                    System.out.println("------------------ENVIANDO CORREO--------------------");
                    String correo = "";
                    ArrayList<String> cadena = new ArrayList<>();
                    ArrayList<ProductoVO> prod = new ArrayList();
                    prod.add(Arreglo.get(i));
                    Arreglo.remove(i);

                    for (int j = 0; j < Arreglo.size(); j++) {

                        if (Arreglo.get(j) != null) {
                            if (Arreglo.get(i).getTienda() == Arreglo.get(j).getTienda()) {
                                prod.add(Arreglo.get(j));
                                Arreglo.remove(j);
                            }
                        }
                    }

                    
                    for (int j = 0; j < prod.size(); j++) {
                        String orden = "Producto: " + prod.get(j).getNombre() + "<br>"+"Cantidad: " + Integer.toString(prod.get(j).getCantidad()) + "<br>"+"Precio: " + Integer.toString(prod.get(j).getPrecio())+ "<br>"+ "<br>";
                        //String orden = "" + "Papitas" + "" + "2" + "" + "100";
                        System.out.println("PRODUCTO "+j+": "+orden);
                        cadena.add(orden);
                    }
                    
                    cadena.add(comentario);
                    
                    System.out.println("idtienda:"+prod.get(i).getTienda());
                    correo = mail.CorreoTienda(prod.get(i).getTienda());
                    //correo = mail.CorreoTienda(1);
                    System.out.println("Correo:"+correo);
                    String pedido="";
                    for (int j = 0; j < cadena.size(); j++) {
                        pedido=pedido+cadena.get(j);
                    }
                    
                    System.out.println("PEDIDOOOOO:"+pedido);
                    
                    String map = "<p>"+pedido+"</p><img src='https://maps.googleapis.com/maps/api/staticmap?center="+request.getParameter("latitud")+","+request.getParameter("longitud")+"&zoom=15&size=400x400&maptype=roadmap\n" +
"&markers=color:red%7Clabel:C%7C"+request.getParameter("latitud")+","+request.getParameter("longitud")+"&key=AIzaSyAJOwdex9jqp6DZ-klv-NlBxoAmwaCyKt8'/>";
                    mail.sendCheckOut(correo,map);
                    
                    session.setAttribute("carrito", null);
                    System.out.println("-------------CORREO ENVIADO-------------");
                }
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
