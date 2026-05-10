import Image from "next/image";
import styles from "./styles.module.css";
import predio from "../../../../public/predio_idUPSF.jpeg";

const statistics = [
    { valor: "12", label: "Cursos de Graduação" },
    { valor: "4.800", label: "Discentes Ativos" },
    { valor: "320", label: "Docentes" },
    { valor: "8", label: "Departamentos" },
];

const timeline = [
    { ano: "2010", evento: "Fundação da UPSF no município de Niterói, RJ." },
    { ano: "2012", evento: "Abertura dos primeiros cursos de Computação e Engenharia." },
    { ano: "2015", evento: "Inauguração do campus principal e biblioteca central." },
    { ano: "2018", evento: "Expansão para 12 cursos de graduação e início da pós-graduação." },
    { ano: "2022", evento: "Lançamento do programa de pesquisa e inovação tecnológica." },
    { ano: "2026", evento: "Lançamento do idUPSF — Plataforma de Identificação Digital." },
];

export default function SobreUPSFPage() {
    return (
        <main className={styles.main}>

            {/* Banner Hero */}
            <section className={styles.bannerHero}>
                
                <span className={styles.overlayHero}/>
                
                <Image src={predio} alt="Campus da UPSF"
                className={styles.imageHero}
                fill
                priority
                /> 
            
                <div className={styles.heroContent}>
                    
                    <p className={styles.heroKnowMsg}>Conheça a UPSF</p>
                    
                    <h1 className={styles.titleHero}>
                        Universidade de Projeto de Software Fluminense
                    </h1>
                    
                    <p className={styles.subtitleHero}>
                        Formando profissionais éticos, inovadores e comprometidos com a
                        sociedade desde 2010.
                    </p>
                
                </div>
            
            </section>

            {/* Estatísticas */}
            <section className={styles.statistics}>
                
                {statistics.map((item) => (
                    
                    <div key={item.label} className={styles.statisticCard}>
                        <span className={styles.statisticValue}>{item.valor}</span>
                        <span className={styles.statisticLabel}>{item.label}</span>
                    </div>
                
                ))}
            
            </section>

            <div className={styles.info}>
                {/* O que é a UPSF */}
                <section className={styles.section}>
                    <h2 className={styles.titleSection}>O que é a UPSF</h2>
                    <p>
                        A Universidade de Projeto de Software Fluminense (UPSF) é uma
                        instituição de ensino superior fictícia criada no contexto da
                        disciplina de Projeto de Software, com o objetivo de simular um
                        ambiente acadêmico real para fins educacionais e de desenvolvimento
                        de sistemas.
                    </p>
                    <p>
                        A UPSF serve como cenário para a aplicação prática de conceitos de
                        engenharia de software, modelagem de sistemas, desenvolvimento web
                        e boas práticas de programação.
                    </p>
                </section>

                {/* Missão, Visão e Valores */}
                <section className={styles.section}>
                    
                    <h2 className={styles.titleSection}>Missão, Visão e Valores</h2>
                    
                    <div className={styles.gridCards}>

                        <div className={styles.card}>
                            <span className={styles.iconCard}>🎯</span>
                            
                            <h3 className={styles.titleCard}>Missão</h3>
                            
                            <p className={styles.textCard}>
                                Promover a formação acadêmica de excelência por meio da
                                integração entre ensino, pesquisa e extensão, preparando
                                profissionais éticos e comprometidos com o desenvolvimento
                                da sociedade.
                            </p>
                        </div>

                        <div className={styles.card}>
                            <span className={styles.iconCard}>🔭</span>
                            
                            <h3 className={styles.titleCard}>Visão</h3>
                            
                            <p className={styles.textCard}>
                                Ser reconhecida como referência em inovação acadêmica e
                                tecnológica no estado do Rio de Janeiro, promovendo um
                                ambiente inclusivo, diverso e de qualidade para toda a
                                comunidade universitária.
                            </p>
                        </div>

                        <div className={styles.card}>
                            <span className={styles.iconCard}>💡</span>
                            
                            <h3 className={styles.titleCard}>Valores</h3>
                            
                            <ul className={styles.valuesList}>
                                <li>Ética e transparência</li>
                                <li>Qualidade no ensino</li>
                                <li>Diversidade e inclusão</li>
                                <li>Inovação e pensamento crítico</li>
                                <li>Responsabilidade social</li>
                            </ul>
                        </div>

                    </div>
                </section>

                {/* Timeline */}
                <section className={styles.section}>
                    <h2 className={styles.titleSection}>Nossa História</h2>
                    
                    <div className={styles.timelineDiv}>
                            
                            {timeline.map((item, index) => (
                                <div key={item.ano} className={styles.timelineItem}>
                                        
                                        <div className={styles.timelineYearSide}>
                                            
                                            <span className={styles.timelineYear}>{item.ano}</span>
                                        
                                        </div>
                                        
                                        <div className={styles.timelineLinha}>
                                            <span className={styles.timelinePoint} />
                                            {index < timeline.length - 1 && (
                                                <span className={styles.timelineConector} />
                                            )}
                                        </div>
                                        
                                        <div className={styles.timelineContent}>
                                            <p>{item.evento}</p>
                                        </div>
                                </div>
                            ))}
                    
                    </div>
                
                </section>

                {/* Contexto Acadêmico */}
                <section className={styles.section}>
                    <div className={styles.warning}>
                        <span>⚠️</span>
                        
                        <p>
                            Este sistema é um projeto acadêmico fictício desenvolvido para
                            fins educacionais na disciplina de Projeto de Software. Todo o
                            conteúdo institucional apresentado é simulado.
                        </p>
                    
                    </div>
                </section>

            </div>
        </main>
    );
}