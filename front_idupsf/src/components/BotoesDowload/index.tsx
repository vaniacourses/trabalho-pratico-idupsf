"use client";

import { documentosService } from "@/services/documentosService";
import styles from "./styles.module.css"


export default function BotoesDownload({ id }: { id?: string }) {
    
    async function handleDownload(arquivo: string) {
        try {
            
            let blob; 
            
            if (arquivo === "historico.pdf") {
                blob = await documentosService.baixarHistorico(id!);
            }
            else {
                blob = await documentosService.baixarRegularidade(id!);
            }

            const url = window.URL.createObjectURL(blob);
            const link = document.createElement("a");
            link.href = url;
            link.download = arquivo; // ex: "declaracao.pdf"
            document.body.appendChild(link);
            link.click();
            document.body.removeChild(link);
            window.URL.revokeObjectURL(url);

        } catch (error) {
            console.error("Erro ao baixar:", error);
        }
    }

    return (
        
        <div className={styles.btnsDiv}>

            <button className={styles.caseCard} type="button" onClick={() => handleDownload("historico.pdf")}>
                Gerar Declaração de Histórico
            </button>

            <button className={styles.caseCard} type="button" onClick={() => handleDownload("regularidade.pdf")}>
                Gerar Declaração de Regularidade de Matrícula
            </button>
        
        </div>
    );
}