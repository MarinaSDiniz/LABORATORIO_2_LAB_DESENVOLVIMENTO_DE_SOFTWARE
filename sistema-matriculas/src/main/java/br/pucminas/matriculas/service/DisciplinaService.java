package br.pucminas.matriculas.service;

import br.pucminas.matriculas.model.Curso;
import br.pucminas.matriculas.model.Disciplina;
import br.pucminas.matriculas.model.Professor;
import br.pucminas.matriculas.model.enums.TipoDisciplina;
import br.pucminas.matriculas.repository.DisciplinaRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * US02 - Consultar disciplinas disponiveis.
 * US10 - Cadastrar disciplina.
 *
 * Toda disciplina nasce vinculada a um curso e com o limite padrao de 60 vagas.
 */
@Service
public class DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;

    public DisciplinaService(DisciplinaRepository disciplinaRepository) {
        this.disciplinaRepository = disciplinaRepository;
    }

    /**
     * Cadastra uma disciplina vinculada a um curso existente, com capacidade maxima de 60.
     */
    public Disciplina cadastrar(String codigo, String nome, int creditos, TipoDisciplina tipo, Curso curso) {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    /**
     * Vincula o professor responsavel pela disciplina.
     */
    public Disciplina atribuirProfessor(Long disciplinaId, Professor professor) {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    /**
     * Disciplinas ofertadas no periodo, com vagas ocupadas/disponiveis e tipo,
     * incluindo as que ja estao com inscricoes encerradas.
     */
    public List<Disciplina> listarDisponiveis() {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    public List<Disciplina> listarPorCurso(Curso curso) {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    /**
     * Busca uma disciplina pelo id. Lanca EntidadeNaoEncontradaException se nao existir.
     */
    public Disciplina buscarPorId(Long id) {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }
}
