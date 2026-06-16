-- ==============================================================================
-- 1. DEPARTAMENTOS (5 registros)
-- ==============================================================================
INSERT IGNORE INTO departamento (id, cod, nome, endereco, campus) VALUES
(1, 'DCC', 'Dept. de Ciência da Computação', 'Rua Passo da Pátria, 156', 'Praia Vermelha'),
(2, 'GMA', 'Dept. de Matemática Aplicada', 'Rua Mário Santos Braga', 'Valonguinho'),
(3, 'EGF', 'Dept. de Física', 'Av. Litorânea, s/n', 'Gragoatá'),
(4, 'TCE', 'Dept. de Engenharia de Telecomunicações', 'Rua Passo da Pátria, 156', 'Praia Vermelha'),
(5, 'GLE', 'Dept. de Letras', 'Av. Visconde do Rio Branco', 'Gragoatá');

-- ==============================================================================
-- 2. CURSOS (5 registros)
-- ==============================================================================
INSERT IGNORE INTO curso (id, cod, nome, duracao_min, duracao_max, turno, cod_curriculo_atual, departamento_id) VALUES
(1, 'CC', 'Ciência da Computação', 8, 14, 'INTEGRAL', 'CC-2024', 1),
(2, 'SI', 'Sistemas de Informação', 8, 14, 'NOTURNO', 'SI-2024', 1),
(3, 'MAT', 'Matemática', 8, 14, 'INTEGRAL', 'MAT-2024', 2),
(4, 'FIS', 'Física', 8, 14, 'INTEGRAL', 'FIS-2024', 3),
(5, 'ENG', 'Engenharia de Telecomunicações', 10, 16, 'INTEGRAL', 'ENG-2024', 4);

-- ==============================================================================
-- 3. CURRÍCULOS (5 registros)
-- ==============================================================================
INSERT IGNORE INTO curriculo (id, cod, curso_id) VALUES
(1, 'CC-2024', 1),
(2, 'SI-2024', 2),
(3, 'MAT-2024', 3),
(4, 'FIS-2024', 4),
(5, 'ENG-2024', 5);

-- ==============================================================================
-- 4. DISCIPLINAS (10 registros)
-- ==============================================================================
INSERT IGNORE INTO disciplina (id, cod, nome, carga_horaria, status) VALUES
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
INSERT IGNORE INTO disciplina_prerequisitos (disciplina_id, prerequisito_id) VALUES
(3, 2),  -- Estrutura de Dados exige Algoritmos
(6, 3),  -- Banco de Dados exige Estrutura de Dados
(8, 3),  -- Sistemas Operacionais exige Estrutura de Dados
(10, 2), -- Inteligência Artificial exige Algoritmos
(10, 5); -- Inteligência Artificial exige Álgebra Linear

-- ==============================================================================
-- 6. REGISTRO DE DISCIPLINAS NO CURRÍCULO DE COMPUTAÇÃO (CC-2024)
-- ==============================================================================
INSERT IGNORE INTO registro_disciplina (id, periodo_recomendado, tipo_categoria, curriculo_id, disciplina_id) VALUES
(1, 1, 'OBRIGATORIA', 1, 1), -- Cálculo 1 no 1º período
(2, 1, 'OBRIGATORIA', 1, 2), -- Algoritmos no 1º período
(3, 2, 'OBRIGATORIA', 1, 3), -- ED no 2º período
(4, 2, 'OBRIGATORIA', 1, 5), -- Álgebra no 2º período
(5, 3, 'OBRIGATORIA', 1, 6), -- BD no 3º período
(6, 4, 'OBRIGATORIA', 1, 7), -- Redes no 4º período
(7, 5, 'OPTATIVA', 1, 10);   -- IA como optativa no 5º período

-- ==============================================================================
-- 7. USUÁRIOS BASE (10 registros: 1-5 Docentes, 6-10 Discentes)
-- Inclusão das colunas mapeadas no modelo atual: nome_social, cep, logradouro, genero
-- ==============================================================================
INSERT IGNORE INTO usuario (id, matricula, nome, nome_social, cep, logradouro, genero, email, email_inst, senha, status, data_nasc, cpf, data_ingresso) VALUES
(1, 'DOC001', 'Alan Turing', NULL, NULL, NULL, NULL, 'alan@uff.br', 'alan.turing@id.uff.br', 'senha123', 'ATIVO', '1912-06-23', '111.111.111-11', '2010-01-15'),
(2, 'DOC002', 'Ada Lovelace', NULL, NULL, NULL, NULL, 'ada@uff.br', 'ada.lovelace@id.uff.br', 'senha123', 'ATIVO', '1815-12-10', '222.222.222-22', '2012-03-10'),
(3, 'DOC003', 'Grace Hopper', NULL, NULL, NULL, NULL, 'grace@uff.br', 'grace.hopper@id.uff.br', 'senha123', 'ATIVO', '1906-12-09', '333.333.333-33', '2015-08-01'),
(4, 'DOC004', 'Linus Torvalds', NULL, NULL, NULL, NULL, 'linus@uff.br', 'linus.torvalds@id.uff.br', 'senha123', 'ATIVO', '1969-12-28', '444.444.444-44', '2018-02-20'),
(5, 'DOC005', 'Tim Berners-Lee', NULL, NULL, NULL, NULL, 'tim@uff.br', 'tim.lee@id.uff.br', 'senha123', 'ATIVO', '1955-06-08', '555.555.555-55', '2020-07-05'),
(6, 'DIS001', 'Guilherme Fontoura', NULL, NULL, NULL, NULL, 'guilherme@gmail.com', 'guilherme@id.uff.br', 'senha123', 'ATIVO', '2000-01-01', '666.666.666-66', '2022-03-01'),
(7, 'DIS002', 'Maria Silva', NULL, NULL, NULL, NULL, 'maria@gmail.com', 'maria@id.uff.br', 'senha123', 'ATIVO', '2001-02-02', '777.777.777-77', '2023-03-01'),
(8, 'DIS003', 'João Souza', NULL, NULL, NULL, NULL, 'joao@gmail.com', 'joao@id.uff.br', 'senha123', 'ATIVO', '1999-03-03', '888.888.888-88', '2021-03-01'),
(9, 'DIS004', 'Ana Costa', NULL, NULL, NULL, NULL, 'ana@gmail.com', 'ana@id.uff.br', 'senha123', 'ATIVO', '2002-04-04', '999.999.999-99', '2024-03-01'),
(10, 'DIS005', 'Pedro Santos', NULL, NULL, NULL, NULL, 'pedro@gmail.com', 'pedro@id.uff.br', 'senha123', 'ATIVO', '2000-05-05', '000.000.000-00', '2020-03-01');

-- ==============================================================================
-- 7.1. USUÁRIOS COORDENADORES (5 registros)
-- ==============================================================================
INSERT IGNORE INTO usuario (id, matricula, nome, nome_social, cep, logradouro, genero, email, email_inst, senha, status, data_nasc, cpf, data_ingresso) VALUES
(11, 'COORD001', 'Edsger Dijkstra', NULL, NULL, NULL, NULL, 'dijkstra@uff.br', 'edsger.dijkstra@id.uff.br', 'senha123', 'ATIVO', '1930-05-11', '101.101.101-11', '2011-03-01'),
(12, 'COORD002', 'Margaret Hamilton', NULL, NULL, NULL, NULL, 'margaret@uff.br', 'margaret.hamilton@id.uff.br', 'senha123', 'ATIVO', '1936-08-17', '202.202.202-22', '2013-03-01'),
(13, 'COORD003', 'Katherine Johnson', NULL, NULL, NULL, NULL, 'katherine@uff.br', 'katherine.johnson@id.uff.br', 'senha123', 'ATIVO', '1918-08-26', '303.303.303-33', '2014-03-01'),
(14, 'COORD004', 'Richard Feynman', NULL, NULL, NULL, NULL, 'feynman@uff.br', 'richard.feynman@id.uff.br', 'senha123', 'ATIVO', '1918-05-11', '404.404.404-44', '2016-03-01'),
(15, 'COORD005', 'Claude Shannon', NULL, NULL, NULL, NULL, 'shannon@uff.br', 'claude.shannon@id.uff.br', 'senha123', 'ATIVO', '1916-04-30', '505.505.505-55', '2017-03-01');

-- ==============================================================================
-- 8. DOCENTES (Aproveitando os IDs 1 a 5)
-- ==============================================================================
INSERT IGNORE INTO docente (id, departamento_id, titulacao, regime, lattes) VALUES
(1, 1, 'DOUTOR', 'DE', 'http://lattes.cnpq.br/111'),
(2, 1, 'DOUTOR', 'DE', 'http://lattes.cnpq.br/222'),
(3, 2, 'MESTRE', 'TP', 'http://lattes.cnpq.br/333'),
(4, 4, 'DOUTOR', 'DE', 'http://lattes.cnpq.br/444'),
(5, 1, 'MESTRE', 'TP', 'http://lattes.cnpq.br/555');

INSERT IGNORE INTO docente_areas_atuacao (docente_id, areas_atuacao) VALUES
(1, 'Inteligência Artificial'), (1, 'Teoria da Computação'),
(2, 'Algoritmos'), (2, 'Engenharia de Software'),
(3, 'Cálculo Numérico'),
(4, 'Sistemas Operacionais'), (4, 'Redes'),
(5, 'Desenvolvimento Web');

-- ==============================================================================
-- 8.1. COORDENADORES (Aproveitando os IDs 11 a 15)
-- ==============================================================================
INSERT IGNORE INTO docente (id, departamento_id, titulacao, regime, lattes) VALUES
(11, 1, 'DOUTOR', 'DE', 'http://lattes.cnpq.br/101'),
(12, 1, 'DOUTOR', 'DE', 'http://lattes.cnpq.br/202'),
(13, 2, 'DOUTOR', 'DE', 'http://lattes.cnpq.br/303'),
(14, 3, 'DOUTOR', 'DE', 'http://lattes.cnpq.br/404'),
(15, 4, 'DOUTOR', 'DE', 'http://lattes.cnpq.br/505');

INSERT IGNORE INTO coordenador (id, curso_id, inicio_mandato, fim_mandato) VALUES
(11, 1, '2024-01-01', NULL),
(12, 2, '2024-01-01', NULL),
(13, 3, '2024-01-01', NULL),
(14, 4, '2024-01-01', NULL),
(15, 5, '2024-01-01', NULL);

INSERT IGNORE INTO docente_areas_atuacao (docente_id, areas_atuacao) VALUES
(11, 'Algoritmos'), (11, 'Sistemas Distribuídos'),
(12, 'Engenharia de Software'), (12, 'Sistemas Críticos'),
(13, 'Matemática Aplicada'),
(14, 'Física Teórica'),
(15, 'Teoria da Informação'), (15, 'Telecomunicações');

-- ==============================================================================
-- 9. DISCENTES E SEUS HISTÓRICOS (Aproveitando os IDs 6 a 10)
-- ==============================================================================
INSERT IGNORE INTO discente (id, curso_id, cod_curriculo, periodo, forma_permanencia, situacao_academica) VALUES
(6, 1, 'CC-2024', '5', 'DEFINITIVA', 'ATIVO'),
(7, 1, 'CC-2024', '3', 'DEFINITIVA', 'ATIVO'),
(8, 2, 'SI-2024', '7', 'DEFINITIVA', 'ATIVO'),
(9, 3, 'MAT-2024', '1', 'DEFINITIVA', 'ATIVO'),
(10, 4, 'FIS-2024', '8', 'DEFINITIVA', 'FORMADO');

INSERT IGNORE INTO historico (id, discente_id, coeficiente_rend) VALUES
(1, 6, 8.5), (2, 7, 9.1), (3, 8, 7.2), (4, 9, 0.0), (5, 10, 8.8);

-- ==============================================================================
-- 10. HORÁRIOS (5 slots padrão da universidade)
-- ==============================================================================
INSERT IGNORE INTO horario (id, horario_inicio, horario_fim) VALUES
(1, '08:00', '10:00'),
(2, '10:00', '12:00'),
(3, '14:00', '16:00'),
(4, '18:00', '20:00'),
(5, '20:00', '22:00');

INSERT IGNORE INTO horario_dias_da_semana (horario_id, dias_da_semana) VALUES
(1, 'SEGUNDA'), (1, 'QUARTA'),
(2, 'TERCA'), (2, 'QUINTA'),
(3, 'SEGUNDA'), (3, 'QUARTA'),
(4, 'TERCA'), (4, 'QUINTA'),
(5, 'SEXTA');

-- ==============================================================================
-- 11. PERÍODOS LETIVOS (2 registros - 2023.2 e 2024.1)
-- ==============================================================================
INSERT IGNORE INTO periodo (id, semestre, data_inicio, data_fim, data_inicio_inscricao, data_fim_inscricao) VALUES
(1, '2023.2', '2023-08-01 00:00:00', '2023-12-15 00:00:00', '2023-07-15 00:00:00', '2023-07-30 00:00:00'),
(2, '2024.1', '2024-03-01 00:00:00', '2024-07-15 00:00:00', '2024-01-01 00:00:00', '2026-12-31 00:00:00');

-- ==============================================================================
-- 12. TURMAS (Associadas aos Períodos criados)
-- ==============================================================================
INSERT IGNORE INTO turma (id, cod, ano_semestre, status, ementa, max_alunos, disciplina_id, docente_id, horario_id, periodo_id) VALUES
(1, 'T01', '2023.2', 'ATIVA', 'Lógica em C', 40, 2, 2, 1, 2),
(2, 'T02', '2023.2', 'ATIVA', 'Limites e Derivadas', 50, 1, 3, 2, 2),
(3, 'T03', '2024.1', 'ATIVA', 'Listas e Árvores', 30, 3, 1, 3, 2),
(4, 'T04', '2024.1', 'ATIVA', 'Modelo OSI e TCP/IP', 40, 7, 4, 4, 2),
(5, 'T05', '2025.1', 'ATIVA', 'Machine Learning', 25, 10, 1, 5, 2),
(6, 'T06', '2023.2', 'ATIVA', 'SQL e Modelagem', 40, 6, 5, 2, 2),
(7, 'T07', '2024.1', 'ATIVA', 'Processos e Threads', 35, 8, 4, 1, 2),
(8, 'T08', '2024.1', 'ATIVA', 'Mecânica Clássica', 50, 4, 3, 3, 2),
(9, 'T09', '2023.2', 'FECHADA', 'Lógica em Python', 40, 2, 2, 5, 1),
(10, 'T10', '2023.2', 'FECHADA', 'Scrum e UML', 40, 9, 5, 4, 1),
(11, 'T11', '2024.1', 'ATIVA', 'HPC em Nuvem', 40, 9, 5, 1, NULL);

-- ==============================================================================
-- 13. INSCRIÇÕES ATUAIS (Alunos cursando turmas no semestre 2024.1)
-- ==============================================================================

INSERT IGNORE INTO inscricao (id, discente_id, turma_id, status) VALUES
(1, 6, 3, 'ATIVA'),
(2, 6, 5, 'ATIVA'),
(3, 7, 1, 'ATIVA'),
(4, 7, 2, 'ATIVA'),
(5, 8, 6, 'ATIVA');

-- ==============================================================================
-- 14. DISCIPLINAS CURSADAS (Histórico passado dos alunos)
-- ==============================================================================
INSERT IGNORE INTO disciplina_cursada (id, historico_id, turma_id, carga_horaria, nota, nota_vs, frequencia, periodo, status_final) VALUES
(1, 1, 9, 60, 9.5, 0.0, 1, '2023.2', 'APROVADO'),
(2, 1, 10, 60, 8.0, 0.0, 1, '2023.2', 'APROVADO'),
(3, 3, 9, 60, 4.0, 6.5, 1, '2023.2', 'APROVADO'),
(4, 5, 10, 60, 10.0, 0.0, 1, '2023.2', 'APROVADO'),
(5, 2, 9, 60, 3.0, 0.0, 0, '2023.2', 'REPROVADO');
