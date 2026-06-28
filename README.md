# Agenda de Consultas Kblz

Sistema de console desenvolvido em Java para organizar pacientes, profissionais e consultas. O projeto integra os conteúdos das disciplinas de Projeto de Desenvolvimento de Software (PDS) e Programação com Acesso a Banco de Dados (PABD).

## Equipe

- Ney Eduardo
- Thierry Almeida
- Josenildo Araujo

## Funcionalidades

- Cadastro, consulta, atualização e exclusão de pacientes.
- Cadastro, consulta, atualização e exclusão de profissionais.
- Agendamento, consulta, atualização e exclusão de consultas.
- Associação de cada consulta a um paciente e a um profissional.
- Persistência dos dados em banco MySQL.

## Tecnologias

- Java e orientação a objetos
- JDBC
- MySQL e MySQL Workbench
- Eclipse IDE
- Git e GitHub
- Scrum e Kanban

## Estrutura do projeto

```text
src/       Código-fonte Java
sql/       Script de criação do banco de dados
docs/      Documentação, planejamento e diagramas
imagens/   Registros visuais da aplicação
```

## Como executar

1. No MySQL Workbench, execute o arquivo [`sql/schema.sql`](sql/schema.sql).
2. Importe este repositório no Eclipse como um projeto existente.
3. Adicione o MySQL Connector/J ao Build Path do projeto.
4. Confira o usuário e a senha do MySQL em `ConexaoMySQL.java`.
5. Execute a classe `Main.java`.

O sistema utiliza o banco `trabalho_kblz`, criado automaticamente pelo script SQL.

## Documentação

- [Quadro Kanban](https://github.com/users/neykblz-full/projects/5)
- [Banco de dados](docs/banco-de-dados.md)
- [Instruções de execução](docs/execucao.md)
- [Planejamento ágil](docs/planejamento-agil.md)
- [Diagramas](docs/diagramas.md)
- [Interface do sistema](docs/capturas-de-tela.md)
