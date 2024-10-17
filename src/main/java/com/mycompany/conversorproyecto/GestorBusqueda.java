package com.mycompany.conversorproyecto;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GestorBusqueda {

        public DivisaOmdb realizarBusqueda(String divisa) {
                try {
                        String direccion = "https://v6.exchangerate-api.com/v6/390eb03f9d4289b82a4d528b/latest/"
                                        + divisa;
                        Gson gson = new GsonBuilder()
                                        .setPrettyPrinting()
                                        .create();

                        HttpClient cliente = HttpClient.newHttpClient();
                        HttpRequest request = HttpRequest.newBuilder()
                                        .uri(URI.create(direccion))
                                        .build();
                        HttpResponse<String> response = cliente
                                        .send(request, HttpResponse.BodyHandlers.ofString());
                        return gson.fromJson(response.body(), DivisaOmdb.class);
                } catch (Exception e) {
                        System.out.println("Divisa no encontrada");
                        return null;
                }
        }
}