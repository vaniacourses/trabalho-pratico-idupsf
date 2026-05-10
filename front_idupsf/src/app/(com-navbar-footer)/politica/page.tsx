import styles from "./styles.module.css";

const dadosColetados = [
  { icone: "👤", dado: "Nome completo" },
  { icone: "🪪", dado: "CPF" },
  { icone: "🎓", dado: "Matrícula institucional" },
  { icone: "📧", dado: "E-mail institucional" },
  { icone: "📅", dado: "Data de nascimento" },
  { icone: "📚", dado: "Dados acadêmicos (curso, período, histórico)" },
];

const finalidades = [
  { icone: "🔐", titulo: "Autenticação", descricao: "Controle de acesso e identificação no sistema." },
  { icone: "📊", titulo: "Dados acadêmicos", descricao: "Exibição de informações personalizadas por perfil." },
  { icone: "📝", titulo: "Inscrições", descricao: "Gerenciamento de inscrições em turmas e disciplinas." },
  { icone: "📋", titulo: "Relatórios", descricao: "Geração de relatórios acadêmicos internos." },
];

const direitos = [
  { numero: "01", titulo: "Acesso", descricao: "Consultar os dados pessoais armazenados no sistema." },
  { numero: "02", titulo: "Correção", descricao: "Solicitar a correção de dados incorretos ou desatualizados." },
  { numero: "03", titulo: "Exclusão", descricao: "Solicitar a exclusão dos seus dados pessoais." },
  { numero: "04", titulo: "Revogação", descricao: "Revogar o consentimento a qualquer momento." },
  { numero: "05", titulo: "Portabilidade", descricao: "Solicitar a transferência dos seus dados a outro sistema." },
  { numero: "06", titulo: "Informação", descricao: "Ser informado sobre o uso e compartilhamento dos seus dados." },
];

export default function PoliticaPage() {
    return (
        <main className={styles.main}>

            {/* HERO */}
            <section className={styles.bannerHero}>
                
                <div className={styles.heroContent}>
                    
                    <p className={styles.heroMsg}>Transparência e segurança</p>
                    
                    <h1 className={styles.titleHero}>Política de Privacidade</h1>
                    
                    <p className={styles.subtitleHero}>
                        Saiba como o idUPSF coleta, utiliza e protege os seus dados
                        pessoais em conformidade com a LGPD.
                    </p>
                    
                    <p className={styles.dataHero}>Última atualização: maio de 2026</p>
                
                </div>
                
                <span className={styles.iconHero}>🛡️</span>
            
            </section>

            <div className={styles.info}>

                {/* Aviso */}
                <div className={styles.warning}>
                    
                    <span>⚠️</span>
                    
                    <p>
                        Este sistema é um projeto acadêmico fictício desenvolvido para fins
                        educacionais na disciplina de Projeto de Software. Os dados inseridos
                        não são compartilhados com terceiros nem utilizados fora do contexto
                        acadêmico.
                    </p>
                
                </div>

                {/* Introdução */}
                <section className={styles.section}>
                    
                    <h2 className={styles.titleSection}>Introdução</h2>
                    
                    <p>
                        A presente Política de Privacidade descreve como o idUPSF coleta,
                        utiliza e protege as informações dos usuários, em conformidade com
                        a Lei Geral de Proteção de Dados Pessoais (LGPD — Lei nº 13.709/2018).
                        Ao utilizar o sistema, o usuário consente com as práticas aqui descritas.
                    </p>
                
                </section>

                {/* Dados Coletados */}
                <section className={styles.section}>
                    
                    <h2 className={styles.titleSection}>Dados coletados</h2>
                    
                    <p>Para fins de autenticação e uso do sistema, são coletados:</p>
                    
                    <div className={styles.gridDados}>
                        
                        {dadosColetados.map((item) => (
                            
                            <div key={item.dado} className={styles.dadoCard}>
                                
                                <span className={styles.iconDado}>{item.icone}</span>
                                
                                <span className={styles.textDado}>{item.dado}</span>
                            
                            </div>
                        ))}
                    
                    </div>
                
                </section>

                {/* Finalidades */}
                <section className={styles.section}>
                    
                    <h2 className={styles.titleSection}>Finalidade do tratamento</h2>
                    
                    <div className={styles.finalidades}>
                        {finalidades.map((item) => (
                            <div key={item.titulo} className={styles.finalidadeCard}>
                                
                                <span className={styles.iconFinalidade}>{item.icone}</span>
                                
                                <div>
                                    <strong className={styles.finalidadeTitle}>{item.titulo}</strong>
                                    <p className={styles.finalidadeDescription}>{item.descricao}</p>
                                </div>
                            
                            </div>
                        ))}
                    </div>
                
                </section>

                {/* Compartilhamento */}
                <section className={styles.section}>
                    
                    <h2 className={styles.titleSection}>Compartilhamento de dados</h2>
                    
                    <div className={styles.destaque}>
                        <span className={styles.destaqueIcon}>🚫</span>
                        <p>
                        Os dados dos usuários <strong>não são compartilhados com terceiros</strong>,
                        vendidos ou utilizados para fins comerciais. O acesso é restrito
                        aos próprios usuários e aos administradores do sistema, no contexto
                        exclusivo do projeto acadêmico.
                        </p>
                    </div>
                
                </section>

                {/* Direitos */}
                <section className={styles.section}>
                    
                    <h2 className={styles.titleSection}>Seus direitos</h2>
                    
                    <p>Em conformidade com a LGPD, você tem direito a:</p>
                    
                    <div className={styles.direitos}>
                        {direitos.map((item) => (
                        <div key={item.numero} className={styles.direitoCard}>
                            <span className={styles.direitoNumber}>{item.numero}</span>
                            
                            <div>
                                <strong className={styles.direitoTitle}>{item.titulo}</strong>
                                
                                <p className={styles.direitoDescription}>{item.descricao}</p>
                            </div>
                        </div>
                        ))}
                    </div>
                
                </section>

                {/* Segurança */}
                <section className={styles.section}>
                    <h2 className={styles.titleSection}>Segurança</h2>
                    
                    <p>
                        O sistema adota medidas técnicas para proteger os dados dos
                        usuários, incluindo autenticação por CPF e senha, controle de
                        sessão e restrição de acesso por perfil (discente, docente,
                        coordenador).
                    </p>
                
                </section>

                {/* Contato */}
                <div className={styles.contact}>
                    <span className={styles.contactIcon}>✉️</span>
                    
                    <div>
                        <h3 className={styles.contactTitle}>
                            Dúvidas sobre sua privacidade?
                        </h3>

                        <p className={styles.contactText}>
                            Para dúvidas ou solicitações relacionadas à privacidade, entre em
                            contato pelo e-mail fictício{" "}
                            <strong>privacidade@upsf.edu.br</strong>. Responderemos em até
                            5 dias úteis.
                        </p>
                    </div>
                
                </div>

            </div>
        
        </main>
    );
}