package org.pazurkiewicz;

import org.pazurkiewicz.database.mysql.ClientDatabase;
import org.pazurkiewicz.database.mysql.InvoiceDatabase;

import java.sql.SQLException;
import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        Client c1 = new Client(1,"Microsoft","132134","Zadupie","Śmieciowa 4/5","45-234");
        InvoiceBuilder invoiceBuilder = new InvoiceBuilder();
        invoiceBuilder.addElement(new Item("VR",4000f, TaxManager.taxType.o23),1);
        invoiceBuilder.addElement(new Item("jajka",4f, TaxManager.taxType.o8),1);
        invoiceBuilder.addElement(new Item("książki",45.34f, TaxManager.taxType.o5),3);
        invoiceBuilder.addElement(new Item("jałmóżna",11.45f, TaxManager.taxType.zw),1);
        Invoice invoice = invoiceBuilder.createNewInvoice(c1, Calendar.getInstance().getTime(),10);
        try {
            InvoiceDatabase invoiceDatabase = new InvoiceDatabase();
            System.out.println(invoiceDatabase.getInvoiceById(6));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
