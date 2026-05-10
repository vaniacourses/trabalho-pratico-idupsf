import styles from "./styles.module.css";
import "bootstrap-icons/font/bootstrap-icons.min.css";

const integrantes = [
    {
        nome: "Cláudio Pires Salgado",
        github: "https://github.com/Claudio0101-bit",
    },
    {
        nome: "Diogo Gouveia de Oliveira",
        github: "https://github.com/gouveiadiogo",
    },
    {
        nome: "Guilherme da Hora Andrade Fontoura",
        github: "https://github.com/gdhfontoura",
    },
    {
        nome: "Lucas Rodrigues Soares Moraes",
        github: "https://github.com/luckssl",
    },
    {
        nome: "Miguel Faria Dantas",
        github: "https://github.com/ToffeBR",
    },
];

const tecnologiasFrontend = [
    "Next.js", "TypeScript", "CSS Modules", "NextAuth",
];

const tecnologiasBackend = [
    "Java", "Spring Boot", "Spring Data JPA", "Spring Web", "Lombok", "MySQL",
];

export default function SobreNosPage() {
    return (
        <main className={styles.main}>

            {/* Banner Hero */}
            <section className={styles.bannerHero}>
                <div className={styles.heroContent}>
                    <p className={styles.heroTag}>Projeto de Software — UFF</p>
                    
                    <h1 className={styles.titleHero}>Quem somos</h1>
                    
                    <p className={styles.subtitleHero}>
                        Conheça o time de desenvolvedores responsável pela concepção,
                        design e desenvolvimento do idUPSF.
                    </p>
                
                </div>
                
                <span className={styles.iconHero}>👨‍💻</span>
            
            </section>

            <div className={styles.info}>

                {/* Sobre o Projeto */}
                <section className={styles.projectSection}>
                    <div className={styles.projectText}>
                        
                        <h2 className={styles.titleSection}>O projeto</h2>
                        
                        <p>
                            O idUPSF foi desenvolvido como trabalho prático da disciplina de
                            Projeto de Software da Universidade Federal Fluminense (UFF). O
                            sistema tem como objetivo simular uma plataforma de identificação
                            digital e acesso unificado para membros da comunidade acadêmica,
                            tendo como referência o idUFF, sistema oficial de identificação da UFF.
                        </p>
                        
                        <p>
                            O projeto foi construído com uma arquitetura moderna separando
                            Frontend e Backend, seguindo boas práticas de engenharia de
                            software, modelagem de sistemas e desenvolvimento web.
                        </p>
                    
                    </div>

                    <div className={styles.projectStack}>

                        <div className={styles.stackBlock}>
                            <div className={styles.stackHeader}>
                                
                                <span className={styles.stackIcon}>🖥️</span>
                                
                                <h3 className={styles.stackTitle}>Front-End</h3>
                            
                            </div>
                            
                            <div className={styles.tecnologiasList}>
                                
                                {tecnologiasFrontend.map((tech) => (
                                <span key={tech} className={`${styles.tecnologiaTag} ${styles.tagFront}`}>
                                    {tech}
                                </span>
                                ))}
                            
                            </div>
                        
                        </div>

                        <div className={styles.stackDivisor} />

                        <div className={styles.stackBlock}>
                            
                            <div className={styles.stackHeader}>
                                
                                <span className={styles.stackIcon}>⚙️</span>
                                
                                <h3 className={styles.stackTitle}>Back-End</h3>
                            
                            </div>
                            
                            <div className={styles.tecnologiasList}>
                                
                                {tecnologiasBackend.map((tech) => (
                                <span key={tech} className={`${styles.tecnologiaTag} ${styles.tagBack}`}>
                                    {tech}
                                </span>
                                ))}
                            
                            </div>
                        
                        </div>

                    </div>
                
                </section>

                {/* Integrantes */}
                <section className={styles.section}>
                    <h2 className={styles.titleSection}>Nossa equipe</h2>
                    <div className={styles.gridGrupo}>
                        {integrantes.map((integrante, index) => (
                            <a key={integrante.nome} href={integrante.github} target="_blank"
                                rel="noopener noreferrer"
                                className={styles.card}
                            >
                                <div className={styles.cardNumber}>
                                {String(index + 1).padStart(2, "0")}
                                </div>

                                <div className={styles.cardInfo}>
                                    <strong className={styles.cardNome}>{integrante.nome}</strong>
                                    <p className={styles.cardGithubUrl}>
                                        {integrante.github.replace("https://github.com/", "@")}
                                    </p>
                                </div>

                                <figure className={styles.githubIcon}>

                                    <i className="bi bi-github"></i>
                                    
                                </figure>

                            </a>
                        ))}
                    
                    </div>
                
                </section>

                {/* Rodapé Institucional */}
                <div className={styles.institucional}>
                    
                    <div className={styles.institucionalText}>
                        
                        <p className={styles.institucionalLabel}>Desenvolvido na</p>
                        
                        <strong className={styles.institucionalNome}>
                        Universidade Federal Fluminense — UFF
                        </strong>
                        
                        <p className={styles.institucionalDisciplina}>
                        Disciplina de Projeto de Software
                        </p>
                        
                        <p className={styles.institucionalReferencia}>
                        Inspirado no{" "}idUFF{" "}— Sistema de Identificação da UFF
                        </p>
                    
                    </div>
                    
                    <span className={styles.institucionalIcon}>🎓</span>
                
                </div>

            </div>
        
        </main>
    );
}

