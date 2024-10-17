/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.conversorproyecto;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JyL
 */
public class ConversorPrincipal {

    public static void main(String[] args) throws IOException, InterruptedException {
        GestorBusqueda gestor = new GestorBusqueda();
        Divisa divisa = gestor.realizarBusqueda("AED");
        List<String> divisas = new ArrayList<>(divisa.conversion_rates().keySet());

        while(true){
            System.out.println("Las divisas disponibles son: ");
            for(String div : divisas){
                System.out.print(div+", ");
            }
            System.out.println("Introduce la divisa de origen: ");
            String divisaOrigen = System.console().readLine();
            divisa = gestor.realizarBusqueda(divisaOrigen);
            System.out.println("Introduce la divisa de destino: ");
            String divisaDestino = System.console().readLine();
            System.out.println("Introduce la cantidad a convertir: ");
            double cantidad = Double.parseDouble(System.console().readLine());
            double resultado = cantidad * divisa.conversion_rates().get(divisaDestino);
            System.out.println("El resultado de la conversión es: " + resultado);
            System.out.println("¿Quieres guardar la conversión? (s/n)");
            String respuesta = System.console().readLine();
            if(respuesta.equals("s")){
                try(FileWriter writer = new FileWriter("conversor.txt", true)){
                    writer.write("De " + divisaOrigen + " a " + divisaDestino + ": " + cantidad + " -> " + resultado + "\n");
                }
            }
            System.out.println("¿Quieres hacer otra conversión? (s/n)");
            respuesta = System.console().readLine();            
        }
    }
}
