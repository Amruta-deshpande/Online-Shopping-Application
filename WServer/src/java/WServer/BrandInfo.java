/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WServer;

import java.util.LinkedList;

/**
 *
 * @author Amruta Deshpande
 */
public class BrandInfo {
    
    LinkedList brandlist=new LinkedList();
    
    public void setBrandName(String brand){
        if(!(brandlist.contains(brand))){
            brandlist.add(brand);
        }    
    }
    
    public LinkedList getBrandName(){
        return brandlist;
    }
    
}
