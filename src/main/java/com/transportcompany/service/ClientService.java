package com.transportcompany.service;

import com.transportcompany.model.Client;
import com.transportcompany.repository.ClientRepository;

import java.sql.SQLException;
import java.util.List;

public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService() {
        this.clientRepository = new ClientRepository();
    }

    public void addClient(Client client) throws SQLException {
        if (client.getName() == null || client.getName().isEmpty()) {
            throw new IllegalArgumentException("Client name cannot be null or empty");
        }
        clientRepository.addClient(client);
    }

    public Client getClientById(int id) throws SQLException {
        return clientRepository.getClientById(id);
    }

    public List<Client> getAllClients() throws SQLException {
        return clientRepository.getAllClients();
    }

    public void updateClient(Client client) throws SQLException {
        if (client.getName() == null || client.getName().isEmpty()) {
            throw new IllegalArgumentException("Client name cannot be null or empty");
        }
        clientRepository.updateClient(client);
    }

    public void deleteClient(int clientId) throws SQLException {
        clientRepository.deleteClient(clientId);
    }
}
