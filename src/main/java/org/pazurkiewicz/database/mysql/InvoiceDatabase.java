package org.pazurkiewicz.database.mysql;

import org.pazurkiewicz.Invoice;
import org.pazurkiewicz.InvoiceBuilder;
import org.pazurkiewicz.InvoiceElement;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InvoiceDatabase extends Database {
    private static InvoiceBuilder invoiceBuilder;

    public InvoiceDatabase() {
        super();
        invoiceBuilder = new InvoiceBuilder();
    }

    public Invoice getInvoiceById(int id) throws SQLException {

        ResultSet rs = statement.executeQuery("SELECT *\n" +
                "FROM invoice\n" +
                "INNER JOIN invoice_elements ie on invoice.invoice_id = ie.invoice_id\n" +
                "INNER JOIN client c on invoice.client_id = c.id\n" +
                "WHERE ie.invoice_id ='" + id+"'");
        return resultToInvoice(rs);
    }

    private Invoice resultToInvoice(ResultSet rs) throws SQLException {
        while (rs.next()) {
            invoiceBuilder.addElement(InvoiceElementDatabase.resultToInvoiceElement(rs));
        }
        rs.beforeFirst();
        rs.next();
        return invoiceBuilder.createNewInvoice(ClientDatabase.resultToClient(rs), rs.getDate("date"), rs.getInt("invoice_id"));
    }

    public void addInvoice(Invoice invoice) throws SQLException {
        preparedStatement = connection.prepareStatement("INSERT INTO invoice VALUES (invoice_id,?,?)");
        preparedStatement.setInt(1, ClientDatabase.idClient(invoice.getClientNIP()));
        preparedStatement.setDate(2, new Date(invoice.getDate().getTime()));
        preparedStatement.executeUpdate();
        int id = lastInvoiceId();
        for (InvoiceElement element :
                invoice.getElements()) {
            InvoiceElementDatabase.addInvoiceElement(element, id);
        }
    }

    private static int lastInvoiceId() throws SQLException {
        ResultSet rs = statement.executeQuery("SELECT MAX(invoice_id) FROM invoice");
        rs.next();
        return rs.getInt(1);
    }

    public void deleteInvoice(int invoiceId) throws SQLException {
        statement.executeUpdate("DELETE invoice,ie\n" +
                "FROM invoice\n" +
                "LEFT JOIN invoice_elements ie on invoice.invoice_id = ie.invoice_id\n" +
                "WHERE invoice.invoice_id ='" + invoiceId+"'");
    }

    public ArrayList<Integer> getAllClientInvoiceName(String name) throws SQLException {
        ArrayList<Integer> invoices = new ArrayList<>();
        ResultSet rs = statement.executeQuery("SELECT invoice_id\n" +
                "FROM invoice\n" +
                "INNER JOIN client c on invoice.client_id = c.id\n" +
                "WHERE name = '" + name + "'");
        while (rs.next())
            invoices.add(rs.getInt(1));
        return invoices;
    }
}
