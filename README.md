# MINHAS TAREFAS

## API REST para fazer o gerenciamento de tarefas

A API faz a persistência de dados com o JPA no mySQL e faz uso de relacionamento ManyToOne e 
OneToMany.

Cada **Usuário** possui suas **Tarefas** e cada tarefa uma **Categoria**.

## Rodar o projeto

1. O ambiente de desenvolvimento usa o banco de dados em memória h2. Para selecionar o ambiente de desenvolvimento, altere o Spring Profiles para `spring.profiles.active=dev` no [application.properties](src/main/resources/application.properties).
2. O ambiente de produçao usa o banco de dados mySQL. Para selecionar o ambiente de produção, altere o Spring Profiles para `spring.profiles.active=prod` no [application.properties](src/main/resources/application.properties).
**Lembrando que é necessário ter o mySQL rodando e configurado na sua máquina.**
3. Adicione sua senha do mySQL em  `spring.datasource.password=` no [application-prod.properties](src/main/resources/application-prod.properties).
4. Ao rodar a primeira vez em produção, sugiro descomentar o `@Profile("dev")` no [CarregaBaseDeDados](src/main/java/com/app/minhastarefas/config/CarregaBaseDeDados.java) para gerar os primeiros dados no seu banco de dados e assim você usar como modelo para os outros POSTs.
5. Rodar direto da IDE a classe `MinhasTarefasApplication`.
6. Acessar [http://localhost:8080/minhastarefas/tarefas](http://localhost:8080/minhastarefas/tarefas)
**As rotas estão nas controllers.**