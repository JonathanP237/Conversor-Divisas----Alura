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
        GestorBusqueda gestor = new GestorBusqueda();
        DivisaOmdb divisa = gestor.realizarBusqueda("AED");
        List<String> divisas = new ArrayList<>(divisa.conversion_rates().keySet());
        List<String> historico = new ArrayList<>();
        System.out.println("Bienvenido al conversor de divisas");
        while (true) {
            System.out.println("¿Qué operación deseas realizar?");
            System.out.println("1. Conversión de divisas");
            System.out.println("2. Consultar histórico de conversiones");
            System.out.println("3. Salir");
            String opcion = System.console().readLine();
            switch (opcion) {
                case "1":
                    imprimirDivisas(divisas);
                    realizarConversion(historico);
                    break;
                case "2":
                    imprimirHistorico(historico);
                    break;
                case "3":
                    System.out.println("Hasta luego");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
    }

    public static void imprimirHistorico(List<String> historico) {
        System.out.println("Historico de conversiones: ");
        for (String conversion : historico) {
            System.out.println(conversion);
        }
    }

    public static void imprimirDivisas(List<String> divisas) {
        System.out.println("Las divisas disponibles son: ");
        for (String div : divisas) {
            System.out.print("|" + div);
        }
        System.out.print("|");
    }

    public static void realizarConversion(List<String> historico) throws IOException, InterruptedException {
        DivisaOmdb divisa;
        GestorBusqueda gestor = new GestorBusqueda();
        System.out.println("\nIntroduce la divisa de origen: ");
        String divisaOrigen = System.console().readLine();
        divisa = gestor.realizarBusqueda(divisaOrigen.toUpperCase());
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
