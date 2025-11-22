# Sistema de Gerenciamento de Animes

Sistema Java para CRUD de animes e produtoras com MySQL.

## 🛠️ Tecnologias
- Java 11+
- MySQL 8.0
- Maven
- Docker
- JDBC

## 📁 Estrutura do Projeto
```
src/
├── main/
│   ├── java/
│   │   ├── conn/
│   │   │   └── ConnectionDataBase.java    # Gerenciamento de conexão DB
│   │   ├── domain/
│   │   │   ├── Anime.java                 # Modelo de dados Anime
│   │   │   └── Producer.java              # Modelo de dados Producer
│   │   ├── Repository/
│   │   │   ├── AnimeRepository.java       # Operações CRUD Anime
│   │   │   └── ProducerRepository.java    # Operações CRUD Producer
│   │   └── services/
│   │       ├── AnimeServices.java         # Lógica de negócio Anime
│   │       └── ProducerServices.java      # Lógica de negócio Producer
│   └── resources/
│       └── config.properties              # ⚙️ Configuração do banco
├── docker-compose.yml                     # Configuração Docker
├── .env                                   # Variáveis de ambiente (não versionar)
└── pom.xml                               # Dependências Maven
```

## 🐳 Configuração Rápida

### Docker Compose
```yaml
services:
  db:
    image: mysql:8.0
    container_name: mysql_anime
    environment:
      MYSQL_ROOT_PASSWORD: ${DB_PASSWORD}
      MYSQL_DATABASE: anime_store
    ports:
      - "${DB_PORT}:3306"
    volumes:
      - anime_data:/var/lib/mysql

volumes:
  anime_data:
```

### Variáveis de Ambiente
Crie um arquivo `.env`:
```
DB_PASSWORD=sua_senha_aqui
DB_PORT=sua_porta_aqui
DB_USER=seu_usuario_aqui
```

### Configuração da Aplicação
**Localização:** `src/main/resources/config.properties`
```properties
url=jdbc:mysql://localhost:sua_porta_aqui/anime_store
user=rootsua_porta_aqui
password=seu_usuario_aqui
```

## 🗄️ Schema do Banco
```sql
CREATE DATABASE anime_store;

CREATE TABLE producer (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE anime (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    episodes INT NOT NULL,
    producer_id INT,
    FOREIGN KEY (producer_id) REFERENCES producer(id)
);
```

## 📦 Dependências Maven
- MySQL Connector 8.0.33
- Lombok 1.18.42  
- SLF4J 2.0.17
- Logback 1.5.21

## 📋 Funcionalidades
- ✅ CRUD Animes
- ✅ CRUD Produtoras
- ✅ Busca por nome
- ✅ Console-Interativo

## 🏆 Boas Práticas Implementadas

- **Arquitetura em camadas** - Repository, Service, Domain
- **Prepared Statements** - Prevenção SQL Injection
- **Try-with-resources** - Gerenciamento automático de recursos
- **Padrão Builder** - Lombok para construção de objetos
- **Objetos imutáveis** - Domain models com @Value
- **Logs estruturados** - SLF4J para rastreamento
- **Validação de entrada** - Verificação de tipos
