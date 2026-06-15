// app/api/documentos/[id]/route.ts
import { NextRequest, NextResponse } from "next/server";

const BACKEND_URL = process.env.BACKEND_API_URL || "http://localhost:8080";

export async function GET(
    request: NextRequest,
    { params }: { params: Promise<{ id: string }> }
) {
    try {
        const { id } = await params;

        const response = await fetch(`${BACKEND_URL}/api/documentos/regularidade/${id}`, {
            method: "GET",
        });

        if (!response.ok) {
            return NextResponse.json(
                { error: "Erro ao gerar documento" },
                { status: response.status }
            );
        }

        const blob = await response.blob();

        return new NextResponse(blob, {
            headers: {
                "Content-Type": "application/pdf",
                "Content-Disposition": response.headers.get("Content-Disposition") ?? "attachment",
            },
        });

    } catch (error) {
        const message = error instanceof Error ? error.message : "Erro desconhecido";
        console.error("[GET /api/documentos/regularidade/[id]]", message);
        return NextResponse.json({ error: "Erro interno no servidor" }, { status: 500 });
    }
}