"use client";

import { useState } from "react";
import styles from './styles.module.css'
import DinamicContent from "@/components/DinamicContent";
import PerfilCard from "../PerfilCard";
import { useUsuarioStore } from "@/stores/usuarioStore";
import CasesBox from "@/components/CasesBox";



export default function HomeClient() {
    const [caseSelecionado, setCaseSelecionado] = useState<string>("");

    function handleCaseSelect(caseKey: string) {
        setCaseSelecionado(caseKey);
    }

    const usuario = useUsuarioStore((state) => state.usuario);

    return (     

        <section className={styles.contentSection}>
            
            <div className={styles.funcsSection}>
                
                <PerfilCard />
                
                <CasesBox perfil={usuario!.perfil} onSelect={handleCaseSelect} caseSelecionado={caseSelecionado} />
            
            </div>

            
            <DinamicContent caseKey={caseSelecionado} perfil={usuario?.perfil} />
            
        
        </section>

    );
}