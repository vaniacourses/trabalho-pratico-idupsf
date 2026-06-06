// app/api/discentes/[id]/route.ts
import { NextRequest, NextResponse } from "next/server";

// URL do Back-End Java (Fazer .env em algum momento)
const BACKEND_URL = process.env.BACKEND_API_URL || "http://localhost:8080";

export async function GET( 
    _: NextRequest, 
    { params }: {params: Promise<{id: string}>}
) {
    
    try {
        
        const {id} = await params;

        const response = await fetch(`${BACKEND_URL}/api/discentes/${id}`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            },
        });

        if (!response.ok) {
            return NextResponse.json(
                { error: "Erro ao buscar discente" },
                { status: response.status }
            );
        }

        const data = await response.json();
        return NextResponse.json(data);

    } catch (error) {
        const message = error instanceof Error ? error.message : "Erro desconhecido";
        console.error("Erro na API Route Handler [GET /api/discentes/:id]:", message);

        return NextResponse.json(
            { error: "Erro interno no servidor" },
            { status: 500 }
        );
    }
}

export async function PATCH(
    request: NextRequest,
    { params }: { params: Promise<{ id: string }> }
) {

    try {
        const { id } = await params;
        const body = await request.json();

        const response = await fetch(`${BACKEND_URL}/api/discentes/${id}`, {
            method: "PATCH",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(body),
        });

        if (!response.ok) {
            return NextResponse.json(
                { error: "Erro ao atualizar discente" },
                { status: response.status }
            );
        }

        const data = await response.json();
        return NextResponse.json(data);

    } catch (error) {
        const message = error instanceof Error ? error.message : "Erro desconhecido";
        console.error("[PATCH /api/discentes/[id]]", message);
        return NextResponse.json({ error: "Erro interno no servidor" }, { status: 500 });
    }
}