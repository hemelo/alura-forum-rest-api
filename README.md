# forum-alura-rest-api
API que representa um fórum com sistema de perfis/autenticação por Bearer Token desenvolvida no curso de Spring Rest API da Alura. Foi documentada com Swagger e realizado métricas utilizando Spring Admin 

Basicamente o fórum tem os cursos que tem CRUD gerenciado por perfil Admin, os tópicos e respostas de tópicos que são abertos por estudantes e cada estudante só pode gerenciar os próprios tópicos

## Tecnologias utilizadas

![](https://img.shields.io/static/v1?label=Spring&message=Spring%20boot&color=brightgreen&style=for-the-badge&logo=Springboot)
![](https://img.shields.io/static/v1?label=Spring&message=Spring%20data%20jpa&color=inactive&style=for-the-badge)
![](https://img.shields.io/static/v1?label=Spring&message=Spring%20web&color=inactive&style=for-the-badge)
![](https://img.shields.io/static/v1?label=Spring&message=Spring%20security&color=inactive&style=for-the-badge)
![](https://img.shields.io/static/v1?label=Spring&message=Spring%20actuator&color=inactive&style=for-the-badge)
![](https://img.shields.io/static/v1?label=Spring&message=Spring%20cache&color=inactive&style=for-the-badge)
![](https://img.shields.io/static/v1?label=Spring&message=Spring%20doc&color=inactive&style=for-the-badge)
![](https://img.shields.io/static/v1?label=Java&message=JUnit%205&color=inactive&style=for-the-badge)
![](https://img.shields.io/static/v1?label=SQL&message=Maria%20DB&color=orange&style=for-the-badge&logo=MariaDb)

## O que eu aprendi

- Uso de métricas de servidor do Spring Actuator, ele exibe dados importantes quando integrado ao Spring Admin (ferramenta que exibe os dados em interface gráfica)
- Autenticação e autorização por Token Bearer
- Criação de perfis e suas funções nos Cruds
- Uso do Postman pra fazer requisições sem uma interface gráfica
- Criação de interceptadores de Status de Erro (400, 403, 401, 404, 405, 500...)
- Criptografia de Senhas e Tokens
- Criação de Specificators para fazer query de parâmetros de dados (ex: ...topicos?id>20;titulo:alura)
- Recurso de paginação de dados no Spring Data
- Testes automatizados utilizando JUNIT 5
- Cache de dados 

## Fotos da documentação gerada pelo SpringDoc 

![Página do Pedido](/screenshots/swagger.png "Documentacao")
![Bearer](/screenshots/auth.png "Bearer")
