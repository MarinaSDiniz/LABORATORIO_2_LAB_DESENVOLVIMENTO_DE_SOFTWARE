package br.pucminas.matriculas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Ponto de entrada do Sistema de Matriculas.
 *
 * Aplicacao de linha de comando: o Spring Boot sobe o contexto, conecta no H2 e
 * entrega o controle ao MenuPrincipal (CommandLineRunner).
 */
@SpringBootApplication
public class MatriculasApplication {

    public static void main(String[] args) {
        SpringApplication.run(MatriculasApplication.class, args);
    }
}
