"use client";

import { signOut } from "next-auth/react";
import { useUsuarioStore } from "@/stores/usuarioStore";
import styles from "./styles.module.css"
import { redirect } from "next/dist/server/api-utils";

export default function BotaoLogout() {
    
    const limparUsuario = useUsuarioStore((state) => state.limparUsuario);

    async function handleLogout() {

        await signOut({ callbackUrl: "/login", redirect: true }); // limpa o cookie do NextAuth
        
        // Garantia extra — espera a navegação assentar antes de limpar o store
        setTimeout(() => {
            limparUsuario(); // limpa Zustand + localStorage
        }, 300); 
    }

    return (
        <button onClick={handleLogout} className={styles.link}>
            
            <i className="bi bi-box-arrow-right"></i>
            
            Sair
        
        </button>
    );
}