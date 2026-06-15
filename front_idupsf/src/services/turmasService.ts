
import { FiltrosBuscaTurmas } from "@/types/buscaTypes";
import { Inscricao, Turma } from "@/types/modelUPSF";

const BASE_URL = process.env.NEXT_PUBLIC_APP_URL ?? "http://localhost:3000";

const BACKEND_URL = process.env.BACKEND_API_URL ?? "http://localhost:8080";

export const turmaService = {
    
    async buscarComFiltros(filtros: FiltrosBuscaTurmas): Promise<Turma[]> {
        const params = new URLSearchParams();

        if (filtros.nomeCodDisciplina) params.append("nomeCodDisciplina", filtros.nomeCodDisciplina);
        if (filtros.nomeDocente)       params.append("nomeDocente", filtros.nomeDocente);
        if (filtros.departamentoCod)    params.append("departamento", filtros.departamentoCod);
        if (filtros.anoSemestre)       params.append("anoSemestre", filtros.anoSemestre);
        if (filtros.cursoId)           params.append("cursoId", filtros.cursoId);
        if (filtros.curriculoId)       params.append("curriculoId", filtros.curriculoId);

        const queryString = params.toString();
        
        
        const res = await fetch(`${BASE_URL}/api/quadro${queryString ? `?${queryString}` : ""}`);
        
        if (!res.ok) throw new Error("Erro ao buscar turmas");
        
        console.log(queryString)
        
        return res.json();
    },
    
    async buscarTurmaPorId(id: string): Promise<Turma> {

        const res = await fetch(`${BACKEND_URL}/api/turmas/${id}`);

        if (!res.ok) throw new Error("Erro ao buscar turma");
        
        return res.json();
    },

    async listarInscricoesPorTurma(id: string): Promise<Inscricao[]> {

        const res = await fetch(`${BACKEND_URL}/api/turmas/${id}/inscricoes`);

        if (!res.ok) throw new Error("Erro ao buscar inscrições em turma");
        
        return res.json();
    }
};