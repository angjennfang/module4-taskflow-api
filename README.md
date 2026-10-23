# Taskflow API

A Java REST API for managing tasks. Built with Spring Boot, it provides endpoints to create, retrieve, update, complete, and delete tasks. It can also generate a plain-English task summary using Spring AI and an OpenRouter-compatible chat model.

## Key features

- Create tasks with a title and description.
- Retrieve all tasks or look up a task by ID.
- Update task details and completion status.
- Mark a task as complete.
- Delete a task.
- Generate an AI summary of the current tasks.
- Build, test, and run the project with the included Maven Wrapper.

> **Note:** Tasks are stored in memory, not in a database. They are lost when the application stops or restarts.

## Prerequisites

- Java Development Kit (JDK) 21.
- An OpenRouter API key for the AI-powered task summary endpoint.
- Internet access so Maven can download dependencies.

## Installation and setup

1. Clone the repository and change into the project directory:

   ```bash
   git clone <repository-url>
   cd taskflow-api
   ```

2. Set your OpenRouter API key as an environment variable. Do not commit your key to the repository.

   **macOS / Linux**

   ```bash
   export OPENROUTER_API_KEY="your-api-key"
   ```

   **Windows PowerShell**

   ```powershell
   $env:OPENROUTER_API_KEY = "your-api-key"
   ```

3. Run the tests:

   ```bash
   ./mvnw test
   ```

   On Windows:

   ```powershell
   .\mvnw.cmd test
   ```

4. Start the application:

   ```bash
   ./mvnw spring-boot:run
   ```

   On Windows:

   ```powershell
   .\mvnw.cmd spring-boot:run
   ```

The API is available at `http://localhost:8080`.

## API endpoints

All task endpoints use the `/api/tasks` base path.

| Method | Endpoint | Description |
| --- | --- | --- |
| `GET` | `/api/tasks` | List all tasks |
| `GET` | `/api/tasks/{id}` | Get a task by ID |
| `POST` | `/api/tasks` | Create a task |
| `PUT` | `/api/tasks/{id}` | Update a task |
| `DELETE` | `/api/tasks/{id}` | Delete a task |
| `PUT` | `/api/tasks/{id}/complete` | Mark a task as complete |
| `GET` | `/api/tasks/summary` | Generate an AI summary of tasks |

### Example: create a task

```bash
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Prepare project README",
    "description": "Document setup and API endpoints",
    "completed": false
  }'
```

### Example: list tasks

```bash
curl http://localhost:8080/api/tasks
```

### Example: mark a task as complete

```bash
curl -X PUT http://localhost:8080/api/tasks/1/complete
```

### Example: generate an AI task summary

```bash
curl http://localhost:8080/api/tasks/summary
```

The summary endpoint requires `OPENROUTER_API_KEY` to be set.

## Technology stack

- Java 21
- Spring Boot
- Spring Web MVC
- Spring AI
- OpenRouter-compatible chat API
- Maven Wrapper
