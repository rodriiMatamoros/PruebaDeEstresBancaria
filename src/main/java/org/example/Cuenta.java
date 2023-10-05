package org.example;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cuenta {
    private double saldo;
    private Lock lock;

    public Cuenta(double saldoInicial) {
        saldo = saldoInicial;
        lock = new ReentrantLock();
    }

    public void depositar(double cantidad) {
        lock.lock();
        try {
            saldo += cantidad;
        } finally {
            lock.unlock();
        }
    }

    public void retirar(double cantidad) {
        lock.lock();
        try {
            saldo -= cantidad;
        } finally {
            lock.unlock();
        }
    }

    public double getSaldo() {
        return saldo;
    }
}
