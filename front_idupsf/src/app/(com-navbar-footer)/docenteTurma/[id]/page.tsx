import { turmaService } from "@/services/turmasService";
import styles from "./styles.module.css"
import LancarNotasTurma from "@/components/LancarNotasTurma";


export default async function TurmaPage({params}: {params: Promise<{id: string}>}) {
    
    const {id} = await params;

    const inscricoes = await turmaService.listarInscricoesPorTurma(id);
    
    const turma = await turmaService.buscarTurmaPorId(id);

    
    return (
        <section className={styles.section}>
            
            <h2 className={styles.titleSection}>Alunos da Turma {turma.cod} - {turma.disciplina?.nome}</h2>
            
            <LancarNotasTurma
                turmaId={id}
                inscricoesIniciais={inscricoes}
            />
            
        </section>
    )
}