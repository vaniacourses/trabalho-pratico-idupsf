"use client";
 
import { useState } from "react";
import { InscricaoResponseDTO, InscricaoUpdate } from "@/types/modelUPSF";
import styles from "./styles.module.css";
import DinamicTable, { Column } from "../DinamicTable";
import { turmaService } from "@/services/turmasService";
import { useUsuarioStore } from "@/stores/usuarioStore";
 
// ─── Tipos ────────────────────────────────────────────────────────────────────
 
interface LancarNotasTurmaProps {
    turmaId: string;
    inscricoesIniciais: InscricaoResponseDTO[];
}
 
// Estado local editável — chaveado pelo id da inscrição
type EdicoesMap = Record<string, { nota?: number; notaVS?: number; frequencia?: boolean }>;
 
// ─── Componente ───────────────────────────────────────────────────────────────
 
export default function LancarNotasTurma({ turmaId, inscricoesIniciais }: LancarNotasTurmaProps) {
    
    const [inscricoes, setInscricoes] = useState<InscricaoResponseDTO[]>(inscricoesIniciais);
    const [edicoes, setEdicoes] = useState<EdicoesMap>({});
    const [salvando, setSalvando] = useState(false);
    const [erro, setErro] = useState("");
    const [sucesso, setSucesso] = useState(false);

    const usuario = useUsuarioStore((state) => state.usuario)
    
    const docenteId = usuario?.id; 

 
    // ─── Helpers de edição ────────────────────────────────────────────────────
 
    function getNota(row: InscricaoResponseDTO, campo: "nota" | "notaVS"): number | "" {
        const edicao = edicoes[row.id!];
        if (edicao && campo in edicao) return edicao[campo] ?? "";
        return row[campo] ?? "";
    }

    function getFrequencia(row: InscricaoResponseDTO): boolean {
        const edicao = edicoes[row.id!];
        if (edicao && "frequencia" in edicao) return edicao.frequencia ?? false;
        return row.frequencia ?? false;
    }

    function checkVS(getNota: number | ""): boolean {
        if (getNota === "") return false;
        if ( getNota > 4 && getNota < 6) return true;
        else return false
    }
 
    function handleChangeNota(id: string, campo: "nota" | "notaVS", valor: string) {
        const numero = valor === "" ? undefined : Number(valor);
        setEdicoes((prev) => ({
            ...prev,
            [id]: { ...prev[id], [campo]: numero },
        }));
    }
 
    function handleChangeFrequencia(id: string, checked: boolean) {
        setEdicoes((prev) => ({
            ...prev,
            [id]: { ...prev[id], frequencia: checked },
        }));
    }
 
    // ─── Salvar tudo ──────────────────────────────────────────────────────────
 
    async function handleSalvarTudo() {
        setErro("");
        setSucesso(false);
 
        const atualizacoes: InscricaoUpdate[] = inscricoes
            .filter((insc) => edicoes[insc.id!]) // só envia o que foi editado
            .map((insc) => ({
                inscricaoId: insc.id,
                nota: getNota(insc, "nota") as number | undefined,
                notaVS: getNota(insc, "notaVS") as number | undefined,
                frequencia: getFrequencia(insc) as boolean | undefined,
            }));
 
        if (atualizacoes.length === 0) {
            setErro("Nenhuma alteração para salvar.");
            return;
        }
 
        setSalvando(true);
 
        try {
            await turmaService.atribuirNotas(turmaId, docenteId!, atualizacoes);
 
            // Atualiza o estado local com os valores editados
            setInscricoes((prev) =>
                prev.map((insc) => {
                    const edicao = edicoes[insc.id!];
                    if (!edicao) return insc;
 
                    const nota = edicao.nota ?? insc.nota;
                    const notaVS = edicao.notaVS ?? insc.notaVS;
                    const frequencia = edicao.frequencia ?? insc.frequencia;
 
                    return {
                        ...insc,
                        nota,
                        notaVS,
                        frequencia,
                    };
                })
            );
 
            setEdicoes({});
            setSucesso(true);
 
        } catch {
            setErro("Erro ao salvar notas. Tente novamente.");
        } finally {
            setSalvando(false);
        }
    }
 
    // ─── Colunas ──────────────────────────────────────────────────────────────
 
    const colunas: Column<InscricaoResponseDTO>[] = [
        {
            header: "Matrícula",
            accessor: "matriculaAluno",
            render: (row) => row.matriculaAluno ?? "—",
        },
        {
            header: "Nome",
            accessor: "nomeAluno",
            render: (row) => row.nomeAluno ?? "—",
        },
        {
            header: "Nota",
            accessor: "nota",
            render: (row) => (
                <input
                    type="number"
                    step="0.1"
                    min={0}
                    max={10}
                    className={styles.inputNota}
                    value={getNota(row, "nota") ?? ""}
                    onChange={(e) => handleChangeNota(row.id!, "nota", e.target.value)}
                />
            ),
        },
        {
            header: "VS",
            accessor: "notaVS",
            render: (row) => (
                <input
                    type="number"
                    step="0.1"
                    min={0}
                    max={10}
                    className={styles.inputNota}
                    disabled={checkVS(getNota(row, "nota"))}
                    value={getNota(row, "notaVS") ?? ""}
                    onChange={(e) => handleChangeNota(row.id!, "notaVS", e.target.value)}
                />
            ),
        },
        {
            header: "Frequência",
            accessor: "frequencia",
            render: (row) => (
                <input
                    type="checkbox"
                    className={styles.checkbox}
                    checked={Boolean(getFrequencia(row))}
                    onChange={(e) => handleChangeFrequencia(row.id!, e.target.checked)}
                />
            ),
        },
    ];
 
    const temEdicoes = Object.keys(edicoes).length > 0;
 
    return (
        <section className={styles.container}>
            <header className={styles.header}>
                <h2 className={styles.titulo}>Lançamento de Notas</h2>
                <p className={styles.subtitulo}>Edite as notas e a frequência dos alunos e salve as alterações.</p>
            </header>
 
            <DinamicTable
                colunas={colunas}
                dados={inscricoes}
                keyExtractor={(insc) => insc.id!}
                mensagemVazia="Nenhum aluno inscrito nesta turma."
            />
 
            {erro && <p className={styles.erro}>{erro}</p>}
            {sucesso && <p className={styles.sucesso}>Notas salvas com sucesso!</p>}
 
            <div className={styles.acoes}>
                <button
                    className={styles.btnSalvar}
                    onClick={handleSalvarTudo}
                    disabled={salvando || !temEdicoes}
                >
                    {salvando ? "Salvando..." : "Salvar Notas"}
                </button>
            </div>
        </section>
    );
}