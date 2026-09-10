package br.pucminas.matriculas.service;

import br.pucminas.matriculas.model.Curso;
import br.pucminas.matriculas.repository.CursoRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * US09 - Cadastrar curso.
 * Um curso tem nome e numero de creditos, e nenhum dos dois pode faltar.
 */
@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    /**
     * Cadastra um curso. Rejeita nome vazio, creditos nao positivos e nome duplicado.
     */
    public Curso cadastrar(String nome, int creditos) {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    public List<Curso> listar() {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    /**
     * Busca um curso pelo id. Lanca EntidadeNaoEncontradaException se nao existir.
     */
    public Curso buscarPorId(Long id) {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }
}
