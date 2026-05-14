
import { Curriculo, Curso, Departamento } from "@/types/modelUPSF";

export const opcoesService = {
  async listarDepartamentos(): Promise<Departamento[]> {
    
    const res = await fetch("/api/opcoes/departamentos");
    
    if (!res.ok) throw new Error("Erro ao buscar departamentos");

    return res.json();
  },

  // Ainda não funcional
  // Necessita arrumar relação entre Turma e (Curso, Currículo)
  async listarCursos(): Promise<Curso[]> {
    
    const res = await fetch("/api/opcoes/cursos");
    
    if (!res.ok) throw new Error("Erro ao buscar cursos");
    
    return res.json();
  },

  
  // Ainda não funcional
  // Necessita arrumar relação entre Turma e (Curso, Currículo)
  async listarCurriculosPorCurso(cursoId: number): Promise<Curriculo[]> {
    
    const res = await fetch(`/api/opcoes/curriculos?cursoId=${cursoId}`);
    
    if (!res.ok) throw new Error("Erro ao buscar currículos");
    
    return res.json();
  },
};