/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package multi_sockets;

import java.nio.ByteBuffer;
import java.util.LinkedHashMap;

/**
 *
 * @author milif
 */
public final class AgenciaBancaria extends Thread{
    
    private String IP = ConfiguracaoServidor.IP_SERV;
    private int numero_agencia;
    private Comunicador canalDoServidor;
    private LinkedHashMap<Integer, ContaBancaria> listaDeContas;
    private short mensagem_tipo;
    private String numeroConta;
    private String descricao;
    private float valorSaque;
    private float valorDeposito;
    private float valorTotalConta;
    private int mensagem_tamanho;
    private int conexao_porta;
    
    public AgenciaBancaria(int numero_agencia) {
        try {
            this.setNumeroAgencia(numero_agencia);
            this.canalDoServidor = new Comunicador(this.IP);
            this.listaDeContas = new LinkedHashMap<>();

            System.out.println("Servidor da agência " + this.getNumeroAgencia()
                    + " iniciado no canal " + this.canalDoServidor.Server());

            this.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            ByteBuffer buf = null;
            System.out.println("----------------------------------------------------");
            System.out.println("Agencia Bancária do Paga Bem e de Pressa! =>        |");
            System.out.println("\t Recebendo Mensagens ... \n                       |");
            System.out.println("----------------------------------------------------");
            while (true) {
                buf = this.canalDoServidor.RecebendoMensagem();
                this.mensagem_tipo = buf.getShort();
                this.mensagem_tamanho = buf.getInt();

                switch (this.mensagem_tipo) {
                    case ConfiguracaoServidor.DEPOSITO:
                        this.numeroConta = buf.toString();
                        this.descricao = buf.toString();
                        this.valorDeposito = buf.getFloat();
                        this.conexao_porta = buf.getInt();
                        System.out.println("----------------------------------------------------");
                        System.out.println("Usuário Conectado =>                                |");
                        System.out.println("\tDepositando...                                    |");
                        System.out.println("\t Conta Bancaria: " + this.numeroConta + "         |");
                        System.out.println("\t Valor depósito: " + this.valorDeposito + "       |");
                        System.out.println("\t Descrição do Deposito: " + this.descricao + "    |");
                        System.out.println("----------------------------------------------------");
                        this.tratamentoMensagemDeposito(this.numeroConta, this.descricao, this.valorDeposito, this.conexao_porta);
                        break;
                    case ConfiguracaoServidor.SAQUE:
                        this.numeroConta = buf.toString();
                        this.descricao = buf.toString();
                        this.valorSaque = buf.getFloat();
                        this.conexao_porta = buf.getInt();
                        System.out.println("----------------------------------------------------");
                        System.out.println("Usuário Conectado =>                                |");
                        System.out.println("\t Sacando...                                       |");
                        System.out.println("\t Conta Bancaria: " + this.numeroConta + "         |");
                        System.out.println("\t Valor saque: " + this.valorSaque + "             |");
                        System.out.println("\t Descrição do Saque: " + this.descricao + "       |");
                        System.out.println("----------------------------------------------------");
                        this.tratamentoMensagemSaque(this.numeroConta, this.descricao, this.valorDeposito, this.conexao_porta);
                        break;
                    case ConfiguracaoServidor.EXTRATO:
                        this.numeroConta = buf.toString();
                        this.conexao_porta = buf.getInt();
                        System.out.println("----------------------------------------------------");
                        System.out.println("Usuário Conectado =>                                |");
                        System.out.println("\t Tirando extrato...                               |");
                        System.out.println("\t Conta Bancaria: " + this.numeroConta + "         |");
                        System.out.println("----------------------------------------------------");
                        this.tratamentoMensagemExtrato(this.numeroConta, this.descricao, this.valorTotalConta, this.conexao_porta);
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

    public void setNumeroAgencia(int numero_agencia) {
        this.numero_agencia = numero_agencia;
    }

    public int getNumeroAgencia() {
        return this.numero_agencia;
    }

    public void setContaBancaria(int numeroConta, ContaBancaria conta) {
        try {
            this.listaDeContas.put(numeroConta, conta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ContaBancaria getContaBancaria(int numeroConta) {
        try {
            return this.listaDeContas.get(numeroConta);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void tratamentoMensagemExtrato(String contaBancaria, String descricao, float valorTotal, int conexao_porta) {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tratamentoMensagemSaque(String contaBancaria, String descricao, float valorSaque, int conexao_porta) {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tratamentoMensagemDeposito(String contaBancaria, String descricao, float valorDeposito, int conexao_porta) {
        try {
            /*if (this.listaDeContas.containsValue(this.numeroConta)) {
                this.listaDeContas.get(numero_conta).setConexao_porta(conexao_porta);

                if (Comunicador.getClienteSocketLista().containsKey(conexao_porta)) {
                    this.canalDoServidor.MsgSend_Extrato(Comunicador.getClienteSocketLista().get(conexao_porta), numero_conta, valorDeposito);
                }
            }*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
    
}
