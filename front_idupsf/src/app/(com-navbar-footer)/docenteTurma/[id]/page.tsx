import { turmaService } from "@/services/turmasService";
import styles from "./styles.module.css"
import { Inscricao } from "@/types/modelUPSF";
import DinamicTable, { Column } from "@/components/DinamicTable";


const colunas: Column<Inscricao>[] = [
    {
        header: "Matrícula",
        accessor: "aluno",
        key: "mat",
        render: (v) => v.aluno?.matricula ?? "—",
    },
    {
        header: "Nome",
        accessor: "aluno",
        key: "nome",
        render: (v) => v.aluno?.nome?.toUpperCase() ?? "—",
    },
    {
        header: "E-mail Inst.",
        accessor: "aluno",
        key: "email",
        render: (v) => v.aluno?.emailInst ?? "—",
    },
    // {
    //     header: "Ação",
    //     accessor: "aluno",
    //     key: "acao",
    //     render: (v) => (
    //         <Link className={styles.actionLink} href={`/docenteTurma/${v.id}`}>
    //             Ver Turma
    //         </Link>
    //     ),
    // },

];


export default async function TurmaPage({params}: {params: Promise<{id: string}>}) {
    
    const {id} = await params;

    // const inscricoes = await turmaService.listarInscricoesPorTurma(id);
    // const turma = await turmaService.buscarTurmaPorId(id);

    return (
        <section className={styles.section}>
            <p>Teste</p>
            {/* <h2 className={styles.titleSection}>Alunos da Turma {turma.cod} - {turma.disciplina?.nome}</h2>
            
            <DinamicTable
                colunas={colunas}
                dados={inscricoes ?? []}
                keyExtractor={(t) => t.id}
                mensagemVazia="Nenhuma inscrição encontrada."
            /> */}
        </section>
    )
}