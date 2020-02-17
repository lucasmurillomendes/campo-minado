/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lucas.cm.visao;

import br.com.lucas.cm.excecao.ExplosaoException;
import br.com.lucas.cm.excecao.SairException;
import br.com.lucas.cm.modelo.Tabuleiro;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import jdk.nashorn.internal.parser.Lexer;

/**
 *
 * @author lucas
 */
public class TabuleiroConsole {
    private Tabuleiro tabuleiro;
    private Scanner le = new Scanner(System.in);

    public TabuleiroConsole(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
        executarJogo();
    }

    private void executarJogo() {
        try {
            boolean continuar = true;
            while (continuar) { 
                cicloDoJogo();
                
                String resposta = le.nextLine();
                System.out.println("Outra partida? (S/n)");
                if ("n".equalsIgnoreCase(resposta)) {
                    continuar = false;
                }else{
                    tabuleiro.reinicializar();
                }
            }
        } catch (SairException e) {
            System.out.println("Você saiu! ");
        }finally{
            le.close();
        }
    }

    private void cicloDoJogo() {
        try {
            while (!tabuleiro.objetivoAlcancado()) {                
                System.out.println(tabuleiro.toString());
                String digitado = capturaValorDigitado("Digite (x,y): ");
                
                Iterator<Integer> xy = Arrays.stream(digitado.split(","))
                        .map(e -> Integer.parseInt(e.trim())).iterator();
                
                digitado = capturaValorDigitado("1 - Abrir ou 2 - (Des)Marcar: ");
                
                if ("1".equals(digitado)) {
                    tabuleiro.abrir(xy.next(), xy.next());
                }else if ("2".equals(digitado)){
                    tabuleiro.marcar(xy.next(), xy.next());
                }
            }
            System.out.println(tabuleiro);
            System.out.println("Você ganhou!!");
        } catch (ExplosaoException e) {
            System.out.println(tabuleiro);
            System.out.println("Você Perdeu!!");
        }
    }
    
    private String capturaValorDigitado(String texto){
        System.out.println(texto);
        String digitado = le.nextLine();
        
        if ("sair".equalsIgnoreCase(digitado)) {
            throw new SairException();
        }
        return digitado;
    }
    
    
}
