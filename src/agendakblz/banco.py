from pathlib import Path
import sqlite3


PASTA_RAIZ = Path(__file__).resolve().parents[2]
CAMINHO_BANCO = PASTA_RAIZ / "agenda_kblz.sqlite3"
CAMINHO_SCHEMA = PASTA_RAIZ / "sql" / "schema.sql"


class BancoDeDados:
    def __init__(self, caminho_banco: Path = CAMINHO_BANCO):
        self.caminho_banco = caminho_banco

    def conectar(self) -> sqlite3.Connection:
        conexao = sqlite3.connect(self.caminho_banco)
        conexao.row_factory = sqlite3.Row
        conexao.execute("PRAGMA foreign_keys = ON")
        return conexao

    def inicializar(self) -> None:
        with self.conectar() as conexao:
            script = CAMINHO_SCHEMA.read_text(encoding="utf-8")
            conexao.executescript(script)

