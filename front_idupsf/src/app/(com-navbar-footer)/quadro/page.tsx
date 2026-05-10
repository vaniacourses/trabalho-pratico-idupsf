import styles from './styles.module.css'
import Image from 'next/image'
import calendar from '../../../assets/calendar.webp'

export default async function QuadroHorarios() {


    return (
        <main className={styles.main}>
            <header className={styles.header}>
                <section className={styles.titleSection}>
                    <figure className={styles.iconFigure}>
                        <Image src={calendar} alt="Ícone de calendário" className={styles.icon} />
                    </figure>
                    
                    <h1 className={styles.title}>Quadro de Horários</h1>
                </section>

                {/* Componente de Navegação? */}
            
            </header>
            

            <section className={styles.filtersSection}>
                {/* Componente de Filtros para consultas de turmas. */}
            </section>

            <section className={styles.tableSection}>
                {/* Componente de Tabela para exibir os resultados das consultas. */}
            </section>

        </main>
    )
}