
import styles from "./styles.module.css";

// ─── Tipos ────────────────────────────────────────────────────────────────────

export interface Column<T> {
    header: string;                                             // título da coluna
    accessor: keyof T;                                          // chave do objeto
    render?: (valor: T[keyof T], row: T) => React.ReactNode;    // renderização customizada
    className?: string;                                         // classe CSS extra para a célula
}

interface TabelaDinamicaProps<T> {
    colunas: Column<T>[];
    dados: T[];
    keyExtractor: (row: T) => string | number | undefined; // chave única por linha
    mensagemVazia?: string;
    className?: string;
}

// ─── Componente ───────────────────────────────────────────────────────────────

export default function DinamicTable<T>({
    colunas,
    dados,
    keyExtractor,
    mensagemVazia = "Nenhum dado encontrado.",
    className,
}: TabelaDinamicaProps<T>) {
    
    return (
        <div className={`${styles.wrapper} ${className ?? ""}`}>
            {dados.length > 0 ? (
                <table className={styles.tabela}>
                    <thead>
                        <tr>
                            {colunas.map((col) => (
                                <th key={String(col.accessor)} className={styles.th}>
                                    {col.header}
                                </th>
                            ))}
                        </tr>
                    </thead>
                    
                    <tbody>
                        {dados.map((row) => (
                            <tr key={keyExtractor(row)} className={styles.tr}>
                                {colunas.map((col) => (
                                    <td
                                        key={String(col.accessor)}
                                        className={`${styles.td} ${col.className ?? ""}`}
                                    >
                                        {col.render
                                            ? col.render(row[col.accessor], row)
                                            : String(row[col.accessor] ?? "—")}
                                    </td>
                                ))}
                            </tr>
                        ))}
                    </tbody>
                </table>
            ) : (
                <p className={styles.vazio}>{mensagemVazia}</p>
            )}
        </div>
    );
}
