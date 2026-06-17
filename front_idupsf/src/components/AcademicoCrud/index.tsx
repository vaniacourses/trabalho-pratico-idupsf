"use client";

import { FormEvent, useEffect, useState } from "react";
import {
    academicoCrudService,
    CursoPayload,
    DisciplinaUpdatePayload,
} from "@/services/academicoCrudService";
import { Curso, Departamento, Disciplina, Turno } from "@/types/modelUPSF";
import styles from "./styles.module.css";

type StatusDisciplina = "ATIVA" | "INATIVA";

type DisciplinaCrud = Disciplina & {
    status?: StatusDisciplina;
};

type CursoForm = {
    cod: string;
    nome: string;
    duracaoMin: string;
    duracaoMax: string;
    turno: Turno;
};

type DisciplinaForm = {
    cod: string;
    nome: string;
    cargaHoraria: string;
    status: StatusDisciplina;
};

const cursoInicial: CursoForm = {
    cod: "",
    nome: "",
    duracaoMin: "",
    duracaoMax: "",
    turno: "DIURNO",
};

const disciplinaInicial: DisciplinaForm = {
    cod: "",
    nome: "",
    cargaHoraria: "",
    status: "ATIVA",
};

const turnos: Turno[] = ["DIURNO", "NOTURNO", "INTEGRAL"];
const statusDisciplinas: StatusDisciplina[] = ["ATIVA", "INATIVA"];

export default function AcademicoCrud() {
    
    const [departamentos, setDepartamentos] = useState<Departamento[]>([]);
    const [departamentoSelecionado, setDepartamentoSelecionado] = useState("");
    
    const [cursos, setCursos] = useState<Curso[]>([]);
    const [disciplinas, setDisciplinas] = useState<DisciplinaCrud[]>([]);

    const [cursoForm, setCursoForm] = useState<CursoForm>(cursoInicial);
    
    const [disciplinaForm, setDisciplinaForm] = useState<DisciplinaForm>(disciplinaInicial);
    
    const [cursoEditandoId, setCursoEditandoId] = useState<string | null>(null);
    const [disciplinaEditandoId, setDisciplinaEditandoId] = useState<string | null>(null);

    const [loading, setLoading] = useState(false);
    const [mensagem, setMensagem] = useState("");
    const [erro, setErro] = useState("");

    useEffect(() => {
        async function carregarDadosIniciais() {
            setLoading(true);
            setErro("");

            try {
                const [departamentosData, disciplinasData] = await Promise.all([
                    academicoCrudService.listarDepartamentos(),
                    academicoCrudService.listarDisciplinas(),
                ]);

                setDepartamentos(departamentosData);
                setDisciplinas(disciplinasData as DisciplinaCrud[]);

                const primeiroDepartamentoId = departamentosData[0]?.id;
                if (primeiroDepartamentoId) {
                    const departamentoId = String(primeiroDepartamentoId);
                    setDepartamentoSelecionado(departamentoId);
                    const cursosData = await academicoCrudService.listarCursosPorDepartamento(departamentoId);
                    setCursos(cursosData);
                }
            } catch (error) {
                console.error(error);
                setErro("Erro ao carregar dados acadêmicos.");
            } finally {
                setLoading(false);
            }
        }

        carregarDadosIniciais();
    }, []);

    async function carregarCursos(departamentoId: string) {
        const cursosData = await academicoCrudService.listarCursosPorDepartamento(departamentoId);
        setCursos(cursosData);
    }

    async function carregarDisciplinas() {
        const disciplinasData = await academicoCrudService.listarDisciplinas();
        setDisciplinas(disciplinasData as DisciplinaCrud[]);
    }

    async function handleDepartamentoChange(departamentoId: string) {
        setDepartamentoSelecionado(departamentoId);
        setCursoEditandoId(null);
        setCursoForm(cursoInicial);

        if (!departamentoId) {
            setCursos([]);
            return;
        }

        setLoading(true);
        setErro("");

        try {
            await carregarCursos(departamentoId);
        } catch (error) {
            console.error(error);
            setErro("Erro ao carregar cursos do departamento.");
        } finally {
            setLoading(false);
        }
    }

    function handleCursoChange(
        event: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
    ) {
        const { name, value } = event.target;
        setCursoForm((form) => ({ ...form, [name]: value }));
    }

    function handleDisciplinaChange(
        event: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
    ) {
        const { name, value } = event.target;
        setDisciplinaForm((form) => ({ ...form, [name]: value }));
    }

    async function handleCursoSubmit(event: FormEvent<HTMLFormElement>) {
        event.preventDefault();

        if (!departamentoSelecionado) {
            setErro("Selecione um departamento antes de salvar o curso.");
            return;
        }

        const dados: CursoPayload = {
            cod: cursoForm.cod.trim(),
            nome: cursoForm.nome.trim(),
            duracaoMin: Number(cursoForm.duracaoMin),
            duracaoMax: Number(cursoForm.duracaoMax),
            turno: cursoForm.turno,
            idDepartamento: Number(departamentoSelecionado),
        };

        setLoading(true);
        setErro("");
        setMensagem("");

        try {
            if (cursoEditandoId) {
                await academicoCrudService.atualizarCurso(
                    cursoEditandoId,
                    dados
                );
                setMensagem("Curso atualizado com sucesso.");
            } else {
                await academicoCrudService.criarCurso(dados);
                setMensagem("Curso criado com sucesso.");
            }

            setCursoForm(cursoInicial);
            setCursoEditandoId(null);
            await carregarCursos(departamentoSelecionado);
        } catch (error) {
            console.error(error);
            setErro("Erro ao salvar curso.");
        } finally {
            setLoading(false);
        }
    }

    function editarCurso(curso: Curso) {
        if (!curso.id) return;

        setCursoEditandoId(String(curso.id));
        setCursoForm({
            cod: curso.cod ?? "",
            nome: curso.nome ?? "",
            duracaoMin: String(curso.duracaoMin ?? ""),
            duracaoMax: String(curso.duracaoMax ?? ""),
            turno: curso.turno ?? "DIURNO",
        });
        setMensagem("");
        setErro("");
    }

    async function excluirCurso(cursoId?: string) {
        if (!cursoId || !departamentoSelecionado) return;

        setLoading(true);
        setErro("");
        setMensagem("");

        try {
            await academicoCrudService.excluirCurso(cursoId);
            setMensagem("Curso excluído com sucesso.");
            await carregarCursos(departamentoSelecionado);

            if (cursoEditandoId === cursoId) {
                cancelarEdicaoCurso();
            }
        } catch (error) {
            console.error(error);
            setErro("Erro ao excluir curso.");
        } finally {
            setLoading(false);
        }
    }

    function cancelarEdicaoCurso() {
        setCursoEditandoId(null);
        setCursoForm(cursoInicial);
    }

    async function handleDisciplinaSubmit(event: FormEvent<HTMLFormElement>) {
        event.preventDefault();

        setLoading(true);
        setErro("");
        setMensagem("");

        try {
            if (disciplinaEditandoId) {
                const dados: DisciplinaUpdatePayload = {
                    id: disciplinaEditandoId,
                    cod: disciplinaForm.cod.trim(),
                    nome: disciplinaForm.nome.trim(),
                    cargaHoraria: Number(disciplinaForm.cargaHoraria),
                    status: disciplinaForm.status,
                };

                await academicoCrudService.atualizarDisciplina(disciplinaEditandoId, dados);
                setMensagem("Disciplina atualizada com sucesso.");
            } else {
                await academicoCrudService.criarDisciplina({
                    cod: disciplinaForm.cod.trim(),
                    nome: disciplinaForm.nome.trim(),
                    cargaHoraria: Number(disciplinaForm.cargaHoraria),
                    preRequisitos: [],
                });
                setMensagem("Disciplina criada com sucesso.");
            }

            setDisciplinaForm(disciplinaInicial);
            setDisciplinaEditandoId(null);
            await carregarDisciplinas();
        } catch (error) {
            console.error(error);
            setErro("Erro ao salvar disciplina.");
        } finally {
            setLoading(false);
        }
    }

    function editarDisciplina(disciplina: DisciplinaCrud) {
        if (!disciplina.id) return;

        setDisciplinaEditandoId(String(disciplina.id));
        setDisciplinaForm({
            cod: disciplina.cod ?? "",
            nome: disciplina.nome ?? "",
            cargaHoraria: String(disciplina.cargaHoraria ?? ""),
            status: disciplina.status ?? "ATIVA",
        });
        setMensagem("");
        setErro("");
    }

    async function excluirDisciplina(disciplinaId?: string) {
        if (!disciplinaId) return;

        setLoading(true);
        setErro("");
        setMensagem("");

        try {
            await academicoCrudService.excluirDisciplina(disciplinaId);
            setMensagem("Disciplina excluída com sucesso.");
            await carregarDisciplinas();

            if (disciplinaEditandoId === disciplinaId) {
                cancelarEdicaoDisciplina();
            }
        } catch (error) {
            console.error(error);
            setErro("Erro ao excluir disciplina.");
        } finally {
            setLoading(false);
        }
    }

    function cancelarEdicaoDisciplina() {
        setDisciplinaEditandoId(null);
        setDisciplinaForm(disciplinaInicial);
    }

    return (
        <main className={styles.main}>
            <header className={styles.header}>
                <h1 className={styles.title}>Gestão Acadêmica</h1>
                <p className={styles.subtitle}>CRUD de cursos e disciplinas</p>
            </header>

            {(mensagem || erro) && (
                <section className={styles.feedback} aria-live="polite">
                    {mensagem && <p className={styles.success}>{mensagem}</p>}
                    {erro && <p className={styles.error}>{erro}</p>}
                </section>
            )}

            <section className={styles.section}>
                <div className={styles.sectionHeader}>
                    <h2 className={styles.sectionTitle}>Cursos</h2>
                    <label className={styles.fieldCompact}>
                        Departamento
                        <select
                            value={departamentoSelecionado}
                            onChange={(event) => handleDepartamentoChange(event.target.value)}
                            className={styles.input}
                            disabled={loading}
                        >
                            <option value="">Selecione</option>
                            {departamentos.map((departamento) => (
                                <option key={departamento.id} value={departamento.id}>
                                    {departamento.nome}
                                </option>
                            ))}
                        </select>
                    </label>
                </div>

                <form className={styles.form} onSubmit={handleCursoSubmit}>
                    <label className={styles.field}>
                        Código
                        <input
                            name="cod"
                            value={cursoForm.cod}
                            onChange={handleCursoChange}
                            className={styles.input}
                            required
                        />
                    </label>

                    <label className={styles.field}>
                        Nome
                        <input
                            name="nome"
                            value={cursoForm.nome}
                            onChange={handleCursoChange}
                            className={styles.input}
                            required
                        />
                    </label>

                    <label className={styles.field}>
                        Duração mínima
                        <input
                            name="duracaoMin"
                            type="number"
                            min="1"
                            value={cursoForm.duracaoMin}
                            onChange={handleCursoChange}
                            className={styles.input}
                            required
                        />
                    </label>

                    <label className={styles.field}>
                        Duração máxima
                        <input
                            name="duracaoMax"
                            type="number"
                            min="1"
                            value={cursoForm.duracaoMax}
                            onChange={handleCursoChange}
                            className={styles.input}
                            required
                        />
                    </label>

                    <label className={styles.field}>
                        Turno
                        <select
                            name="turno"
                            value={cursoForm.turno}
                            onChange={handleCursoChange}
                            className={styles.input}
                            required
                        >
                            {turnos.map((turno) => (
                                <option key={turno} value={turno}>
                                    {turno}
                                </option>
                            ))}
                        </select>
                    </label>

                    <div className={styles.actions}>
                        <button type="submit" className={styles.primaryButton} disabled={loading}>
                            {cursoEditandoId ? "Atualizar curso" : "Criar curso"}
                        </button>
                        {cursoEditandoId && (
                            <button
                                type="button"
                                className={styles.secondaryButton}
                                onClick={cancelarEdicaoCurso}
                                disabled={loading}
                            >
                                Cancelar
                            </button>
                        )}
                    </div>
                </form>

                <div className={styles.tableWrapper}>
                    <table className={styles.table}>
                        <thead>
                            <tr>
                                <th>Código</th>
                                <th>Nome</th>
                                <th>Duração</th>
                                <th>Turno</th>
                                <th>Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            {cursos.map((curso) => (
                                <tr key={curso.id}>
                                    <td>{curso.cod}</td>
                                    <td>{curso.nome}</td>
                                    <td>
                                        {curso.duracaoMin} - {curso.duracaoMax}
                                    </td>
                                    <td>{curso.turno}</td>
                                    <td className={styles.rowActions}>
                                        <button
                                            type="button"
                                            className={styles.smallButton}
                                            onClick={() => editarCurso(curso)}
                                            disabled={loading}
                                        >
                                            Editar
                                        </button>
                                        <button
                                            type="button"
                                            className={styles.dangerButton}
                                            onClick={() => excluirCurso(curso.id)}
                                            disabled={loading}
                                        >
                                            Excluir
                                        </button>
                                    </td>
                                </tr>
                            ))}
                            {cursos.length === 0 && (
                                <tr>
                                    <td colSpan={5} className={styles.empty}>
                                        Nenhum curso encontrado.
                                    </td>
                                </tr>
                            )}
                        </tbody>
                    </table>
                </div>
            </section>

            <section className={styles.section}>
                <div className={styles.sectionHeader}>
                    <h2 className={styles.sectionTitle}>Disciplinas</h2>
                </div>

                <form className={styles.form} onSubmit={handleDisciplinaSubmit}>
                    <label className={styles.field}>
                        Código
                        <input
                            name="cod"
                            value={disciplinaForm.cod}
                            onChange={handleDisciplinaChange}
                            className={styles.input}
                            required
                        />
                    </label>

                    <label className={styles.field}>
                        Nome
                        <input
                            name="nome"
                            value={disciplinaForm.nome}
                            onChange={handleDisciplinaChange}
                            className={styles.input}
                            required
                        />
                    </label>

                    <label className={styles.field}>
                        Carga horária
                        <input
                            name="cargaHoraria"
                            type="number"
                            min="1"
                            value={disciplinaForm.cargaHoraria}
                            onChange={handleDisciplinaChange}
                            className={styles.input}
                            required
                        />
                    </label>

                    {disciplinaEditandoId && (
                        <label className={styles.field}>
                            Status
                            <select
                                name="status"
                                value={disciplinaForm.status}
                                onChange={handleDisciplinaChange}
                                className={styles.input}
                                required
                            >
                                {statusDisciplinas.map((status) => (
                                    <option key={status} value={status}>
                                        {status}
                                    </option>
                                ))}
                            </select>
                        </label>
                    )}

                    <div className={styles.actions}>
                        <button type="submit" className={styles.primaryButton} disabled={loading}>
                            {disciplinaEditandoId ? "Atualizar disciplina" : "Criar disciplina"}
                        </button>
                        {disciplinaEditandoId && (
                            <button
                                type="button"
                                className={styles.secondaryButton}
                                onClick={cancelarEdicaoDisciplina}
                                disabled={loading}
                            >
                                Cancelar
                            </button>
                        )}
                    </div>
                </form>

                <div className={styles.tableWrapper}>
                    <table className={styles.table}>
                        <thead>
                            <tr>
                                <th>Código</th>
                                <th>Nome</th>
                                <th>Carga horária</th>
                                <th>Status</th>
                                <th>Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            {disciplinas.map((disciplina) => (
                                <tr key={disciplina.id}>
                                    <td>{disciplina.cod}</td>
                                    <td>{disciplina.nome}</td>
                                    <td>{disciplina.cargaHoraria}</td>
                                    <td>{disciplina.status ?? "ATIVA"}</td>
                                    <td className={styles.rowActions}>
                                        <button
                                            type="button"
                                            className={styles.smallButton}
                                            onClick={() => editarDisciplina(disciplina)}
                                            disabled={loading}
                                        >
                                            Editar
                                        </button>
                                        <button
                                            type="button"
                                            className={styles.dangerButton}
                                            onClick={() => excluirDisciplina(disciplina.id)}
                                            disabled={loading}
                                        >
                                            Excluir
                                        </button>
                                    </td>
                                </tr>
                            ))}
                            {disciplinas.length === 0 && (
                                <tr>
                                    <td colSpan={5} className={styles.empty}>
                                        Nenhuma disciplina encontrada.
                                    </td>
                                </tr>
                            )}
                        </tbody>
                    </table>
                </div>
            </section>
        </main>
    );
}
