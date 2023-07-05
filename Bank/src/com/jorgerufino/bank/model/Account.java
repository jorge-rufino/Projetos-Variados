package com.jorgerufino.bank.model;

import com.jorgerufino.bank.Log;

public class Account {

    private static final int MAX_LENGTH = 12;
    private String ag;
    private String cc;
    private String name;
    private double balance;
    private Log logger;

    public Account(String ag, String cc, String name){
        this.ag = ag;
        this.cc = cc;
        setName(name);
        logger = new Log();
    }

    public void setName(String name){
        if(name.length() > MAX_LENGTH){
            this.name = name.substring(0,MAX_LENGTH);
        } else {
            this.name = name;
        }
    }

    public void withDraw(double value){
        if (balance < value){
            logger.out("Saldo insuficiente para saque");
        } else {
            balance -= value;
            logger.out("SAQUE no valor de: R$ " + value + "\nSaldo da conta: " + balance);
        }
    }

    public void deposit (double value){
        this.balance += value;
        logger.out("DEPÓSITO realizado no valor de: R$ " + value);
    }

    @Override
    public String toString() {
        return "Conta nº " + cc + ", da agencia " + ag  +" em nome de " + name +", tem saldo de: R$ " + balance;
    }
}
