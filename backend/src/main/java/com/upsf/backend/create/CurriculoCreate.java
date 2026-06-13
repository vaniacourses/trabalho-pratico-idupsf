package com.upsf.backend.create;

import java.util.List;

public record CurriculoCreate(
        String cod,
        List<RegistroDisciplinaCreate> disciplinas
) {}
