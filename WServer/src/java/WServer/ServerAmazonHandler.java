/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WServer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.w3c.dom.NodeList;
import java.util.HashMap;
import javax.xml.rpc.soap.SOAPFaultException;

/**
 *
 * @author Amruta Deshpande
 */
public class ServerAmazonHandler implements SOAPHandler<SOAPMessageContext> {
    static HashMap<String,String> hmap=new HashMap<String,String>();
    
    public boolean handleMessage(SOAPMessageContext messageContext) {
        
        
        String outProperty=SOAPMessageContext.MESSAGE_OUTBOUND_PROPERTY;
        boolean outgoing= (Boolean)messageContext.get(outProperty);
        SOAPMessage msg=messageContext.getMessage();
        String uniqueID="";
        String processNo="";
        try{
            if(outgoing)
                msg.writeTo(new FileOutputStream("C:/Users/Amruta Deshpande/Handler/responseMessage.txt"));
            else{

            SOAPHeader header=msg.getSOAPHeader();
            Iterator it=header.examineAllHeaderElements();
            while(it.hasNext()){
                SOAPHeaderElement e=(SOAPHeaderElement)it.next();
                NodeList nl=e.getElementsByTagName("wsse:UniqueID");
                for(int i=0;i<nl.getLength();i++){
                    uniqueID+=nl.item(i).getTextContent();
                }
                
                NodeList processlist=e.getElementsByTagName("wsse:ProcessNo");
                for(int i=0;i<processlist.getLength();i++){
                    processNo+=processlist.item(i).getTextContent();
                }
            }
            System.out.println(hmap);
            System.out.println(uniqueID +"   " +processNo +"----"+ !hmap.containsKey(uniqueID) + "----" + hmap.containsKey(uniqueID) + uniqueID.equals(""));
            
            if (processNo.equals("1") && !hmap.containsKey(uniqueID)){
                
                // insert into hashmap
                hmap.put(uniqueID, processNo);
            
            }else if (processNo.equals("1") && hmap.containsKey(uniqueID)){
                
                // insert into hashmap
                hmap.put(uniqueID, processNo);
            
            }
            
            else if (processNo.equals("2") && hmap.containsKey(uniqueID)){
                //pass
                hmap.put(uniqueID, processNo);
            }else if (uniqueID.equals("")){
                
                // Do nothing 
            }
            else{
                
                System.out.println("Cannot proceed");
                throw new SOAPFaultException(null, null, null, null);
            }
            
            
            //FileOutputStream f=new FileOutputStream("C:/Users/Amruta Deshpande/Handler/username.txt");
            //byte data[]=uniqueID.getBytes();
            //f.write(data);
            //msg.writeTo(new FileOutputStream("C:/Users/Amruta Deshpande/Handler/inputMessage.txt"));
                
            } 
        }
        catch(IOException e){
            System.out.println("IO Error!!!!");
            throw new RuntimeException(e);
        }
        
        catch(SOAPException e)
        {
            System.out.println("SOAP IO Error!!!!");
            throw new RuntimeException(e);
        }
       return true; 
    }
    
    public Set<QName> getHeaders() {
        return Collections.EMPTY_SET;
    }
    
    public boolean handleFault(SOAPMessageContext messageContext) {
        return true;
    }
    
    public void close(MessageContext context) {
    }
    
}
