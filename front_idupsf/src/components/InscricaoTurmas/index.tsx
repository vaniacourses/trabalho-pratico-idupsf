"use client";

import { useState } from "react";
import { Turma } from "@/types/modelUPSF";
import styles from "./styles.module.css";
import DinamicTable, { Column } from "../DinamicTable";
import { inscricaoService } from "@/services/inscricaoService";

// ─── Tipos ────────────────────────────────────────────────────────────────────

interface InscricaoTurmasProps {
    discenteId: string;
    turmasIniciais: Turma[];
}

// ─── Componente ───────────────────────────────────────────────────────────────

export default function InscricaoTurmas({ discenteId, turmasIniciais }: InscricaoTurmasProps) {
    
    const [turmas, setTurmas] = useState<Turma[]>(turmasIniciais);
    const [selecionadas, setSelecionadas] = useState<Set<string>>(new Set());
    const [enviando, setEnviando] = useState(false);
    const [erro, setErro] = useState("");
    const [sucesso, setSucesso] = useState("");

    // ─── Seleção ──────────────────────────────────────────────────────────────

    function toggleSelecao(turmaId: string) {
        setSelecionadas((prev) => {
            const nova = new Set(prev);
            if (nova.has(turmaId)) nova.delete(turmaId);
            else nova.add(turmaId);
            return nova;
        });
    }

    function toggleSelecionarTodas() {
        if (selecionadas.size === turmas.length) {
            setSelecionadas(new Set());
        } else {
            setSelecionadas(new Set(turmas.map((t) => t.id!)));
        }
    }

    // ─── Inscrição ────────────────────────────────────────────────────────────

    async function handleInscrever() {
        setErro("");
        setSucesso("");

        if (selecionadas.size === 0) {
            setErro("Selecione ao menos uma turma para se inscrever.");
            return;
        }

        setEnviando(true);

        try {
            await inscricaoService.realizarInscricao({
                discenteId: discenteId,
                turmasIds: Array.from(selecionadas),
            });

            // Remove da listagem as turmas em que o aluno acabou de se inscrever
            setTurmas((prev) => prev.filter((t) => !selecionadas.has(t.id!)));
            setSelecionadas(new Set());
            setSucesso("Inscrição realizada com sucesso!");

        } catch {
            setErro("Erro ao realizar inscrição. Tente novamente.");
        } finally {
            setEnviando(false);
        }
    }

    // ─── Colunas ──────────────────────────────────────────────────────────────

    const colunas: Column<Turma>[] = [
        {
            header: "",
            accessor: "id",
            className: styles.colCheckbox,
            render: (row) => (
                <input
                    type="checkbox"
                    className={styles.checkbox}
                    checked={selecionadas.has(row.id!)}
                    onChange={() => toggleSelecao(row.id!)}
                />
            ),
        },
        {
            header: "Código",
            accessor: "cod",
            render: (row) => row.disciplina?.cod ?? "—",
        },
        {
            header: "Disciplina",
            accessor: "nome",
            render: (row) => row.disciplina?.nome?.toUpperCase() ?? "—",
        },
        {
            header: "Turma",
            accessor: "cod",
            render: (row) => row.cod ?? "—",
        },
        {
            header: "Docente",
            accessor: "docente",
            render: (row) => row.docente?.nome ?? "A definir",
        },
    ];

    return (
        <section className={styles.container}>

            <header className={styles.header}>
                <h2 className={styles.titulo}>Turmas Disponíveis</h2>
                <p className={styles.subtitulo}>
                    Selecione as turmas em que deseja se inscrever e clique em &quot;Inscrever-se&quot;.
                </p>
            </header>

            {turmas.length > 0 && (
                <div className={styles.acoesTopo}>
                    <label className={styles.selecionarTodas}>
                        <input
                            type="checkbox"
                            checked={selecionadas.size === turmas.length}
                            onChange={toggleSelecionarTodas}
                        />
                        Selecionar todas
                    </label>
                    <span className={styles.contador}>
                        {selecionadas.size} turma(s) selecionada(s)
                    </span>
                </div>
            )}

            <DinamicTable
                colunas={colunas}
                dados={turmas}
                keyExtractor={(t) => t.id!}
                mensagemVazia="Não há turmas disponíveis para inscrição no momento."
            />

            {erro && <p className={styles.erro}>{erro}</p>}
            {sucesso && <p className={styles.sucesso}>{sucesso}</p>}

            {turmas.length > 0 && (
                <div className={styles.acoes}>
                    <button
                        className={styles.btnInscrever}
                        onClick={handleInscrever}
                        disabled={enviando || selecionadas.size === 0}
                    >
                        {enviando ? "Inscrevendo..." : "Inscrever-se"}
                    </button>
                </div>
            )}

        </section>
    );
}