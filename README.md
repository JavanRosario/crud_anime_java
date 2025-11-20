# 📘 CRUD Anime Java – JDBC + MySQL + Maven

Um projeto de estudo para demonstrar um CRUD completo utilizando:

- **Java 17**
- **JDBC**
- **MySQL**
- **Maven**
- **SLF4J + Logback**
- **Lombok**
- **Padrão Repository + Services**
- **Menu interativo no terminal**

---

## 📌 Funcionalidades

✔ Buscar produtores por nome (ou listar todos)  
✔ Inserir novo produtor  
✔ Deletar produtor pelo ID  
✔ Atualizar produtor  
✔ Validação de entradas  
✔ Configuração via `config.properties`  
✔ Logs com SLF4J/Logback  
✔ Conexões fechadas automaticamente com try-with-resources

---

## 🛠️ Tecnologias usadas

- Java 17
- Maven
- MySQL
- JDBC
- Lombok
- Logback / SLF4J
- IntelliJ IDEA

---

## 📁 Estrutura do Projeto
```
crud_anime_java/
│
├── src/main/java/
│   ├── conn/ConnectionDataBase.java
│   ├── domain/Producer.java
│   ├── Repository/ProducerRepository.java
│   ├── services/ProducerServices.java
│   └── tests/CrudTest.java
│
├── src/main/resources/
│   └── config.properties
│
├── pom.xml
└── README.md
```

---

## ⚙️ Configuração do Banco de Dados

No MySQL:
```sql
CREATE DATABASE anime_store;

USE anime_store;

CREATE TABLE producer (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);
```

---

## ⚙️ Configuração do arquivo `config.properties`

Crie em:
```
src/main/resources/config.properties
```

Conteúdo:
```properties
url=jdbc:mysql://localhost:3306/anime_store
user=SEU_USUARIO
password=SUA_SENHA
```

---

## ▶️ Como executar o projeto

### ✔ Pela IDE (IntelliJ)

Execute a classe:
```
tests.CrudTest
```

### ✔ Pelo terminal
```bash
mvn clean compile exec:java
```

---

## 🖥️ Menu do sistema
```
====================================
        PRODUCER MANAGEMENT         
====================================
1. Search Producers (or Show All)
2. Delete Producer
3. Insert New Producer
0. Exit
====================================
Choose an option:
```

---

## 🧱 Boas práticas aplicadas

* **Repository Pattern** – Separação entre acesso a dados e lógica de negócio
* **Separação entre camadas** – Repository, Services e Tests isolados
* **PreparedStatement** – Prevenção contra SQL Injection
* **Retornos seguros** – Lista vazia ao invés de null
* **Builder Pattern (Lombok)** – Criação limpa e legível de objetos: `Producer.builder().name("Toei").build()`
* **Log estruturado (SLF4J)** – Rastreamento eficiente
* **Configuração externa** – Credenciais fora do código


---

## 🤝 Contribuições

Pull requests são bem-vindos!

---

## 📄 Licença

Livre para uso e estudo.
