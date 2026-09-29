package br.pucminas.matriculas.service;

import br.pucminas.matriculas.exception.EntidadeNaoEncontradaException;
import br.pucminas.matriculas.model.Aluno;
import br.pucminas.matriculas.model.Curso;
import br.pucminas.matriculas.model.Professor;
import br.pucminas.matriculas.model.Secretaria;
import br.pucminas.matriculas.exception.LoginJaCadastradoException;
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
        validarLogin(login, senha);
        if (nome == null || nome.isBlank() || matricula == null || matricula.isBlank() || curso == null) {
            throw new IllegalArgumentException("Dados do aluno invalidos");
        }
        return alunoRepository.save(new Aluno(nome.trim(), login.trim(), senha, matricula.trim(), curso));
    }

    /**
     * Cadastra um professor.
     * Lanca LoginJaCadastradoException se o login ja existir.
     */
    public Professor cadastrarProfessor(String nome, String login, String senha) {
        validarLogin(login, senha);
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do professor e obrigatorio");
        }
        return professorRepository.save(new Professor(nome.trim(), login.trim(), senha));
    }

    /**
     * Cadastra um usuario da secretaria.
     */
    public Secretaria cadastrarSecretaria(String nome, String login, String senha) {
        validarLogin(login, senha);
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome da secretaria e obrigatorio");
        }
        return usuarioRepository.save(new Secretaria(nome.trim(), login.trim(), senha));
    }

    public List<Aluno> listarAlunos() {
        return alunoRepository.findAll();
    }

    /**
     * Busca um professor pelo id. Lanca EntidadeNaoEncontradaException se nao existir.
     */
    public Professor buscarProfessorPorId(Long id) {
        return professorRepository.findById(id)
            .orElseThrow(() -> new EntidadeNaoEncontradaException("Professor nao encontrado: " + id));
    }

    public List<Professor> listarProfessores() {
        return professorRepository.findAll();
    }

    private void validarLogin(String login, String senha) {
        if (login == null || login.isBlank() || senha == null || senha.isBlank()) {
            throw new IllegalArgumentException("Login e senha sao obrigatorios");
        }
        if (usuarioRepository.existsByLogin(login.trim())) {
            throw new LoginJaCadastradoException("Login ja cadastrado: " + login);
        }
    }
}
