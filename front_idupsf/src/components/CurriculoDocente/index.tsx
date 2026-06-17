import { useUsuarioStore } from "@/stores/usuarioStore";
import styles from "./styles.module.css"

export default function CurriculoDocente() {

    const usuario = useUsuarioStore((state) => state.usuario);

    return (
        <section className={styles.caseBlockSection}>
            
            <header className={styles.headerCase}>
                
                <h1 className={styles.title}>Currículo de {usuario?.nome}</h1>
            
            </header>

            
            {usuario?.perfil === "DOCENTE" &&
                
                <div className={styles.contentDiv}>

                    <div className={styles.infoDiv}>

                        <p><span className={styles.infoText}>Currículo LATTES:</span> {usuario.lattes}</p>
                    
                        <p><span className={styles.infoText}>Títulação:</span> {usuario.titulacao}</p>

                        <p><span className={styles.infoText}>Regime:</span> {usuario.regime}</p>

                    </div>
                
                    <div className={styles.infoDiv}>
                        
                        <p><span className={styles.infoText}>Áreas de interesse:</span></p>
                        
                        <ul className={styles.list}>
                            {usuario.areasAtuacao?.map((area, index) => (
                                <p key={index}>{area}</p>
                            ))}
                        </ul>
                    
                    </div>
                    
                    

                </div>
            }

        </section>
    )
}