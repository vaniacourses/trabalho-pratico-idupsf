import styles from "./styles.module.css";

const secoes = [
  {
    numero: "01",
    titulo: "Aceitação dos termos",
    conteudo:
      "Ao acessar e utilizar o idUPSF, o usuário declara ter lido, compreendido e concordado com os presentes Termos de Uso. Caso não concorde com qualquer disposição, o acesso ao sistema deve ser interrompido.",
  },
  {
    numero: "02",
    titulo: "Acesso ao sistema",
    conteudo:
      "O acesso ao idUPSF é restrito a membros da comunidade acadêmica da UPSF — discentes, docentes e coordenadores — mediante autenticação por CPF e senha institucional. O usuário é responsável pela confidencialidade de suas credenciais e por todas as ações realizadas em sua conta.",
  },
  {
    numero: "03",
    titulo: "Uso adequado",
    lista: [
      "Compartilhar credenciais de acesso com terceiros",
      "Tentar acessar contas ou dados de outros usuários",
      "Inserir informações falsas ou enganosas no sistema",
      "Utilizar o sistema para fins alheios ao contexto acadêmico",
      "Realizar tentativas de invasão ou comprometimento do sistema",
    ],
    prefixo: "É vedado ao usuário:",
  },
  {
    numero: "04",
    titulo: "Responsabilidades",
    conteudo:
      "O idUPSF se compromete a manter o sistema disponível e seguro, mas não se responsabiliza por eventuais indisponibilidades decorrentes de manutenção, falhas técnicas ou fatores externos.",
  },
  {
    numero: "05",
    titulo: "Propriedade intelectual",
    conteudo:
      "Todo o conteúdo, design e código do idUPSF são de propriedade dos desenvolvedores do projeto acadêmico. É vedada a reprodução ou distribuição sem autorização expressa.",
  },
  {
    numero: "06",
    titulo: "Alterações nos termos",
    conteudo:
      "Estes termos podem ser atualizados a qualquer momento. O uso continuado do sistema após alterações implica a aceitação dos novos termos.",
  },
  {
    numero: "07",
    titulo: "Legislação aplicável",
    conteudo:
      "Estes termos são regidos pela legislação brasileira, em especial pela Lei Geral de Proteção de Dados (LGPD — Lei nº 13.709/2018) e pelo Marco Civil da Internet (Lei nº 12.965/2014).",
  },
];

const destaques = [
  { icone: "🔐", titulo: "Acesso restrito", descricao: "Apenas membros da comunidade UPSF" },
  { icone: "📋", titulo: "Uso responsável", descricao: "Uso exclusivamente acadêmico" },
  { icone: "⚖️", titulo: "Amparo legal", descricao: "LGPD e Marco Civil da Internet" },
  { icone: "🛡️", titulo: "Seus dados protegidos", descricao: "Segurança e privacidade garantidas" },
];

export default function TermosPage() {
    return (
        <main className={styles.main}>

            {/* Banner Hero */}
            <section className={styles.bannerHero}>
                
                <div className={styles.heroContent}>
                    
                    <p className={styles.heroDocText}>Documentação legal</p>
                    
                    <h1 className={styles.titleHero}>Termos de Uso</h1>
                    
                    <p className={styles.subtitleHero}>
                        Leia com atenção antes de utilizar o idUPSF. Ao acessar o sistema,
                        você concorda automaticamente com os termos descritos abaixo.
                    </p>
                    
                    <p className={styles.dataHero}>Última atualização: 10 de Maio de 2026</p>
                
                </div>
                
                <span className={styles.iconHero}>⚖️</span>
            
            </section>

            {/* Destaques */}
            <section className={styles.gridDestaques}>
                
                {destaques.map((item) => (
                    <div key={item.titulo} className={styles.destaqueCard}>
                        
                        <span className={styles.destaqueIcon}>{item.icone}</span>
                        
                        <strong className={styles.destaqueTitle}>{item.titulo}</strong>
                        
                        <p className={styles.destaqueDescription}>{item.descricao}</p>
                    
                    </div>
                ))}
            
            </section>

            <div className={styles.info}>

                {/* Aviso */}
                <div className={styles.warning}>
                    
                    <span>⚠️</span>
                    
                    <p>
                        Este sistema é um projeto acadêmico fictício desenvolvido para fins
                        educacionais na disciplina de Projeto de Software. Os termos abaixo
                        existem para simulação de um ambiente institucional real.
                    </p>
                
                </div>

                {/* Seções */}
                <div className={styles.sectionsDiv}>
                    
                    {secoes.map((secao) => (
                        <div key={secao.numero} className={styles.section}>
                            <div className={styles.numberSection}>{secao.numero}</div>
                            
                            <div className={styles.bodySection}>
                                <h2 className={styles.titleSection}>{secao.titulo}</h2>
                                
                                {secao.prefixo && (
                                <p className={styles.secaoTexto}>{secao.prefixo}</p>
                                )}
                                
                                {secao.conteudo && (
                                <p className={styles.secaoTexto}>{secao.conteudo}</p>
                                )}
                                
                                {secao.lista && (
                                    <ul className={styles.listSection}>
                                        
                                        {secao.lista.map((item) => (
                                            <li key={item} className={styles.listItem}>
                                                <span className={styles.listIcon}>✕</span>
                                                {item}
                                            </li>
                                        ))}
                                    
                                    </ul>
                                )}
                            
                            </div>
                        </div>
                    ))}
                
                </div>

                {/* CONTATO */}
                <div className={styles.contact}>
                    <span className={styles.contactIcon}>✉️</span>
                    
                    <div>
                        
                        <h3 className={styles.contactTitle}>Dúvidas sobre os termos?</h3>
                        
                        <p className={styles.contactText}>
                        Entre em contato pelo e-mail fictício{" "}
                        <strong>juridico@upsf.edu.br</strong>. Nossa equipe responderá
                        em até 5 dias úteis.
                        </p>
                    
                    </div>
                
                </div>

            </div>
        </main>
    );
}