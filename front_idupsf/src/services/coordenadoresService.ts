import { CamposEditaveis } from "@/components/EditarUsuarioForm";
import { Coordenador } from "@/types/modelUPSF";

const BASE_URL = process.env.NEXT_PUBLIC_APP_URL ?? "http://localhost:3000";

export const coordenadoresService = {
    async buscarCoordenadorPorId(id: string): Promise<Coordenador> {
        const res = await fetch(`${BASE_URL}/api/coordenadores/${id}`);
        
        if (!res.ok) throw new Error("Erro ao buscar coordenador de id " + id);

        return res.json();
    },

    async atualizarCoordenador(id: string, dados: CamposEditaveis): Promise<Coordenador> {
            
        const res = await fetch(`${BASE_URL}/api/coordenadores/${id}`, {
            method: "PATCH",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(dados),
        });

        if (!res.ok) throw new Error("Erro ao atualizar coordenador de id " + id);
        
        return res.json();
    }
};