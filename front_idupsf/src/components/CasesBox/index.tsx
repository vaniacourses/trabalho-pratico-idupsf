import Link from 'next/link'
import styles from './styles.module.css'
import "bootstrap-icons/font/bootstrap-icons.min.css";
import { CasesProps } from '@/types/PropsTypes';

interface Case {
    key: string;
    label: string;
    icon: string; // classe do bootstrap icons
}

const casesPorPerfil: Record<string, Case[]> = {
    DISCENTE: [
        { key: "gerar-declaracoes",           label: "Gerar Declarações",           icon: "bi bi-filetype-pdf" },
        { key: "historico",                   label: "Histórico",                   icon: "bi bi-file-ruled" },
        // { key: "plano-de-estudos",            label: "Plano de Estudos",            icon: "bi bi-journal-bookmark" },
        // { key: "integralizacao-de-curriculo", label: "Integralização de Currículo", icon: "bi bi-file-earmark-break" },
    ],
    DOCENTE: [
        { key: "consultar-turmas",  label: "Consultar Turmas",   icon: "bi bi-calendar3" },
        // { key: "historico-turmas",  label: "Histórico de Turmas", icon: "bi bi-clock-history" },
        { key: "curriculo",         label: "Currículo",           icon: "bi bi-person-vcard" },
    ],
    COORDENADOR: [
        // { key: "gerenciar-turmas",   label: "Gerenciar Turmas",   icon: "bi bi-easel" },
        { key: "gerenciar-docentes", label: "Gerenciar Docentes", icon: "bi bi-people" },
        // { key: "gerenciar-curso",    label: "Gerenciar Curso",    icon: "bi bi-mortarboard" },
    ],
};

const titulosPorPerfil: Record<string, string> = {
    DISCENTE:    "Discentes",
    DOCENTE:     "Docentes",
    COORDENADOR: "Coordenadores",
};

export default function CasesBox({ perfil, onSelect, caseSelecionado }: CasesProps) {
    
    const cases = casesPorPerfil[perfil] ?? [];
    const titulo = titulosPorPerfil[perfil] ?? perfil;

    return (
        <>
            <section className={styles.atorSection}>
                <h1 className={styles.title}>Usuários</h1>

                <div className={styles.casesList}>
                    <Link href="/update-user" className={styles.caseCard}>
                        <i className="bi bi-person" />
                        Atualizar Dados Cadastrais
                    </Link>
                </div>
            </section>

            <section className={styles.atorSection}>
                <h1 className={styles.title}>{titulo}</h1>

                <div className={styles.casesList}>
                    {cases.map((c) => (
                        <button
                            key={c.key}
                            type="button"
                            className={`${styles.caseCard} ${caseSelecionado === c.key ? styles.ativo : ""}`}
                            onClick={() => onSelect(c.key)}
                        >
                            <i className={c.icon} />
                            {c.label}
                        </button>
                    ))}

                    {perfil === "COORDENADOR" && 
                        <Link href="/gestao-academica" className={styles.caseCard}>
                            Gestão Acadêmica
                        </Link>
                    }
                </div>
            </section>
        </>
    );
}