# BingeTrack

Aplicativo Android desenvolvido em Java para controle de filmes.

## Descrição

O **BingeTrack** é um aplicativo mobile nativo para Android que permite ao usuário cadastrar, organizar e acompanhar filmes assistidos ou que deseja assistir.

O sistema foi desenvolvido como projeto prático da disciplina de Dispositivos Móveis, utilizando **Android Studio**, **Java**, **XML** e banco de dados local com **SQLite**.

## Objetivo

O objetivo do aplicativo é facilitar o controle pessoal de filmes, permitindo que o usuário registre informações como nome, gênero, ano de lançamento, status e nota de avaliação.

## Funcionalidades

* Cadastrar filmes;
* Listar filmes cadastrados;
* Visualizar detalhes de cada filme;
* Editar informações de um filme;
* Excluir filmes cadastrados;
* Marcar status como **Assistido** ou **Quero assistir**;
* Avaliar filmes com nota de 1 a 5;
* Validar campos obrigatórios antes do cadastro.

## Tecnologias utilizadas

* Java;
* Android Studio;
* XML;
* SQLite;
* Git e GitHub.

## Entidade principal

### Filme

A entidade principal do sistema é **Filme**, composta pelos seguintes atributos:

* ID;
* Nome;
* Gênero;
* Ano de lançamento;
* Status;
* Nota.

## Estrutura do projeto

```text
com.example.bingeapp
│
├── database
│   └── DatabaseHelper.java
│
├── model
│   └── Filme.java
│
├── CadastroFilme.java
├── DetalhesFilme.java
└── MainActivity.java
```

## Telas do aplicativo

O aplicativo possui três telas principais:

### Tela inicial

Exibe o nome do aplicativo, o botão para adicionar filmes e a lista de filmes cadastrados.

### Tela de cadastro/edição

Permite cadastrar ou editar um filme, informando:

* Nome do filme;
* Gênero;
* Ano de lançamento;
* Status;
* Nota.

### Tela de detalhes

Exibe as informações completas do filme selecionado e permite editar ou excluir o registro.

## CRUD implementado

O aplicativo possui CRUD completo:

| Operação | Descrição                                      |
| -------- | ---------------------------------------------- |
| Create   | Cadastro de novos filmes                       |
| Read     | Listagem e visualização dos filmes cadastrados |
| Update   | Edição dos dados de um filme                   |
| Delete   | Exclusão de filmes                             |

## Validações

O sistema possui validações para evitar cadastros incorretos:

* O nome do filme não pode ficar vazio;
* O gênero não pode ficar vazio;
* O ano de lançamento deve ser válido;
* A nota deve ser de 1 a 5.

## Como executar o projeto

1. Clone este repositório:

```bash
git clone https://github.com/louise-peccin/BingeTrack.git
```

2. Abra o projeto no Android Studio.

3. Aguarde o Gradle sincronizar.

4. Execute o aplicativo em um emulador Android ou dispositivo físico.

## Autora

**Louise Paccola Peccin**

Projeto desenvolvido para a disciplina de Dispositivos Móveis.
