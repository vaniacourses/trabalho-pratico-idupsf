import Image from 'next/image'
import logo from '../../../public/logo_idUPSF.jpg'
import styles from './styles.module.css'
import Link from 'next/link'


export default function NavBar() {
    return (
        <nav className={styles.navBar}>

            <div className={styles.logoDiv}>
                
                <figure className={styles.figure}>
                    <Image src={logo} alt="Logo idUPSF" />
                </figure>
                
                <h1 className={styles.title}>Id-UPSF</h1>
            
            </div>
            
            <div className={styles.linksDiv}>
                
                <Link href="/home" className={styles.link}>
                    Início
                </Link>

                <Link href="/sobre" className={styles.link}>
                    Sobre
                </Link>

                <Link href="/inscricao" className={styles.link}>
                    Inscrição On-line
                </Link>

                <Link href="/quadro" className={styles.link}>
                    Quadro de Horários
                </Link>

                <Link href="/solicita" className={styles.link}>
                    Solicitação UPSF
                </Link>
            
            </div>
            

        </nav>
    )
}