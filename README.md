# FlashCards : Steps to Vocab

Welcome to the FlashCards project!

## Description

FlashCards is a simple web API for studying and memorizing language vocabularies using digital flashcards. It allows users to create flashcards with phrases in different languages (ENGLISH and SPANISH for now), add question themselves to test their knowledge

The main purpose of the project is to provide a sandbox to test out/learn new concepts and technologies without having to start all over again each time ^^

## Features

- Add questions and answers to each flashcard
- Quiz yourself with random flashcards from a chosen deck

## Plans
- Create multiple decks of flashcards
- Quiz yourself with random flashcards
- Keep track of your progress and scores
- Add familarity levels (steps on the ladder) to phrases/translations

## Installation

To use FlashCards, follow these steps:

### Run locally

> :memo: **Note:** You will need a postgres db running somewhere for now (in memory profile in plans)

1. Set env variables `POSTGRESS_URL`, `POSTGRESS_USER` and `POSTGRES_PASS` with credentails and address of db
2. Build project with `./mvnw package -DskipTests`
3. Run server with `./mvnw spring-boot:run -f fc-web-api/pom.xml -DskipTests`


### Run in a container

1. Set env variable `POSTGRES_PASS` with a password to the database that will be used in deployment
2. Run `docker compose up --build`

## Usage

You can refer to swagger documentation for endpoints available once the application is running (http://localhost:8080/swagger-ui/index.html)

