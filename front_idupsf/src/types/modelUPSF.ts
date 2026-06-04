
// ============================================================
// TIPOS BASE / ENUMS
// ============================================================

export type TipoCategoria = "Obrigatória" | "Optativa" | "Eletiva";

export type SituacaoAcademica = "ATIVO" | "TRANCADO" | "FORMADO" | "JUBILADO";

// export type FormaPermanencia = "Regular" | "Transferência" | "Reopção" | "Ação Afirmativa";
export type FormaPermanencia = "DEFINITIVA" | "TEMPORARIA" // A verifivar

export type Titulacao = "MESTRE" | "DOUTOR";

export type StatusUsuario = "ATIVO" | "INATIVO";

export type StatusTurma = "ATIVA" | "INATIVA" | "FECHADA";

export type StatusInscricao = "PENDENTE" |"MATRICULADO" | "CANCELADO" | "CONCLUIDO"; // Colocar enum no Back

export type StatusDisciplinaCursada = "APROVADO" | "REPROVADO" | "TRANCADO" | "AGUARDO";

export type Regime = "DE" | "TP";

export type Turno = "DIURNO" | "NOTURNO" | "INTEGRAL";

// ============================================================
// PERFIL
// ============================================================

export type Perfil = Usuario & {
    curso?: string;
    departamento?: string;
}


// ============================================================
// PERÍODO
// ============================================================

export type Periodo = {
    id?: number;
    semestre?: string; // "2026.1", "2024.2"
    dataInicio?: string;
    dataFim?: string;
    dataInicioInscricao?: string;
    dataFimInscricao?: string;
}

// ============================================================
// HORÁRIO
// ============================================================

export type Horario = {
    id?: number;
    diasDaSemana?: string[];
    horarioInicio?: string; // formato "HH:mm"
    horarioFim?: string;    // formato "HH:mm"
};

// ============================================================
// DISCIPLINA
// ============================================================

export type Disciplina = {
    id?: number;
    nome?: string;
    cod?: string;
    cargaHoraria?: number;
    preRequisitos?: Disciplina[]; // auto-referência — pode ser array vazio
};

// ============================================================
// REGISTRO DE DISCIPLINA
// ============================================================

export type RegistroDisciplina = {
    id?: number;
    tipoCategoria?: TipoCategoria;
    periodoRecomendado?: number;
    disciplina?: Disciplina;
};

// ============================================================
// CURRÍCULO
// ============================================================

export type Curriculo = {
    id?: number;
    cod?: string;
    registroDisciplina?: RegistroDisciplina[];
};

// ============================================================
// DEPARTAMENTO
// ============================================================

export type Departamento = {
    id?: number;
    nome?: string;
    cod?: number;
    endereco?: string;
    campus?: string;
    cursos?: Curso[];
};

// ============================================================
// CURSO
// ============================================================

export type Curso = {
    id?: number;
    cod?: string;
    nome?: string;
    duracaoMin?: number;
    duracaoMax?: number;
    turno?: Turno;
    codCurriculoAtual?: string;
    curriculos?: Curriculo[];
};

// ============================================================
// USUÁRIO (abstrato — base para Discente, Docente e Coordenador)
// ============================================================

export type Usuario = {
    id?: number;
    nome?: string;
    matricula?: string;
    status?: StatusUsuario;
    email?: string;
    emailInst?: string;
    CPF?: string;
    senha?: string;
    dataNasc?: string; // formato "YYYY-MM-DD"
};

// ============================================================
// HISTÓRICO
// ============================================================

export type Historico = {
    id?: number;
    coeficienteRend?: number;
    listaDisciplinas?: DisciplinaCursada[];
};

// ============================================================
// DISCENTE
// ============================================================

export type Discente = Usuario & {
    curso?: Curso;
    periodo?: string;
    situacaoAcademica?: SituacaoAcademica;
    formaPermanencia?: FormaPermanencia;
    periodoIngresso?: string;
    historico?: Historico;
    codCurriculo?: string;
};

// ============================================================
// DOCENTE
// ============================================================

export type Docente = Usuario & {
    titulacao?: Titulacao;
    departamento?: Departamento;
    areasAtuacao?: string[];
    lattes?: string;
    dataAdmissao?: string; // formato "YYYY-MM-DD"
    regime?: Regime;
};

// ============================================================
// COORDENADOR
// ============================================================

// Coordenador é um Docente com atribuições de gestão de curso
export type Coordenador = Docente & {
    curso?: Curso;
    dataInicioMandato?: string; // formato "YYYY-MM-DD"
    dataFimMandato?: string;    // formato "YYYY-MM-DD"
};

// ============================================================
// TURMA
// ============================================================

export type Turma = {
    id?: number;
    nome?: string;
    cod?: string;
    anoSemestre?: string; // formato "YYYY.S" ex: "2026.1"
    horario?: Horario;
    status?: string;
    disciplina?: Disciplina;
    ementa?: string;
    maxAlunos?: number;
    docente?: Docente;
};

// ============================================================
// DISCIPLINA CURSADA
// ============================================================

export type DisciplinaCursada = {
    id?: number;
    turma?: Turma;
    nota?: number;
    notaVS?: number;
    statusFinal?: StatusDisciplinaCursada;
    frequencia?: boolean;
    periodo?: string;
    cargaHoraria?: number;
};

// ============================================================
// INSCRIÇÃO
// ============================================================

export type Inscricao = {
    id?: number;
    turma?: Turma;
    aluno?: Discente;
    status?: StatusInscricao;
    nota?: number;
    notaVS?: number;
    frequencia?: boolean;
};

// ============================================================
// OBSERVAÇÕES
// ============================================================

// 1. Disciplina com pré-requisitos auto-referenciados
//    Ao serializar para JSON pode causar loop infinito.
//    Dúvida: considerar preRequisitos?: string[] com os códigos das disciplinas?

// 2. Usuario como tipo abstrato
//    TypeScript não tem abstrato para tipos — usamos intersection (&)
//    para replicar herança em Discente, Docente e Coordenador

// 3. Senhas
//    O campo senha em Usuario nunca deve vir da API para o frontend.
//    Quando integrar com backend real, use:
//    export type UsuarioPublico = Omit<Usuario, "senha">;

//  4. Tipo Date para Datas
//     Quando os dados vêm de uma API (Json-Server ou qualquer backend), as datas chegam como string no JSON.
//     O TypeScript não converte automaticamente para Date. Se você tipar como Date mas receber uma string, o TypeScript não vai reclamar em runtime — e funções de Date vão quebrar silenciosamente.