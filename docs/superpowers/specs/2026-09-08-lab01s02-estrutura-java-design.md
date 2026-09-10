# Lab01S02 — Estrutura do projeto Java (Sistema de Matrículas)

**Data:** 2026-09-08
**Entrega:** Lab01S02 — Projeto Estrutural (4 pontos)
**Escopo:** classes, atributos e *stubs* dos métodos. Nenhuma regra de negócio implementada.

## 1. Decisões

| Decisão | Escolha | Motivo |
|---|---|---|
| Build | Maven | Layout padrão `src/main/java`, abre em qualquer IDE sem configuração — grupo de 4 pessoas com IDEs diferentes. |
| Framework | Spring Boot 3.5.6 | Dá as camadas controller/service/repository e a injeção de dependência prontas. |
| Persistência | H2 embedded (arquivo `./data/matriculas.mv.db`) via Spring Data JPA | O enunciado permite persistência em arquivo; o H2 entrega isso com SQL de verdade e sem servidor externo. |
| Interface | CLI via `CommandLineRunner` | Autorizada explicitamente pelo enunciado. Sem `spring-boot-starter-web`. |
| Java | 21 (LTS) | Compatível com Spring Boot 3.5 e com JDKs mais antigos que os integrantes possam ter. |
| Organização | Por camada | Espelha diretamente o diagrama de classes em camadas exigido no S02. |

## 2. Camadas e responsabilidades

```
ui (CLI)  →  controller  →  service  →  repository  →  H2
                              ↓
                     integration (Sistema de Cobranças)
```

- **`model`** — entidades JPA e enums. É o que o diagrama de classes representa.
- **`repository`** — interfaces Spring Data (`JpaRepository`). No diagrama aparecem como `«interface»`, sem implementação: quem implementa é o framework em tempo de execução.
- **`service`** — **todas** as regras de negócio. Limites de 4 obrigatórias / 2 optativas, mínimo de 3 e máximo de 60 alunos, período de matrícula aberto e notificação de cobrança vivem aqui e em nenhum outro lugar.
- **`controller`** — traduz entrada da interface em chamadas de serviço. Não valida regra.
- **`ui`** — menus de console. Só `ConsoleIO` toca `Scanner`/`System.out`.
- **`integration`** — `SistemaCobrancaClient` como interface, porque o Sistema de Cobranças é ator **externo** no diagrama de caso de uso.
- **`exception`** — cada regra violada tem exceção nomeada, herdando de `MatriculaException`.

## 3. Regras de negócio e onde elas moram

| Regra | Origem | Local previsto |
|---|---|---|
| Até 4 obrigatórias e 2 optativas por semestre | US03, US04 | `MatriculaService.matricular` + `MatriculaRepository.countByAlunoAndSemestreAndTipoAndStatus` |
| Máximo de 60 alunos; inscrições encerram ao atingir | US02, US03 | `Disciplina.CAPACIDADE_MAXIMA`, `Disciplina.aceitaNovaMatricula`, `MatriculaService` |
| Mínimo de 3 alunos ao fim do período, senão cancela | US06 | `Disciplina.MINIMO_ALUNOS`, `SemestreService.encerrarMatriculas` |
| Matrícula/cancelamento só no período | US05 | `Semestre.periodoMatriculaAberto`, `MatriculaService` |
| Cancelamento libera a vaga | US05 | `Matricula.cancelar` (status `CANCELADA`) |
| Notificar Sistema de Cobranças a cada matrícula | US12 | `MatriculaService` → `SistemaCobrancaClient.notificarMatricula` |
| Login único entre todos os perfis | US11 | `UsuarioService` + `UsuarioRepository.existsByLogin` |
| Professor só vê as próprias disciplinas | US07 | `ProfessorService.listarAlunosMatriculados` |

## 4. Cobertura das histórias de usuário

US01 `AutenticacaoService` · US02 `DisciplinaService` · US03/US04/US05 `MatriculaService` ·
US06 `SemestreService` · US07 `ProfessorService` · US08 `CurriculoService` ·
US09 `CursoService` · US10 `DisciplinaService` · US11 `UsuarioService` · US12 `SistemaCobrancaClient`.

## 5. Convenção dos stubs

Todo método não implementado tem corpo `throw new UnsupportedOperationException("Implementar no Lab01S03")`.
Isso torna o que falta rastreável por busca simples:

```bash
grep -rn "Lab01S03" src/main/java | wc -l
```

**Exceção deliberada:** `MenuPrincipal.run()` tem corpo real (imprime um banner) para que
`mvn spring-boot:run` suba sem quebrar — necessário para as apresentações semanais.

## 6. Evidência de que a estrutura funciona

```
mvn compile        → BUILD SUCCESS, 51 classes
mvn spring-boot:run → contexto sobe, H2 criado em ./data/matriculas.mv.db
```

Tabelas geradas pelo Hibernate a partir das entidades (schema validado):
`ALUNO, CURRICULO, CURRICULO_DISCIPLINA, CURSO, DISCIPLINA, MATRICULA, PROFESSOR, SECRETARIA, SEMESTRE, USUARIO`

## 7. Limites e riscos

- **`ddl-auto=update`** é conveniente no protótipo, mas não versiona schema. Se o modelo mudar muito no S03, apagar `data/` e recriar é mais seguro que confiar no update.
- **Senha em texto puro** no campo `Usuario.senha`. Aceitável no escopo acadêmico; se a professora cobrar, `BCryptPasswordEncoder` entra sem mexer na estrutura.
- **Herança JOINED** em `Usuario` gera JOIN em toda consulta de usuário. Irrelevante nesta escala; foi escolhida por ser a que mais se parece com o diagrama de classes.
- **Spring Data esconde implementação de repository.** Precisa ser explicado na apresentação, porque a professora avalia alinhamento modelo↔código e os repositories no diagrama não terão corpo de método.

## 8. Gatilho de revisão

Revisar este documento se: (a) a professora pedir correção no diagrama de classes que mude entidades ou associações; (b) o grupo decidir trocar CLI por interface web; (c) o S03 exigir persistência que o `ddl-auto=update` não acompanhe.
