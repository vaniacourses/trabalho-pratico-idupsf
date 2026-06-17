
import { NextRequest, NextResponse } from "next/server";

const BACKEND_URL = process.env.BACKEND_API_URL || "http://localhost:8080";

export async function PATCH(
    request: NextRequest,
    { params }: { params: Promise<{ id: string }> }
) {
    try {
        const { id } = await params;
        const docenteId = request.nextUrl.searchParams.get("docenteId");
        const body = await request.json();

        if (!docenteId) {
            return NextResponse.json(
                { error: "Parâmetro docenteId é obrigatório" },
                { status: 400 }
            );
        }

        const response = await fetch(
            `${BACKEND_URL}/api/turmas/${id}/notas?docenteId=${docenteId}`,
            {
                method: "PATCH",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(body),
            }
        );

        if (!response.ok) {
            return NextResponse.json(
                { error: "Erro ao atribuir notas" },
                { status: response.status }
            );
        }

        return new NextResponse(null, { status: 204 });

    } catch (error) {
        const message = error instanceof Error ? error.message : "Erro desconhecido";
        console.error("[PATCH /api/turmas/[id]/notas]", message);
        return NextResponse.json({ error: "Erro interno no servidor" }, { status: 500 });
    }
}