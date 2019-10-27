package org.pazurkiewicz.database.mysql;

import org.pazurkiewicz.InvoiceElement;
import org.pazurkiewicz.Item;
import org.pazurkiewicz.TaxManager;

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
    static void deleteInvoiceElement(int invoice_id, String item) throws SQLException{
        statement.executeUpdate("DELETE invoice_elements\n" +
                "FROM invoice_elements\n" +
                "WHERE invoice_id = " + invoice_id + "\n" +
                "AND item = '" + item + "'");
    }

    static InvoiceElement resultToInvoiceElement(ResultSet rs) throws SQLException{
        return new InvoiceElement(new Item(rs.getString("item"), rs.getFloat("netAmount"), TaxManager.stringToTax(rs.getString("tax"))), rs.getFloat("quantity"));
    }

    static void addInvoiceElement(InvoiceElement invoiceElement,int invoiceId) throws SQLException{
        preparedStatement = connection.prepareStatement("INSERT INTO invoice_elements VALUES (?,?,?,?,?)");
        preparedStatement.setInt(1,invoiceId);
        preparedStatement.setString(2,invoiceElement.getItem().getName());
        preparedStatement.setFloat(3,invoiceElement.getQuantity());
        preparedStatement.setFloat(4,invoiceElement.getItem().getNetAmount());
        preparedStatement.setString(5,TaxManager.taxToString(invoiceElement.getItem().getTaxType()));
        preparedStatement.executeUpdate();
    }
}
