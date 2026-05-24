public record CursoCreate(String cod,
                          String nome,
                          int duracaoMin,
                          int duracaoMax,
                          String codCurriculoAtual,
                          Curso.Turno turno) {
}