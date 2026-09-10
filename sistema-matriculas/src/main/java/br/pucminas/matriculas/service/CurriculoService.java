package br.pucminas.matriculas.service;

import br.pucminas.matriculas.model.Curriculo;
import br.pucminas.matriculas.model.Curso;
import br.pucminas.matriculas.model.Disciplina;
import br.pucminas.matriculas.model.Semestre;
import br.pucminas.matriculas.repository.CurriculoRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * US08 - Gerar curriculo do semestre.
 * O curriculo reune as disciplinas que um curso oferece em um semestre e e o que
 * fica visivel ao aluno durante o periodo de matriculas.
 */
@Service
public class CurriculoService {

    private final CurriculoRepository curriculoRepository;

    public CurriculoService(CurriculoRepository curriculoRepository) {
        this.curriculoRepository = curriculoRepository;
    }

    /**
     * Gera o curriculo de um curso para um semestre com as disciplinas informadas.
     */
    public Curriculo gerar(Curso curso, Semestre semestre, List<Disciplina> disciplinas) {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    /**
     * Curriculos publicados para o semestre, consultados pelo aluno.
     */
    public List<Curriculo> listarPorSemestre(Semestre semestre) {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    public Curriculo buscarPorCursoESemestre(Curso curso, Semestre semestre) {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }
}
