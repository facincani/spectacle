# Spectacle API

**O objetivo do projeto e servir de backend para o app spectacle!** </br>
Com o app você é capaz de registrar suas filmes e musicas preferidas.

# Como rodar o projeto na sua maquina:


**Requisitos:** 

- [JVM 11](https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html)
- [Docker-compose](https://docs.docker.com/compose/install/)
- [Gradle](https://gradle.org/install/)

**Com todos os pré requisitos atendidos:**

Utilizando o terminal de comando vá até a o diretório raiz do projeto e digite os eguinte comando:

~~~
docker-compose up 
~~~

Este comando irá iniciar o banco de dados mysql todas as configurações estão no arquivo docker-compose.yml

Uma vez que o banco de dados tenha sido iniciado basta rodar a aplicação com o comando 
~~~
gradlew bootRun
~~~
para rodar via linha de comando ou se preferir pode utilizar a IDE de sua preferencia o projeto foi construido itilizando o [IntelliJ Idea](https://www.jetbrains.com/pt-br/idea/download/#section=windows).