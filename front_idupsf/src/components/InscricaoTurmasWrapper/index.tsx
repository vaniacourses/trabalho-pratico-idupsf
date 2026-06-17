"use client";

import { useState, useEffect } from "react";
import { useUsuarioStore } from "@/stores/usuarioStore";
import { inscricaoService } from "@/services/inscricaoService";
import { Turma } from "@/types/modelUPSF";
import InscricaoTurmas from "../InscricaoTurmas";

export default function InscricaoTurmasWrapper() {
    
    const usuario = useUsuarioStore((state) => state.usuario);
    
    const [turmas, setTurmas] = useState<Turma[] | null>(null);

    useEffect(() => {
        if (!usuario?.id) return;
        inscricaoService.listarTurmasDisponiveis(String(usuario.id)).then(setTurmas);
    }, [usuario?.id]);

    if (!usuario?.id) return null;
    
    if (turmas === null) return <p>Carregando turmas disponíveis...</p>;

    return <InscricaoTurmas discenteId={String(usuario.id)} turmasIniciais={turmas} />;
}