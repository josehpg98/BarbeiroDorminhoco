/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Caixa;

/**
 *
 * @author José Henrique PG
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(" Atendente Dorminhoco! ");
        Atendente atendente1 = new Atendente("Antônio", 2, 5);///nome,cadeiras de espere e máximo de clientes
        Thread threadatendente1 = new Thread(atendente1);
        threadatendente1.start();///inicia a thread
    }
    
}
