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
public class ContaBancaria extends Thread{
    private int conexao_porta;
    private int numeroConta;
    private float saldo;
    private float limite;
    private String nomeUsuario;
    private String cfp;
    private AgenciaBancaria agencia;
    private String IP = ConfiguracaoServidor.IP_SERV;
    private Comunicador canalDoServidor;
    private short mensagem_tipo;
    private int mensagem_tamanho;
    private LinkedHashMap<Integer, ContaBancaria> listaDeContas;
    
      
    
    public ContaBancaria(int numeroConta) {
        this.numeroConta = numeroConta;
        this.limite = 1000000;
        try {
            this.setNumeroConta(numeroConta);
            this.canalDoServidor = new Comunicador(this.IP);
            this.listaDeContas = new LinkedHashMap<Integer, ContaBancaria>();
            System.out.println("Servidor da Conta Banária " + this.getContaBancaria(numeroConta)
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
            System.out.println("Conta Bancária do Paga Bem e de Pressa! =>          |");
            System.out.println("\t Recebendo Mensagens ... \n                       |");
            System.out.println("----------------------------------------------------");
            while (true) {
                buf = this.canalDoServidor.RecebendoMensagem();
                this.mensagem_tipo = buf.getShort();
                this.mensagem_tamanho = buf.getInt();

                switch (this.mensagem_tipo) {
                    case ConfiguracaoServidor.CRIAR_CONTA:
                        this.numeroConta = buf.getInt();
                        this.nomeUsuario = buf.toString();
                        this.cfp = buf.toString();
                        this.conexao_porta = buf.getInt();
                        System.out.println("----------------------------------------------------");
                        System.out.println("Usuário Conectado =>                                |");
                        System.out.println("\t Recebi mensagem do ....                          |");
                        System.out.println("\t Conta Bancaria: " + this.numeroConta + "         |");
                        System.out.println("\t Descrição do Deposito: " + this.nomeUsuario + "  |");
                        System.out.println("\t Descrição do Deposito: " + this.cfp + "          |");
                        System.out.println("----------------------------------------------------");
                        this.tratamentoMensagemDeposito(this.numeroConta, this.nomeUsuario, this.conexao_porta);
                        break;
                    case ConfiguracaoServidor.ATUALIZAR_CONTA:
                        this.numeroConta = buf.getInt();
                        this.nomeUsuario = buf.toString();
                        this.cfp = buf.toString();
                        this.conexao_porta = buf.getInt();
                        System.out.println("----------------------------------------------------");
                        System.out.println("Usuário Conectado =>                                |");
                        System.out.println("\t Recebi mensagem do ....                          |");
                        System.out.println("\t Conta Bancaria: " + this.numeroConta + "         |");
                        System.out.println("\t Descrição do Deposito: " + this.nomeUsuario + "  |");
                        System.out.println("\t Descrição do Deposito: " + this.cfp + "          |");
                        System.out.println("----------------------------------------------------");
                        this.tratamentoMensagemDeposito(this.numeroConta, this.nomeUsuario, this.conexao_porta);
                        break;
                    case ConfiguracaoServidor.LER_CONTA:
                        this.numeroConta = buf.getInt();
                        this.nomeUsuario = buf.toString();
                        this.cfp = buf.toString();
                        this.conexao_porta = buf.getInt();
                        System.out.println("----------------------------------------------------");
                        System.out.println("Usuário Conectado =>                                |");
                        System.out.println("\t Recebi mensagem do ....                          |");
                        System.out.println("\t Conta Bancaria: " + this.numeroConta + "         |");
                        System.out.println("\t Descrição do Deposito: " + this.nomeUsuario + "  |");
                        System.out.println("\t Descrição do Deposito: " + this.cfp + "          |");
                        System.out.println("----------------------------------------------------");
                        this.tratamentoMensagemDeposito(this.numeroConta, this.nomeUsuario, this.conexao_porta);
                        break;
                    case ConfiguracaoServidor.DELETAR_CONTA:
                        this.numeroConta = buf.getInt();
                        this.nomeUsuario = buf.toString();
                        this.cfp = buf.toString();
                        this.conexao_porta = buf.getInt();
                        System.out.println("----------------------------------------------------");
                        System.out.println("Usuário Conectado =>                                |");
                        System.out.println("\t Recebi mensagem do ....                          |");
                        System.out.println("\t Conta Bancaria: " + this.numeroConta + "         |");
                        System.out.println("\t Descrição do Deposito: " + this.nomeUsuario + "  |");
                        System.out.println("\t Descrição do Deposito: " + this.cfp + "          |");
                        System.out.println("----------------------------------------------------");
                        this.tratamentoMensagemDeposito(this.numeroConta, this.nomeUsuario, this.conexao_porta);
                        break;
                    default:
                        System.out.println("Usuário =>");
                        System.out.println("\t\t TIPO DE MENSAGEM INVALIDA: " + mensagem_tipo + "\n");
                        break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void tratamentoMensagemSaque(ContaBancaria conta, float valorSaque) {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tratamentoMensagemDeposito(int numero_conta, String descricao, int conexao_porta) {
        try {
           

        } catch (Exception e) {
            e.printStackTrace();
        }
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
