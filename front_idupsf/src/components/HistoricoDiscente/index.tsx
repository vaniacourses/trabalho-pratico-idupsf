"use client";

import { DisciplinaCursada, Historico } from "@/types/modelUPSF";
import DinamicTable, { Column } from "../DinamicTable";
import styles from "./styles.module.css"
import { historicoService } from "@/services/historicoService";
import { useEffect, useState } from "react";
import { useUsuarioStore } from "@/stores/usuarioStore";
import Carrossel from "../Carrossel";
import { defaultSlides } from "@/types/carrosselTypes";

function BadgeStatus({ status }: { status: string }) {
    const normalizado = status?.toUpperCase();

    const classeMap: Record<string, string> = {
        APROVADO:  styles.badgeAprovado,
        REPROVADO: styles.badgeReprovado,
        TRANCADO:  styles.badgeTrancado,
        AGUARDO:   styles.badgeAguardo,
    };

    const labelMap: Record<string, string> = {
        APROVADO:  "Aprovado",
        REPROVADO: "Reprovado",
        TRANCADO:  "Trancado",
        AGUARDO:   "Em Andamento",
    };

    return (
        <span className={`${styles.badge} ${classeMap[normalizado] ?? styles.badgeAguardo}`}>
            {labelMap[normalizado] ?? status}
        </span>
    );
}

const colunas: Column<DisciplinaCursada>[] = [
    { header: "Código",   accessor: "codigoDisciplina", 
      render: (v) => String(v.codigoDisciplina)},
    
    { header: "Disciplina",     accessor: "nomeDisciplina",
      render: (v) => String(v.nomeDisciplina).toUpperCase() },
    
    { header: "Situação", accessor: "statusFinal",
      render: (v) => <BadgeStatus status={String(v.statusFinal)} />},
    
    { header: "Turma",    accessor: "codigoTurma",
      render: (v) => String(v.codigoTurma) },
    
    { header: "Nota",     accessor: "nota",
      render: (v) => Number(v.nota).toFixed(1) },
    
    { header: "VS",       accessor: "notaVS",
      render: (v) => Number(v.notaVS).toFixed(1) },
    
    { header: "Horas",    accessor: "cargaHoraria", 
      render: (v) => String(v.cargaHoraria)},
    
    { header: "Semestre", accessor: "anoSemestre", 
      render: (v) => String(v.anoSemestre)},
];

export default function HistoricoDiscente() {

    const [historico, setHistorico] = useState<Historico | null>(null);

    const [loading, setLoading] = useState(true);
    
    const usuario = useUsuarioStore((state) => state.usuario);

    useEffect(() => {
        if (!usuario?.id) return;
         
        historicoService.buscarHistoricoPorDiscente(String(usuario.id)).then(setHistorico).finally(() => setLoading(false))
        
    }, [usuario?.id]);

    console.log(historico);

    
    return (
        <section className={styles.caseBlockSection}>
            
            <div className={styles.newsCarrosselDiv}>
                    
                    <Carrossel slides={defaultSlides} />
                
            </div>

            <header className={styles.headerCase}>
                
                <h1 className={styles.title}>Histórico</h1>
            
            </header>

            {loading ? <p className={styles.infoText}>Carregando histórico...</p> :
            (   !historico ? <p className={styles.infoText}>Histórico não encontrado.</p> :
                (<>
                    <div className={styles.infoDiv}>
                        <p className={styles.infoText}>Coeficiente de Rendimento: {historico.coeficienteRend}</p>
                        {usuario!.perfil === "DISCENTE" && <p className={styles.infoText}>Situação: {usuario!.situacaoAcademica}</p>}
                    </div>

                    <div>
                        <DinamicTable 
                            colunas={colunas}
                            dados={historico.disciplinas ?? []}
                            keyExtractor={(dc) => dc.id}
                            mensagemVazia="Nenhuma disciplina cursada encontrada."
                        />
                    </div>
                </>)
            )}
            

        </section>
    )
}