package org.example;

public class Lanzador {
    public static void mostrarMain() {
        Cuenta cuenta = new Cuenta(10000);

        int numDepositos = 400;
        int numRetiros = 400;

        Thread[] hilos = new Thread[numDepositos + numRetiros];

        for (int i = 0; i < numDepositos; i++) {
            hilos[i] = new Thread(new HiloCliente(cuenta, 100, true));
        }

        for (int i = numDepositos; i < numDepositos + numRetiros; i++) {
            hilos[i] = new Thread(new HiloCliente(cuenta, 100, false));
        }

        for (Thread hilo : hilos) {
            hilo.start();
        }

        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        double saldoFinal = cuenta.getSaldo();
        if (saldoFinal == 10000) {
            System.out.println("La simulación se completó correctamente. Saldo final: " + saldoFinal);
        } else {
            System.out.println("La simulación tiene un saldo final incorrecto: " + saldoFinal);
        }
    }
}
