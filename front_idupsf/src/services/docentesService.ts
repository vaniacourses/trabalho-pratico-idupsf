import { CamposEditaveis } from "@/components/EditarUsuarioForm";
import { Docente, DocenteCreate, Turma } from "@/types/modelUPSF";

const BASE_URL = process.env.NEXT_PUBLIC_APP_URL ?? "http://localhost:3000";

const BACKEND_URL = process.env.BACKEND_API_URL ?? "http://localhost:8080";

export const docentesService = {
    
    // Feito pra API Next
    async buscarDocentePorId(id: string): Promise<Docente> {
        const res = await fetch(`${BASE_URL}/api/docentes/${id}`);
        
        if (!res.ok) throw new Error("Erro ao buscar docente de id " + id);

        return res.json();
    },

    // Feito pra API Next
    async atualizarDocente(id: string, dados: CamposEditaveis): Promise<Docente> {
            
        const res = await fetch(`${BASE_URL}/api/docentes/${id}`, {
            method: "PATCH",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(dados),
        });

        if (!res.ok) throw new Error("Erro ao atualizar docente de id " + id);
        
        return res.json();
    },

    // Feito pro Back
    async listarTurmasPorDocente(id: string): Promise<Turma[]> {
        
        const res = await fetch(`${BACKEND_URL}/api/docentes/${id}/turmas`);

        if (!res.ok) throw new Error("Erro ao buscar turmas do docente de id " + id);
        
        return res.json();
    },

    async cadastrarDocente(dados: DocenteCreate): Promise<Docente> {
 
        const res = await fetch(`${BACKEND_URL}/api/docentes`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(dados),
        });
 
        if (!res.ok) throw new Error("Erro ao cadastrar docente");
 
        return res.json();
    }
};