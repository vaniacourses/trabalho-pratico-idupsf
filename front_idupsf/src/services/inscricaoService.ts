import { Turma, Inscricao, InscricaoResponseDTO, InscricaoCreate } from "@/types/modelUPSF";

const BASE_URL = process.env.NEXT_PUBLIC_APP_URL ?? "http://localhost:3000";


export const inscricaoService = {

    async listarTurmasDisponiveis(discenteId: string): Promise<Turma[]> {
        
        const res = await fetch(`${BASE_URL}/api/inscricoes/disponiveis/${discenteId}`);
        
        if (!res.ok) throw new Error("Erro ao buscar turmas disponíveis");
        
        return res.json();
    },

    async realizarInscricao(dados: InscricaoCreate): Promise<Inscricao[]> {
        
        const res = await fetch(`${BASE_URL}/api/inscricoes`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(dados),
        });
        
        if (!res.ok) throw new Error("Erro ao realizar inscrição");
        
        return res.json();
    },
};