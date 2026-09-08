package br.pucminas.matriculas.config;

import br.pucminas.matriculas.service.CursoService;
import br.pucminas.matriculas.service.DisciplinaService;
import br.pucminas.matriculas.service.SemestreService;
import br.pucminas.matriculas.service.UsuarioService;
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
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }
}
