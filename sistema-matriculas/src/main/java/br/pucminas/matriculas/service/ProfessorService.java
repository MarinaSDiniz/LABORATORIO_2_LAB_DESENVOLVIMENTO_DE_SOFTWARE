package br.pucminas.matriculas.service;

import br.pucminas.matriculas.model.Aluno;
import br.pucminas.matriculas.model.Disciplina;
import br.pucminas.matriculas.model.Professor;
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
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    /**
     * Alunos com matricula ativa em uma disciplina do professor.
     * Deve recusar disciplina que nao pertenca ao professor informado.
     */
    public List<Aluno> listarAlunosMatriculados(Professor professor, Long disciplinaId) {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }
}
