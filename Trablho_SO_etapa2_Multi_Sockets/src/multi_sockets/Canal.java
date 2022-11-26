/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package multi_sockets;

/**
 *
 * @author milif
 */
public class Canal {

    public static void main (String[] args) {
        AgenciaBancaria agenciaA = new AgenciaBancaria("0226");

        ContaBancaria conta1 = new ContaBancaria("99");
        conta1.setAgencia("Agencia");   
        agenciaA.setContaBancaria(conta1.getNumeroConta(), conta1);

    }
}