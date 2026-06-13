// app/api/quadro/route.ts
import { NextRequest, NextResponse } from "next/server";

export async function GET(request: NextRequest) {
    try {
        const BACKEND_URL = process.env.BACKEND_API_URL || "http://localhost:8080";

        // Repassa os searchParams do Front direto pro Back-End
        const searchParams = request.nextUrl.searchParams;
        const queryString = searchParams.toString();

        const response = await fetch(
            `${BACKEND_URL}/api/turmas/quadro${queryString ? `?${queryString}` : ""}`,
            {
                method: "GET",
                headers: { "Content-Type": "application/json" },
                next: { revalidate: 0 }, // busca com filtros deve ser sempre fresca
            }
        );

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
        console.error("[GET /api/quadro]", message);
        return NextResponse.json(
            { error: "Erro interno no servidor" },
            { status: 500 }
        );
    }
}