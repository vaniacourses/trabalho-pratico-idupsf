import { CamposEditaveis } from "@/components/EditarUsuarioForm";
import { Discente } from "@/types/modelUPSF";


const BASE_URL = process.env.NEXT_PUBLIC_APP_URL ?? "http://localhost:3000";

export const discentesService = {
    async buscarDiscentePorId(id: string): Promise<Discente> {
        const res = await fetch(`${BASE_URL}/api/discentes/${id}`);
		
		if (!res.ok) throw new Error("Erro ao buscar discente de id " + id);

		return res.json();
    },
    
    async atualizarDiscente(id: string, dados: CamposEditaveis): Promise<Discente> {
        
        const res = await fetch(`${BASE_URL}/api/discentes/${id}`, {
            method: "PATCH",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(dados),
        });

        if (!res.ok) throw new Error("Erro ao atualizar discente de id " + id);
        
        return res.json();
    }
};
