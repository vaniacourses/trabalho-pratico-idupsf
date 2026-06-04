import { Curriculo, Curso, Departamento, Periodo } from "@/types/modelUPSF";

const BASE_URL = process.env.NEXT_PUBLIC_APP_URL ?? "http://localhost:3000";

export const opcoesService = {
	
	async listarDepartamentos(): Promise<Departamento[]> {
		
		const res = await fetch(`${BASE_URL}/api/opcoes/departamentos`);
		
		if (!res.ok) throw new Error("Erro ao buscar departamentos");

		return res.json();
	},

	async listarCursos(): Promise<Curso[]> {
		
		const res = await fetch(`${BASE_URL}/api/opcoes/cursos`);
		
		if (!res.ok) throw new Error("Erro ao buscar cursos");
		
		return res.json();
	},

	
	// Ainda não funcional
	// Necessita arrumar relação entre Turma e (Curso, Currículo)
	async listarCurriculosPorCurso(cursoId: number): Promise<Curriculo[]> {
		
		const res = await fetch(`${BASE_URL}/api/opcoes/curriculos?cursoId=${cursoId}`);
		
		if (!res.ok) throw new Error("Erro ao buscar currículos");
		
		return res.json();
	},

	async listarPeriodos(): Promise<Periodo[]> {
		
		const res = await fetch(`${BASE_URL}/api/opcoes/periodos`);
		
		if (!res.ok) throw new Error("Erro ao buscar periodos");
		
		return res.json();
	},
};