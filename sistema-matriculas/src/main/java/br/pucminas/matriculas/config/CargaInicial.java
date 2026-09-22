package br.pucminas.matriculas.config;

import br.pucminas.matriculas.service.CursoService;
import br.pucminas.matriculas.service.DisciplinaService;
import br.pucminas.matriculas.service.SemestreService;
import br.pucminas.matriculas.service.UsuarioService;
import br.pucminas.matriculas.model.Curso;
import br.pucminas.matriculas.model.Semestre;
import br.pucminas.matriculas.model.enums.TipoDisciplina;
import java.time.LocalDate;
import org.springframework.stereotype.Component;

/**
 * Carga de dados de demonstracao (cursos, disciplinas, usuarios e um semestre aberto)
 * usada na apresentacao do prototipo.
 *
 * Nao roda automaticamente: o Lab01S03 decide onde chamar carregar().
 */
@Component
public class CargaInicial {

    private final CursoService cursoService;
    private final DisciplinaService disciplinaService;
    private final UsuarioService usuarioService;
    private final SemestreService semestreService;

    public CargaInicial(CursoService cursoService,
                        DisciplinaService disciplinaService,
                        UsuarioService usuarioService,
                        SemestreService semestreService) {
        this.cursoService = cursoService;
        this.disciplinaService = disciplinaService;
        this.usuarioService = usuarioService;
        this.semestreService = semestreService;
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
        usuarioService.cadastrarSecretaria("Secretaria", "secretaria", "secretaria");
        usuarioService.cadastrarAluno("Aluno Demonstracao", "aluno", "aluno", "2026001", curso);
        usuarioService.cadastrarProfessor("Professor Demonstracao", "professor", "professor");
        Semestre semestre = semestreService.criar(LocalDate.now().getYear(), 1,
            LocalDate.now().minusDays(1), LocalDate.now().plusDays(30));
        semestreService.abrirMatriculas(semestre.getId());
    }
}
