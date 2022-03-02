# forum-alura-rest-api
API que representa um fórum com sistema de perfis/autenticação por Bearer Token desenvolvida no curso de Spring Rest API da Alura. Foi documentada com Swagger e realizado métricas utilizando Spring Admin 

Basicamente o fórum tem os cursos que tem CRUD gerenciado por perfil Admin, os tópicos e respostas de tópicos que são abertos por estudantes e cada estudante só pode gerenciar os próprios tópicos

## O que eu aprendi

- Uso de métricas de servidor do Spring Actuator, ele exibe dados importantes quando integrado ao Spring Admin (ferramenta que exibe os dados em interface gráfica)
- Autenticação e autorização por Token Bearer
- Criação de perfis e suas funções nos Cruds
- Uso do Postman pra fazer requisições sem uma interface gráfica
- Criação de interceptadores de Status de Erro (400, 403, 401, 404, 405, 500...)
- Criptografia de Senhas e Tokens
- Criação de Specificators para fazer query de parâmetros de dados (ex: ...topicos?id>20;titulo:alura)
- Recurso de paginação de dados no Spring Data

## Fotos da documentação gerada pelo SpringDoc 

![Página do Pedido](/screenshots/swagger.png "Documentacao")
![Bearer](/screenshots/auth.png "Bearer")
