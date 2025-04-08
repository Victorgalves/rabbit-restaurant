# rabbit-restaurant

Sistema de mensageria para restaurante usando RabbitMQ.

---

### 📋 Pré-requisitos

- Java 17+
- Python 3.8+
- Maven instalado
- Biblioteca `pika` (instale com `pip install pika`)

---

### 🔐 Configurando o RabbitMQ

- Crie uma conta gratuita no [CloudAMQP](https://www.cloudamqp.com/)
- Crie uma instância e copie a URL de conexão (exemplo: `amqps://user:password@host/vhost`)

Atualize a URL nos seguintes locais:

- **Java**: abra o arquivo `src/main/resources/application.properties`  
  Substitua as linhas:
  ```properties
      spring.rabbitmq.host=SEU_HOST
      spring.rabbitmq.port=5671
      spring.rabbitmq.username=SEU_USERNAME
      spring.rabbitmq.password=SUA_SENHA
      spring.rabbitmq.virtual-host=SEU_VHOST
      spring.rabbitmq.ssl.enabled=true spring.rabbitmq.addresses=amqps://user:senha@host/vhost
  ```



- **Python**: nos arquivos `Cliente.py` e `auditoria.py`, localize a linha:
  ```python
  params = pika.URLParameters("amqps://user:senha@host/vhost")
  ```
  E substitua com a URL da sua instância.

---

  ### 🚀 Como rodar

1. Abra um terminal na raiz do projeto e execute:
   ```bash
   mvn spring-boot:run
   ```
   ou
   ```bash
   ./mvnw spring-boot:run
   ```

3. Abra outro terminal, vá até a pasta `consumer`:
   ```bash
   cd consumer
   python Cliente.py
   ```

4. Abra outro terminal, também na pasta `consumer`, e execute:
   ```bash
   python auditoria.py
   ```

