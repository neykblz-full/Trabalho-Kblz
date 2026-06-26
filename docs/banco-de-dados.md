# Banco de Dados

O sistema utiliza SQLite, um banco relacional simples e adequado para aplicacoes de console.

## Entidades

- `pacientes`: armazena os dados das pessoas atendidas.
- `profissionais`: armazena os profissionais responsaveis pelas consultas.
- `consultas`: registra cada consulta marcada.

## Relacionamentos

- Uma consulta pertence a um paciente.
- Uma consulta pertence a um profissional.

## Script

O script de criacao do banco esta em `sql/schema.sql`.
