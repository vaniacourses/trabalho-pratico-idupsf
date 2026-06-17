"use client";

import { useState, useEffect } from "react";
import { useRouter } from "next/navigation";
import { formatCPF } from "@/utils/formatters";
import styles from "./styles.module.css";
import { DocenteCreate, Regime, Titulacao } from "@/types/modelUPSF";
import { useUsuarioStore } from "@/stores/usuarioStore";
import { docentesService } from "@/services/docentesService";

const TITULACAO_OPCOES: { value: Titulacao; label: string }[] = [
    { value: "MESTRE", label: "Mestre" },
    { value: "DOUTOR", label: "Doutor" },
];

const REGIME_OPCOES: { value: Regime; label: string }[] = [
    { value: "DE", label: "Dedicação Exclusiva (DE)" },
    { value: "TP", label: "Tempo Parcial (TP)" },
];

export default function RegisterDocenteForm() {
    const router = useRouter();

    // ─── TODOS os hooks primeiro, sem nenhum return antes ───
    const usuario = useUsuarioStore((state) => state.usuario);
    const [loading, setLoading] = useState(false);
    const [erro, setErro] = useState("");
    const [sucesso, setSucesso] = useState(false);
    const [form, setForm] = useState<DocenteCreate>({
        nome: "",
        email: "",
        cpf: "",
        senha: "",
        dataNasc: "",
        titulacao: "" as Titulacao,
        regime: "" as Regime,
        idDepartamento: "",
    });


    const idDepartamento = usuario?.perfil === "COORDENADOR" ? usuario.departamento?.id : undefined;
    const nomeDepartamento = usuario?.perfil === "COORDENADOR" ? usuario.departamento?.nome : undefined;

    // Sincroniza idDepartamento no form só quando ele mudar
    useEffect(() => {
        if (!idDepartamento) return;
        setForm((prev) => ({ ...prev, idDepartamento }));
    }, [idDepartamento]);

    // ─── SÓ DEPOIS dos hooks, o early return condicional ───
    if (usuario?.perfil !== "COORDENADOR") return null;

    function handleChange(e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) {
        const { name, value } = e.target;
        setForm((prev) => ({ ...prev, [name]: value }));
    }

    function handleCPF(e: React.ChangeEvent<HTMLInputElement>) {
        setForm((prev) => ({ ...prev, cpf: formatCPF(e.target.value) }));
    }

    function validar(): string | null {
        if (!form.nome.trim()) return "Informe o nome do docente.";
        if (!form.email.trim()) return "Informe o e-mail.";
        if (!form.cpf.trim()) return "Informe o CPF.";
        if (!form.senha.trim()) return "Informe uma senha provisória.";
        if (!form.dataNasc) return "Informe a data de nascimento.";
        if (!form.titulacao) return "Selecione a titulação.";
        if (!form.regime) return "Selecione o regime de trabalho.";
        if (!form.idDepartamento) return "Departamento não identificado.";
        return null;
    }

    async function handleSubmit(e: React.FormEvent) {
        e.preventDefault();
        setErro("");
        setSucesso(false);

        const mensagemValidacao = validar();
        if (mensagemValidacao) {
            setErro(mensagemValidacao);
            return;
        }

        setLoading(true);

        try {
            await docentesService.cadastrarDocente(form);
            setSucesso(true);
            router.push("/home");
        } catch {
            setErro("Não foi possível cadastrar o docente. Verifique os dados e tente novamente.");
        } finally {
            setLoading(false);
        }
    }

    return (
        <section className={styles.container}>
            <header className={styles.header}>
                <h2 className={styles.titulo}>Cadastrar Novo Docente</h2>
                {nomeDepartamento && (
                    <p className={styles.subtitulo}>Departamento: {nomeDepartamento}</p>
                )}
            </header>

            <form onSubmit={handleSubmit} className={styles.form}>

                <div className={styles.campo}>
                    <label htmlFor="nome" className={styles.label}>Nome Completo</label>
                    <input
                        id="nome"
                        name="nome"
                        type="text"
                        className={styles.input}
                        placeholder="Nome do docente"
                        value={form.nome}
                        onChange={handleChange}
                    />
                </div>

                <div className={styles.linhaDouble}>
                    <div className={styles.campo}>
                        <label htmlFor="cpf" className={styles.label}>CPF</label>
                        <input
                            id="cpf"
                            name="cpf"
                            type="text"
                            className={styles.input}
                            placeholder="000.000.000-00"
                            maxLength={14}
                            value={form.cpf}
                            onChange={handleCPF}
                        />
                    </div>

                    <div className={styles.campo}>
                        <label htmlFor="dataNasc" className={styles.label}>Data de Nascimento</label>
                        <input
                            id="dataNasc"
                            name="dataNasc"
                            type="date"
                            className={styles.input}
                            value={form.dataNasc}
                            onChange={handleChange}
                        />
                    </div>
                </div>

                <div className={styles.campo}>
                    <label htmlFor="email" className={styles.label}>E-mail</label>
                    <input
                        id="email"
                        name="email"
                        type="email"
                        className={styles.input}
                        placeholder="docente@email.com"
                        value={form.email}
                        onChange={handleChange}
                    />
                </div>

                <div className={styles.campo}>
                    <label htmlFor="senha" className={styles.label}>Senha Provisória</label>
                    <input
                        id="senha"
                        name="senha"
                        type="password"
                        className={styles.input}
                        placeholder="Senha de acesso inicial"
                        value={form.senha}
                        onChange={handleChange}
                    />
                </div>

                <div className={styles.linhaDouble}>
                    <div className={styles.campo}>
                        <label htmlFor="titulacao" className={styles.label}>Titulação</label>
                        <select
                            id="titulacao"
                            name="titulacao"
                            className={styles.select}
                            value={form.titulacao}
                            onChange={handleChange}
                        >
                            <option value="">Selecione</option>
                            {TITULACAO_OPCOES.map((op) => (
                                <option key={op.value} value={op.value}>{op.label}</option>
                            ))}
                        </select>
                    </div>

                    <div className={styles.campo}>
                        <label htmlFor="regime" className={styles.label}>Regime de Trabalho</label>
                        <select
                            id="regime"
                            name="regime"
                            className={styles.select}
                            value={form.regime}
                            onChange={handleChange}
                        >
                            <option value="">Selecione</option>
                            {REGIME_OPCOES.map((op) => (
                                <option key={op.value} value={op.value}>{op.label}</option>
                            ))}
                        </select>
                    </div>
                </div>

                {erro && <p className={styles.erro}>{erro}</p>}
                {sucesso && <p className={styles.sucesso}>Docente cadastrado com sucesso!</p>}

                <div className={styles.acoes}>
                    <button
                        type="button"
                        className={styles.btnSecundario}
                        onClick={() => router.back()}
                        disabled={loading}
                    >
                        Cancelar
                    </button>
                    <button
                        type="submit"
                        className={styles.btnPrimario}
                        disabled={loading}
                    >
                        {loading ? "Cadastrando..." : "Cadastrar Docente"}
                    </button>
                </div>

            </form>
        </section>
    );
}