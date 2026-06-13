

export function formatCPF(valor: string): string {
    return valor
        .replace(/\D/g, "")                         // Remove tudo que não é número
        .replace(/(\d{3})(\d)/, "$1.$2")            // Coloca o primeiro ponto após os 3 primeiros dígitos
        .replace(/(\d{3})(\d)/, "$1.$2")            // Coloca o segundo ponto após os próximos 3 dígitos
        .replace(/(\d{3})(\d{1,2})$/, "$1-$2")      // Coloca o traço
        .slice(0, 14);                              // Limita a 14 caracteres
}

export function formatCEP(valor:string): string {
    return valor
        .replace(/\D/g, "")                         // Remove tudo que não é número
        .replace(/(\d{5})(\d)/, "$1-$2")            // Coloca o traço após os 5 primeiros dígitos
        .slice(0, 9);                               // Limita a 9 caracteres
}