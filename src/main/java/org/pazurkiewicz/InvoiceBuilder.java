package org.pazurkiewicz;

import java.util.ArrayList;
import java.util.Date;

public class InvoiceBuilder {
    private ArrayList <InvoiceElement> elements;
    InvoiceBuilder(){
        elements = new ArrayList<>();
    }

    void addElement(Item item,float quantity){
        elements.add(new InvoiceElement(item,quantity));
    }
    void addElement(InvoiceElement invoiceElement){
        elements.add(invoiceElement);
    }

    Invoice createNewInvoice(Client client,Date date,int id){
        float net=0;
        float gross=0;
        for (InvoiceElement element:
             elements) {
            net+=element.netCalculation();
            gross+=element.grossCalculation();
        }
        return new Invoice(id,elements,client.name,client.NIP,client.postcode,client.streetAndNumber,client.city,net,gross,date);
    }

}