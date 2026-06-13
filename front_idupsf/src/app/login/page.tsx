
import Image from "next/image";
import styles from "./styles.module.css";
import LoginForm from "@/components/LoginForm";
import predioUPSF from "../../../public/predio_idUPSF.jpeg";

export default function LoginPage() {
  return (
    
    <main className={styles.main}>

        <section className={styles.formSide}>
            
            <LoginForm />
        
        </section>

        <section className={styles.imageSide}>
            
            <Image
				src={predioUPSF}
				alt="Prédio da Universidade Projeto de Software Fluminense"
				className={styles.image}
				priority
            />
        
        </section>

    </main>
  );
}