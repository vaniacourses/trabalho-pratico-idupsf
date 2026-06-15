"use client";

import { useUsuarioStore } from "@/stores/usuarioStore";
import styles from "./styles.module.css"
import DinamicTable, { Column } from "../DinamicTable";
import { Horario, Turma } from "@/types/modelUPSF";
import Link from "next/link";
import { docentesService } from "@/services/docentesService";
import { useEffect, useState } from "react";

function checkHorario(h: Horario | undefined, dia: string): React.ReactNode {
    if (!h?.diasDaSemana?.includes(dia)) return null;
    return <p>{h.horarioInicio} - {h.horarioFim}</p>;
}

const DIAS = [
    { label: "Seg", valor: "SEGUNDA" },
    { label: "Ter", valor: "TERCA" },
    { label: "Qua", valor: "QUARTA" },
    { label: "Qui", valor: "QUINTA" },
    { label: "Sex", valor: "SEXTA" },
];

const colunas: Column<Turma>[] = [
    {
        header: "Código",
        accessor: "disciplina",
        key: "codigo",
        render: (v) => v.disciplina?.cod ?? "—",
    },
    {
        header: "Nome",
        accessor: "disciplina",
        key: "nome",
        render: (v) => v.disciplina?.nome?.toUpperCase() ?? "—",
    },
    {
        header: "Turma",
        accessor: "cod",
        key: "turma",
        render: (v) => v.cod ?? "—",
    },
    ...DIAS.map((dia) => ({
        header: dia.label,
        accessor: "horario" as const,
        key: `horario-${dia.valor}`,
        render: (v: Turma) => checkHorario(v.horario, dia.valor),
    })),
    {
        header: "Ação",
        accessor: "cod",
        key: "acao",
        render: (v) => (
            <Link href={`/docenteTurma/${v.id}`} className={styles.actionLink}>
                Ver Turma
            </Link>
        ),
    },

];


export default function DocenteTurmas() {

    const usuario = useUsuarioStore((state) => state.usuario);
    
    if (!usuario?.id) return null;

    const [turmasDocente, setTurmasDocente] = useState<Turma[] | null>(null);
    
    const [loading, setLoading] = useState(true);
        
    
    useEffect(() => {
        if (!usuario?.id) return;
            
        docentesService.listarTurmasPorDocente(String(usuario.id)).then(setTurmasDocente).finally(() => setLoading(false))
        
    }, [usuario?.id]);

    return (
        
        <section className={styles.caseBlockSection}>
            
            <header className={styles.headerCase}>
                
                <h1 className={styles.title}>Turmas de {usuario?.nome}</h1>
            
            </header>

            {loading ? <p className={styles.infoText}>Carregando turmas...</p> : (
            <DinamicTable
                colunas={colunas}
                dados={turmasDocente ?? []}
                keyExtractor={(t) => t.id}
                mensagemVazia="Nenhuma turma encontrada."
            />)}
            

        </section>
    )
}