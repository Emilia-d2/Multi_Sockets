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
    
    private final String IP_SERV = ConfiguracaoServidor.IP_SERV;
    private final int PORTA_SERV = ConfiguracaoServidor.PORTA_SERV;
    private Comunicador canalServidor;
    private Scanner entradaDados;
    private short mensagem_tipo;
    private int mensagem_tamanho;
    private int porta_conexao;
    private ClienteBanco clienteBanco; 
    
    public static void main(String[] args) {
        GerenteBancario gerente = new GerenteBancario();
        gerente.conectarServidor();
        gerente.menu();
    }

    @Override
    public void run() {
        try {
            ByteBuffer buffer = null;
            System.out.println("---------------------------------------");
            System.out.println("Gerente do banco =>                    |");
            System.out.println("\t Há uma troca de mensagens           |");
            System.out.println("---------------------------------------");
            
            while (true) {
                buffer = this.canalServidor.RecebendoMensagem();
                this.mensagem_tipo = buffer.getShort();
                this.mensagem_tamanho = buffer.getInt();

                switch (this.mensagem_tipo) {
                    case ConfiguracaoServidor.CRIAR_CONTA:
                        System.out.println("Gerente =>");
                        System.out.println("\t Criei uma conta Bancária! ");
                        break;
                    case ConfiguracaoServidor.ATUALIZAR_CONTA:
                        System.out.println("Gerente =>");
                        System.out.println("\t Atualizei uma conta Bancária! ");
                        break;
                    case ConfiguracaoServidor.LER_CONTA:
                        System.out.println("Gerente =>");
                        System.out.println("\t Quero ver as contas Bancárias! ");
                        break;
                    case ConfiguracaoServidor.DELETAR_CONTA:
                        System.out.println("Gerente =>");
                        System.out.println("\t Exclui uma conta Bancária! ");
                        break;
                    case ConfiguracaoServidor.PORTA_CONEXAO:
                        this.porta_conexao = buffer.getInt();
                        System.out.println("Gerente =>");
                        System.out.println("\t Porta de Conexao com o servidor: " + this.porta_conexao);
                        break;
                    default:
                        System.out.println("Gerente =>");
                        System.out.println("\t TIPO DE MENSAGEM INVALIDA: " + mensagem_tipo + "\n");
                        break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void conectarServidor() {
        try {
            this.canalServidor = new Comunicador(IP_SERV);
            this.canalServidor.conectaServidor(this.IP_SERV + ":" + this.PORTA_SERV);
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

            System.out.println("-----------------------------");
            System.out.println("\t 1 - Nova Conta            |");
            System.out.println("\t 2 - Consulta Conta        |");
            System.out.println("\t 3 - Atualizar Conta       |");
            System.out.println("\t 3 - Deletar Conta         |");
            System.out.println("-----------------------------");
            System.out.println("Digite a opção ==> ");
            opcao = this.entradaDados.nextInt();
                
            while(opcao != 0) {
                switch (opcao) {
                    case 1:
                        criaConta();
                        break;
                    case 2:
                        consultaConta();
                        break;
                    case 3:
                        atualizaConta();
                        break;
                    case 4:
                        deletarConta();
                        break;
                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
               
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void criaConta() {
        try {
            this.entradaDados = new Scanner(System.in);
            System.out.println("Informe o n° da conta bancaria que deseja criar: ");
            int conta = this.entradaDados.nextInt();
            System.out.println("Informe o valor para deposito: ");
            float valorDeposito = this.entradaDados.nextFloat();
            //this.canalServidor.MsgSend_Deposito(this.canalServidor.getSocket(), conta, valorDeposito, this.porta_conexao);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void consultaConta() {
        try {
            this.entradaDados = new Scanner(System.in);
            System.out.println("Informe o n° da conta bancaria que deseja : ");
            int conta = this.entradaDados.nextInt();
            System.out.println("Informe o valor para deposito: ");
            float valorDeposito = this.entradaDados.nextFloat();
            //this.canalServidor.MsgSend_Deposito(this.canalServidor.getSocket(), conta, valorDeposito, this.porta_conexao);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void atualizaConta() {
        try {
            this.entradaDados = new Scanner(System.in);
            System.out.println("Informe o n° da conta bancaria que deseja : ");
            int conta = this.entradaDados.nextInt();
            System.out.println("Informe o valor para deposito: ");
            float valorDeposito = this.entradaDados.nextFloat();
            //this.canalServidor.MsgSend_Deposito(this.canalServidor.getSocket(), conta, valorDeposito, this.porta_conexao);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void deletarConta() {
        try {
            this.entradaDados = new Scanner(System.in);
            System.out.println("Informe o n° da conta bancaria que deseja : ");
            int conta = this.entradaDados.nextInt();
            System.out.println("Informe o valor para deposito: ");
            float valorDeposito = this.entradaDados.nextFloat();
            //this.canalServidor.MsgSend_Deposito(this.canalServidor.getSocket(), conta, valorDeposito, this.porta_conexao);


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