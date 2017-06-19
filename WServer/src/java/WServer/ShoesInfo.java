/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WServer;

/**
 *
 * @author Amruta Deshpande
 */
public class ShoesInfo {
    
    public int ID;
    public String Size;
    public String Stock;
    public Double Price;
    
    public void setIDInfo(int id){
        ID=id;
    }
 
    public void setSizeInfo(String size){
        Size=size;
    }
    
    public void setStockInfo(String stocks){
          Stock=stocks;
    }
    public void setPriceInfo(Double price){
        Price=price;
    }
    
    
}
