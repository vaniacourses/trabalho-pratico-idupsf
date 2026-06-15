import { NextRequest, NextResponse } from "next/server";

// URL do Back-End Java (Fazer .env em algum momento)
const BACKEND_URL = process.env.BACKEND_API_URL || "http://localhost:8080";

export async function GET( 
    _: NextRequest, 
    { params }: {params: Promise<{id: string}>}
) {
    
    try {
        
        const {id} = await params;

        const response = await fetch(`${BACKEND_URL}/api/discentes/historico/${id}`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            },
        });

        if (!response.ok) {
            return NextResponse.json(
                { error: "Erro ao buscar histórico do discente" },
                { status: response.status }
            );
        }

        const data = await response.json();
        return NextResponse.json(data);

    } catch (error) {
        const message = error instanceof Error ? error.message : "Erro desconhecido";
        console.error("Erro na API Route Handler [GET /api/discentes/historico/:idDiscente]:", message);

        return NextResponse.json(
            { error: "Erro interno no servidor" },
            { status: 500 }
        );
    }
}