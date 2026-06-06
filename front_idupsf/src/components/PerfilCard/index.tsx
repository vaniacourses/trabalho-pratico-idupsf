import Image from 'next/image'
import avatar from '../../../public/avatar.png'
import styles from './styles.module.css'
import { useUsuarioStore } from '@/stores/usuarioStore';
import { useStore } from 'zustand';

export default function PerfilCard() {
    

    const usuario = useStore(useUsuarioStore, (state) => state.usuario);
    
    if (!usuario) { return null; }
    
    return (
        <section className={styles.perfilCard}>
            
            <div className={styles.figureDiv}>
                <h1>Foto oficial</h1>
                
                <figure className={styles.figure}>
                    <Image src={avatar} alt="Foto do Perfil" />
                </figure>
            
            </div>
            

            <div className={styles.info}>
                <h2>{usuario!.nome}</h2>

                {usuario.nomeSocial && (<h3><span>Nome Social:</span> {usuario.nomeSocial}</h3>)}
                
                <h3><span>Perfil:</span> {usuario!.perfil}</h3>
                <h3><span>Matrícula:</span> {usuario!.matricula}</h3>
                <h3><span>Id-UPSF:</span> {usuario!.cpf}</h3>

                {usuario!.perfil === "DISCENTE" && usuario!.curso && (
                    <h3><span>Curso:</span> {usuario!.curso.nome}</h3>
                )}

                {usuario!.perfil === "DOCENTE" && usuario!.departamento && (
                    <h3><span>Departamento:</span> {usuario!.departamento.nome}</h3>
                )}

            </div>
        
        </section>
    )
}