import { Docente, Turma } from "@/types/modelUPSF";


const BACKEND_URL = process.env.BACKEND_API_URL ?? "http://localhost:8080";

export const cursoService = {
    
    async listarDocentesDoDepartamentoDoCurso(cursoId: string): Promise<Docente[]> {
                
        const res = await fetch(`${BACKEND_URL}/api/cursos/${cursoId}/departamento/docentes`);

        if (!res.ok) throw new Error("Erro ao buscar docentes do curso de id " + cursoId);
        
        return res.json();
    },
    
    // Feito pro Back
    async listarTurmasDosCurriculosDoCurso(cursoId: string): Promise<Turma[]> {
        
        const res = await fetch(`${BACKEND_URL}/api/cursos/${cursoId}/curriculos/turmas`);

        if (!res.ok) throw new Error("Erro ao buscar turmas do curso de id " + cursoId);
        
        return res.json();
    }
}