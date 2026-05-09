import Link from 'next/link'
import styles from './styles.module.css'
import "bootstrap-icons/font/bootstrap-icons.min.css";

export default function DiscenteCases() {
    return (
        <section className={styles.discenteSection}>
            <h1 className={styles.title}>Discentes</h1>

            <div className={styles.discenteContent}>
                
                <Link href="/discentes/declaracoes" className={styles.caseCard}>
                    <i className="bi bi-archive"></i>
                    Declarações Geradas
                </Link>

                <Link href="/discentes/gerar" className={styles.caseCard}>
                    <i className="bi bi-filetype-pdf"></i>
                    Gerar Declarações
                </Link>

                <Link href="/discentes/historico" className={styles.caseCard}>
                    <i className="bi bi-file-ruled"></i>
                    Histórico
                </Link>

                <Link href="/discentes/plano" className={styles.caseCard}>
                    <i className="bi bi-journal-bookmark"></i>
                    Plano de Estudos
                </Link>

                <Link href="/discentes/horascurriculo" className={styles.caseCard}>
                    <i className="bi bi-file-earmark-break"></i>
                    Integralização de Currículo
                </Link>
            
            </div>

        </section>
    )
}