package br.pucminas.matriculas.controller;

import br.pucminas.matriculas.model.Aluno;
import br.pucminas.matriculas.model.Curriculo;
import br.pucminas.matriculas.model.Curso;
import br.pucminas.matriculas.model.Disciplina;
import br.pucminas.matriculas.model.Professor;
import br.pucminas.matriculas.model.Semestre;
import br.pucminas.matriculas.model.enums.TipoDisciplina;
import br.pucminas.matriculas.service.CurriculoService;
import br.pucminas.matriculas.service.CursoService;
import br.pucminas.matriculas.service.DisciplinaService;
import br.pucminas.matriculas.service.SemestreService;
import br.pucminas.matriculas.service.UsuarioService;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;

/**
 * Operacoes disponiveis a secretaria (US06, US08, US09, US10, US11).
 */
@Controller
public class SecretariaController {

    private final CursoService cursoService;
    private final DisciplinaService disciplinaService;
    private final CurriculoService curriculoService;
    private final SemestreService semestreService;
    private final UsuarioService usuarioService;

    public SecretariaController(CursoService cursoService,
                                DisciplinaService disciplinaService,
                                CurriculoService curriculoService,
                                SemestreService semestreService,
                                UsuarioService usuarioService) {
        this.cursoService = cursoService;
        this.disciplinaService = disciplinaService;
        this.curriculoService = curriculoService;
        this.semestreService = semestreService;
        this.usuarioService = usuarioService;
    }

    /** US09 */
    public Curso cadastrarCurso(String nome, int creditos) {
        return cursoService.cadastrar(nome, creditos);
    }

    /** US10 */
    public Disciplina cadastrarDisciplina(String codigo, String nome, int creditos,
                                          TipoDisciplina tipo, Long cursoId) {
        return disciplinaService.cadastrar(codigo, nome, creditos, tipo, cursoService.buscarPorId(cursoId));
    }

    /** US11 */
    public Aluno cadastrarAluno(String nome, String login, String senha, String matricula, Long cursoId) {
        return usuarioService.cadastrarAluno(nome, login, senha, matricula, cursoService.buscarPorId(cursoId));
    }

    /** US11 */
    public Professor cadastrarProfessor(String nome, String login, String senha) {
        return usuarioService.cadastrarProfessor(nome, login, senha);
    }

    /** US08 */
    public Curriculo gerarCurriculo(Long cursoId, Long semestreId, List<Long> disciplinaIds) {
        Curso curso = cursoService.buscarPorId(cursoId);
        Semestre semestre = semestreService.buscarPorId(semestreId);
        List<Disciplina> disciplinas = disciplinaIds.stream().map(disciplinaService::buscarPorId).toList();
        return curriculoService.gerar(curso, semestre, disciplinas);
    }

    public Semestre criarSemestre(int ano, int periodo, LocalDate inicioMatriculas, LocalDate fimMatriculas) {
        return semestreService.criar(ano, periodo, inicioMatriculas, fimMatriculas);
    }

    /** US06 - devolve as disciplinas canceladas por nao atingirem o minimo de 3 alunos e os alunos a notificar. */
    public Map<Disciplina, List<Aluno>> encerrarPeriodoMatriculas(Long semestreId) {
        return semestreService.encerrarMatriculas(semestreId);
    }

    /** US07 - vincula o professor que leciona a disciplina. */
    public Disciplina atribuirProfessor(Long disciplinaId, Long professorId) {
        return disciplinaService.atribuirProfessor(disciplinaId, usuarioService.buscarProfessorPorId(professorId));
    }

    public List<Curso> listarCursos() {
        return cursoService.listar();
    }

    public List<Disciplina> listarDisciplinas() {
        return disciplinaService.listarTodas();
    }

    public List<Professor> listarProfessores() {
        return usuarioService.listarProfessores();
    }

    public List<Semestre> listarSemestres() {
        return semestreService.listar();
    }
}
