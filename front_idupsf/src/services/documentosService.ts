
const BASE_URL = process.env.NEXT_PUBLIC_APP_URL ?? "http://localhost:3000";

export const documentosService = {
    async baixarHistorico(id: string): Promise<Blob> {
        
        const res = await fetch(`${BASE_URL}/api/documentos/historico/${id}`);
        
        if (!res.ok) throw new Error("Erro ao baixar documento");
        
        return res.blob();
    },
    
    async baixarRegularidade(id: string): Promise<Blob> {
        
        const res = await fetch(`${BASE_URL}/api/documentos/regularidade/${id}`);
        
        if (!res.ok) throw new Error("Erro ao baixar documento");
        
        return res.blob();
    },
};