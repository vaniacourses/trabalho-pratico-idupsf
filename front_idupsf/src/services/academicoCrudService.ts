import { Curso, Departamento, Disciplina, Turno } from "@/types/modelUPSF";

const BACKEND_URL =
    process.env.NEXT_PUBLIC_BACKEND_API_URL ??
    process.env.BACKEND_API_URL ??
    "http://localhost:8080";

export type CursoPayload = {
    cod: string;
    nome: string;
    duracaoMin: number;
    duracaoMax: number;
    turno: Turno;
    idDepartamento: number;
};

export type DisciplinaPayload = {
    cod: string;
    nome: string;
    cargaHoraria: number;
    preRequisitos?: number[];
};

export type DisciplinaUpdatePayload = {
    id: string | number;
    cod: string;
    nome: string;
    cargaHoraria: number;
    status: "ATIVA" | "INATIVA";
};

async function fetchJson<T>(url: string, init?: RequestInit): Promise<T> {
    const res = await fetch(url, {
        ...init,
        headers: {
            "Content-Type": "application/json",
            ...init?.headers,
        },
    });

    if (!res.ok) {
        throw new Error(`Erro na requisição: ${res.status}`);
    }

    return res.json();
}

async function fetchVoid(url: string, init?: RequestInit): Promise<void> {
    const res = await fetch(url, {
        ...init,
        headers: {
            "Content-Type": "application/json",
            ...init?.headers,
        },
    });

    if (!res.ok) {
        throw new Error(`Erro na requisição: ${res.status}`);
    }
}

export const academicoCrudService = {
    async listarDepartamentos(): Promise<Departamento[]> {
        return fetchJson<Departamento[]>(`${BACKEND_URL}/api/departamentos`);
    },

    async listarCursosPorDepartamento(departamentoId: string | number): Promise<Curso[]> {
        return fetchJson<Curso[]>(`${BACKEND_URL}/api/departamentos/${departamentoId}/cursos`);
    },

    async criarCurso(departamentoId: string | number, dados: CursoPayload): Promise<Curso> {
        return fetchJson<Curso>(`${BACKEND_URL}/api/departamentos/${departamentoId}/cursos`, {
            method: "POST",
            body: JSON.stringify(dados),
        });
    },

    async atualizarCurso(
        departamentoId: string | number,
        cursoId: string | number,
        dados: CursoPayload
    ): Promise<Curso> {
        return fetchJson<Curso>(`${BACKEND_URL}/api/departamentos/${departamentoId}/cursos/${cursoId}`, {
            method: "PUT",
            body: JSON.stringify(dados),
        });
    },

    async excluirCurso(departamentoId: string | number, cursoId: string | number): Promise<void> {
        return fetchVoid(`${BACKEND_URL}/api/departamentos/${departamentoId}/cursos/${cursoId}`, {
            method: "DELETE",
        });
    },

    async listarDisciplinas(): Promise<Disciplina[]> {
        return fetchJson<Disciplina[]>(`${BACKEND_URL}/api/disciplinas`);
    },

    async criarDisciplina(dados: DisciplinaPayload): Promise<Disciplina> {
        return fetchJson<Disciplina>(`${BACKEND_URL}/api/disciplinas`, {
            method: "POST",
            body: JSON.stringify(dados),
        });
    },

    async atualizarDisciplina(
        disciplinaId: string | number,
        dados: DisciplinaUpdatePayload
    ): Promise<Disciplina> {
        return fetchJson<Disciplina>(`${BACKEND_URL}/api/disciplinas/${disciplinaId}`, {
            method: "PUT",
            body: JSON.stringify(dados),
        });
    },

    async excluirDisciplina(disciplinaId: string | number): Promise<void> {
        return fetchVoid(`${BACKEND_URL}/api/disciplinas/${disciplinaId}`, {
            method: "DELETE",
        });
    },
};
