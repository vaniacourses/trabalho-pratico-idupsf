import Link from 'next/link'
import styles from './styles.module.css'
import "bootstrap-icons/font/bootstrap-icons.min.css";

export default function DiscenteCases() {
    return (
        <section className={styles.atorSection}>
            <h1 className={styles.title}>Docentes</h1>

            <div className={styles.discenteContent}>
                
                <Link href="/docentes/turmas" className={styles.caseCard}>
                    <i className="bi bi-archive"></i>
                    Consultar suas Turmas
                </Link>

                <Link href="/docentes/gerar" className={styles.caseCard}>
                    <i className="bi bi-filetype-pdf"></i>
                    Gerar Declarações
                </Link>

                <Link href="/docentes/historico" className={styles.caseCard}>
                    <i className="bi bi-file-ruled"></i>
                    Histórico de Turmas
                </Link>

                <Link href="/docentes/plano" className={styles.caseCard}>
                    <i className="bi bi-journal-bookmark"></i>
                    Plano de Aulas
                </Link>

                <Link href="/docentes/horascurriculo" className={styles.caseCard}>
                    <i className="bi bi-file-earmark-break"></i>
                    Seu Currículo
                </Link>
            
            </div>

        </section>
    )
}