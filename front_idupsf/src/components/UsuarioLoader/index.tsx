"use client";

import { useEffect } from "react";
import { useSession } from "next-auth/react";
import { useUsuarioStore } from "@/stores/usuarioStore";
import { Perfil } from "@/types/modelUPSF";

export default function UsuarioLoader() {
    
    const { data: session, status } = useSession();

    const usuario = useUsuarioStore((state) => state.usuario);
    const carregarUsuario = useUsuarioStore((state) => state.carregarUsuario);
    const limparUsuario = useUsuarioStore((state) => state.limparUsuario);

    useEffect(() => {
        if (status === "loading") return;

        if (session?.user?.id && session?.user?.perfil) {
            
            // só recarrega se não tiver usuario no store
            if (!usuario) {
                carregarUsuario(session.user.id, session.user.perfil as Perfil);
                // carregarUsuario("3", "DOCENTE");
            }
        } else {
            limparUsuario();
        }
    
    }, [session, status]);

    // Componente invisível, só inicializa o UsuarioStore
    return null;
}