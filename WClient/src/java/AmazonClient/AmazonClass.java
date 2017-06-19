/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AmazonClient;

import java.util.Scanner;
import javax.xml.ws.BindingProvider;

/**
 *
 * @author Amruta Deshpande
 */
public class AmazonClass {
    static String uniqueClientID;
    public static void main(String args[]){
        
        System.out.println("Enter the unique Id for the client");
        Scanner scanuniq=new Scanner(System.in);
        uniqueClientID=(String)scanuniq.nextLine();
        
        System.out.println("-----------Shoes Shopping from Amazon-----------");
        //System.out.println("1.Select Brand" +"                "+"2.Select Brand Size"+"          ");
        //System.out.println("Enter the operation number you want to perform");
        int operation =0;
        
        do{
        System.out.println("1.Select Brand" +"                "+"2.Select Brand Size"+"          "+"3.To Exit");
        System.out.println("Enter the operation number you want to perform");
        Scanner sysin=new Scanner(System.in);
        operation=sysin.nextInt();
        switch(operation){
            case 1:
               try{
                   
               java.util.List<java.lang.Object> gtbrand=getBrandInfo();
                int brandlen=gtbrand.size();
                for(int i=0;i<brandlen;i++){
                    System.out.println(gtbrand.get(i));

                } 
                System.out.println("Enter the Brand name for you want to shop for");
                Scanner scan=new Scanner(System.in);
                String brand=scan.nextLine();
                    if(gtbrand.contains(brand)){

                        java.util.List<AmazonClient.ShoesInfo> gtShInfo=getShoesInfo(brand);
                        int listlen=gtShInfo.size();
                        System.out.println("ID"+"   "+"Size"+"         InStock   "+" Price");
                        for(int i=0;i< listlen;i++){
                            System.out.println(gtShInfo.get(i).id+"   "+gtShInfo.get(i).size+"   "+gtShInfo.get(i).stock+"        "+gtShInfo.get(i).price);

                        }
                    }//end of if
                    else{
                        System.out.println("No such brand Exist!!");

                     }//end of else
                    break;
                    }catch(Exception e){

                        System.out.println("Cannot Proceed('Enter valid operation sequence(1->2)')");
                    }
                    
               
            case 2:
                try{
                
                    System.out.println("Choose a id of the product");
                    Scanner scan1=new Scanner(System.in);
                    int prodID=scan1.nextInt();

                    int IDval=getIdInfo(prodID);
                    if(IDval==0){
                        System.out.println("NO product exist");
                    }
                    else{
                        System.out.println("Product Exist");
                        String stock_val=getStockInfo(prodID);
                        if(stock_val=="Sold"){
                            System.out.println("Cannot place a order!Product Sold!!");
                        }
                        else{
                            System.out.println("Product Not Sold!!Can place a order!!");
                        }
                    }
                    break;
                }catch(Exception e){
                    System.out.println("Cannot Procede('Enter valid operation sequence')");
                }
                
                
            case 3:
                break;
            } //switch
            
                
       } while(operation!=3) ;        
        
    }

    private static java.util.List<java.lang.Object> getBrandInfo() {
        AmazonClient.AmazonWS_Service service = new AmazonClient.AmazonWS_Service();
        AmazonClient.AmazonWS port = service.getAmazonWSPort();
        ((BindingProvider)port).getRequestContext().put("UniqueID",uniqueClientID);
        ((BindingProvider)port).getRequestContext().put("ProcessNo",1);
        return port.getBrandInfo();
    }

    private static java.util.List<AmazonClient.ShoesInfo> getShoesInfo(java.lang.String brand) {
        AmazonClient.AmazonWS_Service service = new AmazonClient.AmazonWS_Service();
        AmazonClient.AmazonWS port = service.getAmazonWSPort();
        ((BindingProvider)port).getRequestContext().put("UniqueID",uniqueClientID);
        ((BindingProvider)port).getRequestContext().put("ProcessNo",1);
        return port.getShoesInfo(brand);
    }

    private static Integer getIdInfo(int id) {
        AmazonClient.AmazonWS_Service service = new AmazonClient.AmazonWS_Service();
        AmazonClient.AmazonWS port = service.getAmazonWSPort();
        ((BindingProvider)port).getRequestContext().put("UniqueID",uniqueClientID);
        ((BindingProvider)port).getRequestContext().put("ProcessNo",2);
        return port.getIdInfo(id);
    }

    private static String getStockInfo(int id) {
        AmazonClient.AmazonWS_Service service = new AmazonClient.AmazonWS_Service();
        AmazonClient.AmazonWS port = service.getAmazonWSPort();
        ((BindingProvider)port).getRequestContext().put("UniqueID",uniqueClientID);
        ((BindingProvider)port).getRequestContext().put("ProcessNo",2);
        return port.getStockInfo(id);
    }


    
}
