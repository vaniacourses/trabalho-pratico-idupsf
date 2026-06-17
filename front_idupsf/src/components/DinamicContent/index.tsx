import { DinamicContentProps } from "@/types/PropsTypes";
import NewsSection from "../NewsSection";
import HistoricoDiscente from "../HistoricoDiscente";
import GerarDeclaracoes from "../GerarDeclaracoes";
import DocenteTurmas from "../DocenteTurmas";
import CurriculoDocente from "../CurriculoDocente";

const conteudoDiscente: Record<string, React.ReactNode> = {
    "gerar-declaracoes": <GerarDeclaracoes />,
    "historico": <HistoricoDiscente />,
    "plano-de-estudos": null, // <PlanoDeEstudos />,
    "integralizacao-de-curriculo": null,
};

const conteudoDocente: Record<string, React.ReactNode> = {
    "consultar-turmas": <DocenteTurmas />,
    "historico-turmas": null, // <HistoricoTurmas />,
    "curriculo": <CurriculoDocente />,
};

const conteudoCoordenador: Record<string, React.ReactNode> = {
    "gerenciar-turmas": null, // <GerenciarTurmas />,
    "gerenciar-docentes": null, // <GerenciarDocentes />,
    "gerenciar-curso": null, // <GerenciarCurso />,
};


export default function DinamicContent({ caseKey, perfil }: DinamicContentProps) {
    const conteudo: Record<string, React.ReactNode> = {};
    
    if (perfil === "DISCENTE") {
        Object.assign(conteudo, conteudoDiscente);
    }
    
    if (perfil === "DOCENTE") {
        Object.assign(conteudo, conteudoDocente);
    }
    
    if (perfil === "COORDENADOR") {
        Object.assign(conteudo, conteudoCoordenador);
    }

    return conteudo[caseKey] ?? <NewsSection />;
}