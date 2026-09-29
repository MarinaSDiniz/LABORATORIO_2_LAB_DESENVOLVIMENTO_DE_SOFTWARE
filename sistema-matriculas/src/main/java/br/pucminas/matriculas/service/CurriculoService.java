package br.pucminas.matriculas.service;

import br.pucminas.matriculas.model.Curriculo;
import br.pucminas.matriculas.model.Curso;
import br.pucminas.matriculas.model.Disciplina;
import br.pucminas.matriculas.model.Semestre;
import br.pucminas.matriculas.repository.CurriculoRepository;
import java.util.List;
import java.util.Optional;
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
        if (curso == null || semestre == null || disciplinas == null) {
            throw new IllegalArgumentException("Curso, semestre e disciplinas sao obrigatorios");
        }
        Curriculo curriculo = curriculoRepository.findByCursoAndSemestre(curso, semestre)
                .orElseGet(() -> new Curriculo(curso, semestre));
        curriculo.getDisciplinas().clear();
        disciplinas.forEach(curriculo::adicionarDisciplina);
        return curriculoRepository.save(curriculo);
    }

    /**
     * Curriculos publicados para o semestre, consultados pelo aluno.
     */
    public List<Curriculo> listarPorSemestre(Semestre semestre) {
        return curriculoRepository.findBySemestre(semestre);
    }

    /**
     * Curriculo do curso no semestre com as disciplinas ja carregadas, exibido ao aluno.
     */
    public Optional<Curriculo> buscarComDisciplinas(Long cursoId, Semestre semestre) {
        return curriculoRepository.findByCursoIdAndSemestreComDisciplinas(cursoId, semestre);
    }

    public Curriculo buscarPorCursoESemestre(Curso curso, Semestre semestre) {
        return curriculoRepository.findByCursoAndSemestre(curso, semestre).orElse(null);
    }
}
