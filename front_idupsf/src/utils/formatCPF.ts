

export function formatCPF(valor: string): string {
    return valor
        .replace(/\D/g, "")                         // Remove tudo que não é número
        .replace(/(\d{3})(\d)/, "$1.$2")            // Coloca o primeiro ponto
        .replace(/(\d{3})(\d)/, "$1.$2")            // Coloca o segundo ponto
        .replace(/(\d{3})(\d{1,2})$/, "$1-$2")      // Coloca o traço
        .slice(0, 14);                              // Limita a 14 caracteres
}