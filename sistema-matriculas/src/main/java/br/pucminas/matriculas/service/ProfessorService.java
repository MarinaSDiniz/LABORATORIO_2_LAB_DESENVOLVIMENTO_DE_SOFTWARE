package br.pucminas.matriculas.service;

import br.pucminas.matriculas.model.Aluno;
import br.pucminas.matriculas.model.Disciplina;
import br.pucminas.matriculas.model.Professor;
import br.pucminas.matriculas.exception.EntidadeNaoEncontradaException;
import br.pucminas.matriculas.repository.DisciplinaRepository;
import br.pucminas.matriculas.repository.MatriculaRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * US07 - Consultar alunos matriculados.
 * O professor so enxerga as disciplinas vinculadas a ele.
 */
@Service
public class ProfessorService {

    private final DisciplinaRepository disciplinaRepository;
    private final MatriculaRepository matriculaRepository;

    public ProfessorService(DisciplinaRepository disciplinaRepository,
                            MatriculaRepository matriculaRepository) {
        this.disciplinaRepository = disciplinaRepository;
        this.matriculaRepository = matriculaRepository;
    }

    /**
     * Disciplinas lecionadas pelo professor autenticado.
     */
    public List<Disciplina> listarDisciplinasDoProfessor(Professor professor) {
        return disciplinaRepository.findByProfessor(professor);
    }

    /**
     * Alunos com matricula ativa em uma disciplina do professor.
     * Deve recusar disciplina que nao pertenca ao professor informado.
     */
    public List<Aluno> listarAlunosMatriculados(Professor professor, Long disciplinaId) {
        Disciplina disciplina = disciplinaRepository.findById(disciplinaId)
            .orElseThrow(() -> new EntidadeNaoEncontradaException("Disciplina nao encontrada: " + disciplinaId));
        if (disciplina.getProfessor() == null || !disciplina.getProfessor().equals(professor)) {
            throw new EntidadeNaoEncontradaException("Disciplina nao pertence ao professor informado");
        }
        return matriculaRepository.findByDisciplinaAndStatus(
                disciplina, br.pucminas.matriculas.model.enums.StatusMatricula.ATIVA)
            .stream().map(br.pucminas.matriculas.model.Matricula::getAluno).toList();
    }
}
