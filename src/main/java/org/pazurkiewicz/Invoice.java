package org.pazurkiewicz;

import java.util.ArrayList;
import java.util.Date;

public class Invoice {
    private int invoiceId;
    private ArrayList <InvoiceElement> elements;
    private String clientName;
    private String clientNIP;
    private String clientPostCode;
    private String clientStreetAndNumber;
    private String clientCity;
    private Float fullNet;
    private Float fullGross;
    private Date date;

    public Invoice(int invoiceId, ArrayList<InvoiceElement> elements, String clientName, String clientNIP, String clientPostCode, String clientStreetAndNumber, String clientCity, float fullNet, float fullGross, Date date) {
        this.invoiceId = invoiceId;
        this.elements = elements;
        this.clientName = clientName;
        this.clientNIP = clientNIP;
        this.clientPostCode = clientPostCode;
        this.clientStreetAndNumber = clientStreetAndNumber;
        this.clientCity = clientCity;
        this.fullNet = fullNet;
        this.fullGross = fullGross;
        this.date = date;
    }
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder("Faktura VAT Nr " + invoiceId + ",z dnia "+date+"\n" +
                "Sprzedawca: " + User.name + "\n" +
                "       " + User.streetAndNumber + "\n" +
                "       " + User.postcode + " " + User.city + "\n" +
                "NIP    " + User.NIP + "\n" +
                "Nabywca\n" +
                clientName + "\n" +
                clientStreetAndNumber + ", " + clientPostCode + " " + clientCity + "\n" +
                "NIP    " + clientNIP + "\n" +
                "Nazwa towaru lub usługi\n");
        for (InvoiceElement element:
             elements) {
            s.append(element.getItem().getName());
            s.append(" Ilość ");
            s.append(element.getQuantity());
            s.append(" netto ");
            s.append(String.format ("%,.2f",element.getItem().getNetAmount()));
            s.append(" stawka ");
            switch (element.getItem().getTaxType()){
                case o23:
                    s.append("23%");
                    break;
                case o8:
                    s.append("8%");
                    break;
                case o5:
                    s.append("5%");
                    break;
                case o0:
                    s.append("0%");
                    break;
                case zw:
                    s.append("ZW");
            }
            s.append(" kwota ");
            s.append(String.format ("%,.2f",element.taxCalculation()));
            s.append(" Wartość z podatkiem ");
            s.append(String.format ("%,.2f",element.grossCalculation()));
            s.append("\n");
        }
        s.append("\n    Do zapłaty ");
        s.append(String.format ("%,.2f",fullGross));
        return s.toString();
    }
}