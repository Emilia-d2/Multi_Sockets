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
        AgenciaBancaria agenciaA = new AgenciaBancaria(12345);
        
        ContaBancaria conta1 = new ContaBancaria(987);
        conta1.setAgencia(agenciaA);   
        agenciaA.setContaBancaria(conta1.getNumeroConta(), conta1);
        
        ContaBancaria conta2 = new ContaBancaria(989);
        conta2.setAgencia(agenciaA);   
        agenciaA.setContaBancaria(conta2.getNumeroConta(), conta2);
        
        ContaBancaria conta3 = new ContaBancaria(989);
        conta3.setAgencia(agenciaA);   
        agenciaA.setContaBancaria(conta3.getNumeroConta(), conta3);
    }
}