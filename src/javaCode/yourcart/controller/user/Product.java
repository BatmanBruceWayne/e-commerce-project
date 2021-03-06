
package javaCode.yourcart.controller.user;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javaCode.yourcart.model.ProductModel;


/**
 * handle display product and recomand products
 * @author MotYim
 */
@WebServlet(name = "Product", urlPatterns = {"/Product"})
public class Product extends HttpServlet {




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
        
        int productID = Integer.parseInt(request.getParameter("id"));
        ProductModel productModel = new ProductModel();
        javaCode.yourcart.beans.Product product = productModel.getProduct(productID);
        System.out.println("getCategory: " + product.getCategory());
        
        //no product with this id 
        if(product==null){
            response.sendRedirect("404.jsp");
        }else{
             //assigne it on request
            request.setAttribute("product", product);
            
            ArrayList<javaCode.yourcart.beans.Product> recommeendedItem = productModel.getRecommeendedItem(productID, product.getCategory());
            request.setAttribute("recomed", recommeendedItem);
            
            request.getRequestDispatcher("/product-details.jsp").forward(request, response);
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
