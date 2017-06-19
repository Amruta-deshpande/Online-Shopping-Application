/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AmazonClient;

import java.util.Collections;
import java.util.Set;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

/**
 *
 * @author Amruta Deshpande
 */
public class ClientAmazonHandler implements SOAPHandler<SOAPMessageContext> {
    
    public boolean handleMessage(SOAPMessageContext messageContext) {

          Boolean outboundProperty=(Boolean)messageContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
          if(outboundProperty.booleanValue())
          {
              SOAPMessage message=messageContext.getMessage();
              try{
                  SOAPEnvelope envelope=messageContext.getMessage().getSOAPPart().getEnvelope();
                  SOAPHeader header=envelope.getHeader();
                    if(header==null){
                      header=envelope.addHeader();
                    }
                    String UID=(String)messageContext.get("UniqueID");
                    //System.out.println(UID+"......");
                    SOAPElement uniqueIDToken=header.addChildElement("UniqueIDToken","wsse","http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
                    SOAPElement uniqueID=uniqueIDToken.addChildElement("UniqueID","wsse");
                    uniqueID.addTextNode(UID);
                    
                    int processNo=(int)messageContext.get("ProcessNo");
                    SOAPElement processToken=header.addChildElement("ProcessToken","wsse","http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
                    SOAPElement process=processToken.addChildElement("ProcessNo","wsse");
                    process.addTextNode("" + processNo);
                    
                }
                catch(Exception e){
                        e.printStackTrace();
                } 
          } 
          else{
                try{
                    SOAPMessage message=messageContext.getMessage();
                    //message.writeTo(System.out);
                    //System.out.println("");
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
              
            }
          return outboundProperty;
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
