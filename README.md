# Entertain-Guide
Acesse seus filmes, séries e jogos favoritos. Crie listas de filmes e series já assistidas ou jogos zerados. Também é possível criar lista de filmes e series para assistirem depois ou jogos para se jogarem depois.
Foi usado Koin, Glide, Retrofit, MVVM, Clean Architecture, SharedPreferences, Feature Toggle e a Api TMDB

## Koin

Koin é um injetor de dependências para Android. Ajudando gerenciar as dependências de um app de forma simples e eficiente.

## Glide

Glide é uma biblioteca de carregamento de imagens para Android. Ajudando a carregar imagens de forma rápida e eficiente.

## Retrofit

Retrofit é uma biblioteca para fazer chamadas HTTP para APIs REST. Ajudando a fazer chamadas HTTP de forma simples e eficiente.

## MVVM

MVVM é um padrão de arquitetura para Android. Onde se separa a lógica da interface do usuário da lógica de negócios.

## Clean Architecture

Clean Architecture é um padrão de arquitetura de software. Ajudando a criar apps mais robustos e testáveis.

## SharedPreferences
SharedPreferences são um mecanismo para armazenar pequenos conjuntos de dados, como preferências do usuário ou configurações do aplicativo, no formato chave-valor. Elas oferecem acesso rápido e são úteis para armazenar informações simples e persistentes entre diferentes sessões do aplicativo, 
sendo fácil salvar e recuperar dados pequenos, porém não são recomendadas para dados sensíveis ou grandes volumes de informações.

## Feature Toggle
É uma técnica que oferece flexibilidade e controle sobre funcionalidades do aplicativo, permitindo ativação/desativação remota de recursos, facilitando testes, experimentos e gerenciamento de riscos durante o ciclo de vida do software.

## Api TMDB
Api que disponibiliza lista de filmes, TV, atores e imagens.

# A ideia inicial
A ideia inicial era listar filmes, seriés e jogos, criando listas. O problema a ser selecionado é que normalmente seria necessário baixar 2 apps, para filmes/series e outro para jogos. Então a solução seria no app ter disponível jogo, series e filmes.

# O que foi implementado
- Foi implementado uma lista de filmes mais avaliados, mais populares e now playing.
- Tela de login e cadastro usando Authentication do Firebase.
- Componente HeaderView.
- Componente CardView.
- Componente TabBar.

# O que não foi implementado
- Lista de series e jogos. Porém, já foi criado a api, repository e business das series.
- A lista de series vistas, jogos zerados e filmes assistidos. Porém, já deixei uma RecyclerView para listar e uma verificação se o usuário está logado.
- Não foi utilizado a feature toggle para desligar e ligar, por exemplo, a lista de jogos ou filmes. Porém, já esta configurado no projeto.
- Login usando o Google. Porém, a maior parte está implementada.
- Usuário poder comentar dentro de um filme especifico e avaliá-lo.Porém, já foi adicionado os ícones e seus respectivos listeners.
- Implementar tela de erro.
- Acessibilidade.
- Teste unitário.
- Teste de snapshot.

# Futuras feature
Além de implementar o que falta gostaria de:
- Implementar Dark Mode
- Especficar as Exceptions
- Banco de Dados para salvar as listas, podendo assim o usuário fazer sync em outro app.
- Notificações.
- Implementar animações

<img src="https://github.com/athenasaran/Entertain-Guide/assets/23267293/29b5d17f-cb8e-44c2-a326-6d3e4d33e9d0" alt="Descrição da imagem" width="500" height="900">

<img src="https://github.com/athenasaran/Entertain-Guide/assets/23267293/071bab66-64ac-4e09-9314-dece02c349eb" alt="Descrição da imagem" width="500" height="900">

<img src="https://github.com/athenasaran/Entertain-Guide/assets/23267293/966e9f70-adf3-4549-8027-e5340571b52c" alt="Descrição da imagem" width="500" height="900">

<img src="https://github.com/athenasaran/Entertain-Guide/assets/23267293/69052c72-b657-4931-bea4-75db165f0c6a" alt="Descrição da imagem" width="500" height="900">

<img src="https://github.com/athenasaran/Entertain-Guide/assets/23267293/767372f0-49ca-4617-affc-41804dd6941b" alt="Descrição da imagem" width="500" height="900">

