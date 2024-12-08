package br.com.santander.nearagencyapi.infrastructure.adapters.restclient;

import br.com.santander.nearagencyapi.domain.Address;
import br.com.santander.nearagencyapi.domain.exception.ViaCepGetAddressException;
import br.com.santander.nearagencyapi.domain.gateway.ZipCodeClientGateway;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ZipCodeClientImpl implements ZipCodeClientGateway {

    private final RestClient restClient;

    public ZipCodeClientImpl(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public Address getAddress(String zipcode) {

        try {
            return restClient.get()
                    .uri("ws/{zipcode}/json/", zipcode)
                    .retrieve()
                    .body(Address.class);
        } catch (Exception e) {
            throw new ViaCepGetAddressException("Error fetching address for zipcode: " + zipcode);
        }
    }
}