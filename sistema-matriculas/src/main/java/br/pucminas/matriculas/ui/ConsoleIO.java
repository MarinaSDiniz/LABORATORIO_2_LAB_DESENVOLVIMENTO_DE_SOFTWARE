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
        System.out.print(rotulo + ": ");
        return scanner.nextLine().trim();
    }

    public String lerSenha(String rotulo) {
        return lerTexto(rotulo);
    }

    public int lerInteiro(String rotulo) {
        while (true) {
            try {
                return Integer.parseInt(lerTexto(rotulo));
            } catch (NumberFormatException ex) {
                exibirErro("Informe um numero inteiro valido.");
            }
        }
    }

    public Long lerId(String rotulo) {
        while (true) {
            try {
                return Long.valueOf(lerTexto(rotulo));
            } catch (NumberFormatException ex) {
                exibirErro("Informe um identificador valido.");
            }
        }
    }

    public void exibir(String mensagem) {
        System.out.println(mensagem);
    }

    public void exibirErro(String mensagem) {
        System.err.println("Erro: " + mensagem);
    }

    public void exibirTitulo(String titulo) {
        System.out.println("\n=== " + titulo + " ===");
    }
}
