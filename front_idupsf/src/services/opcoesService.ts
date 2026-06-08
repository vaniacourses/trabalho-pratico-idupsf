import { Curriculo, Curso, Departamento, Periodo } from "@/types/modelUPSF";

const BASE_URL = process.env.NEXT_PUBLIC_APP_URL ?? "http://localhost:3000";

const BACKEND_URL = process.env.BACKEND_API_URL ?? "http://localhost:8080";

// Verificar em outro momento:

/*
	Em opcoesService, ocorre um erro de Sintaxe de transformação em JSON,
	caso o opcoesService use a API do Next, mas o erro não ocorre se o 
	opcoesService realizar a requisição direto para o BACK,

	Aparentemente, é porque o Quadro de Horários é Server Component, então a
	rota da API do Next fica ocupada para renderização.
*/

export const opcoesService = {
	
	async listarDepartamentos(): Promise<Departamento[]> {
		
		// const res = await fetch(`${BASE_URL}/api/opcoes/departamentos`);
		const res = await fetch(`${BACKEND_URL}/opcoes/departamentos`);
		
		if (!res.ok) throw new Error("Erro ao buscar departamentos");

		return res.json();
	},

	async listarCursos(): Promise<Curso[]> {
		
		// const res = await fetch(`${BASE_URL}/api/opcoes/cursos`);
		const res = await fetch(`${BACKEND_URL}/opcoes/cursos`);

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
		
		// const res = await fetch(`${BASE_URL}/api/opcoes/periodos`);
		const res = await fetch(`${BACKEND_URL}/opcoes/periodos`);

		if (!res.ok) throw new Error("Erro ao buscar periodos");
		
		return res.json();
	},
};