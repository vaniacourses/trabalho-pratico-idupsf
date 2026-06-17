import { NextRequest, NextResponse } from "next/server";

const BACKEND_URL = process.env.BACKEND_API_URL || "http://localhost:8080";

export async function GET(request: NextRequest, { params }: {params: Promise<{id: string}>}) {
    try {
        
        const {id} = await params;

        if (!id) {
            return NextResponse.json(
                { error: "Parâmetro discenteId é obrigatório" },
                { status: 400 }
            );
        }

        const response = await fetch(
            `${BACKEND_URL}/api/inscricoes/turmas-disponiveis/${id}`,
            {
                method: "GET",
                headers: { "Content-Type": "application/json" },
                next: { revalidate: 0 },
            }
        );

        if (!response.ok) {
            return NextResponse.json(
                { error: "Erro ao buscar turmas disponíveis" },
                { status: response.status }
            );
        }

        const data = await response.json();
        return NextResponse.json(data);

    } catch (error) {
        
        const message = error instanceof Error ? error.message : "Erro desconhecido";
        console.error("[GET /api/inscricoes/disponiveis]", message);
        return NextResponse.json({ error: "Erro interno no servidor" }, { status: 500 });
    }
}