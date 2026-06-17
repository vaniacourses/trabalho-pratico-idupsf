// proxy.ts
import { withAuth } from "next-auth/middleware";
import { NextResponse } from "next/server";

export default withAuth(
    
    function proxy(req) {
        
        const perfil = req.nextauth.token?.perfil;
        
        const path = req.nextUrl.pathname;

        // Rotas exclusivas do Coordenador
        if (path.startsWith("/coordenador") && perfil !== "COORDENADOR") {
            return NextResponse.redirect(new URL("/home", req.url));
        }

        // Rotas exclusivas do Docente
        if (path.startsWith("/docente") && perfil !== "DOCENTE" && perfil !== "COORDENADOR") {
            return NextResponse.redirect(new URL("/home", req.url));
        }

        // Rota de inscrição apenas para Discentes
        if (path.startsWith("/inscricao") && perfil !== "DISCENTE") {
            return NextResponse.redirect(new URL("/home", req.url));
        }

        return NextResponse.next();
    },
    {
        callbacks: {
            authorized: ({ token }) => !!token, // exige token válido
        },
    }
);

export const config = {
    matcher: [
        // Protege todas as rotas exceto login, api/auth e arquivos estáticos
        "/((?!login|api/auth|_next/static|_next/image|favicon.ico).*)",
    ],
};