### Script SQL
```sql 
 CREATE DATABASE gestao_clientes;

  USE gestao_clientes;

  CREATE TABLE cliente (
      id INT PRIMARY KEY AUTO_INCREMENT,
      nome VARCHAR(100) NOT NULL,
      email VARCHAR(100),
      telefone VARCHAR(20),
      cpf VARCHAR(14),
      endereco VARCHAR(200)
  );
```
