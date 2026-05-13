-- Desativa checagem de chave estrangeira temporariamente para evitar erros na inserção
SET FOREIGN_KEY_CHECKS = 0;

-- 1. DEPARTAMENTOS
INSERT INTO departamento (id, cod, nome, endereco, campus) VALUES
    (1, 'DCC', 'Departamento de Ciência da Computação', 'Rua Passo da Pátria, 156', 'Praia Vermelha');

-- 2. CURSOS
INSERT INTO curso (id, cod, nome, duracao_min, duracao_max, cod_curriculo_atual, turno, departamento_id) VALUES
    (1, 'CC', 'Ciência da Computação', 8, 12, 'CC-2024', 'INTEGRAL', 1);

-- 3. CURRÍCULOS
INSERT INTO curriculo (id, cod, curso_id) VALUES
    (1, 'CC-2024', 1);

-- 4. DISCIPLINAS
INSERT INTO disciplina (id, cod, nome, carga_horaria) VALUES
                                                          (1, 'MAT001', 'Cálculo 1', 60),
                                                          (2, 'COMP001', 'Algoritmos 1', 60),
                                                          (3, 'COMP002', 'Estrutura de Dados', 60);

-- 5. PRÉ-REQUISITOS (Estrutura de Dados exige Algoritmos)
INSERT INTO disciplina_prerequisitos (disciplina_id, prerequisito_id) VALUES
    (3, 2);

-- 6. REGISTRO DE DISCIPLINAS NO CURRÍCULO
INSERT INTO registro_disciplina (id, tipo_categoria, periodo_recomendado, disciplina_id, curriculo_id) VALUES
                                                                                                           (1, 'OBRIGATORIA', 1, 1, 1),
                                                                                                           (2, 'OBRIGATORIA', 1, 2, 1),
                                                                                                           (3, 'OBRIGATORIA', 2, 3, 1);

-- 7. USUÁRIOS BASE (A Herança JOINED exige criar o Usuario primeiro)
-- Usuário 1: Será um Docente (ID 1)
INSERT INTO usuario (id, matricula, nome, email, email_inst, cpf, senha, data_nasc, status) VALUES
    (1, 'DOC01', 'Alan Turing', 'alan@email.com', 'alan@uff.br', '11122233344', 'senha123', '1912-06-23', 'ATIVO');

-- Usuário 2: Será uma Discente (ID 2)
INSERT INTO usuario (id, matricula, nome, email, email_inst, cpf, senha, data_nasc, status) VALUES
    (2, 'ALU01', 'Ada Lovelace', 'ada@email.com', 'ada@id.uff.br', '55566677788', 'senha123', '1815-12-10', 'ATIVO');

-- 8. DOCENTES (O ID tem que ser igual ao do Usuario correspondente)
INSERT INTO docente (id, titulacao, regime, lattes, data_admissao, departamento_id) VALUES
    (1, 'DOUTOR', 'DE', 'lattes.cnpq.br/123', '2015-02-01', 1);

-- (Opcional) Áreas de atuação do Docente
INSERT INTO docente_areas_atuacao (docente_id, areas_atuacao) VALUES
                                                                  (1, 'Inteligência Artificial'), (1, 'Criptografia');

-- 9. DISCENTES (O ID tem que ser igual ao do Usuario correspondente)
INSERT INTO discente (id, curso_id, periodo, periodo_ingresso, cod_curriculo, situacao_academica, forma_permanencia) VALUES
    (2, 1, '2024.1', '2022.1', 'CC-2024', 'ATIVO', 'DEFINITIVA');

-- 10. HORÁRIOS
INSERT INTO horario (id, horario_inicio, horario_fim) VALUES
    (1, '14:00', '16:00');

-- (Opcional) Dias da semana do horário
INSERT INTO horario_dias_da_semana (horario_id, dias_da_semana) VALUES
                                                                    (1, 'SEGUNDA'), (1, 'QUARTA');

-- 11. TURMAS
INSERT INTO turma (id, cod, nome, ano_semestre, status, ementa, max_alunos, disciplina_id, docente_id, horario_id) VALUES
    (1, 'T01', 'Algoritmos Turma A', '2024.1', 'ATIVA', 'Introdução à lógica de programação', 40, 2, 1, 1);

-- 12. HISTÓRICO (O Hibernate criou discente_id graças ao @JoinColumn)
INSERT INTO historico (id, coeficiente_rend, discente_id) VALUES
    (1, 9.5, 2);

-- 13. DISCIPLINAS CURSADAS (A Ada tirou 9.5 em Algoritmos)
INSERT INTO disciplina_cursada (id, nota, nota_vs, status_final, frequencia, periodo, carga_horaria, turma_id, historico_id) VALUES
    (1, 9.5, 0.0, 'APROVADO', 1, '2024.1', 60, 1, 1);

-- Reativa a checagem de chave estrangeira
SET FOREIGN_KEY_CHECKS = 1;