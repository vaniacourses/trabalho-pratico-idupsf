/*
-- Legenda rápida:
----> 'CC' = 'Client Component'
----> 'SC' = 'Server Component'

*/

import { Curriculo, Curso, Departamento } from "./modelUPSF";

// Interface que representa todos os possíveis Filtros para Buscar Turmas (Quadro de Horários)
export interface FiltrosBuscaTurmas {
  nomeCodDisciplina?: string;
  anoSemestre?: string;
  turno?: string; // Ainda a ser implementado
  departamentoCod?: string;
  nomeDocente?: string;
  cursoId?: string;
  curriculoId?: string;
}

/*
 --> Interface usada para passar os Objetos de Opções<option> (Departamentos e Cursos) para os <select>
 
 O "opcoesService" é usado em 'SC' Page QuadroHorarios para realizar essas buscas.
 
 O 'CC' QuadroHelper está tipado com esse Props e os passa para 'CC' QuadroFiltrosTurma,
 
 Esta interface é intermediária pois ainda falta currículo para as Opções<option>,
*/
export interface TurmasOptionsProps {
    departamentos: Departamento[];
    cursos: Curso[]; 
}


/*
    --> Interface fechada com todas as Opções<option> e com tratamento para mudança de curso

    O 'SC' QuadroFiltrosTurma é tipado com esse Props
*/
export interface BuscaTurmasProps {
    departamentos: Departamento[];
    cursos: Curso[];
    curriculos: Curriculo[];
    onCursoChange: (cursoId: string) => void;
    onBuscar: (filtros: FiltrosBuscaTurmas) => void;
    isLoading?: boolean;
}