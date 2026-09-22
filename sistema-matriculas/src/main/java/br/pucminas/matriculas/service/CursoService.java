package br.pucminas.matriculas.service;

import br.pucminas.matriculas.model.Curso;
import br.pucminas.matriculas.exception.EntidadeNaoEncontradaException;
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
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do curso e obrigatorio");
        }
        if (creditos <= 0) {
            throw new IllegalArgumentException("Creditos devem ser positivos");
        }
        if (cursoRepository.existsByNome(nome.trim())) {
            throw new IllegalArgumentException("Curso ja cadastrado");
        }
        return cursoRepository.save(new Curso(nome.trim(), creditos));
    }

    public List<Curso> listar() {
        return cursoRepository.findAll();
    }

    /**
     * Busca um curso pelo id. Lanca EntidadeNaoEncontradaException se nao existir.
     */
    public Curso buscarPorId(Long id) {
        return cursoRepository.findById(id)
            .orElseThrow(() -> new EntidadeNaoEncontradaException("Curso nao encontrado: " + id));
    }
}
