-- ==============================================================================
-- 1. DEPARTAMENTOS (5 registros)
-- ==============================================================================
INSERT INTO departamento (id, cod, nome, endereco, campus) VALUES
                                                               (1, 'DCC', 'Departamento de Ciência da Computação', 'Rua Passo da Pátria, 156', 'Praia Vermelha'),
                                                               (2, 'GMA', 'Departamento de Matemática Aplicada', 'Rua Mário Santos Braga', 'Valonguinho'),
                                                               (3, 'EGF', 'Departamento de Física', 'Av. Litorânea, s/n', 'Gragoatá'),
                                                               (4, 'TCE', 'Departamento de Engenharia de Telecomunicações', 'Rua Passo da Pátria, 156', 'Praia Vermelha'),
                                                               (5, 'GLE', 'Departamento de Letras', 'Av. Visconde do Rio Branco', 'Gragoatá');

-- ==============================================================================
-- 2. CURSOS (5 registros)
-- ==============================================================================
INSERT INTO curso (id, cod, nome, duracao_min, duracao_max, turno, cod_curriculo_atual, departamento_id) VALUES
                                                                                                             (1, 'CC', 'Ciência da Computação', 8, 14, 'INTEGRAL', 'CC-2024', 1),
                                                                                                             (2, 'SI', 'Sistemas de Informação', 8, 14, 'NOTURNO', 'SI-2024', 1),
                                                                                                             (3, 'MAT', 'Matemática', 8, 14, 'INTEGRAL', 'MAT-2024', 2),
                                                                                                             (4, 'FIS', 'Física', 8, 14, 'INTEGRAL', 'FIS-2024', 3),
                                                                                                             (5, 'ENG', 'Engenharia de Telecomunicações', 10, 16, 'INTEGRAL', 'ENG-2024', 4);

-- ==============================================================================
-- 3. CURRÍCULOS (5 registros)
-- ==============================================================================
INSERT INTO curriculo (id, cod, curso_id) VALUES
                                              (1, 'CC-2024', 1),
                                              (2, 'SI-2024', 2),
                                              (3, 'MAT-2024', 3),
                                              (4, 'FIS-2024', 4),
                                              (5, 'ENG-2024', 5);

-- ==============================================================================
-- 4. DISCIPLINAS (10 registros)
-- ==============================================================================
INSERT INTO disciplina (id, cod, nome, carga_horaria, status) VALUES
                                                                  (1, 'MAT001', 'Cálculo 1', 60, 'ATIVA'),
                                                                  (2, 'COMP001', 'Algoritmos 1', 60, 'ATIVA'),
                                                                  (3, 'COMP002', 'Estrutura de Dados', 60, 'ATIVA'),
                                                                  (4, 'FIS001', 'Física 1', 60, 'ATIVA'),
                                                                  (5, 'MAT002', 'Álgebra Linear', 60, 'ATIVA'),
                                                                  (6, 'COMP003', 'Bancos de Dados', 60, 'ATIVA'),
                                                                  (7, 'COMP004', 'Redes de Computadores', 60, 'ATIVA'),
                                                                  (8, 'COMP005', 'Sistemas Operacionais', 60, 'ATIVA'),
                                                                  (9, 'COMP006', 'Engenharia de Software', 60, 'ATIVA'),
                                                                  (10, 'COMP007', 'Inteligência Artificial', 60, 'ATIVA');

-- ==============================================================================
-- 5. PRÉ-REQUISITOS (Amarrando a lógica das disciplinas)
-- ==============================================================================
INSERT INTO disciplina_prerequisitos (disciplina_id, prerequisito_id) VALUES
                                                                          (3, 2),  -- Estrutura de Dados exige Algoritmos
                                                                          (6, 3),  -- Banco de Dados exige Estrutura de Dados
                                                                          (8, 3),  -- Sistemas Operacionais exige Estrutura de Dados
                                                                          (10, 2), -- Inteligência Artificial exige Algoritmos
                                                                          (10, 5); -- Inteligência Artificial exige Álgebra Linear

-- ==============================================================================
-- 6. REGISTRO DE DISCIPLINAS NO CURRÍCULO DE COMPUTAÇÃO (CC-2024)
-- ==============================================================================
INSERT INTO registro_disciplina (id, periodo_recomendado, tipo_categoria, curriculo_id, disciplina_id) VALUES
                                                                                                           (1, 1, 'OBRIGATORIA', 1, 1), -- Cálculo 1 no 1º período
                                                                                                           (2, 1, 'OBRIGATORIA', 1, 2), -- Algoritmos no 1º período
                                                                                                           (3, 2, 'OBRIGATORIA', 1, 3), -- ED no 2º período
                                                                                                           (4, 2, 'OBRIGATORIA', 1, 5), -- Álgebra no 2º período
                                                                                                           (5, 3, 'OBRIGATORIA', 1, 6), -- BD no 3º período
                                                                                                           (6, 4, 'OBRIGATORIA', 1, 7), -- Redes no 4º período
                                                                                                           (7, 5, 'OPTATIVA', 1, 10);   -- IA como optativa no 5º período

-- ==============================================================================
-- 7. USUÁRIOS BASE (10 registros: 1-5 Docentes, 6-10 Discentes)
-- ==============================================================================
INSERT INTO usuario (id, matricula, nome, email, email_inst, senha, status, data_nasc, cpf) VALUES
                                                                                                (1, 'DOC001', 'Alan Turing', 'alan@uff.br', 'alan.turing@id.uff.br', 'senha123', 'ATIVO', '1912-06-23', '11111111111'),
                                                                                                (2, 'DOC002', 'Ada Lovelace', 'ada@uff.br', 'ada.lovelace@id.uff.br', 'senha123', 'ATIVO', '1815-12-10', '22222222222'),
                                                                                                (3, 'DOC003', 'Grace Hopper', 'grace@uff.br', 'grace.hopper@id.uff.br', 'senha123', 'ATIVO', '1906-12-09', '33333333333'),
                                                                                                (4, 'DOC004', 'Linus Torvalds', 'linus@uff.br', 'linus.torvalds@id.uff.br', 'senha123', 'ATIVO', '1969-12-28', '44444444444'),
                                                                                                (5, 'DOC005', 'Tim Berners-Lee', 'tim@uff.br', 'tim.lee@id.uff.br', 'senha123', 'ATIVO', '1955-06-08', '55555555555'),
                                                                                                (6, 'DIS001', 'Guilherme Fontoura', 'guilherme@gmail.com', 'guilherme@id.uff.br', 'senha123', 'ATIVO', '2000-01-01', '66666666666'),
                                                                                                (7, 'DIS002', 'Maria Silva', 'maria@gmail.com', 'maria@id.uff.br', 'senha123', 'ATIVO', '2001-02-02', '77777777777'),
                                                                                                (8, 'DIS003', 'João Souza', 'joao@gmail.com', 'joao@id.uff.br', 'senha123', 'ATIVO', '1999-03-03', '88888888888'),
                                                                                                (9, 'DIS004', 'Ana Costa', 'ana@gmail.com', 'ana@id.uff.br', 'senha123', 'ATIVO', '2002-04-04', '99999999999'),
                                                                                                (10, 'DIS005', 'Pedro Santos', 'pedro@gmail.com', 'pedro@id.uff.br', 'senha123', 'ATIVO', '2000-05-05', '00000000000');

-- ==============================================================================
-- 8. DOCENTES (Aproveitando os IDs 1 a 5)
-- ==============================================================================
INSERT INTO docente (id, departamento_id, titulacao, regime, lattes, data_admissao) VALUES
                                                                                        (1, 1, 'DOUTOR', 'DE', 'http://lattes.cnpq.br/111', '2010-01-15'),
                                                                                        (2, 1, 'DOUTOR', 'DE', 'http://lattes.cnpq.br/222', '2012-03-10'),
                                                                                        (3, 2, 'MESTRE', 'TP', 'http://lattes.cnpq.br/333', '2015-08-01'),
                                                                                        (4, 4, 'DOUTOR', 'DE', 'http://lattes.cnpq.br/444', '2018-02-20'),
                                                                                        (5, 1, 'MESTRE', 'TP', 'http://lattes.cnpq.br/555', '2020-07-05');

INSERT INTO docente_areas_atuacao (docente_id, areas_atuacao) VALUES
                                                                  (1, 'Inteligência Artificial'), (1, 'Teoria da Computação'),
                                                                  (2, 'Algoritmos'), (2, 'Engenharia de Software'),
                                                                  (3, 'Cálculo Numérico'),
                                                                  (4, 'Sistemas Operacionais'), (4, 'Redes'),
                                                                  (5, 'Desenvolvimento Web');

-- ==============================================================================
-- 9. DISCENTES E SEUS HISTÓRICOS (Aproveitando os IDs 6 a 10)
-- ==============================================================================
INSERT INTO discente (id, curso_id, cod_curriculo, periodo, periodo_ingresso, forma_permanencia, situacao_academica) VALUES
                                                                                                                         (6, 1, 'CC-2024', '5', '2022.1', 'DEFINITIVA', 'ATIVO'),
                                                                                                                         (7, 1, 'CC-2024', '3', '2023.1', 'DEFINITIVA', 'ATIVO'),
                                                                                                                         (8, 2, 'SI-2024', '7', '2021.1', 'DEFINITIVA', 'ATIVO'),
                                                                                                                         (9, 3, 'MAT-2024', '1', '2024.1', 'DEFINITIVA', 'ATIVO'),
                                                                                                                         (10, 4, 'FIS-2024', '8', '2020.1', 'DEFINITIVA', 'FORMADO');

INSERT INTO historico (id, discente_id, coeficiente_rend) VALUES
                                                              (1, 6, 8.5), (2, 7, 9.1), (3, 8, 7.2), (4, 9, 0.0), (5, 10, 8.8);

-- ==============================================================================
-- 10. HORÁRIOS (5 slots padrão da universidade)
-- ==============================================================================
INSERT INTO horario (id, horario_inicio, horario_fim) VALUES
                                                          (1, '08:00', '10:00'),
                                                          (2, '10:00', '12:00'),
                                                          (3, '14:00', '16:00'),
                                                          (4, '18:00', '20:00'),
                                                          (5, '20:00', '22:00');

INSERT INTO horario_dias_da_semana (horario_id, dias_da_semana) VALUES
                                                                    (1, 'SEGUNDA'), (1, 'QUARTA'),
                                                                    (2, 'TERCA'), (2, 'QUINTA'),
                                                                    (3, 'SEGUNDA'), (3, 'QUARTA'),
                                                                    (4, 'TERCA'), (4, 'QUINTA'),
                                                                    (5, 'SEXTA');

-- ==============================================================================
-- 11. TURMAS (Sem a coluna "nome")
-- ==============================================================================
INSERT INTO turma (id, cod, ano_semestre, status, ementa, max_alunos, disciplina_id, docente_id, horario_id) VALUES
                                                                                                                 (1, 'T01', '2024.1', 'ATIVA', 'Lógica em C', 40, 2, 2, 1),
                                                                                                                 (2, 'T02', '2024.1', 'ATIVA', 'Limites e Derivadas', 50, 1, 3, 2),
                                                                                                                 (3, 'T03', '2024.1', 'ATIVA', 'Listas e Árvores', 30, 3, 1, 3),
                                                                                                                 (4, 'T04', '2024.1', 'ATIVA', 'Modelo OSI e TCP/IP', 40, 7, 4, 4),
                                                                                                                 (5, 'T05', '2024.1', 'ATIVA', 'Machine Learning', 25, 10, 1, 5),
                                                                                                                 (6, 'T06', '2024.1', 'ATIVA', 'SQL e Modelagem', 40, 6, 5, 2),
                                                                                                                 (7, 'T07', '2024.1', 'ATIVA', 'Processos e Threads', 35, 8, 4, 1),
                                                                                                                 (8, 'T08', '2024.1', 'ATIVA', 'Mecânica Clássica', 50, 4, 3, 3),
                                                                                                                 (9, 'T09', '2023.2', 'FECHADA', 'Lógica em Python', 40, 2, 2, 5),
                                                                                                                 (10, 'T10', '2023.2', 'FECHADA', 'Scrum e UML', 40, 9, 5, 4);

-- ==============================================================================
-- 12. INSCRIÇÕES ATUAIS (Alunos cursando turmas neste semestre 2024.1)
-- ==============================================================================
INSERT INTO inscricao (id, aluno_id, turma_id, nota, notavs, frequencia, status) VALUES
                                                                                     (1, 6, 3, 0.0, 0.0, 1, 'INSCRITO'), -- Guilherme em ED (T03)
                                                                                     (2, 6, 5, 0.0, 0.0, 1, 'INSCRITO'), -- Guilherme em IA (T05)
                                                                                     (3, 7, 1, 0.0, 0.0, 1, 'INSCRITO'), -- Maria em Algoritmos (T01)
                                                                                     (4, 7, 2, 0.0, 0.0, 1, 'INSCRITO'), -- Maria em Cálculo 1 (T02)
                                                                                     (5, 8, 6, 0.0, 0.0, 1, 'INSCRITO'); -- João em Banco de Dados (T06)

-- ==============================================================================
-- 13. DISCIPLINAS CURSADAS (Histórico passado dos alunos)
-- ==============================================================================
INSERT INTO disciplina_cursada (id, historico_id, turma_id, carga_horaria, nota, notavs, frequencia, periodo, status_final) VALUES
                                                                                                                                (1, 1, 9, 60, 9.5, 0.0, 1, '2023.2', 'APROVADO'), -- Guilherme passou em Algoritmos
                                                                                                                                (2, 1, 10, 60, 8.0, 0.0, 1, '2023.2', 'APROVADO'), -- Guilherme passou em Eng. Software
                                                                                                                                (3, 3, 9, 60, 4.0, 6.5, 1, '2023.2', 'APROVADO'), -- João foi pra VS em Algoritmos e passou
                                                                                                                                (4, 5, 10, 60, 10.0, 0.0, 1, '2023.2', 'APROVADO'), -- Pedro passou com 10
                                                                                                                                (5, 2, 9, 60, 3.0, 0.0, 0, '2023.2', 'REPROVADO'); -- Maria reprovou por falta em Algoritmos