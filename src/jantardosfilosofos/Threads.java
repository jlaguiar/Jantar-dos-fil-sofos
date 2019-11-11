/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jantardosfilosofos;

import java.util.Random;

/**
 *
 * @author joao luiz
 */
public class Threads extends Thread {

    Thread[] threads;
    private boolean[] garfos;
    boolean tentandoOuComendo[];
    int qtdd;
    Random random = new Random();

    public Threads() {
    }

    public Threads(int qtdd) {
        this.garfos = new boolean[qtdd + 11];
        this.threads = new Thread[qtdd + 11];
        this.tentandoOuComendo = new boolean[qtdd + 11];
    }

    public void inicializa() {
        for (int i = 0; i < garfos.length; i++) {
            //falso quer dizer q nao tem ninguem usando o garfo
            garfos[i] = false;
            threads[i] = new Thread(comer);
            tentandoOuComendo[i] = false;
        }
    }

    public Runnable comer = new Runnable() {

        int j = 0;
        int j1 = 0;

        @Override
        public void run() {
            try {
                int aux, ajuda;
                //11 = 0 
                while (true) {
                    //aleatorio quem vai tentar comer primeiro
                    Long longAjuda = Thread.currentThread().getId();
                    Integer id = Integer.valueOf(longAjuda.toString());

                    tentandoOuComendo[id] = true;
                    System.out.println(Thread.currentThread().getName() + " Ira tentar comer, seu garfo é " + id);
                    if (!garfos[id]) {
                        garfos[id] = true;
                        System.out.println("Pegou um garfo o " + Thread.currentThread().getName());
                        //ajuda vai dizer qual o proximo garfo da direita dele
                        if ((id + 1 == garfos.length)) {
                            ajuda = 11;
                        } else {
                            ajuda = id + 1;
                        }
                        if (!garfos[ajuda % garfos.length]) {
                            System.out.println("Ja vai pegar o segundo Garfo " + Thread.currentThread()
                                    .getName());
                            garfos[(ajuda) % garfos.length] = true;
                            System.out.println("Pegou o segundo garfo " + Thread.currentThread().getName());
                            System.out.println(Thread.currentThread().getName() + " Comendo com o garfo do Colega  "
                                    + (ajuda % garfos.length) + " é  "
                                    + garfos[ajuda % garfos.length] + "  seu garfo " + id
                                    + " é  " + garfos[id]);
                            //ele demora meio segundo comendo
                            Thread.sleep(500);
                            garfos[ajuda % garfos.length] = false;
                            garfos[id] = false;
                            System.out.println(Thread.currentThread().getName() + " Terminou de comer e devolveu os garfos a mes");
                            //ele comeca a dormir
                            Thread.sleep(random.nextInt(1000)+1);
                            System.out.println("Terminou de Dormir " + Thread.currentThread().getName());
                        } else {
                            System.out.println("O outro garfo esta sendo utilizado " + Thread.currentThread().getName());
                            garfos[id] = false;
                        }

                    } else {
                        System.out.println("Seu garfo esta sendo utilizado " + Thread.currentThread().getName()
                                + " numero do seu garfo é " + Thread.currentThread().getId());
                    }
                    tentandoOuComendo[id] = false;

                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    };


}
