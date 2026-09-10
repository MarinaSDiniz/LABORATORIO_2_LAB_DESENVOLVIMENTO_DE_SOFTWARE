package br.pucminas.matriculas.ui;

import java.util.Scanner;
import org.springframework.stereotype.Component;

/**
 * Isola a leitura do teclado e a escrita no console.
 * Nenhuma outra classe da interface deve usar Scanner ou System.out diretamente.
 */
@Component
public class ConsoleIO {

    private final Scanner scanner = new Scanner(System.in);

    public String lerTexto(String rotulo) {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    public String lerSenha(String rotulo) {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    public int lerInteiro(String rotulo) {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    public Long lerId(String rotulo) {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    public void exibir(String mensagem) {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    public void exibirErro(String mensagem) {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    public void exibirTitulo(String titulo) {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }
}
