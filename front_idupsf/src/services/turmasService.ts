
import { FiltrosBuscaTurmas } from "@/types/buscaTypes";
import { Turma } from "@/types/modelUPSF";

const BASE_URL = process.env.NEXT_PUBLIC_APP_URL ?? "http://localhost:3000";

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
};