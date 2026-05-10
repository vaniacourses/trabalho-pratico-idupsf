import Image from "next/image";
import Link from "next/link";
import logo from '../../../public/logo_idUPSF.jpg'
import styles from "./styles.module.css";

export default function Footer() {
  return (
    <footer className={styles.footer}>
        
        <div className={styles.container}>

            <div className={styles.marca}>
                
                <div className={styles.logoDiv}>
                
                    <figure className={styles.figure}>
                        <Image src={logo} alt="Logo idUPSF" />
                    </figure>
                    
                    <h1 className={styles.titleLogo}>Id-UPSF</h1>
            
                </div>
                
                <p className={styles.description}>
                    Plataforma de Identificação Digital e Sistema de Acesso Unificado da
                    Universidade de Projeto de Software Fluminense.
                </p>
            
            </div>

            <div className={styles.column}>
                
                <h3 className={styles.title}>Navegação</h3>
                
                <ul className={styles.list}>
                    <Link href="/home" className={styles.link}>Início</Link>
                    
                    <Link href="/sobre" className={styles.link}>Sobre</Link>
                    
                    <Link href="/inscricao" className={styles.link}>Inscrição On-line</Link>
                    
                    <Link href="/quadro" className={styles.link}>Quadro de Horários</Link>
                    
                    <Link href="/solicita" className={styles.link}>Solicitação UPSF</Link>
                </ul>
            
            </div>

            <div className={styles.column}>
                <h3 className={styles.title}>Institucional</h3>
                
                <ul className={styles.list}>
                    <Link href="/sobreUPSF" className={styles.link}>Sobre a UPSF</Link>
                    
                    <Link href="/politica" className={styles.link}>Política de Privacidade</Link>
                    
                    <Link href="/termos" className={styles.link}>Termos de Uso</Link>
                    
                    <Link href="/acessibilidade" className={styles.link}>Acessibilidade</Link>
                </ul>
            </div>

            <div className={styles.column}>
                <h3 className={styles.title}>Contato</h3>
                <ul className={styles.list}>
                    <li className={styles.contato}>📍 Niterói, Rio de Janeiro</li>
                    <li className={styles.contato}>📞 (21) 0000-0000</li>
                    <li className={styles.contato}>✉️ contato@upsf.edu.br</li>
                </ul>
            </div>

        </div>

        <div className={styles.rodape}>
            
            <p>© {new Date().getFullYear()} Id-UPSF — Todos os direitos reservados.</p>
            
            <p>Universidade de Projeto de Software Fluminense</p>
        
        </div>

    </footer>
  );
}