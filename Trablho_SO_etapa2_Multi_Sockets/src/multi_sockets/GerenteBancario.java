/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package multi_sockets;

import java.nio.ByteBuffer;
import java.util.Scanner;

/**
 *
 * @author milif
 */
public class GerenteBancario extends Thread{
    
    private String IP = ConfiguracaoServidor.IP_SERV;
    private int PORTA = ConfiguracaoServidor.PORTA_SERV;
    private Comunicador canalServidor;
    private Scanner entradaDados;
    private short mensagem_tipo;
    private int mensagem_tamanho;
    private int porta_conexao;
    
    public static void main(String[] args) {
        GerenteBancario gerente = new GerenteBancario();
        gerente.conectarServidor();
        gerente.menu();
    }

    public void run() {
        try {
            ByteBuffer buffer = null;
            ///////////////////////////////////////////////////////////////////
            System.out.println("Gerente do banco =>");
            System.out.println("\t Está recebendo mensagens ... \n");
            ///////////////////////////////////////////////////////////////////
            while (true) {
                buffer = this.canalServidor.RecebendoMensagem();
                this.mensagem_tipo = buffer.getShort();
                this.mensagem_tamanho = buffer.getInt();

                switch (this.mensagem_tipo) {
                    case ConfiguracaoServidor.EXTRATO:
                        System.out.println("Gerente =>");
                        System.out.println("\t Recebi o extrato");
                        break;
                    case ConfiguracaoServidor.DEPOSITO:
                        System.out.println("Gerente =>");
                        System.out.println("\t Recebi o deposito");
                        break;
                    case ConfiguracaoServidor.SAQUE:
                        System.out.println("Gerente =>");
                        System.out.println("\t Recebi o saque");
                        break;
                    case ConfiguracaoServidor.PORTA_CONEXAO:
                        this.porta_conexao = buffer.getInt();
                        System.out.println("Gerente =>");
                        System.out.println("\t Porta de Conexao com o servidor: " + this.porta_conexao);
                        break;
                    default:
                        System.out.println("Gerente =>");
                        System.out.println("\t\t TIPO DE MENSAGEM INVALIDA: " + mensagem_tipo + "\n");
                        break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void conectarServidor() {
        try {
            this.canalServidor = new Comunicador(IP);
            this.canalServidor.conectaServidor(this.IP + ":" + this.PORTA);
            System.out.println("Conectei ao servidor: " + this.canalServidor.canalRemotoClienteDesc());
            this.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void menu() {
        try {
            int opcao;
            this.entradaDados = new Scanner(System.in);

            System.out.println("==============================");
            System.out.println("\t 1 - Deposito");
            System.out.println("\t 2 - Saque");
            System.out.println("\t 3 - Consulta extrato");
            System.out.println("==============================");
            do {
                System.out.println("Digite a opção:");
                opcao = this.entradaDados.nextInt();

                switch (opcao) {
                    case 1:
                        deposito();
                        break;

                    case 2:
                        saque();
                        break;

                    case 3:
                        extrato();
                        break;

                    default:
                        System.out.println("Opção inválida.");
                }
            } while (opcao != 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deposito() {
        try {
            this.entradaDados = new Scanner(System.in);
            System.out.println("Informe a conta bancaria: ");
            int conta = this.entradaDados.nextInt();
            System.out.println("Informe o valor para deposito: ");
            float valorDeposito = this.entradaDados.nextFloat();
            this.canalServidor.MsgSend_Deposito(this.canalServidor.getSocket(), conta, valorDeposito, this.porta_conexao);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saque() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void extrato() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getPorta_conexao() {
        return porta_conexao;
    }

    public void setPorta_conexao(int porta_conexao) {
        this.porta_conexao = porta_conexao;
    }
    
}
