package org.pazurkiewicz;

import org.pazurkiewicz.database.mysql.ClientDatabase;
import org.pazurkiewicz.database.mysql.InvoiceDatabase;
import org.pazurkiewicz.database.mysql.InvoiceElementDatabase;

import java.sql.SQLException;
import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        Client c1 = new Client(0,"Microsoft","132134","Zadupie","Śmieciowa 4/5","45-234");
        InvoiceBuilder invoiceBuilder = new InvoiceBuilder();
        invoiceBuilder.addElement(new Item("VR",4000f, TaxManager.taxType.o23),1);
        invoiceBuilder.addElement(new Item("jajka",4f, TaxManager.taxType.o8),1);
        invoiceBuilder.addElement(new Item("książki",45.34f, TaxManager.taxType.o5),3);
        invoiceBuilder.addElement(new Item("jałmóżna",11.45f, TaxManager.taxType.zw),1);
        Invoice invoice = invoiceBuilder.createNewInvoice(c1, Calendar.getInstance().getTime(),0);
        try {
            new ClientDatabase().addClient(c1);
            //invoiceDatabase.addInvoice(invoice);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
