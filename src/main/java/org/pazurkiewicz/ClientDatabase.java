package org.pazurkiewicz;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientDatabase extends Database {
    ClientDatabase() {
        super();
    }

    void addClient(Client client) throws SQLException {
        preparedStatement = connection.prepareStatement("INSERT INTO client VALUES (id,?,?,?,?,?)");
        preparedStatement.setString(1, client.name);
        preparedStatement.setString(2, client.NIP);
        preparedStatement.setString(3, client.city);
        preparedStatement.setString(4, client.streetAndNumber);
        preparedStatement.setString(5, client.postcode);
        preparedStatement.executeUpdate();
    }

    Client getClientByID(int id) throws SQLException {
        ResultSet rs = statement.executeQuery("select * from client WHERE id =" + id);
        return resultToClient(rs);
    }

    ArrayList<Client> getAllClients() throws SQLException {
        ArrayList<Client> clients = new ArrayList<>();
        ResultSet rs = statement.executeQuery("select * from client");
        while (rs.next())
            clients.add(resultToClient(rs));
        return clients;
    }

    void deleteClientByID(int id) throws SQLException {
        statement.executeUpdate("DELETE invoice,ie,c\n" +
                "FROM invoice\n" +
                "INNER JOIN invoice_elements ie on invoice.invoice_id = ie.invoice_id\n" +
                "INNER JOIN client c on invoice.client_id = c.id\n" +
                "WHERE client_id =" + id);
    }

    static Client resultToClient(ResultSet rs) throws SQLException {
        return new Client(rs.getInt("id"), rs.getString("name"), rs.getString("NIP"), rs.getString("city"), rs.getString("streetAndNumber"), rs.getString("postcode"));

    }


}
