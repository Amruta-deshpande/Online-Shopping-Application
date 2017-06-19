/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WServer;


import java.sql.DriverManager;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javax.jws.HandlerChain;

/**
 *
 * @author Amruta Deshpande
 */
@WebService(serviceName = "AmazonWS")

@HandlerChain(file = "AmazonWS_handler.xml")
public class AmazonWS {
    /**
     * Web service operation
     */
    @WebMethod(operationName = "getBrandInfo")
    public LinkedList getBrandInfo() {
        BrandInfo binfo=new BrandInfo();
    
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost/sys","root","root2207");
            Statement st=con.createStatement();
            
            String query=("select * from AmazonShoes");
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                String brand=rs.getString(2);
                binfo.setBrandName(brand);
                
            }
            
        }
        catch(Exception e){
            
        }
    return binfo.getBrandName();
    }



    /**
     * Web service operation
     */
    @WebMethod(operationName = "getShoesInfo")
    public List<ShoesInfo> getShoesInfo(@WebParam(name = "brand") String brand) {
         List<ShoesInfo> infoList = new ArrayList<ShoesInfo>();
         
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost/sys","root","root2207");
            Statement st=con.createStatement();
            
            String query=("select * from AmazonShoes where BrandName='"+brand+"'");
            
            ResultSet rs3=st.executeQuery(query);
            while(rs3.next()){
                ShoesInfo shinfo=new ShoesInfo();
                
                int id=rs3.getInt(1);
                shinfo.setIDInfo(id);
                String size=rs3.getString(3);
                shinfo.setSizeInfo(size);
                
                String instock=rs3.getString(4);
                shinfo.setStockInfo(instock);
                Double price=rs3.getDouble(5);
                shinfo.setPriceInfo(price);
                
                infoList.add(shinfo);  
            }
            
        }
        catch(Exception e){
            
        }
    return infoList;   
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getIdInfo")
    public Integer getIdInfo(@WebParam(name = "ID") int ID) {
       int flag=0;
       //IdInfo IDinfo=new IdInfo();
    
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost/sys","root","root2207");
            Statement st=con.createStatement();
            
            String query=("select * from AmazonShoes where ID="+ID);
            ResultSet rs2=st.executeQuery(query);
            
            if (!rs2.next() ) {
                System.out.println("No ID exist");
                
            } else {
                flag=1;    
            }
        }
            catch(Exception e){

                }
        if(flag==0)
            return 0;
        else
            return 1;
   
    
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getStockInfo")
    public String getStockInfo(@WebParam(name = "id") int id) {
        int flag=0;
        String instock="";
       
        //StockInfo stinfo=new StockInfo();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost/sys","root","root2207");
            Statement st=con.createStatement();
            
            String query=("select InStock from AmazonShoes where ID="+id);
            ResultSet rs2=st.executeQuery(query);
            while(rs2.next()){
                instock=rs2.getString("InStock");
                return instock;
            }
        }
            catch(Exception e){

            }     
        return instock;

    }

    
   
}
