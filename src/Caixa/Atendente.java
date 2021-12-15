/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Caixa;

import java.util.Random;

/**
 *
 * @author José Henrique PG
 */
public class Atendente implements Runnable {

    private int cadeiraDeEspera;///define quantas pessoas podem esperar atendimento
    boolean caixaOcupado = false;///true caixa ocupado | false = livre
    int[] clientes;///vetor de clientes
    boolean atendenteAtende = false;///true = espera | false = atende
    private String nome;//nome da thread criada
    private int clientesNovos;///gera random até o númeero máximo de clientes
    int numerodeClientes = 0;///inicializa~~ao da variavel total de clientes

    ///Método que inicialaza, com os parâmetros para o atendente
    Atendente(String nome, int cadeiraDeEspera, int clientes) {
        clientesNovos = clientes;//inicializa o máximo de clientes randomicos
        this.nome = nome;//referencia o nome
        this.cadeiraDeEspera = cadeiraDeEspera;///e a cadeira de espera
        System.out.println("O atendente " + nome + " chegou ao estabrlecimento!");
    }

    public void clientes() {///método que gera clientes e cria o vetor de clientes
        Random r = new Random();///gera um número aleatóri de clientes
        numerodeClientes = r.nextInt(clientesNovos);//gera o total de clientes randômicos
        clientes = new int[numerodeClientes];//defineo tamanho do vetor de clientes
        ///preenche o vetor com o numero de clientes
        for (int i = 0; numerodeClientes < clientes.length; i++) {
            clientes[i] = i;
        }
    }

    public void AtendenteEspera() throws InterruptedException {
        System.out.println("Não Existem Clientes para o atendente " + nome + " .");
        System.out.println("O " + nome + " está esperando os clientes!");
        Thread.sleep(2000);///como não há clientes a thread espera por 2 minutos
        System.out.println("O caixa do antendente " + nome + " está livre! ");
        clientes();///chama o método que cria clientes
    }

    ///métod que atende os clientes
    public void AtendenteAtende() throws InterruptedException {
        if (numerodeClientes != 0) {//se há clientes
            if (numerodeClientes > 1 && caixaOcupado == false) {//caixa livre
                System.out.println("Entrou(arão" + numerodeClientes + "cliente(s) no banco!");
            } else {
                System.out.println("Existem " + numerodeClientes + " clientes esperando atendimento do caixa do" + nome);
            }
            //se há clientes, 1 já pode ser atendido
            System.out.println("Um cliente ocupou o caixa do atendente!");
            numerodeClientes--;///decrementa o número de clientes
            System.out.println("Um cliente está sendo atendido pelo atendente de caixa: " + nome);
            caixaOcupado = true;///o caixa está ocupado
            ///atendente atendendo, a thread espera o atendimento terminar
            Thread.sleep(1000);
            ///se o número de clientes é maior que o de cadeiras de espera
            if (numerodeClientes > cadeiraDeEspera) {
                ///verifica quantos clientes irão embora
                int cli = numerodeClientes - cadeiraDeEspera;
                ///verifica quantos clientes esperam
                numerodeClientes = numerodeClientes - cli;
                ///enquanto o contador for menor que o numero de clientes, o vetor é zerado nas posições.
                for (int i = 0; i < clientes.length - 1; i++) {
                    clientes[i] = 0;
                }
                ///atualiza o total de clientes
                for (int j = 0; j < numerodeClientes; j++) {
                    clientes[j] = j + 1;
                }
                ///mostra quantos clientes foram embora
                System.out.println(cli + " clientes foram embora!");
                ///mostra quantos clientes estão esperando
                System.out.println(numerodeClientes + " clientes esão esperando atendimento!");
            }
            ///mostra qual atendente de caixa já atendeu
            System.out.println("um cliente foi atendido pelo caixa: " + nome);
            ///se o número de clientes for igual a 1
        } else if (numerodeClientes == 1) {
            ///mostra o caixa que está livre
            System.out.println("O caixa de " + nome + " está disponivel!");
            ///avisa que vai atender
            System.out.println("O caixa do atendente " + nome + " está ocupado e não há clientes esperando!");
            Thread.sleep(1000);///faz a thread esperar um segundo
            numerodeClientes--;///decrementa o número de clientes
            ///mostra qual atendente de caixa que já atendeu
            System.out.println("um cliente foi atendido pelo caixa: " + nome);
        } else {
            ///mostra o atendente de caixa que está livre
            System.out.println("O caixa " + nome + " está disponivel!");
            ///libera as cadeiras de espera
            caixaOcupado = false;//caixa fica livre
        }
    }

    @Override
    public void run() {///executa a thread
         while (true) {///fica verificando a todo tempo
            if (numerodeClientes <= 0) {///se não tem clientes
                try {
                    AtendenteEspera();///atendente espera
                } catch (InterruptedException ex) {
                    System.out.println(ex);///se der erro mostra o erro
                }
            } else {
                try {
                    AtendenteAtende();///o atendente atende
                } catch (InterruptedException ex) {
                    System.out.println(ex);///se der erro mostra o erro
                }
            }
        }
    }

}
