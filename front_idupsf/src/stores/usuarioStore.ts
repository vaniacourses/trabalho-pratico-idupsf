// stores/usuarioStore.ts
import { coordenadoresService } from "@/services/coordenadoresService";
import { discentesService } from "@/services/discentesService";
import { docentesService } from "@/services/docentesService";
import { UsuarioLogado } from "@/types/modelUPSF";
import { create } from "zustand";
import { persist } from "zustand/middleware";

interface UsuarioStore {
    usuario: UsuarioLogado | null;
    loading: boolean;
    carregarUsuario: (id: string, perfil: "DISCENTE" | "DOCENTE" | "COORDENADOR") => Promise<void>;
    atualizarUsuario: (dados: Partial<UsuarioLogado>) => void;
    limparUsuario: () => void;
}

export const useUsuarioStore = create<UsuarioStore>()(
    persist(
        (set) => ({
            usuario: null,
            loading: false,

            carregarUsuario: async (id, perfil) => {
                try {
                    if (!id) throw new Error("ID do usuário é necessário");
                    if (!perfil) throw new Error("Perfil do usuário é necessário");

                    let data;
                    
                    switch (perfil) {
                        case "DISCENTE": 
                            data = await discentesService.buscarDiscentePorId(id);
                            break;
                        
                        case "DOCENTE": 
                            data = await docentesService.buscarDocentePorId(id);
                            break;
                        
                        case "COORDENADOR": 
                            data = await coordenadoresService.buscarCoordenadorPorId(id);
                            break;
                        
                        default:
                            throw new Error("Perfil inválido"); 
                    }
                    
                    if (!data) throw new Error("Usuário não encontrado");

                    set({ usuario: { ...data, perfil}, loading: false });
                
                } catch {
                    set({ usuario: null, loading: false });
                }
            },
            
            atualizarUsuario: (dados) => set((state) => ({
                usuario: state.usuario ? { ...state.usuario, ...dados } : null,
            })),

            limparUsuario: () => set({ usuario: null, loading: true }),
        }),
        {
            name: "usuario-storage", // Chave no Local Storage
        }
    )
)

// export const useUsuarioStore = create<UsuarioStore>((set) => ({
//     usuario: null,
//     loading: true,

//     carregarUsuario: async (id, perfil) => {
//         try {
//             if (!id) throw new Error("ID do usuário é necessário");
//             if (!perfil) throw new Error("Perfil do usuário é necessário");

//             let data;

//             if (perfil === "DISCENTE") {
//                 data = await discentesService.buscarDiscentePorId(id);
//             }
//             if (perfil === "DOCENTE") {
//                 data = await docentesService.buscarDocentePorId(id);
//             }
//             if (perfil === "COORDENADOR") {
//                 data = await coordenadoresService.buscarCoordenadorPorId(id);
//             }
            
//             if (!data) throw new Error("Usuário não encontrado");

//             set({ usuario: { ...data, perfil}, loading: false });
        
//         } catch {
//             set({ usuario: null, loading: false });
//         }
//     },

//     limparUsuario: () => set({ usuario: null, loading: true }),
// }));