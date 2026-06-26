package br.com.equipekblz.agendakblz.visao;

import br.com.equipekblz.agendakblz.dao.ConsultaDAO;
import br.com.equipekblz.agendakblz.dao.PacienteDAO;
import br.com.equipekblz.agendakblz.dao.ProfissionalDAO;
import br.com.equipekblz.agendakblz.modelo.Consulta;
import br.com.equipekblz.agendakblz.modelo.Paciente;
import br.com.equipekblz.agendakblz.modelo.Profissional;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MenuPrincipal {
    private final Scanner scanner = new Scanner(System.in);
    private final PacienteDAO pacienteDAO = new PacienteDAO();
    private final ProfissionalDAO profissionalDAO = new ProfissionalDAO();
    private final ConsultaDAO consultaDAO = new ConsultaDAO();

    public void exibir() {
        int opcao;

        do {
            System.out.println("\n==============================================");
            System.out.println("          AGENDA DE CONSULTAS KBLZ");
            System.out.println("==============================================");
            System.out.println("1 - Gerenciar pacientes");
            System.out.println("2 - Gerenciar profissionais");
            System.out.println("3 - Gerenciar consultas");
            System.out.println("0 - Sair");
            opcao = lerInteiro("Escolha uma opcao: ");

            try {
                switch (opcao) {
                    case 1:
                        menuPacientes();
                        break;
                    case 2:
                        menuProfissionais();
                        break;
                    case 3:
                        menuConsultas();
                        break;
                    case 0:
                        System.out.println("Sistema encerrado.");
                        break;
                    default:
                        System.out.println("Opcao invalida.");
                }
            } catch (SQLException erro) {
                System.out.println("Erro ao acessar o banco de dados: " + erro.getMessage());
            }
        } while (opcao != 0);
    }

    private void menuPacientes() throws SQLException {
        System.out.println("\n--- Pacientes ---");
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Listar");
        System.out.println("3 - Atualizar");
        System.out.println("4 - Excluir");
        int opcao = lerInteiro("Escolha uma opcao: ");

        if (opcao == 1) {
            Paciente paciente = new Paciente();
            paciente.setNome(lerTexto("Nome: "));
            paciente.setTelefone(lerTexto("Telefone: "));
            paciente.setEmail(lerTexto("Email: "));
            pacienteDAO.cadastrar(paciente);
            System.out.println("Paciente cadastrado com sucesso.");
        } else if (opcao == 2) {
            listarPacientes();
        } else if (opcao == 3) {
            Paciente paciente = new Paciente();
            paciente.setId(lerInteiro("ID do paciente: "));
            paciente.setNome(lerTexto("Novo nome: "));
            paciente.setTelefone(lerTexto("Novo telefone: "));
            paciente.setEmail(lerTexto("Novo email: "));
            pacienteDAO.atualizar(paciente);
            System.out.println("Paciente atualizado com sucesso.");
        } else if (opcao == 4) {
            pacienteDAO.excluir(lerInteiro("ID do paciente: "));
            System.out.println("Paciente excluido com sucesso.");
        }
    }

    private void menuProfissionais() throws SQLException {
        System.out.println("\n--- Profissionais ---");
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Listar");
        System.out.println("3 - Atualizar");
        System.out.println("4 - Excluir");
        int opcao = lerInteiro("Escolha uma opcao: ");

        if (opcao == 1) {
            Profissional profissional = new Profissional();
            profissional.setNome(lerTexto("Nome: "));
            profissional.setEspecialidade(lerTexto("Especialidade: "));
            profissional.setTelefone(lerTexto("Telefone: "));
            profissionalDAO.cadastrar(profissional);
            System.out.println("Profissional cadastrado com sucesso.");
        } else if (opcao == 2) {
            listarProfissionais();
        } else if (opcao == 3) {
            Profissional profissional = new Profissional();
            profissional.setId(lerInteiro("ID do profissional: "));
            profissional.setNome(lerTexto("Novo nome: "));
            profissional.setEspecialidade(lerTexto("Nova especialidade: "));
            profissional.setTelefone(lerTexto("Novo telefone: "));
            profissionalDAO.atualizar(profissional);
            System.out.println("Profissional atualizado com sucesso.");
        } else if (opcao == 4) {
            profissionalDAO.excluir(lerInteiro("ID do profissional: "));
            System.out.println("Profissional excluido com sucesso.");
        }
    }

    private void menuConsultas() throws SQLException {
        System.out.println("\n--- Consultas ---");
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Listar");
        System.out.println("3 - Atualizar");
        System.out.println("4 - Excluir");
        int opcao = lerInteiro("Escolha uma opcao: ");

        if (opcao == 1) {
            Consulta consulta = lerDadosConsulta();
            consultaDAO.cadastrar(consulta);
            System.out.println("Consulta cadastrada com sucesso.");
        } else if (opcao == 2) {
            listarConsultas();
        } else if (opcao == 3) {
            Consulta consulta = lerDadosConsulta();
            consulta.setId(lerInteiro("ID da consulta: "));
            consultaDAO.atualizar(consulta);
            System.out.println("Consulta atualizada com sucesso.");
        } else if (opcao == 4) {
            consultaDAO.excluir(lerInteiro("ID da consulta: "));
            System.out.println("Consulta excluida com sucesso.");
        }
    }

    private Consulta lerDadosConsulta() {
        Consulta consulta = new Consulta();
        consulta.setPacienteId(lerInteiro("ID do paciente: "));
        consulta.setProfissionalId(lerInteiro("ID do profissional: "));
        consulta.setDataConsulta(lerTexto("Data (AAAA-MM-DD): "));
        consulta.setHorario(lerTexto("Horario (HH:MM:SS): "));
        consulta.setObservacao(lerTexto("Observacao: "));
        consulta.setStatus(lerTexto("Status: "));
        return consulta;
    }

    private void listarPacientes() throws SQLException {
        List<Paciente> pacientes = pacienteDAO.listar();
        for (Paciente paciente : pacientes) {
            System.out.printf("%d - %s | %s | %s%n",
                    paciente.getId(), paciente.getNome(), paciente.getTelefone(), paciente.getEmail());
        }
    }

    private void listarProfissionais() throws SQLException {
        List<Profissional> profissionais = profissionalDAO.listar();
        for (Profissional profissional : profissionais) {
            System.out.printf("%d - %s | %s | %s%n",
                    profissional.getId(), profissional.getNome(), profissional.getEspecialidade(), profissional.getTelefone());
        }
    }

    private void listarConsultas() throws SQLException {
        List<Consulta> consultas = consultaDAO.listar();
        for (Consulta consulta : consultas) {
            System.out.printf("%d - Paciente %d | Profissional %d | %s %s | %s | %s%n",
                    consulta.getId(),
                    consulta.getPacienteId(),
                    consulta.getProfissionalId(),
                    consulta.getDataConsulta(),
                    consulta.getHorario(),
                    consulta.getStatus(),
                    consulta.getObservacao());
        }
    }

    private int lerInteiro(String mensagem) {
        System.out.print(mensagem);
        int valor = Integer.parseInt(scanner.nextLine());
        return valor;
    }

    private String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }
}
