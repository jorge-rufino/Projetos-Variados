package com.jorgerufino.bank;

import com.jorgerufino.bank.model.Account;

public class App {
    public static void main(String[] args) {

        Account account = new Account("0001","1234","Jorge Rufino Ferreira");

        account.deposit(500.0);
        account.deposit(100.0);

        account.withDraw(60.0);

        System.out.println(account);
    }
}