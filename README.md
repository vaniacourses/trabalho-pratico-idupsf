# IdUPSF

## Artefatos entregues

- Backend Spring Boot: [backend/](backend/)
- Frontend Next.js: [front_idupsf/](front_idupsf/)
- Diagramas e documentos visuais: [Diagramas/](Diagramas/)

## Backend

- Código-fonte: [backend/src/main/java/com/upsf/backend/](backend/src/main/java/com/upsf/backend/)
- Configuração: [backend/src/main/resources/application.properties](backend/src/main/resources/application.properties)
- Carga inicial do banco: [backend/src/main/resources/data.sql](backend/src/main/resources/data.sql)
- Dependências Maven: [backend/pom.xml](backend/pom.xml)

## Frontend

- Código-fonte: [front_idupsf/src/](front_idupsf/src/)
- Página de login: [front_idupsf/src/app/login/](front_idupsf/src/app/login/)
- Serviços: [front_idupsf/src/services/](front_idupsf/src/services/)
- Configuração Next.js: [front_idupsf/next.config.ts](front_idupsf/next.config.ts)

## Diagramas

- DSS: [Diagramas/DSS/](Diagramas/DSS/)
- Casos de Uso: [Diagramas/Diagrama de Casos de Uso/](Diagramas/Diagrama%20de%20Casos%20de%20Uso/)
- Diagrama de Classe: [Diagramas/Diagrama de Classe/](Diagramas/Diagrama%20de%20Classe/)
- Diagramas de Interação: [Diagramas/Diagramas de Interacao/](Diagramas/Diagramas%20de%20Interacao/)
- Modelo Conceitual: [Diagramas/Modelo Conceitual/](Diagramas/Modelo%20Conceitual/)
- Visão Arquitetural: [Diagramas/Visao Arquitetural/](Diagramas/Visao%20Arquitetural/)
- LPS e Padrões GoF: [Diagramas/Padrões GOF e LPS/](Diagramas/Padrões%20GOF%20e%20LPS/)

## Relatórios
- Relatórios: [Relatórios/](Relatorios/)

## Execução

Backend:

```bash
cd backend
./mvnw spring-boot:run
```

Frontend:

```bash
cd front_idupsf
npm install
npm run dev
```
