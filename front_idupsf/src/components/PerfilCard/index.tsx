import Image from 'next/image'
import avatar from '../../../public/avatar.png'
import styles from './styles.module.css'

export default function PerfilCard() {
    return (
        <section className={styles.perfilCard}>
            
            <div className={styles.figureDiv}>
                <h1>Foto oficial</h1>
                <figure className={styles.figure}>
                    <Image src={avatar} alt="Foto do Perfil" />
                </figure>
            </div>
            

            <div className={styles.info}>
                <h2>Cláudio Pires Salgado</h2>
                <h3><span>Perfil:</span> Discente</h3>
                <h3><span>Matrícula:</span> 123456</h3>
                <h3><span>Id-UPSF:</span> 123.456.789-00</h3>
            </div>
        
        </section>
    )
}