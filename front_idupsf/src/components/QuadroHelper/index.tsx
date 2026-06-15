"use client";

import styles from './styles.module.css'
import { useState } from "react";
import { Turma, Curriculo, Horario } from "@/types/modelUPSF";
import { turmaService } from "@/services/turmasService";
import { opcoesService } from "@/services/opcoesService";
import QuadroFiltroTurmas from "../QuadroFiltrosTurmas";
import { FiltrosBuscaTurmas, TurmasOptionsProps } from "@/types/buscaTypes";
import DinamicTable, { Column } from '../DinamicTable';

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
];

/*
    Responsabilidades/Intenções do Componente Cliente QuadroHelper:
    
    - Dar os dados das Opções<option> ao Componente QuadroFiltrosTurma, que contém os filtros de busca.
    - Tratar a atualização dos Currículos quando o Filtro de Curso é alterado
    - Conter a função que realiza a requisição de busca e passá-la para QuadroFiltrosTurma. 
    - Armazenar e exibir o resultado da busca.
*/
export default function QuadroHelper({ departamentos, cursos, periodos }: TurmasOptionsProps) {
    
    const [turmas, setTurmas] = useState<Turma[]>([]);
    const [curriculos, setCurriculos] = useState<Curriculo[]>([]);
    const [loading, setLoading] = useState<boolean>(false);
    const [buscaFeita, setBuscaFeita] = useState<boolean>(false);

    // --> Chamado pelo QuadroFiltroTurmas quando o curso muda
    // -> AINDA NÃO FUNCIONAL -> Resolver relação Turma e (Curso, Curriculo)
    async function handleCursoChange(cursoId: string) {
        
        if (!cursoId) { setCurriculos([]); return; }
        
        const data = await opcoesService.listarCurriculosPorCurso(Number(cursoId));
        
        setCurriculos(data);
    }

    async function handleBuscar(filtros: FiltrosBuscaTurmas) {
        try {
            setLoading(true);
            const data = await turmaService.buscarComFiltros(filtros);
            setTurmas(data);
            setBuscaFeita(true);
        
        } catch (error) {
            console.error("Erro na busca de turmas:", error);
        
        } finally {
            setLoading(false);
        }
    }

    return (
        <main className={styles.main}>
            <QuadroFiltroTurmas
                departamentos={departamentos}
                cursos={cursos}
                periodos={periodos}
                curriculos={curriculos}
                onBuscar={handleBuscar}
                onCursoChange={handleCursoChange}
                isLoading={loading}
            />

            {buscaFeita && turmas.length === 0 && (
                <p>Nenhuma turma encontrada para os filtros selecionados.</p>
            )}
            

            <DinamicTable
                colunas={colunas}
                dados={turmas ?? []}
                keyExtractor={(t) => t.id}
                mensagemVazia="Nenhuma turma encontrada."
            />
        </main>
    );
}