package com.gugawag.rpc.banco;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class BancoServiceServer extends UnicastRemoteObject implements BancoServiceIF {
    private List<Conta> contas;

    public BancoServiceServer() throws RemoteException {
        contas = new LinkedList<>();
        contas.add(new Conta("1", 100.0));
        contas.add(new Conta("2", 156.0));
        contas.add(new Conta("3", 950.0));
    }

    @Override
    public Double saldo(String numero) throws RemoteException {
        return contas
                .stream()
                .filter(conta -> conta.getNumero().equals(numero))
                .findFirst()
                .map(Conta::getSaldo)
                .orElse(null);
    }

    @Override
    public int quantidadeContas() throws RemoteException {
        return contas.size();
    }

    @Override
    public void adicionarConta(String chave, Double saldo) throws RemoteException {
        this.contas.add(new Conta(chave, saldo));
    }

}
