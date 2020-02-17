/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lucas.cm;

import br.com.lucas.cm.modelo.Tabuleiro;
import br.com.lucas.cm.visao.TabuleiroConsole;

/**
 *
 * @author lucas
 */
public class Aplicacao {
    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro(6, 6, 3);
        
        new TabuleiroConsole(tabuleiro);
        
      
    }
}
