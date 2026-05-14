"use client";

import { useState } from "react";
import styles from "./styles.module.css";
import { BuscaTurmasProps, FiltrosBuscaTurmas } from "@/types/buscaTypes";

// ─── Dados mock —> Substituir por Services conectados ao Back-End  

const SEMESTRES_MOCK = [
  "2026/1º",
  "2025/2º",
  "2025/1º",
  "2024/2º",
  "2024/1º",
];

// const DEPARTAMENTOS_MOCK: Departamento[] = [
//   { id: 1, nome: "TCC - Departamento de Ciência da Computação" },
//   { id: 2, nome: "TEM - Departamento de Engenharia Mecânica" },
//   { id: 3, nome: "TEE - Departamento de Engenharia Elétrica" },
//   { id: 4, nome: "TGT - Departamento de Geotecnia" },
// ];

// const CURSOS_MOCK: Curso[] = [
//   { id: 1, nome: "Ciência da Computação", departamentoId: 1 },
//   { id: 2, nome: "Sistemas de Informação", departamentoId: 1 },
//   { id: 3, nome: "Engenharia Mecânica", departamentoId: 2 },
//   { id: 4, nome: "Engenharia Elétrica", departamentoId: 3 },
// ];

// const CURRICULOS_MOCK: Curriculo[] = [
//   { id: 1, cod: "CC-2020", cursoId: 1 },
//   { id: 2, cod: "CC-2016", cursoId: 1 },
//   { id: 3, cod: "SI-2019", cursoId: 2 },
//   { id: 4, cod: "EM-2021", cursoId: 3 },
//   { id: 5, cod: "EE-2020", cursoId: 4 },
// ];

// ─── Componente ───
/*
    Responsabilidades/Intenções do Componente Cliente QuadroFiltros:
    
    - Conter todos os filtros de busca com as devidas Opções<option>.
    - Filtros com valores dinâmicos
*/
export default function QuadroFiltroTurmas({ departamentos, cursos, curriculos, onCursoChange, onBuscar, isLoading = false }: BuscaTurmasProps) {
  
  const [nomeCodDisciplina, setNomeCodDisciplina] = useState("");
  const [anoSemestre, setAnoSemestre] = useState("");
  const [turno, setTurno] = useState("");
  const [departamentoId, setDepartamentoId] = useState("");
  const [nomeDocente, setNomeDocente] = useState("");
  const [cursoId, setCursoId] = useState("");
  const [curriculoId, setCurriculoId] = useState("");

  
  function handleCursoChange(valor: string) {
        setCursoId(valor);
        setCurriculoId(""); // limpa currículo ao trocar curso
        onCursoChange(valor);
  }

  function handleBuscar() {
    const filtros: FiltrosBuscaTurmas = {
      ...(nomeCodDisciplina && { nomeCodDisciplina }),
      ...(anoSemestre && { anoSemestre }),
      ...(turno && { turno }),
      ...(departamentoId && { departamentoId }),
      ...(nomeDocente && { nomeDocente }),
      ...(cursoId && { cursoId }),
      ...(curriculoId && { curriculoId }),
    };
    onBuscar(filtros);
  }

  return (
    <section className={styles.container}>
      <h2 className={styles.titulo}>Buscar Turmas</h2>

      <div className={styles.grid}>

        {/* Nome ou código da disciplina */}
        <div className={`${styles.campo} ${styles.colSpan2}`}>
          <label className={styles.label}>
            Por nome ou código da disciplina
            <span className={styles.tooltip} title="Busca parcial por nome ou código">?</span>
          </label>
          <input
            type="text"
            className={styles.input}
            placeholder="Nome ou Código da Disciplina"
            value={nomeCodDisciplina}
            onChange={(e) => setNomeCodDisciplina(e.target.value)}
          />
        </div>

        {/* Curso */}
        <div className={styles.campo}>
          <label className={styles.label}>
            Com vagas para o Curso
            <span className={styles.tooltip} title="Filtra turmas com vagas para o curso selecionado">?</span>
          </label>
          <select
            className={styles.select}
            value={cursoId}
            onChange={(e) => handleCursoChange(e.target.value)}
          >
            <option value="">Selecione um Curso</option>
            {cursos.map((curso) => (
              <option key={curso.id} value={curso.id}>
                {curso.nome}
              </option>
            ))}
          </select>
        </div>

        {/* Ano/Semestre */}
        <div className={styles.campo}>
          <label className={styles.label}>
            Por Semestre / Ano
            <span className={styles.tooltip} title="Filtra pelo período letivo">?</span>
          </label>
          <select
            className={styles.select}
            value={anoSemestre}
            onChange={(e) => setAnoSemestre(e.target.value)}
          >
            <option value="">Selecione um Semestre</option>
            {SEMESTRES_MOCK.map((s) => (
              <option key={s} value={s}>{s}</option>
            ))}
          </select>
        </div>
        
        {/* Turno */}
        <div className={styles.campo}>
          <label className={styles.label}>
            Por Turno
            <span className={styles.tooltip} title="Filtra pelo turno da turma">?</span>
          </label>
          <select
            className={styles.select}
            value={turno}
            onChange={(e) => setTurno(e.target.value)}
          >
            <option value="">Selecione um Turno</option>
            <option value="DIURNO">Diurno</option>
            <option value="NOTURNO">Noturno</option>
            <option value="INTEGRAL">Integral</option>
          </select>
        </div>
        
        {/* Currículo */}
        <div className={styles.campo}>
          <label className={styles.label}>
            Por Currículo
            <span className={styles.tooltip} title="Selecione um curso primeiro">?</span>
          </label>
          <select
            className={styles.select}
            value={curriculoId}
            onChange={(e) => setCurriculoId(e.target.value)}
            disabled={!cursoId}
          >
            <option value="">
              {cursoId ? "Selecione um Currículo" : "Selecione um curso acima primeiro"}
            </option>
            {curriculos.map((curriculo) => (
              <option key={curriculo.id} value={curriculo.id}>
                {curriculo.cod}
              </option>
            ))}
          </select>
        </div>

        {/* Departamento */}
        <div className={styles.campo}>
          <label className={styles.label}>
            Por Departamento ou Coordenação
            <span className={styles.tooltip} title="Filtra pelo departamento responsável">?</span>
          </label>
          <select
            className={styles.select}
            value={departamentoId}
            onChange={(e) => setDepartamentoId(e.target.value)}
          >
            <option value="">Selecione um Departamento</option>
            {departamentos.map((dep) => (
              <option key={dep.id} value={dep.id}>
                {dep.nome}
              </option>
            ))}
          </select>
        </div>
        
        {/* Docente */}
        <div className={styles.campo}>
          <label className={styles.label}>
            Por nome do Docente
            <span className={styles.tooltip} title="Busca parcial pelo nome do docente">?</span>
          </label>
          <input
            type="text"
            className={styles.input}
            placeholder="Nome do Docente"
            value={nomeDocente}
            onChange={(e) => setNomeDocente(e.target.value)}
          />
        </div>

        <div className={styles.campo} /> {/* espaço vazio para alinhar o grid */}

      </div>

      <button
        className={styles.botao}
        onClick={handleBuscar}
        disabled={isLoading}
      >
        {isLoading ? "Buscando..." : "Buscar Turmas"}
      </button>
    </section>
  );
}
