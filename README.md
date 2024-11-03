# API de QR Codes

## Descrição

Este projeto é uma API REST desenvolvida em Spring Boot para a criação e gestão de QR Codes. A aplicação permite criar QR Codes imediatos e com datas de vencimento, além de listar e buscar QR Codes por ID.

## Funcionalidades

- Criar QR Code imediato.
- Criar QR Code com data de vencimento.
- Listar todos os QR Codes.
- Buscar QR Code por ID.

## Tecnologias Utilizadas

- Java 17
- Spring Boot
- Swagger (para documentação)
- Banco de dados H2 (em memória)

## Requisitos para Execução

- JDK 17+
- Maven

## Como Executar o Projeto

1. Clone o repositório:
   ```sh
   git clone https://github.com/rapvalerio/dock.git
   ```
2. Navegue até o diretório do projeto:
   ```sh
   cd dock
   ```
3. Compile e execute a aplicação:
   ```sh
   ./mvnw spring-boot:run
   ```
4. Acesse a aplicação no endereço: `http://localhost:8080`.

## Documentação da API

A documentação Swagger pode ser acessada através do link:

```
http://localhost:8080/swagger-ui.html
```

## Exemplos de Uso

### Criar um QR Code Imediato

**Endpoint**: `POST /qrcode`

**Request Body**:

```json
{
  "description": "Pagamento para fatura XYZ",
  "amount": 150.0,
  "status": "new"
}
```

### Criar um QR Code com Data de Vencimento

**Endpoint**: `POST /qrcode/dueDate`

**Request Body**:

```json
{
  "description": "Pagamento com vencimento",
  "amount": 200.0,
  "status": "new",
  "dueDate": "2024-12-31T23:59:59"
}
```

## Estrutura do Projeto

- `controller`: Contém os endpoints da API.
- `service`: Contém a lógica de negócio.
- `dto`: Contém as classes para transferência de dados (Data Transfer Objects).
- `repository`: Camada responsável pela persistência de dados.

## Como Executar os Testes

Para rodar os testes, execute:

```sh
./mvnw test
```

