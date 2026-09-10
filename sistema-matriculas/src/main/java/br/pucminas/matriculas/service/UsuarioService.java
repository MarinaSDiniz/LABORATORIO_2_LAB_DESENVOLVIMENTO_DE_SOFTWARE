package br.pucminas.matriculas.service;

import br.pucminas.matriculas.model.Aluno;
import br.pucminas.matriculas.model.Curso;
import br.pucminas.matriculas.model.Professor;
import br.pucminas.matriculas.model.Secretaria;
import br.pucminas.matriculas.repository.AlunoRepository;
import br.pucminas.matriculas.repository.ProfessorRepository;
import br.pucminas.matriculas.repository.UsuarioRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * US11 - Cadastrar professores e alunos.
 * Garante login unico entre todos os perfis.
 */
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final AlunoRepository alunoRepository;
    private final ProfessorRepository professorRepository;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          AlunoRepository alunoRepository,
                          ProfessorRepository professorRepository) {
        this.usuarioRepository = usuarioRepository;
        this.alunoRepository = alunoRepository;
        this.professorRepository = professorRepository;
    }

    /**
     * Cadastra um aluno vinculado a um curso.
     * Lanca LoginJaCadastradoException se o login ja existir.
     */
    public Aluno cadastrarAluno(String nome, String login, String senha, String matricula, Curso curso) {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    /**
     * Cadastra um professor.
     * Lanca LoginJaCadastradoException se o login ja existir.
     */
    public Professor cadastrarProfessor(String nome, String login, String senha) {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    /**
     * Cadastra um usuario da secretaria.
     */
    public Secretaria cadastrarSecretaria(String nome, String login, String senha) {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    public List<Aluno> listarAlunos() {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }

    public List<Professor> listarProfessores() {
        throw new UnsupportedOperationException("Implementar no Lab01S03");
    }
}
