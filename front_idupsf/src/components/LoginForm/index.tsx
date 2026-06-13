"use client";

import { useState } from "react";
import Image from "next/image";
import styles from "./styles.module.css";
import logoIdUPSF from "../../../public/logo_idUPSF.jpg";
import { formatCPF } from "@/utils/formatters";
import { signIn } from "next-auth/react";
import { useRouter } from "next/navigation";


export default function LoginForm() {
    
    const [erro, setErro] = useState("");
    
    const [loading, setLoading] = useState(false);

    const [cpf, setCpf] = useState("");
    const [senha, setSenha] = useState("");

    const router = useRouter();

    function handleCPF(e: React.ChangeEvent<HTMLInputElement>) {
        setCpf(formatCPF(e.target.value));
    }

    // Função de submit do Formulário de Login 
    // Chama o signIn do NextAuth com as credenciais
    // Lida com erros e redirecionamento
    async function handleSubmit(e: React.FormEvent) {
        e.preventDefault();
        setErro("");

        if (!cpf || !senha) {
            setErro("Preencha CPF e senha.");
            return;
        }

        setLoading(true);

        // Chama o signIn do NextAuth com as credenciais
        const result = await signIn("credentials", {
            cpf,
            senha,
            redirect: false,
        });

        if (result?.error) {
            setErro("CPF ou senha inválidos.");
            setLoading(false);
            return;
        }

        router.push("/home");
    }
    

    return (
        <form onSubmit={handleSubmit} className={styles.form}>

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
                
                <label htmlFor="senha" className={styles.label}>Sua Senha:</label>
                
                <input
                    id="senha"
                    name="senha"
                    type="password"
                    placeholder="Sua senha"
                    className={styles.input}
                    value={senha}
                    onChange={(e) => setSenha(e.target.value)}
                />
                
            </div>
            

            <button type="submit" disabled={loading} className={styles.btnLogin}>
                {loading ? "Logando..." : "Logar"}
            </button>

            {erro && <p className={styles.erro}>{erro}</p>}

        </form>
    );
}