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
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    /** US10 */
    public Disciplina cadastrarDisciplina(String codigo, String nome, int creditos,
                                          TipoDisciplina tipo, Long cursoId) {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    /** US11 */
    public Aluno cadastrarAluno(String nome, String login, String senha, String matricula, Long cursoId) {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    /** US11 */
    public Professor cadastrarProfessor(String nome, String login, String senha) {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    /** US08 */
    public Curriculo gerarCurriculo(Long cursoId, Long semestreId, List<Long> disciplinaIds) {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    public Semestre criarSemestre(int ano, int periodo, LocalDate inicioMatriculas, LocalDate fimMatriculas) {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    /** US06 - devolve as disciplinas canceladas por nao atingirem o minimo de 3 alunos. */
    public List<Disciplina> encerrarPeriodoMatriculas(Long semestreId) {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }
}
