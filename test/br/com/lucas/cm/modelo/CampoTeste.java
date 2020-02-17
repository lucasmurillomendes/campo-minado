/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lucas.cm.modelo;

import br.com.lucas.cm.excecao.ExplosaoException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author lucas
 */
public class CampoTeste {
    private Campo campo; 
    
    public CampoTeste() {
    }
    
    @Before
    public void iniciarCampo(){
        campo = new Campo(3, 3);
    }
    
    @Test
    public void testeVizinhoRealDistancia1Esquerda(){
        Campo vizinho = new Campo(3, 2);      
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }
    @Test
    public void testeVizinhoRealDistancia1Direita(){
        Campo vizinho = new Campo(3, 4);      
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }
    @Test
    public void testeVizinhoRealDistancia1Cima(){
        Campo vizinho = new Campo(2, 3);      
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }
    @Test
    public void testeVizinhoRealDistancia1Baixo(){
        Campo vizinho = new Campo(4, 3);      
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }
    @Test
    public void testeVizinhoRealDistanciaDiagonal(){
        Campo vizinho = new Campo(2, 2);      
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }
    @Test
    public void testeVizinhoFalso(){
        Campo vizinho = new Campo(1, 1);      
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertFalse(resultado);
    }
    @Test
    public void testeValorPadraoAlternarMarcacao(){
        campo.alternarMarcacao();
        assertTrue(campo.isMarcado());
    }
    @Test
    public void testeValorAlternarMarcacaoDuasChamadas(){
        campo.alternarMarcacao();
        campo.alternarMarcacao();
        assertFalse(campo.isMarcado());
    }
    
    @Test
    public void abrirNaoMinadoNaoMarcado(){
        assertTrue(campo.abrir());
    }
    @Test
    public void abrirNaoMinadoMasMarcado(){
        campo.alternarMarcacao();
        assertFalse(campo.abrir());
    }
    @Test
    public void abrirMinadoMarcado(){
        campo.alternarMarcacao();
        campo.minar();
        assertFalse(campo.abrir());
    }
    @Test(expected = ExplosaoException.class)
    public void abrirMinadoNaoMarcado(){
        campo.minar();
        assertTrue(campo.abrir());
    }
    
     @Test
    public void abrirComVizinhos1(){
        Campo campo11 = new Campo(1,1);
       
        Campo campo22 = new Campo(2,2);
        campo22.adicionarVizinho(campo11);
                
        campo.adicionarVizinho(campo22);
        
        campo.abrir();
        
         assertTrue(campo22.isAberto() && campo11.isAberto());
    }
     @Test
    public void abrirComVizinhos(){
        Campo campo11 = new Campo(1,1);
        Campo campo12 = new Campo(1,2);
        campo12.minar();
       
        Campo campo22 = new Campo(2,2);
        campo22.adicionarVizinho(campo11);
        campo22.adicionarVizinho(campo12);
                
        campo.adicionarVizinho(campo22);
        
        campo.abrir();
        
         assertTrue(campo22.isAberto() && campo11.isFechado());
    }
    
    
    
}
