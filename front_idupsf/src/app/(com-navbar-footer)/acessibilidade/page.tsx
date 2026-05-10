import { link } from "fs";
import styles from "./styles.module.css";

const recursos = [
  {
    icone: "🏷️",
    titulo: "HTML Semântico",
    descricao: "Estrutura semântica com HTML5 para compatibilidade com leitores de tela.",
  },
  {
    icone: "🖼️",
    titulo: "Textos Alternativos",
    descricao: "Todas as imagens possuem descrições alternativas adequadas.",
  },
  {
    icone: "⌨️",
    titulo: "Navegação por Teclado",
    descricao: "Todas as funcionalidades são acessíveis sem o uso de mouse.",
  },
  {
    icone: "🎨",
    titulo: "Contraste de Cores",
    descricao: "Paleta de cores com contraste adequado conforme WCAG 2.1 nível AA.",
  },
  {
    icone: "📝",
    titulo: "Formulários Acessíveis",
    descricao: "Labels associados a todos os campos com mensagens de erro descritivas.",
  },
  {
    icone: "🔍",
    titulo: "Zoom até 200%",
    descricao: "Conteúdo legível e funcional mesmo com zoom elevado no navegador.",
  },
];

const tecnologias = [
  { icone: "🖥️", nome: "NVDA", descricao: "Leitor de tela para Windows" },
  { icone: "🍎", nome: "VoiceOver", descricao: "Leitor de tela para macOS e iOS" },
  { icone: "⌨️", nome: "Teclado", descricao: "Navegação completa sem mouse" },
  { icone: "🔎", nome: "Zoom", descricao: "Suporte a zoom até 200%" },
];

const baseLegal = [
  {
    sigla: "LBI",
    nome: "Lei Brasileira de Inclusão",
    link: "https://www.planalto.gov.br/ccivil_03/_ato2015-2018/2015/lei/l13146.htm",
    descricao: "Lei nº 13.146/2015 — Garante e promove os direitos das pessoas com deficiência.",
  },
  {
    sigla: "WCAG",
    nome: "Web Content Accessibility Guidelines",
    link: "https://www.w3.org/TR/WCAG21/",
    descricao: "Diretrizes internacionais de acessibilidade para conteúdo web — nível AA.",
  },
  {
    sigla: "eMAG",
    nome: "Modelo de Acessibilidade em Governo Eletrônico",
    link: "https://emag.governoeletronico.gov.br/",
    descricao: "Padrão brasileiro de acessibilidade para sistemas governamentais.",
  },
  {
    sigla: "Dec. 5.296",
    nome: "Decreto nº 5.296/2004",
    link: "https://www.planalto.gov.br/ccivil_03/_ato2004-2006/2004/decreto/d5296.htm",
    descricao: "Regulamenta as leis de acessibilidade no Brasil, incluindo meios digitais.",
  },
];

export default function AcessibilidadePage() {
  return (
    <main className={styles.main}>

      {/* Banner Hero */}
      <section className={styles.bannerHero}>
        
        <div className={styles.heroContent}>
          
          <p className={styles.heroIncludeMsg}>Inclusão Digital</p>
          
          <h1 className={styles.titleHero}>Acessibilidade</h1>
          
          <p className={styles.subtitleHero}>
            O idUPSF foi desenvolvido para ser acessível a todos os membros
            da comunidade acadêmica, independentemente de suas limitações.
          </p>
        
        </div>
        
        <span className={styles.iconHero}>♿</span>
      
      </section>

      <div className={styles.info}>

        {/* Compromisso */}
        <section className={styles.section}>
          
          <h2 className={styles.titleSection}>Nosso compromisso</h2>
          
          <p>
            O idUPSF está comprometido em oferecer uma experiência digital
            acessível a todos os membros da comunidade acadêmica, incluindo
            pessoas com deficiência, em conformidade com as diretrizes da
            WCAG 2.1 e com a Lei Brasileira de Inclusão (LBI — Lei nº 13.146/2015).
          </p>
        
        </section>

        {/* Recursos de acessibilidade */}
        <section className={styles.section}>
          
          <h2 className={styles.titleSection}>Recursos de acessibilidade</h2>
          
          <div className={styles.gridCards}>
            
            {recursos.map((item) => (
              <div key={item.titulo} className={styles.card}>
                
                <span className={styles.iconCard}>{item.icone}</span>
                
                <div>
                  <h3 className={styles.titleCard}>{item.titulo}</h3>
                  <p className={styles.textCard}>{item.descricao}</p>
                </div>
              
              </div>
            ))}
          
          </div>
        
        </section>

        {/* Tecnologias Assistivas */}
        <section className={styles.section}>
          
          <h2 className={styles.titleSection}>Tecnologias assistivas suportadas</h2>
          
          <div className={styles.technologies}>
            
            {tecnologias.map((item) => (
              <div key={item.nome} className={styles.technologyCard}>
                <span className={styles.technologyIcon}>{item.icone}</span>
                <strong className={styles.technologyName}>{item.nome}</strong>
                <p>{item.descricao}</p>
              </div>
            ))}
          
          </div>
        
        </section>

        {/* Limitações conhecidas */}
        <section className={styles.section}>
          <div className={styles.warning}>
            <span className={styles.warningIcon}>⚠️</span>
            <div>
              <strong>Limitações conhecidas</strong>
              <p>
                Como projeto acadêmico em desenvolvimento, algumas funcionalidades
                podem ainda não estar totalmente adequadas às diretrizes de
                acessibilidade. Trabalhamos continuamente para identificar e
                corrigir essas limitações.
              </p>
            </div>
          </div>
        </section>

        {/* Base Legal */}
        <section className={styles.section}>
          <h2 className={styles.titleSection}>Base Legal (Navegável)</h2>
          
          <div className={styles.legalBase}>
            
            {baseLegal.map((item) => (
              
              <a key={item.sigla} href={item.link} target="_blank" rel="noopener noreferrer" className={styles.lawCard}>
                
                <span className={styles.lawSigla}>
                  {item.sigla}
                </span>
                
                <div>
                  <strong className={styles.lawName}>{item.nome}</strong>
                  <p>{item.descricao}</p>
                </div>
              
              </a>
            
            ))}
          
          </div>
        
        </section>

        {/* Contato */}
        <section className={styles.section}>
          
          <div className={styles.contactDiv}>
            <span className={styles.contactIcon}>✉️</span>
            
            <div>
              
              <h3 className={styles.contactTitle}>Encontrou uma barreira?</h3>
              
              <p className={styles.contactText}>
                Se você encontrar dificuldades de acessibilidade ao usar o idUPSF,
                entre em contato pelo e-mail{" "}
                <strong>acessibilidade@upsf.edu.br</strong>. Seu feedback é
                essencial para melhorarmos o sistema.
              </p>
            
            </div>
          
          </div>
        
        </section>

      </div>
    </main>
  );
}