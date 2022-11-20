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
        
        ContaBancaria contaA = new ContaBancaria(987);
        contaA.setAgencia(agenciaA);   
        agenciaA.setContaBancaria(contaA.getNumero(), contaA);
        
        ContaBancaria contaB = new ContaBancaria(989);
        contaB.setAgencia(agenciaA);   
        agenciaA.setContaBancaria(contaB.getNumero(), contaB);
    }
}