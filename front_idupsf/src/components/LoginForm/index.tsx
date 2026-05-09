"use client";

import { useState } from "react";
import Image from "next/image";
// import { loginAction } from "@/actions/auth";
import styles from "./styles.module.css";
import logoIdUPSF from "../../../public/logo_idUPSF.jpg";
import { formatCPF } from "@/utils/formatCPF";
import { loginAction } from "@/actions/auth";

export default function LoginForm() {
    
    const [erro, setErro] = useState("");
    
    const [loading, setLoading] = useState(false);

    const [cpf, setCpf] = useState("");

    function handleCPF(e: React.ChangeEvent<HTMLInputElement>) {
        setCpf(formatCPF(e.target.value));
    }

    async function handleSubmit(formData: FormData) {
        setErro("");
        setLoading(true);

        const resposta = await loginAction(formData);

        if (resposta?.erro) {
            setErro(resposta.erro);
            setLoading(false);
        }
    }
    

    return (
        <form action={handleSubmit} className={styles.form}>

            <div className={styles.headerLogin}>
                <figure className={styles.logoFigure}>
                    <Image src={logoIdUPSF} alt="Logo da Universidade Projeto de Software Fluminense" className={styles.logo} />
                </figure>
        
                <h1>Acesse seu id-UPSF</h1>
            </div>

            <div className={styles.field}>
                <label htmlFor="cpf" className={styles.label}>Seu CPF:</label>
                
                <input
                    id="cpf"
                    name="cpf"
                    type="text"
                    placeholder="Seu CPF (Somente números)"
                    value={cpf}
                    onChange={handleCPF}
                    maxLength={14}
                    className={styles.input}
                />

            </div>
            
            <div className={styles.field}>
                
                <label htmlFor="Senha" className={styles.label}>Sua Senha:</label>
                
                <input
                    id="senha"
                    name="senha"
                    type="password"
                    placeholder="Sua senha"
                    className={styles.input}
                />
                
            </div>
            

            <button type="submit" disabled={loading} className={styles.btnLogin}>
                {loading ? "Logando..." : "Logar"}
            </button>

            {erro && <p className={styles.erro}>{erro}</p>}

        </form>
    );
}