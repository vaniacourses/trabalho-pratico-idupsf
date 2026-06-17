// "use client";

// import { useState, useEffect } from "react";
// import { Usuario, Genero, Docente } from "@/types/modelUPSF";
// import { useUsuarioStore } from "@/stores/usuarioStore";
// import styles from "./styles.module.css";
// import { discentesService } from "@/services/discentesService";
// import { docentesService } from "@/services/docentesService";
// import { coordenadoresService } from "@/services/coordenadoresService";
// import { formatCEP } from "@/utils/formatters";


// // --- Campos Editáveis do Usuário

// // export type CamposEditaveis = Pick<Docente, "nome" | "email" | "cpf" | "dataNasc" | "senha" | "titulacao" | "regime" | "idDepartamento">;

// // --- Opções de Gênero 

// const GENERO_OPCOES: { value: Genero; label: string }[] = [
//     { value: "Homem_Cisgenero",    label: "Homem Cisgênero" },
//     { value: "Mulher_Cisgenero",   label: "Mulher Cisgênero" },
//     { value: "Homem_Transgenero",  label: "Homem Transgênero" },
//     { value: "Mulher_Transgenero", label: "Mulher Transgênero" },
//     { value: "Nao_Binario",        label: "Não Binário" },
//     { value: "Outro",              label: "Outro" },
// ];

// // --- Componente 

// export default function RegisterDocenteForm() {
    
//     const usuario = useUsuarioStore((state) => state.usuario);
//     const atualizarUsuario = useUsuarioStore((state) => state.atualizarUsuario);

//     const [form, setForm] = useState<CamposEditaveis>({
//         nomeSocial: "",
//         email: "",
//         cep: "",
//         logradouro: "",
//         genero: undefined,
//     });

//     const [loading, setLoading] = useState(false);
//     const [sucesso, setSucesso] = useState(false);
//     const [erro, setErro] = useState("");

//     // UseEffect para preencher o formulário com os dados atuais do usuário
//     useEffect(() => {
//         if (!usuario) return;
//         setForm({
//             nomeSocial: usuario.nomeSocial ?? "",
//             email:      usuario.email ?? "",
//             cep:        usuario.cep ?? "",
//             logradouro: usuario.logradouro ?? "",
//             genero:     usuario.genero ?? undefined,
//         });
//     }, [usuario]);

//     // Função "handleChange" para atualizar estado do formulário conforme o Usuário digita
//     function handleChange(e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) {
//         const { name, value } = e.target;
//         setForm((prev) => ({ ...prev, [name]: value }));
//     }

//     // Função "handleSubmit" para enviar os dados atualizados
//     async function handleSubmit(e: React.FormEvent) {
//         e.preventDefault();
//         setErro("");
//         setSucesso(false);

//         if (!usuario?.id) {
//             setErro("Usuário não identificado.");
//             return;
//         }

//         setLoading(true);

//         try {

//             let usuarioAtualizado;
//             switch (usuario.perfil) {
//                 case "DISCENTE": 
//                     usuarioAtualizado = await discentesService.atualizarDiscente(usuario.id, form);
//                     break;
                                        
//                 case "DOCENTE": 
//                     usuarioAtualizado = await docentesService.atualizarDocente(usuario.id, form);
//                     break;
                
//                 case "COORDENADOR": 
//                     usuarioAtualizado = await coordenadoresService.atualizarCoordenador(usuario.id, form);
//                     break;
                
//                 default:
//                     throw new Error("Perfil inválido");
//             }

//             atualizarUsuario(usuarioAtualizado); // ← atualiza o store com o retorno do Back-End

//             setSucesso(true);

//         } catch (error) {
//             console.log(error)
//             setErro("Erro ao salvar as alterações. Tente novamente.");
        
//         } finally {
//             setLoading(false);
//         }
//     }

//     if (!usuario) return null;

//     return (
//         <section className={styles.container}>
            
//             <header className={styles.header}>
//                 <h2 className={styles.titulo}>Editar Usuário</h2>
//                 <p className={styles.subtitulo}>Atualize suas informações pessoais</p>
//             </header>

//             <form onSubmit={handleSubmit} className={styles.form}>

//                 <div className={styles.divisor}>
//                     <h3>Dados do {usuario.perfil}</h3>
//                     <hr />
//                 </div>

//                 <div className={styles.linhaDouble}>
                    
//                     {/* Nome */}
//                     <div className={styles.campo}>
                        
//                         <label htmlFor="nome" className={styles.label}>
//                             Nome
//                         </label>
                        
//                         <input
//                             id="nome"
//                             name="nome"
//                             type="text"
//                             disabled
//                             className={styles.input}
//                             placeholder="Como prefere ser chamado(a)"
//                             value={usuario.nome}
//                             onChange={handleChange}
//                         />
//                     </div>

//                     {/* Matrícula */}
//                     <div className={styles.campo}>
                        
//                         <label htmlFor="matricula" className={styles.label}>
//                             Matrícula
//                         </label>
                        
//                         <input
//                             id="matricula"
//                             name="matricula"
//                             type="text"
//                             disabled
//                             className={styles.input}
//                             placeholder="Como prefere ser chamado(a)"
//                             value={usuario.matricula}
//                             onChange={handleChange}
//                         />
//                     </div>
//                 </div>
                

//                 <div className={styles.divisor}>
//                     <h3>Dados Pessoais</h3>
//                     <hr />
//                 </div>

//                 {/* Nome Social */}
//                 <div className={styles.campo}>
                    
//                     <label htmlFor="nomeSocial" className={styles.label}>
//                         Nome Social
//                         <span className={styles.opcional}>opcional</span>
//                     </label>
                    
//                     <input
//                         id="nomeSocial"
//                         name="nomeSocial"
//                         type="text"
//                         className={styles.input}
//                         placeholder="Como prefere ser chamado(a)"
//                         value={form.nomeSocial ?? ""}
//                         onChange={handleChange}
//                     />
                
//                 </div>

//                 {/* E-mail */}
//                 <div className={styles.campo}>
//                     <label htmlFor="email" className={styles.label}>
//                         E-mail pessoal
//                     </label>
//                     <input
//                         id="email"
//                         name="email"
//                         type="email"
//                         className={styles.input}
//                         placeholder="seu@email.com"
//                         value={form.email ?? ""}
//                         onChange={handleChange}
//                     />
//                 </div>

//                 {/* Gênero */}
//                 <div className={styles.campo}>
//                     <label htmlFor="genero" className={styles.label}>
//                         Gênero
//                         <span className={styles.opcional}>opcional</span>
//                     </label>
//                     <select
//                         id="genero"
//                         name="genero"
//                         className={styles.select}
//                         value={form.genero ?? ""}
//                         onChange={handleChange}
//                     >
//                         <option value="">Prefiro não informar</option>
//                         {GENERO_OPCOES.map((op) => (
//                             <option key={op.value} value={op.value}>
//                                 {op.label}
//                             </option>
//                         ))}
//                     </select>
//                 </div>

//                 {/* CEP e Logradouro — linha dupla */}
//                 <div className={styles.linhaDouble}>
//                     <div className={styles.campo}>
//                         <label htmlFor="cep" className={styles.label}>CEP</label>
//                         <input
//                             id="cep"
//                             name="cep"
//                             type="text"
//                             className={styles.input}
//                             placeholder="00000-000"
//                             maxLength={9}
//                             value={formatCEP(form.cep ?? "")}
//                             onChange={handleChange}
//                         />
//                     </div>

//                     <div className={`${styles.campo} ${styles.flex2}`}>
//                         <label htmlFor="logradouro" className={styles.label}>Logradouro</label>
//                         <input
//                             id="logradouro"
//                             name="logradouro"
//                             type="text"
//                             className={styles.input}
//                             placeholder="Rua, Avenida, número..."
//                             value={form.logradouro ?? ""}
//                             onChange={handleChange}
//                         />
//                     </div>
//                 </div>

//                 {/* Feedback */}
//                 {erro && <p className={styles.erro}>{erro}</p>}
//                 {sucesso && <p className={styles.sucesso}>Perfil atualizado com sucesso!</p>}

//                 {/* Botões */}
//                 <div className={styles.acoes}>
//                     <button
//                         type="button"
//                         className={styles.btnSecundario}
//                         // onClick={() => redirect("/home")}
//                         disabled={loading}
//                     >
//                         Cancelar
//                     </button>
//                     <button
//                         type="submit"
//                         className={styles.btnPrimario}
//                         disabled={loading}
//                     >
//                         {loading ? "Salvando..." : "Salvar Alterações"}
//                     </button>
//                 </div>

//             </form>
//         </section>
//     );
// }
