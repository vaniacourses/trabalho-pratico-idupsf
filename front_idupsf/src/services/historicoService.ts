import { Historico } from "@/types/modelUPSF";


const BASE_URL = process.env.NEXT_PUBLIC_APP_URL ?? "http://localhost:3000";

export const historicoService = {
    async buscarHistoricoPorDiscente(id: string): Promise<Historico> {

        const res = await fetch(`${BASE_URL}/api/discentes/historico/${id}`);
		
		if (!res.ok) throw new Error("Erro ao buscar histórido do discente de id " + id);

		return res.json();
    },
}