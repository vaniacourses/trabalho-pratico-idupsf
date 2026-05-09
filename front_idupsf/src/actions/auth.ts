"use server";
import { redirect } from "next/navigation";


export async function loginAction(formData: FormData) {
    
    const cpf = formData.get("cpf");
    
    const senha = formData.get("senha");

    if (cpf !== "000.000.000-00" || senha !== "12345") {
        return { erro: "CPF ou senha inválidos." };
    }

    redirect("/home");
}