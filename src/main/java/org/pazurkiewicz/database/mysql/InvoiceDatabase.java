package org.pazurkiewicz.database.mysql;

import org.pazurkiewicz.Invoice;
import org.pazurkiewicz.InvoiceBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;

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
                "WHERE ie.invoice_id =" + id);
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
}
