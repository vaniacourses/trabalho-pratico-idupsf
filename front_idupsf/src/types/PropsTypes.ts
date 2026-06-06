import { Perfil } from "./modelUPSF";


export interface DinamicContentProps {
    caseKey: string;
    perfil?: string;
}

export interface CasesProps {
    perfil: Perfil;
    onSelect: (caseKey: string) => void;
    caseSelecionado: string | null;
}