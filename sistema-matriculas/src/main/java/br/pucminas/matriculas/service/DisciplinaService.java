package br.pucminas.matriculas.service;

import br.pucminas.matriculas.model.Curso;
import br.pucminas.matriculas.model.Disciplina;
import br.pucminas.matriculas.model.Professor;
import br.pucminas.matriculas.model.enums.TipoDisciplina;
import br.pucminas.matriculas.exception.EntidadeNaoEncontradaException;
import br.pucminas.matriculas.model.enums.StatusDisciplina;
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
        if (codigo == null || codigo.isBlank() || nome == null || nome.isBlank()
                || creditos <= 0 || tipo == null || curso == null) {
            throw new IllegalArgumentException("Dados da disciplina invalidos");
        }
        if (disciplinaRepository.findByCodigo(codigo.trim()).isPresent()) {
            throw new IllegalArgumentException("Codigo de disciplina ja cadastrado");
        }
        Disciplina disciplina = new Disciplina(codigo.trim(), nome.trim(), creditos, tipo, curso);
        curso.adicionarDisciplina(disciplina);
        return disciplinaRepository.save(disciplina);
    }

    /**
     * Vincula o professor responsavel pela disciplina.
     */
    public Disciplina atribuirProfessor(Long disciplinaId, Professor professor) {
        if (professor == null) {
            throw new IllegalArgumentException("Professor e obrigatorio");
        }
        Disciplina disciplina = buscarPorId(disciplinaId);
        disciplina.setProfessor(professor);
        return disciplinaRepository.save(disciplina);
    }

    /**
     * Disciplinas ofertadas no periodo, com vagas ocupadas/disponiveis e tipo,
     * incluindo as que ja estao com inscricoes encerradas.
     */
    public List<Disciplina> listarDisponiveis() {
        return disciplinaRepository.findAll().stream()
            .filter(disciplina -> disciplina.getStatus() != StatusDisciplina.CANCELADA
                && disciplina.getStatus() != StatusDisciplina.ATIVA)
            .toList();
    }

    public List<Disciplina> listarPorCurso(Curso curso) {
        return disciplinaRepository.findByCurso(curso);
    }

    /**
     * Busca uma disciplina pelo id. Lanca EntidadeNaoEncontradaException se nao existir.
     */
    public Disciplina buscarPorId(Long id) {
        return disciplinaRepository.findById(id)
            .orElseThrow(() -> new EntidadeNaoEncontradaException("Disciplina nao encontrada: " + id));
    }
}
