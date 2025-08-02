# Umbrafit - Gestão de academia
Agora a academia possui um nome: Umbrafit!
Meu projeto é um sistema de gerenciamento de academia de musculação com backend em Java - Spring Boot e frontend em Angular.

## Sobre o projeto

Este projeto tem como objetivo oferecer uma solução completa para o gerenciamento de academia, do qual permite cadastro de usuários, controle de presenças, planos, entre outras funcionalidades em desenvolvimento.

## Tecnologias Utilizadas

### Backend
- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven
- Lombok
- CORS configurado para comunicação com frontend

### Frontend
- Angular 17
- TypeScript
- Angular Material
- Reactive Forms
- HTTP Client

## Estrutura do Projeto

```
gym-management/  # Projeto Java Spring Boot
│                
└── src/...
│
├── frontend/               # Projeto Angular
│   └── src/...
│
└── README.md
```

## Para rodar o projeto localmente

### Pré-requisitos

- [Java 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Node.js 18+](https://nodejs.org/)
- [Angular CLI](https://angular.io/cli)
- [PostgreSQL](https://www.postgresql.org/)
- [Maven](https://maven.apache.org/)

---

### Backend - Spring Boot

1. **Configure o banco de dados PostgreSQL**:
   - Crie um banco com o nome desejado (ex: `gym_db`)
   - Atualize o arquivo `application.properties` com seus dados:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/gym_db
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   ```

2. **Execute o projeto**:

   No terminal:

   ```bash
   cd backend
   mvn spring-boot:run
   ```

   O backend estará disponível em: `http://localhost:8081`

---

### Frontend - Angular

1. **Instale as dependências**:

   ```bash
   cd frontend
   npm install
   ```

2. **Execute o servidor Angular**:

   ```bash
   ng serve
   ```

   A aplicação estará disponível em: `http://localhost:4200`

---

### Comunicação entre Frontend e Backend

O serviço de cadastro de usuário utiliza a seguinte URL:

```
POST http://localhost:8081/gym/register
```

O serviço para ver a lista de alunos no banco de dados:

```
GET http://localhost:8081/gym/alunos
```

Por ora somente o serviço de cadastro e login está habilitado no frontend, demais funcionalidades estão por vir!

Caso enfrente erro de **CORS**, verifique se o backend está com CORS habilitado corretamente.

---

## Contato

Desenvolvido por [Gustavo Fernandes Santos](www.linkedin.com/in/gustavo-fernandes-704325260)
