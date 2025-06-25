# 🗳️ API Gerenciamento de votação

"No cooperativismo, cada associado possui um voto e as decisões são tomadas em assembleias, por votação. Imagine que você deve criar uma solução backend para gerenciar essas sessões de votação."

Objetivo: Implementação de uma solução para controlar sessões de votações de pautas, delimitando o tempo e persistindo tais informações.

---

## Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring Web, Spring Data JPA, Validation
- H2 Database (memória)
- Lombok
- Maven

---

## Funcionalidades

- ✅ Cadastro de associados
- ✅ Criação de pautas de votação (topics)
- ✅ Abertura de sessões de votação com duração configurável
- ✅ Registro de votos (sim/não)
- ✅ Apuração automática do resultado
- ✅ Validação de CPF via API externa

---

## 🛠️ Endpoints Principais

### 🔹 Associados (`/member`)

| Método | Rota               | Descrição                          |
|--------|--------------------|------------------------------------|
| GET    | `/member`          | Listar todos os associados         |
| GET    | `/member/{id}`     | Buscar um associado por ID         |
| POST   | `/member`          | Cadastrar um novo associado        |

### 🔹 Pautas (`/topic`)

| Método | Rota               | Descrição                          |
|--------|--------------------|------------------------------------|
| GET    | `/topic`           | Listar todas as pautas             |
| GET    | `/topic/{id}`      | Buscar uma pauta por ID            |
| POST   | `/topic`           | Criar uma nova pauta               |

### 🔹 Sessões (`/session`)

| Método | Rota                     | Descrição                                      |
|--------|--------------------------|------------------------------------------------|
| GET    | `/session`               | Listar todas as sessões                        |
| GET    | `/session/{id}`          | Buscar uma sessão por ID                       |
| POST   | `/session`               | Criar uma nova sessão                          |
| GET    | `/session/{id}/result`   | Obter o resultado da votação de uma pauta      |

### 🔹 Votos (`/vote`)

| Método | Rota         | Descrição                          |
|--------|--------------|------------------------------------|
| POST   | `/vote`      | Registrar um voto                  |
---

## Como Executar

### Pré-requisitos

- Java 17+
- Maven

### Passos

```bash
# Clone o repositório
git clone https://github.com/marlonleoner/technical-assessment.git
cd technical-assessment

# Compile e execute o projeto
./mvnw spring-boot:run
