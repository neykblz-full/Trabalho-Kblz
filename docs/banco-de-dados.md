# Banco de Dados

O sistema utiliza MySQL, com criacao e manutencao do banco pelo MySQL Workbench.

## Entidades

- `pacientes`: armazena os dados das pessoas atendidas.
- `profissionais`: armazena os profissionais responsaveis pelas consultas.
- `consultas`: registra cada consulta marcada.

## Relacionamentos

- Uma consulta pertence a um paciente.
- Uma consulta pertence a um profissional.

## Script

O script de criacao do banco esta em `sql/schema.sql` e deve ser executado no MySQL Workbench.
