// lib/auth.ts
import { NextAuthOptions } from "next-auth";
import CredentialsProvider from "next-auth/providers/credentials";

const BACKEND_URL = process.env.BACKEND_API_URL ?? "http://localhost:8080";

export const authOptions: NextAuthOptions = {
    providers: [
        CredentialsProvider({
            name: "credentials",
            credentials: {
                cpf: { label: "CPF", type: "text" },
                senha: { label: "Senha", type: "password" },
            },
            async authorize(credentials) {
                if (!credentials?.cpf || !credentials?.senha) return null;

                // Mock temporário — remover quando Back-End estiver pronto
                if (credentials.cpf === "000.000.000-00" && credentials.senha === "admin@123") {
                    return {
                        id: "0",
                        perfil: "ADMIN",
                        token: "mock-jwt-token",
                    };
                }

                try {
                    const res = await fetch(`${BACKEND_URL}/api/auth/login`, {
                        method: "POST",
                        headers: { "Content-Type": "application/json" },
                        body: JSON.stringify({
                            cpf: credentials.cpf,
                            senha: credentials.senha,
                        }),
                    });

                    if (!res.ok) return null;

                    const data = await res.json();
                    // data = { id, token, perfil }

                    return {
                        id: String(data.id),
                        perfil: data.perfil,
                        token: data.token,
                    };

                } catch {
                    return null;
                }
            },
        }),
    ],
    callbacks: {
        async jwt({ token, user }) {
            if (user) {
                token.id = user.id;
                token.perfil = user.perfil;
                token.accessToken = user.token;
            }
            return token;
        },
        async session({ session, token }) {
            session.user.id = token.id;
            session.user.perfil = token.perfil;
            session.user.accessToken = token.accessToken;
            return session;
        },
    },
    pages: {
        signIn: "/login",
    },
    session: {
        strategy: "jwt",
    },
};