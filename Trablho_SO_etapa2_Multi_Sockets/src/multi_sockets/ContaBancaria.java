/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package multi_sockets;

/**
 *
 * @author milif
 */
public class ContaBancaria {
    private int conexao_porta;
    private int numeroConta;
    private float saldo;
    private float limite;
    private String nomeUsuario;
    private String cfp;
    private AgenciaBancaria agencia;
    
    
    public ContaBancaria(int numeroConta) {
        this.numeroConta = numeroConta;
        this.limite = 1000000;
    }

    public int getConexao_porta() {
        return conexao_porta;
    }

    public void setConexao_porta(int conexao_porta) {
        this.conexao_porta = conexao_porta;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }
    
     public void deposita(float valor) {
        this.saldo += valor;
    }

    public void saca(float valor) {
        this.saldo -= valor;
    }
    
    public float getSaldo() {
        return saldo + this.limite;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public float getLimite() {
        return limite;
    }

    public void setLimite(float limite) {
        this.limite = limite;
    }

    public AgenciaBancaria getAgencia() {
        return agencia;
    }

    public void setAgencia(AgenciaBancaria agencia) {
        this.agencia = agencia;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getCfp() {
        return cfp;
    }

    public void setCfp(String cfp) {
        this.cfp = cfp;
    }
    
    
    
    
}
