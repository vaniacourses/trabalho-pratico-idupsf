import Link from "next/link"
import styles from "./styles.module.css"
import { Docente } from "@/types/modelUPSF";
import DinamicTable, { Column } from "../DinamicTable";
import { cursoService } from "@/services/cursoService";
import { useUsuarioStore } from "@/stores/usuarioStore";
import { useEffect, useState } from "react";


const colunas: Column<Docente>[] = [
    { header: "Matrícula",   accessor: "matricula", 
      render: (v) => String(v.matricula)},
    
    { header: "Nome",     accessor: "nome",
      render: (v) => String(v.nome).toUpperCase() },
    
    { header: "E-mail Institucional",    accessor: "emailInst",
      render: (v) => String(v.emailInst) },
    
    { header: "Departamento",     accessor: "departamento",
      render: (v) => v.departamento?.nome },
    
    { header: "Data de Admissão",       accessor: "dataIngresso",
      render: (v) => v.dataIngresso },
    
];

export default function GerenciarDocentes() {
    
    const usuario = useUsuarioStore((state) => state.usuario)

    const [docentes, setDocentes] = useState<Docente[]>([]);

    useEffect(() => {
        if (!usuario?.id) return;
             
        if (usuario?.perfil === "COORDENADOR") {
            cursoService.listarDocentesDoDepartamentoDoCurso(usuario.curso?.id!).then(setDocentes)
        }
            
    }, [usuario?.id]);

    return (
        
        <section className={styles.caseBlockSection}>
    
            <header className={styles.headerCase}>
                
                <h1 className={styles.title}>Docentes</h1>
            
            </header>

            <DinamicTable 
                colunas={colunas}
                dados={docentes ?? []}
                keyExtractor={(dc) => dc.id}
                mensagemVazia="Nenhum docente encontrado."
            />
            
            <div className={styles.acoes}>
                <Link href="/coordenador/register-docente" className={styles.btnRegister}>
                    Cadastrar Docente
                </Link>
            </div>

        </section>
    )
}