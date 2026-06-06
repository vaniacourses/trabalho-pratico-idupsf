import { CamposEditaveis } from "@/components/EditarUsuarioForm";
import { Docente } from "@/types/modelUPSF";

const BASE_URL = process.env.NEXT_PUBLIC_APP_URL ?? "http://localhost:3000";

export const docentesService = {
    async buscarDocentePorId(id: string): Promise<Docente> {
        const res = await fetch(`${BASE_URL}/api/docentes/${id}`);
        
        if (!res.ok) throw new Error("Erro ao buscar docente de id " + id);

        return res.json();
    },

    async atualizarDocente(id: string, dados: CamposEditaveis): Promise<Docente> {
            
        const res = await fetch(`${BASE_URL}/api/docentes/${id}`, {
            method: "PATCH",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(dados),
        });

        if (!res.ok) throw new Error("Erro ao atualizar docente de id " + id);
        
        return res.json();
    }
};