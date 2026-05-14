// app/api/quadro/route.ts
import { NextResponse } from "next/server";

export async function GET() {
    try {

        // URL do Back-End Java (Fazer .env em algum momento)
        const BACKEND_URL = process.env.BACKEND_API_URL || "http://localhost:8080";

        const response = await fetch(`${BACKEND_URL}/api/quadro`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                // Token do NextAuth?
                // "Authorization": `Bearer ${token}`
            },
            
            // Next.js 15: controle de cache (revalidate: 0 para dados sempre frescos)
            next: { revalidate: 60 }, 
        });

        if (!response.ok) {
            return NextResponse.json(
                { error: "Erro ao buscar dados do quadro de horários" },
                { status: response.status }
            );
        }

        const data = await response.json();

        return NextResponse.json(data);
    
    } catch (error) {
        const message = error instanceof Error ? error.message : "Erro desconhecido";
        console.error("Erro na API Route Handler [GET /api/quadro/route.ts]:", message);
        
        return NextResponse.json(
            { error: "Erro interno no servidor" },
            { status: 500 }
        );
    }
}