# Movie Voting System 🎬

API de votação de filmes e séries desenvolvido em Spring Boot, conforme teste prático do processo de formação.

---

## 📌 Objetivo

Desenvolver um sistema que permita aos usuários votarem em uma lista de filmes ou séries, registrando votos positivos ("Gostei") e negativos ("Não Gostei"). O sistema também permite o cadastro de novos filmes/séries, armazenamento persistente dos dados e exibição dos totais de votos por item e no geral.

---

## 📋 Funcionalidades Obrigatórias

### 1. Exibição de Itens
- Exibição inicial de pelo menos 5 filmes ou séries.
- Cada item apresenta:
  - Título
  - Gênero
  - Descrição
  - Imagem (URL ou placeholder)
  - Botões: “Gostei” e “Não Gostei”

### 2. Votação
- Atualização imediata do contador ao clicar em “Gostei” ou “Não Gostei”.
- Armazenamento correto dos votos por item.
- Exibição dos totais:
  - Votos positivos e negativos por item.
  - Totais gerais de votos positivos e negativos na página.

### 3. Cadastro de Filmes/Séries
- Cadastro de novos filmes ou séries com os seguintes campos:
  - Obrigatórios: Título, Gênero, Imagem (URL)
  - Opcionais: Descrição
- Novos itens cadastrados aparecem com votos zerados e disponíveis para votação imediatamente.

### 4. Armazenamento de Dados
- Persistência dos dados para garantir que filmes, séries, votos e cadastros não se percam ao recarregar ou reiniciar a aplicação.

---

## 🖥 Requisitos por Tipo de Implementação

### Front-end (HTML/CSS/JavaScript)
- Interface visual contendo:
  - Lista de filmes/séries com informações e botões de voto
  - Contadores de votos por item
  - Contadores gerais de votos (positivos e negativos)
  - Aba com formulário para cadastro de novos filmes/séries
  - Persistência de dados no front-end

### Back-end (API)
- Endpoints para:
  - Listar filmes/séries com votos atuais
  - Cadastrar novos filmes/séries
  - Registrar votos positivos e negativos por item
  - Buscar totais gerais de votos positivos e negativos
- Persistência de dados no back-end

---

## 📦 Estrutura de Dados

| Campo     | Tipo     | Descrição                      |
|-----------|----------|-------------------------------|
| `id`      | Long     | Identificador único do item    |
| `título`  | String   | Nome do filme ou série         |
| `gênero`  | String   | Gênero do conteúdo             |
| `descrição` | String  | Breve descrição do filme/série |
| `imagem`  | String   | URL da imagem                  |
| `gostei`  | Integer  | Total de votos positivos       |
| `naoGostei` | Integer | Total de votos negativos       |

---

## 🚀 Como Executar (Exemplo para implementação Spring Boot com Docker)

### Pré-requisitos
- Java 21
- Docker e Docker Compose
- Node 22
- Maven 3.8+

### Passos

1. Clone o repositório:
```bash
git clone https://github.com/kiqreis/mosten-teste-pratico.git
cd mosten-teste-pratico

docker-compose up -d

mvn spring-boot:run

