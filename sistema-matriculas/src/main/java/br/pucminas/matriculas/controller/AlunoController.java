package br.pucminas.matriculas.controller;

import br.pucminas.matriculas.model.Aluno;
import br.pucminas.matriculas.model.Disciplina;
import br.pucminas.matriculas.model.Matricula;
import br.pucminas.matriculas.service.DisciplinaService;
import br.pucminas.matriculas.service.MatriculaService;
import br.pucminas.matriculas.service.SemestreService;
import br.pucminas.matriculas.model.Curriculo;
import br.pucminas.matriculas.service.CurriculoService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;

/**
 * Operacoes disponiveis ao aluno (US02, US03, US04, US05).
 */
@Controller
public class AlunoController {

    private final DisciplinaService disciplinaService;
    private final MatriculaService matriculaService;
    private final SemestreService semestreService;
    private final CurriculoService curriculoService;

    public AlunoController(DisciplinaService disciplinaService,
                           MatriculaService matriculaService,
                           SemestreService semestreService,
                           CurriculoService curriculoService) {
        this.disciplinaService = disciplinaService;
        this.matriculaService = matriculaService;
        this.semestreService = semestreService;
        this.curriculoService = curriculoService;
    }

    /**
     * US02 - disciplinas ofertadas no periodo, com vagas e tipo.
     */
    public List<Disciplina> consultarDisciplinasDisponiveis() {
        return disciplinaService.listarDisponiveis();
    }

    /**
     * US03 e US04 - matricula em disciplina obrigatoria ou optativa.
     */
    public Matricula matricular(Aluno aluno, Long disciplinaId) {
        return matriculaService.matricular(aluno, disciplinaService.buscarPorId(disciplinaId), semestreService.buscarSemestreAtivo());
    }

    /**
     * US05 - cancelamento de matricula dentro do periodo.
     */
    public void cancelarMatricula(Aluno aluno, Long disciplinaId) {
        matriculaService.cancelar(aluno, disciplinaService.buscarPorId(disciplinaId), semestreService.buscarSemestreAtivo());
    }

    /**
     * US08 - curriculo do curso do aluno no semestre com matriculas abertas.
     */
    public Optional<Curriculo> consultarCurriculo(Aluno aluno) {
        return curriculoService.buscarComDisciplinas(aluno.getCurso().getId(), semestreService.buscarSemestreAtivo());
    }

    /**
     * Matriculas ativas do aluno no semestre corrente.
     */
    public List<Matricula> consultarMinhasMatriculas(Aluno aluno) {
        return matriculaService.listarPorAluno(aluno, semestreService.buscarSemestreAtivo());
    }
}
