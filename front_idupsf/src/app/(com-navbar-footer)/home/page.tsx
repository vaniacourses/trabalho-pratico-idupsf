import PerfilCard from '@/components/PerfilCard'
import styles from './styles.module.css'
import DiscenteCases from '@/components/DiscenteCases'
import Carrossel from '@/components/Carrossel'
import { defaultSlides } from '@/types/carrosselTypes'
import { Perfil } from '@/types/modelUPSF'



export default function Home() {

    const perfil: Perfil = {
        nome: "Cláudio Pires Salgado",
        matricula: "0000000",
        CPF: "123.456.789-00",
        curso: "Ciência da Computação"
    }

    return (     

        <section className={styles.contentSection}>
            
            <div className={styles.funcsSection}>
                <PerfilCard perfil={perfil}/>

                <DiscenteCases/>
            </div>

            
            <div className={styles.newsSection}>
                
                <div className={styles.newsCarrosselDiv}>
                    
                    <Carrossel slides={defaultSlides} />
                
                </div>

                <div className={styles.infoDiv}>
                    
                    <div className={styles.infoGroup}>
                        <h2 className={styles.infoTitle}>Início</h2>
                        <p className={styles.infoText}>Está é a página inicial do Id-UPSF, onde você encontrará informações importantes sobre a universidade e seus serviços, além de acesso direto aos principais recursos de acordo com seu perfil.</p>
                    </div>

                    <div className={styles.infoGroup}>
                        <h2 className={styles.infoTitle}>Sobre</h2>
                        <p className={styles.infoText}>Saiba mais sobre a Id-UPSF, seus serviços, seu contexto de desenvolvimento, sua missão, visão, valores e objetivos.</p>
                    </div>

                    <div className={styles.infoGroup}>
                        <h2 className={styles.infoTitle}>Inscrição On-line</h2>
                        <p className={styles.infoText}>Esta página permite que os discentes realizem suas inscrições em suas respectivas disciplinas disponíveis de forma prática e eficiente. Esta página só está disponível quando o período de inscrições estiver aberto.</p>
                    </div>

                    <div className={styles.infoGroup}>
                        <h2 className={styles.infoTitle}>Quadro de Horários</h2>
                        <p className={styles.infoText}>Esta página exibe o quadro de horários das disciplinas disponíveis para os discentes, servindo como um recurso de consulta tanto para eles quanto para os docentes também. Você pode visualizar os horários de forma prática e eficiente.</p>
                    </div>

                    <div className={styles.infoGroup}>
                        <h2 className={styles.infoTitle}>Solicitação UPSF</h2>
                        <p className={styles.infoText}>Esta página permite que os discentes realizem solicitações relacionadas à Universidade, como pedidos de documentos, informações e suporte. Você pode enviar suas solicitações de forma prática e eficiente.</p>
                    </div>

                </div>
            
            </div>
        
        </section>

    )
}