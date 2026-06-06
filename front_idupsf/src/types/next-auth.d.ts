import "next-auth";
import "next-auth/jwt";

declare module "next-auth" {
    interface User {
        id?: string;
        perfil?: string;
        token?: string;
    }

    interface Session {
        user: {
            id?: string;
            perfil?: string;
            accessToken?: string;
        };
    }
}

declare module "next-auth/jwt" {
    interface JWT {
        id?: string;
        perfil?: string;
        accessToken?: string;
    }
}