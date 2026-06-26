from dataclasses import dataclass


@dataclass
class Paciente:
    nome: str
    telefone: str
    email: str
    id: int | None = None


@dataclass
class Profissional:
    nome: str
    especialidade: str
    telefone: str
    id: int | None = None


@dataclass
class Consulta:
    paciente_id: int
    profissional_id: int
    data_consulta: str
    horario: str
    observacao: str
    status: str = "Agendada"
    id: int | None = None

