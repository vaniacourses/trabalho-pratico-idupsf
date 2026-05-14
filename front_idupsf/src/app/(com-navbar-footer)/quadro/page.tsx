import styles from './styles.module.css'
import Image from 'next/image'
import calendar from '../../../assets/calendar.webp'
import QuadroFiltrosTurma from '@/components/QuadroFiltrosTurmas'
import { opcoesService } from '@/services/opcoesService';
import QuadroHelper from '@/components/QuadroHelper';

export default async function QuadroHorarios() {
    
    const [departamentos, cursos] = await Promise.all([
        opcoesService.listarDepartamentos(),
        opcoesService.listarCursos()
    ]);

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
                <QuadroHelper departamentos={departamentos} cursos={cursos}/>
            </section>

            <section className={styles.tableSection}>
                {/* Componente de Tabela para exibir os resultados das consultas. */}
            </section>

        </main>
    )
}