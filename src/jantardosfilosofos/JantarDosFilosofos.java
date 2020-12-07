/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Scanner;

/**
 *
 * @author joao luiz
 */
public class JantarDosFilosofos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        
        Scanner ler = new Scanner(System.in);
        int qtddFilosofos;
        
        System.out.println("Quantidade de filosofos");
        qtddFilosofos = ler.nextInt();
        
        String nomes[] = new String[qtddFilosofos];
        
        //minha thread
        Threads threadsFilosofos = new Threads(qtddFilosofos);
        threadsFilosofos.inicializa();
        threadsFilosofos.qtdd = qtddFilosofos;

        //cria os filosofos
        for (int i = 0; i < qtddFilosofos; i++) {
            nomes[i] = new String();
            System.out.println("Nome ");
            nomes[i] = ler.next();
           
        }
        for (int i = 0; i < qtddFilosofos; i++) {           
            threadsFilosofos.threads[i].setName(nomes[i]);
        }

        for (int i = 0; i < qtddFilosofos; i++) {
            threadsFilosofos.threads[i].start();
        }


    }

}
