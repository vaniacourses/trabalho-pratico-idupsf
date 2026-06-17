"use client";

import BotoesDowload from "../BotoesDowload";
import styles from "./styles.module.css"
import { useUsuarioStore } from "@/stores/usuarioStore";


export default function GerarDeclaracoes() {

    const usuario = useUsuarioStore((state) => state.usuario);

    return (
        <section className={styles.caseBlockSection}>
            
            <header className={styles.headerCase}>
                
                <h1 className={styles.title}>Gerar Declarações</h1>
            
            </header>

            <BotoesDowload id={usuario!.id}/>
            

        </section>
    )
}