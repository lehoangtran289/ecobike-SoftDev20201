package com.hust.ebr.serverapi.abstractdata;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public interface IServerApi {
    Client client = ClientBuilder.newClient();
}
