
import { FiltrosBuscaTurmas } from "@/types/buscaTypes";
import { Turma } from "@/types/modelUPSF";


export const turmaService = {
    async buscarComFiltros(filtros: FiltrosBuscaTurmas): Promise<Turma[]> {
        const params = new URLSearchParams();

        if (filtros.nomeCodDisciplina) params.append("nomeCodDisciplina", filtros.nomeCodDisciplina);
        if (filtros.nomeDocente)       params.append("nomeDocente", filtros.nomeDocente);
        if (filtros.departamentoCod)    params.append("departamento", filtros.departamentoCod);
        if (filtros.anoSemestre)       params.append("anoSemestre", filtros.anoSemestre);
        if (filtros.turno)             params.append("turno", filtros.turno);
        if (filtros.cursoId)           params.append("cursoId", filtros.cursoId);
        if (filtros.curriculoId)       params.append("curriculoId", filtros.curriculoId);

        const queryString = params.toString();
        
        const res = await fetch(`/api/quadro${queryString ? `?${queryString}` : ""}`);
        
        if (!res.ok) throw new Error("Erro ao buscar turmas");
        
        return res.json();
    },
};