package br.pucminas.matriculas.config;

import br.pucminas.matriculas.service.CursoService;
import br.pucminas.matriculas.service.DisciplinaService;
import br.pucminas.matriculas.service.SemestreService;
import br.pucminas.matriculas.service.UsuarioService;
import br.pucminas.matriculas.model.Aluno;
import br.pucminas.matriculas.model.Curso;
import br.pucminas.matriculas.model.Disciplina;
import br.pucminas.matriculas.model.Matricula;
import br.pucminas.matriculas.model.Semestre;
import br.pucminas.matriculas.model.enums.TipoDisciplina;
import br.pucminas.matriculas.repository.MatriculaRepository;
import java.time.LocalDate;
import org.springframework.stereotype.Component;

/**
 * Carga de dados de demonstracao (cursos, disciplinas, usuarios e um semestre aberto)
 * usada na apresentacao do prototipo.
 *
 * Roda na inicializacao (MenuPrincipal) e so quando o banco esta vazio.
 * A disciplina ENG003 ja nasce com 59 alunos para demonstrar ao vivo a 60a matricula
 * (inscricoes encerradas) e a recusa da 61a.
 */
@Component
public class CargaInicial {

    private static final int ALUNOS_NA_TURMA_QUASE_CHEIA = Disciplina.CAPACIDADE_MAXIMA - 1;

    private final CursoService cursoService;
    private final DisciplinaService disciplinaService;
    private final UsuarioService usuarioService;
    private final SemestreService semestreService;
    private final MatriculaRepository matriculaRepository;

    public CargaInicial(CursoService cursoService,
                        DisciplinaService disciplinaService,
                        UsuarioService usuarioService,
                        SemestreService semestreService,
                        MatriculaRepository matriculaRepository) {
        this.cursoService = cursoService;
        this.disciplinaService = disciplinaService;
        this.usuarioService = usuarioService;
        this.semestreService = semestreService;
        this.matriculaRepository = matriculaRepository;
    }

    public void carregar() {
        if (!cursoService.listar().isEmpty() || !usuarioService.listarAlunos().isEmpty()) {
            return;
        }
        Curso curso = cursoService.cadastrar("Engenharia de Software", 40);
        disciplinaService.cadastrar("ENG001", "Projeto de Software", 4,
            TipoDisciplina.OBRIGATORIA, curso);
        disciplinaService.cadastrar("ENG002", "Qualidade de Software", 4,
            TipoDisciplina.OPTATIVA, curso);
        Disciplina turmaQuaseCheia = disciplinaService.cadastrar("ENG003", "Arquitetura de Software", 4,
            TipoDisciplina.OBRIGATORIA, curso);
        usuarioService.cadastrarSecretaria("Secretaria", "secretaria", "secretaria");
        usuarioService.cadastrarAluno("Aluno Demonstracao", "aluno", "aluno", "2026001", curso);
        usuarioService.cadastrarProfessor("Professor Demonstracao", "professor", "professor");
        usuarioService.cadastrarAluno("Aluno Demonstracao 2", "aluno2", "aluno2", "2026000", curso);
        Semestre semestre = semestreService.criar(LocalDate.now().getYear(), 1,
            LocalDate.now().minusDays(1), LocalDate.now().plusDays(30));
        semestreService.abrirMatriculas(semestre.getId());
        preencherTurma(turmaQuaseCheia, curso, semestre);
    }

    private void preencherTurma(Disciplina disciplina, Curso curso, Semestre semestre) {
        for (int i = 1; i <= ALUNOS_NA_TURMA_QUASE_CHEIA; i++) {
            String numero = String.format("%02d", i);
            Aluno aluno = usuarioService.cadastrarAluno("Aluno Turma " + numero, "turma" + numero, "123",
                "2026T" + numero, curso);
            matriculaRepository.save(new Matricula(aluno, disciplina, semestre, disciplina.getTipo()));
        }
    }
}
