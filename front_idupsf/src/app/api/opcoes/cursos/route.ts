// app/api/opcoes/cursos/route.ts
import { NextResponse } from "next/server";

export async function GET() {
    try {

        // URL do Back-End Java (Fazer .env em algum momento)
        const BACKEND_URL = process.env.BACKEND_API_URL ?? "http://localhost:8080";

        const response = await fetch(`${BACKEND_URL}/opcoes/cursos`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            },
            // Cursos mudam raramente — cache de 1 hora
            next: { revalidate: 3600 },
        });

        if (!response.ok) {
            return NextResponse.json(
                { error: "Erro ao buscar cursos" },
                { status: response.status }
            );
        }

        const data = await response.json();
        return NextResponse.json(data);

    } catch (error) {
        const message = error instanceof Error ? error.message : "Erro desconhecido";
        console.error("Erro na API Route Handler [GET /api/opcoes/cursos]:", message);

        return NextResponse.json(
            { error: "Erro interno no servidor" },
            { status: 500 }
        );
    }
}