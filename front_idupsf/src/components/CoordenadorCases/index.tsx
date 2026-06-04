import Link from 'next/link'
import styles from './styles.module.css'
import "bootstrap-icons/font/bootstrap-icons.min.css";

export default function DiscenteCases() {
    return (
        <section className={styles.atorSection}>
            <h1 className={styles.title}>Coordenadores</h1>

            <div className={styles.discenteContent}>
                
                <Link href="/coordenadores/declaracoes" className={styles.caseCard}>
                    <i className="bi bi-archive"></i>
                    Gerenciar Turmas
                </Link>

                <Link href="/coordenadores/gerar" className={styles.caseCard}>
                    <i className="bi bi-filetype-pdf"></i>
                    Gerar Declarações
                </Link>

                <Link href="/coordenadores/historico" className={styles.caseCard}>
                    <i className="bi bi-file-ruled"></i>
                    Histórico de Turmas
                </Link>

                <Link href="/coordenadores/plano" className={styles.caseCard}>
                    <i className="bi bi-journal-bookmark"></i>
                    Plano de Aulas
                </Link>

                <Link href="/coordenadores/horascurriculo" className={styles.caseCard}>
                    <i className="bi bi-file-earmark-break"></i>
                    Seu Currículo
                </Link>
            
            </div>

        </section>
    )
}