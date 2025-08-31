# API de Adoção de Pets

Esta é uma API REST para um sistema de adoção responsável de pets, desenvolvida em Java 21 com Spring Boot.

## Como Executar a API

1.  **Pré-requisitos:**
    *   Java 21 (ou superior)
    *   Apache Maven

2.  **Execução:**
    *   Navegue até o diretório raiz do projeto (`AdocaoPetsAPI`).
    *   Execute o seguinte comando no seu terminal:
        ```bash
        mvn spring-boot:run
        ```
    *   A API estará disponível em `http://localhost:8080`.

3.  **Acessando o Banco de Dados H2:**
    *   Com a aplicação rodando, acesse `http://localhost:8080/h2-console` no seu navegador.
    *   Use a URL `jdbc:h2:mem:adocao_db` para se conectar.

---

## Rotas da API

### Recurso: Animais (`/animais`)

#### Listar todos os animais (com filtros)

*   **Método:** `GET`
*   **Rota:** `/animais`
*   **Parâmetros de Query (opcionais):**
    *   `tipo` (String): Filtra por tipo de animal (ex: "Cachorro").
    *   `porte` (String): Filtra por porte (ex: "Medio").
    *   `localizacao` (String): Filtra por cidade/estado.
*   **Exemplo (sem filtros):**
    ```bash
    curl -X GET http://localhost:8080/animais
    ```
*   **Exemplo (com filtros):**
    ```bash
    curl -X GET "http://localhost:8080/animais?tipo=Cachorro&localizacao=SP"
    ```
*   **Resposta (200 OK):**
    ```json
    [
        {
            "id": 1,
            "nome": "Rex",
            "tipo": "Cachorro",
            "porte": "Médio",
            "idade": 3,
            "localizacao": "São Paulo, SP",
            "status": "Disponível",
            "necessidadesEspeciais": "Nenhuma"
        }
    ]
    ```

#### Buscar animal por ID

*   **Método:** `GET`
*   **Rota:** `/animais/{id}`
*   **Exemplo:**
    ```bash
    curl -X GET http://localhost:8080/animais/1
    ```
*   **Resposta (200 OK):**
    ```json
    {
        "id": 1,
        "nome": "Rex",
        "tipo": "Cachorro",
        "porte": "Médio",
        "idade": 3,
        "localizacao": "São Paulo, SP",
        "status": "Disponível",
        "necessidadesEspeciais": "Nenhuma"
    }
    ```

#### Adicionar novo animal

*   **Método:** `POST`
*   **Rota:** `/animais`
*   **Corpo da Requisição:**
    ```json
    {
        "nome": "Mimi",
        "tipo": "Gato",
        "porte": "Pequeno",
        "idade": 1,
        "localizacao": "Rio de Janeiro, RJ",
        "status": "Disponível",
        "necessidadesEspeciais": "Arisca com estranhos"
    }
    ```
*   **Exemplo:**
    ```bash
    curl -X POST -H "Content-Type: application/json" -d '''{ "nome": "Mimi", "tipo": "Gato", "porte": "Pequeno", "idade": 1, "localizacao": "Rio de Janeiro, RJ", "status": "Disponível", "necessidadesEspeciais": "Arisca com estranhos" }''' http://localhost:8080/animais
    ```
*   **Resposta (201 Created):**
    ```json
    {
        "id": 2,
        "nome": "Mimi",
        "tipo": "Gato",
        "porte": "Pequeno",
        "idade": 1,
        "localizacao": "Rio de Janeiro, RJ",
        "status": "Disponível",
        "necessidadesEspeciais": "Arisca com estranhos"
    }
    ```

#### Atualizar um animal

*   **Método:** `PUT`
*   **Rota:** `/animais/{id}`
*   **Exemplo:**
    ```bash
    curl -X PUT -H "Content-Type: application/json" -d '''{ "nome": "Mimi", "tipo": "Gato", "porte": "Pequeno", "idade": 1, "localizacao": "Rio de Janeiro, RJ", "status": "Adotado", "necessidadesEspeciais": "Nenhuma" }''' http://localhost:8080/animais/2
    ```
*   **Resposta (200 OK):**
    ```json
    {
        "id": 2,
        "nome": "Mimi",
        "tipo": "Gato",
        "porte": "Pequeno",
        "idade": 1,
        "localizacao": "Rio de Janeiro, RJ",
        "status": "Adotado",
        "necessidadesEspeciais": "Nenhuma"
    }
    ```

#### Deletar um animal

*   **Método:** `DELETE`
*   **Rota:** `/animais/{id}`
*   **Exemplo:**
    ```bash
    curl -X DELETE http://localhost:8080/animais/2
    ```
*   **Resposta (204 No Content):**

---

### Recurso: Adotantes (`/adotantes`)

#### Adicionar novo adotante

*   **Método:** `POST`
*   **Rota:** `/adotantes`
*   **Validação:** Campos `nome`, `email` e `cpf` são obrigatórios. O email deve ter um formato válido.
*   **Exemplo:**
    ```bash
    curl -X POST -H "Content-Type: application/json" -d '''{ "nome": "Maria Souza", "email": "maria.souza@email.com", "cpf": "111.222.333-44", "telefone": "11987654321", "endereco": "Rua das Flores, 123" }''' http://localhost:8080/adotantes
    ```
*   **Resposta de Erro (400 Bad Request):**
    ```json
    {
        "timestamp": "...",
        "status": 400,
        "error": "Bad Request",
        "errors": [
            {
                "codes": [...],
                "arguments": [...],
                "defaultMessage": "O nome é obrigatório",
                "objectName": "adotante",
                "field": "nome",
                ...
            }
        ]
    }
    ```

(As rotas `GET`, `PUT` e `DELETE` para `/adotantes` seguem o mesmo padrão do recurso `/animais`.)
