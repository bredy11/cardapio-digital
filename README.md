# 🍽️ Cardápio Digital

Projeto de **Cardápio Digital** desenvolvido em **Java 17** e **Spring Boot**, seguindo o padrão de **Clean Architecture**.  
O objetivo é permitir que **donos de restaurante** possam criar e gerenciar seus cardápios de forma digital, enquanto **clientes** podem consultar os cardápios sem a necessidade de autenticação.  

---

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot** (REST API, injeção de dependência, etc.)
- **Spring Security + JWT** (autenticação e autorização)
- **Spring Data JPA / Hibernate** (persistência de dados)
- **H2/PostgreSQL** (banco de dados em memória para dev e relacional para produção)
- **MapStruct** (conversão entre entidades e DTOs)
- **Swagger / OpenAPI** (documentação da API)
- **JUnit 5 + Mockito + RestAssured** (testes unitários e de integração)

---

## 🏛️ Arquitetura

A arquitetura segue os princípios de **Clean Architecture**, separando regras de negócio de implementações técnicas.  
Isso facilita testes, manutenção e evolução do projeto.
`
src/main/java/com/comandaDigital
│
├── application → Casos de uso (regras orquestradas do sistema)
│ ├── dto → Objetos de entrada/saída dos casos de uso
│ ├── service → Implementação dos casos de uso
│ └── port → Interfaces (input/output) usadas pelos casos de uso
│
├── domain → Entidades e regras de negócio puras
│ └── model → Entidades do domínio (sem dependência de Spring)
│
├── infrastructure → Implementações técnicas (adapters)
│ ├── config → Configurações do Spring (Beans, MQ, Security etc.)
│ ├── persistence → Repositórios (JPA, JDBC, etc.)
│ ├── messaging → MQ/Kafka/RabbitMQ (futuro)
│ ├── rest → Controllers REST
│ └── mapper → MapStruct/ conversores
│
└── shared → Utilitários e classes comuns
├── exception → Exceções customizadas
└── utils → Helpers

`

---

### 🖼️ Visão em diagrama (Mermaid)
> O GitHub já renderiza automaticamente 🎉

```mermaid
flowchart TD
    A[application] --> B[dto]
    A --> C[service]
    A --> D[port]

    E[domain] --> F[model]

    G[infrastructure] --> H[config]
    G --> I[persistence]
    G --> J[messaging]
    G --> K[rest]
    G --> L[mapper]

    M[shared] --> N[exception]
    M --> O[utils]
