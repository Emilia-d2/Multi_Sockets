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
public class ClienteBanco extends Thread {

    private String IP_SERV = ConfiguracaoServidor.IP_SERV;
    private int PORTA_SERV = ConfiguracaoServidor.PORTA_SERV;
    private Comunicador canalServidor;
    private Scanner entradaDados;
    private short mensagem_tipo;
    private int mensagem_tamanho;
    private int porta_conexao;

    public static void main(String[] args) {
        ClienteBanco cliente = new ClienteBanco();
        cliente.conectarServidor();
        cliente.menu();
    }

    public void run() {
        try {
            ByteBuffer buf = null;
            System.out.println("----------------------------------");
            System.out.println("Cliente =>                        |");
            System.out.println("\t Recebendo Mensagens ... \n     |");
           System.out.println("-----------------------------------");
            while (true) {
                buf = this.canalServidor.RecebendoMensagem();
                this.mensagem_tipo = buf.getShort();
                this.mensagem_tamanho = buf.getInt();

                switch (this.mensagem_tipo) {
                    case ConfiguracaoServidor.EXTRATO:
                        System.out.println("Cliente =>");
                        System.out.println("\t Recebi Msg EXTRATO");
                        break;
                    case ConfiguracaoServidor.DEPOSITO:
                        System.out.println("Cliente =>");
                        System.out.println("\t Recebi Msg DEPOSITO");
                        break;
                    case ConfiguracaoServidor.SAQUE:
                        System.out.println("Cliente =>");
                        System.out.println("\t Recebi Msg SAQUE");
                        break;
                    case ConfiguracaoServidor.PORTA_CONEXAO:
                        this.porta_conexao = buf.getInt();
                        System.out.println("Cliente =>");
                        System.out.println("\t Porta de Conexao com o servidor: " + this.porta_conexao);
                        break;
                    default:
                        System.out.println("Cliente =>");
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
            this.canalServidor = new Comunicador();
            this.canalServidor.conectaServidor(this.IP_SERV + ":" + this.PORTA_SERV);
            System.out.println("Conectei ao servidor: " + this.canalServidor.portaRemotaClienteDesc());
            this.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void menu() {
        try {
            int opcao;
            this.entradaDados = new Scanner(System.in);

            System.out.println("-----------------------------");
            System.out.println("\t 1 - Deposito             |");
            System.out.println("\t 2 - Saque                |");
            System.out.println("\t 3 - Consulta extrato     |");
            System.out.println("-----------------------------");
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
