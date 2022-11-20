/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package multi_sockets;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author milif
 */
public class Comunicador extends Thread{
    private String IP;
    private int PORTA = ConfiguracaoServidor.PORTA_SERV;
    private SocketChannel gerenteConta = null;
    private SocketChannel cliente = null;
    private ServerSocketChannel server = null;
    private InetSocketAddress endereco = null;
    private BlockingQueue<ByteBuffer> entradaDados = new LinkedBlockingQueue<ByteBuffer>();
    private static Map<Integer, SocketChannel> listaClientes;
    private boolean ativo;
    private int tipoUsuario;
    
    private static final byte SAQUE = ConfiguracaoServidor.SAQUE;
    private static final byte DEPOSITO = ConfiguracaoServidor.DEPOSITO;
    private static final byte EXTRATO = ConfiguracaoServidor.EXTRATO;
    private static final byte CRIAR_CONTA = ConfiguracaoServidor.CRIAR_CONTA;
    private static final byte LER_CONTA = ConfiguracaoServidor.LER_CONTA;
    private static final byte ATUALIZAR_CONTA = ConfiguracaoServidor.ATUALIZAR_CONTA;
    private static final byte DELETAR_CONTA = ConfiguracaoServidor.DELETAR_CONTA;
    private static final byte PORTA_CONEXAO = ConfiguracaoServidor.PORTA_CONEXAO;
    
    public Comunicador(String IP) {
        int PORTA_DEF = PORTA;
        this.IP = IP;
        boolean criar = false;
        listaClientes = new LinkedHashMap<Integer, SocketChannel>();

        while (!criar) {
            try {
                server = ServerSocketChannel.open();
                endereco = new InetSocketAddress(this.IP, PORTA_DEF);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
            try {
                server.socket().bind(endereco);
                criar = true;
            } catch (IOException e) {
                PORTA_DEF++;
            }
        }
        this.ativo = true;
        this.start();
    }

    

    public Comunicador() {
    }

    public void run() {
        try {
            while (this.ativo) {
                try {
                    cliente = server.accept();
                    listaClientes.put(this.portaRemotaClienteDesc(), cliente);
                    System.out.println("Alguem se comunicou: " + this.canalRemotoClienteDesc());
                    Mensagem_Conexao_Server(cliente, this.portaRemotaClienteDesc());
                    leituraRecebedor();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void conectaServidor(String hostDescription) {
        try {
            String vetores[] = hostDescription.split(":");
            String hostname = vetores[0];
            int porta = Integer.parseInt(vetores[1].trim());
            cliente = SocketChannel.open(new InetSocketAddress(hostname, porta));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        leituraRecebedor();
    }

    private void leituraRecebedor() {
        try {
            Recebedor primeiro = new Recebedor(cliente, gerenteConta, this.entradaDados);
            primeiro.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String canalRemotoClienteDesc() {
        try {
            String enderecoHost = cliente.socket().getInetAddress().getHostAddress();
            String enderecoPorta = Integer.toString(cliente.socket().getPort());
            return enderecoHost + ":" + enderecoPorta;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public int portaRemotaClienteDesc() {
        try {
            return cliente.socket().getPort();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String Server() {
        try {
            String enderecoHost = endereco.getAddress().getHostAddress();
            String enderecoPorta = Integer.toString(endereco.getPort());
            return enderecoHost + ":" + enderecoPorta;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public SocketChannel getSocket() {
        try {
            return this.cliente;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ByteBuffer RecebendoMensagem() {
        try {
            return entradaDados.take();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }

    public void MsgSend_Deposito(SocketChannel canal, int conta, float valorDeposito, int conexao_porta) {
        // TIPO_MSG, TAMANHO, CONTA, VALOR DO DEPOSITO, PORTA DE CONEXAO
        // SHORTINT, INT, INT, FLOAT, INT
        try {
            int tamMsg = 2 + 4 + 4 + 4 + 4;
            ByteBuffer writeBuffer = ByteBuffer.allocateDirect(tamMsg);
            writeBuffer.putShort(this.DEPOSITO);
            writeBuffer.putInt(tamMsg);
            writeBuffer.putInt(conta);
            writeBuffer.putFloat(valorDeposito);
            writeBuffer.putInt(conexao_porta);
            writeBuffer.rewind();
            channelWrite(canal, writeBuffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void Mensagem_Conexao_Server(SocketChannel canal, int conexao_porta) {
        // TIPO_MSG, TAMANHO, PORTA DE CONEXAO
        // SHORTINT, INT, INT
        try {
            int tamMsg = 2 + 4 + 4;
            ByteBuffer writeBuffer = ByteBuffer.allocateDirect(tamMsg);
            writeBuffer.putShort(this.PORTA_CONEXAO);
            writeBuffer.putInt(tamMsg);
            writeBuffer.putInt(conexao_porta);
            writeBuffer.rewind();
            channelWrite(canal, writeBuffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Mensagem_saque(SocketChannel canal, int conta, float valorSaque) {

    }
    
     public void MsgSend_Extrato(SocketChannel canal, int conta, float valorSaque) {
         // TIPO_MSG, TAMANHO, CONTA, VALOR DO DEPOSITO, PORTA DE CONEXAO
        // SHORTINT, INT, INT, FLOAT, INT
        try {
            int tamMsg = 2 + 4 + 4 + 4 + 4;
            ByteBuffer writeBuffer = ByteBuffer.allocateDirect(tamMsg);
            writeBuffer.putShort(this.EXTRATO);
            writeBuffer.putInt(tamMsg);
            writeBuffer.putInt(conta);
            writeBuffer.putFloat(valorSaque);
            writeBuffer.rewind();
            channelWrite(canal, writeBuffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void channelWrite(SocketChannel canal, ByteBuffer writeBuffer) {
        // TODO Auto-generated method stub
        try {
            long nbytes = 0;
            long toWrite = writeBuffer.remaining();
            try {
                while (nbytes != toWrite) {
                    nbytes += canal.write(writeBuffer);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                    }
                }
            } catch (ClosedChannelException cce) {
                cce.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            writeBuffer.rewind();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<Integer, SocketChannel> getClienteSocketLista() {
        return listaClientes;
    }

    public static void setClienteSocketLista(Map<Integer, SocketChannel> listaClientes) {
        Comunicador.listaClientes = listaClientes;
    }
    
    
    
}
