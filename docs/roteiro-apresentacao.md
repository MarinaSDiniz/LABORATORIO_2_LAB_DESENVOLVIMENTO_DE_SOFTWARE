# Roteiro de apresentação — Sistema de Matrículas (Lab01S03)

Segue os itens de "Testes ao vivo" da planilha de avaliação (2 a 14). Validado de ponta a ponta em 2026-09-29 com banco zerado.

## 0. Preparação

```bash
cd sistema-matriculas
rm -f data/matriculas.mv.db          # banco limpo -> CargaInicial recria os dados de demo
mvn -q -DskipTests package
java -jar target/sistema-matriculas-1.0.0-SNAPSHOT.jar
```

O `0 - Sair` de cada menu volta ao login; **login em branco encerra o programa**.

| Perfil | Login | Senha |
|---|---|---|
| Secretaria | secretaria | secretaria |
| Aluno | aluno | aluno |
| Aluno 2 | aluno2 | aluno2 |
| Professor (id 3) | professor | professor |

Dados de demo: curso 1; disciplinas 1 (Projeto de Software, obrigatória), 2 (Qualidade de Software, optativa) e **3 (Arquitetura de Software, obrigatória, já com 59 alunos)**; semestre 1 aberto.
`Secretaria → 8` lista cursos, disciplinas, professores e semestres com ids.

## Item 2 — Login
- `aluno` / `x` → `Erro: Login ou senha invalidos`. Depois `secretaria` / `secretaria` entra.

## Item 11 — Secretaria cadastra e gera currículo
- `2`: `ENG004`, `Banco de Dados`, `4`, `OBRIGATORIA`, `1` → id 4
- `2`: `ENG005`, `Redes`, `4`, `OBRIGATORIA`, `1` → id 5
- `2`: `ENG006`, `Algoritmos`, `4`, `OBRIGATORIA`, `1` → id 6
- `2`: `ENG007`, `IA`, `4`, `OPTATIVA`, `1` → id 7
- `2`: `ENG008`, `Etica`, `2`, `OPTATIVA`, `1` → id 8
- `3`: `Maria Silva`, `maria`, `123`, `2026002`, `1`
- `4`: `Ana Prof`, `ana`, `123`
- `7`: disciplina `1`, professor `3` (vincula o professor de demo)
- `5`: curso `1`, semestre `1`, `1,2,3,4,5,6,7,8`
- `0`

## Itens 3, 4, 5, 9 e 12 — Aluna `maria` / `123`
- `5` → currículo do curso.
- `2` → `1`: **item 3** + **item 12** (`Cobranca notificada: aluno=Maria Silva, semestre=..., disciplinas=1`).
- `2` → `4`, `2` → `5`, `2` → `6` (4 obrigatórias).
- `2` → `3`: **item 4** → `Limite de disciplinas OBRIGATORIA excedido`.
- `2` → `2`, `2` → `7` (2 optativas).
- `2` → `8`: **item 5** → `Limite de disciplinas OPTATIVA excedido`.
- `3` → `7`: **item 9** → `Matricula cancelada.`; `4` confirma.
- `0`

## Item 7 — 60 alunos
- `aluno` / `aluno`: `1` mostra Arquitetura com `59 ocupadas / 1 disponiveis`.
- `2` → `3` → matrícula realizada (60ª). `1` → agora `inscricoes encerradas`.
- `2` → `1` (para a disciplina 1 ter 3 alunos no item 8). `0`
- `aluno2` / `aluno2`: `2` → `3` → `Erro: Disciplina sem vagas` (61º bloqueado). `2` → `1`. `0`

## Item 10 — Professor
- `professor` / `professor`: `1` → `1` → Maria Silva, Aluno Demonstracao, Aluno Demonstracao 2.

## Item 14 — Entradas inválidas
- No menu: `abc` → `Informe um numero inteiro valido`; `99` → `Opcao invalida`.
- Outros: id inexistente, tipo `xyz` na disciplina, login repetido, curso sem nome, currículo com `a,b` — todos mostram erro e seguem no menu.

## Item 13 — Persistência
- `0` e login em branco (fecha). Rode o `java -jar ...` de novo, entre com `maria` / `123`, `4` → matrículas continuam lá.

## Item 8 — Fechar o período (faça por último)
- `secretaria`: `6` → `1` → lista as canceladas (< 3 alunos) e os alunos notificados.
- `8` → disciplinas 1 e 3 aparecem `ATIVA`; as demais `CANCELADA`.

## Item 6 — Fora do período
- `maria` / `123`: `2` → `2` e `3` → `1` → `Erro: Periodo de matriculas fechado`.

Para apresentar de novo, apague o banco (passo 0).
