/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.conversorproyecto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jonathan Puentes
 */
public class ConversorPrincipal {

    public static void main(String[] args) throws IOException, InterruptedException {
        // Se crea una instancia de la clase GestorBusqueda
        GestorBusqueda gestor = new GestorBusqueda();
        // Se realiza una búsqueda de la divisa AED para obtener las divisas disponibles
        DivisaOmdb divisa = gestor.realizarBusqueda("AED");
        List<String> divisas = new ArrayList<>(divisa.conversion_rates().keySet());
        // Se crea una lista para almacenar el historico de conversiones no es
        // persistente
        List<String> historico = new ArrayList<>();
        System.out.println("Bienvenido al conversor de divisas");
        // Se crea un ciclo para que se realicen varias conversiones
        while (true) {
            System.out.println("¿Qué operación deseas realizar?");
            System.out.println("1. Conversión de divisas");
            System.out.println("2. Consultar histórico de conversiones");
            System.out.println("3. Salir");
            String opcion = System.console().readLine();
            // Se crea un switch para realizar la operación seleccionada
            switch (opcion) {
                case "1":
                    // Se imprimen las divisas disponibles
                    imprimirDivisas(divisas);
                    // Se realiza la conversión de divisas
                    realizarConversion(gestor, divisa, historico);
                    break;
                case "2":
                    // Se imprime el historico de conversiones
                    imprimirHistorico(historico);
                    break;
                case "3":
                    // Salida del programa
                    System.out.println("Hasta luego");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
    }

    // Método para imprimir el historico de conversiones
    public static void imprimirHistorico(List<String> historico) {
        System.out.println("Historico de conversiones: ");
        for (String conversion : historico) {
            System.out.println(conversion);
        }
    }

    // Método para imprimir las divisas disponibles
    public static void imprimirDivisas(List<String> divisas) {
        System.out.println("Las divisas disponibles son: ");
        for (String div : divisas) {
            System.out.print("|" + div);
        }
        System.out.print("|");
    }

    // Método para realizar la conversión de divisas
    public static void realizarConversion(GestorBusqueda gestor, DivisaOmdb divisa, List<String> historico)
            throws IOException, InterruptedException {
        System.out.println("\nIntroduce la divisa de origen: ");
        String divisaOrigen = System.console().readLine();
        // Se realiza una búsqueda de la divisa de origen
        divisa = gestor.realizarBusqueda(divisaOrigen.toUpperCase());
        // Se verifica si la divisa de origen es válida y se realiza la conversión
        if (divisa.conversion_rates() != null) {
            try {
                System.out.println("Introduce la divisa de destino: ");
                String divisaDestino = System.console().readLine();
                double tasa = divisa.conversion_rates().get(divisaDestino.toUpperCase());
                System.out.println("Introduce la cantidad a convertir: ");
                double cantidad = Double.parseDouble(System.console().readLine());
                double resultado = cantidad * tasa;
                System.out.println("El resultado de la conversión es: " + resultado);
                historico.add(divisaOrigen + " a " + divisaDestino + ": " + cantidad + " = " + resultado);
            } catch (NumberFormatException e) {
                System.out.println("Se ingresó un valor a convertir no válido.");
            } catch (NullPointerException e) {
                System.out.println("La divisa de destino ingresada no es válida.");
            } catch (Exception e) {
                System.out.println("Error inesperado en la conversión");
            }
        } else {
            System.out.println("La divisa ingresada no es válida.");
        }
    }
}
