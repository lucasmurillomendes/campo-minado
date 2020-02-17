/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lucas.cm.modelo;

import br.com.lucas.cm.excecao.ExplosaoException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author lucas
 */
public class Tabuleiro {

    private int linhas;
    private int colunas;
    private int minas;

    private final List<Campo> campos = new ArrayList<>();

    public Tabuleiro(int linhas, int colunas, int minas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.minas = minas;

        gerarCampos();
        associarVizinhos();
        sortearMinas();
    }
    public void abrir(int linha, int coluna){
        try {
             campos.parallelStream()
                .filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
                .findFirst()
                .ifPresent(c -> c.abrir());
        } catch (ExplosaoException e) {
            campos.forEach(c -> c.setAberto(true));
            throw e;
        }
    }
    
     public void marcar(int linha, int coluna){
        campos.parallelStream()
                .filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
                .findFirst()
                .ifPresent(c -> c.alternarMarcacao());
    }

    private void gerarCampos() {
        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                campos.add(new Campo(linha, coluna));
            }
        }
    }

    private void associarVizinhos() {
        for (Campo c1 : campos) {
            for (Campo c2 : campos) {
                c1.adicionarVizinho(c2);
            }
        }
    }

    private void sortearMinas() {
        long minasArmadas = 0;
        Predicate<Campo> minado = c -> c.isMinado();
        do {
            //Tem que colocasr entre parenteses a operação para o
            //cast transformar em int apenas após a operação
            int aleatorio = (int) (Math.random() * campos.size());
            campos.get(aleatorio).minar();
            minasArmadas = campos.stream().filter(minado).count();
        } while (minasArmadas < minas);
    }
    
    public boolean objetivoAlcancado(){
        return campos.stream().allMatch(c -> c.objetivoAlcancado());
    }
    
    public void reinicializar(){
        campos.stream().forEach(c -> c.reiniciar());
        sortearMinas();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
          sb.append("  ");
        for (int c = 0; c < colunas; c++) {
            sb.append(" ");
            sb.append(c);
            sb.append(" ");
        }
        sb.append("\n");
        
        int i =0;
        for (int linha = 0; linha < linhas; linha++) {
            sb.append(linha);
            sb.append(" ");
            for (int coluna = 0; coluna < colunas; coluna++) {
                sb.append(" ");
                sb.append(campos.get(i));
                sb.append(" ");
                i++;
            }
            sb.append("\n");
        }
        
        return sb.toString();
    }
    
   

}
