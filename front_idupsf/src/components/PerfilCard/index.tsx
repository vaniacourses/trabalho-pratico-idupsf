import Image from 'next/image'
import avatar from '../../../public/avatar.png'
import styles from './styles.module.css'
import { Perfil } from '@/types/modelUPSF'

interface PerfilProps {
    perfil: Perfil
}

export default function PerfilCard( { perfil }: PerfilProps) {
    return (
        <section className={styles.perfilCard}>
            
            <div className={styles.figureDiv}>
                <h1>Foto oficial</h1>
                <figure className={styles.figure}>
                    <Image src={avatar} alt="Foto do Perfil" />
                </figure>
            </div>
            

            <div className={styles.info}>
                <h2>{perfil.nome}</h2>
                
                <h3><span>Perfil:</span> A fazer</h3>
                <h3><span>Matrícula:</span>{perfil.matricula}</h3>
                <h3><span>Id-UPSF:</span>{perfil.CPF}</h3>

                {perfil.curso ? <h3><span>Curso:</span>{perfil.curso}</h3> : <></>}
                {perfil.departamento ? <h3><span>Departamento:</span>{perfil.departamento}</h3> : <></>}

            </div>
        
        </section>
    )
}