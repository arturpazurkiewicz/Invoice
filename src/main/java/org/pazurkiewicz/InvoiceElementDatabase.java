package org.pazurkiewicz;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InvoiceElementDatabase extends Database {
    InvoiceElement getInvoiceElement(int invoice_id, String item) throws SQLException {
        ResultSet rs = statement.executeQuery("SELECT *\n" +
                "FROM invoice_elements\n" +
                "WHERE invoice_id = " + invoice_id + "\n" +
                "AND item = '" + item + "'");
        return resultToInvoiceElement(rs);
    }
    void deleteInvoiceElement(int invoice_id, String item) throws SQLException{
        statement.executeUpdate("DELETE invoice_elements\n" +
                "FROM invoice_elements\n" +
                "WHERE invoice_id = " + invoice_id + "\n" +
                "AND item = '" + item + "'");
    }

    static InvoiceElement resultToInvoiceElement(ResultSet rs) throws SQLException{
        return new InvoiceElement(new Item(rs.getString("item"), rs.getFloat("netAmount"), TaxManager.stringToTax(rs.getString("tax"))), rs.getFloat("quantity"));
    }
}
