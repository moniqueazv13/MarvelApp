# The Marvel Journey App

O aplicativo marvel journey app é o primeiro (esperamos que de muitos) aplicativos desenvolvidos pelos recém formados desenvolvedores android Monique, Jean, Aline e Leonardo


O aplicativo traz os detalhes de comics e heróis da Marvel e permite que o usuário busque por heróis de sua preferência. Além disso, o usuário pode também testar seus conhecimentos sobre o mundo Marvel por um Quiz, que traz perguntas sobre os principais heróis da Marvel. O aplicativo também pode redirecionar os usuários para o site da API Marvel, para que desenvolvedores possam desbravar os recursos disponíveis no site para criar seus próprios apps.

------------
<br>

#### Alguns Screenshots
<img src="https://github.com/moniqueazv13/The-Marvel-Journey/blob/develop/app/src/main/res/drawable-v24/screenshot_a.png" width="260">   <img src="https://github.com/moniqueazv13/The-Marvel-Journey/blob/develop/app/src/main/res/drawable-v24/screenshot_b.png" width="260">

<img src="https://github.com/moniqueazv13/The-Marvel-Journey/blob/develop/app/src/main/res/drawable-v24/screenshot_c.png" width="260">   <img src="https://github.com/moniqueazv13/The-Marvel-Journey/blob/develop/app/src/main/res/drawable-v24/screenshot_d.png" width="260">


------------
<br>

#### API

A API utilizada é a da Marvel, disponível em: https://developer.marvel.com/

------------
<br>

#### Arquitetura

A arquitetura utilizada é a MVVM

------------
<br>


#### Principais conceitos utilizados

**Realtime Database:** 




**Login com Firebase:** Uso da plataforma do Firebase para realização de login com e-mail e senha sem a necessidade de usar redes sociais

**Login com Redes Sociais:** Conexão com bibliotecas do Facebook e Google para permitir o usuário logar no aplicativo com o uso de suas redes sociais. A cada login a informação do usuário é armazenada no Firebase

**Uso de Activities e Fragments:** Para permitir a criação de um design adequado ao uso do aplicativo

**TabLayout:** Para facilitar a navegação no aplicativo. Recurso usado em aplicativos de grande utilização como o Whatsapp

**Dialog Fragment Customizado:** Fragment usado para interação com o usuário no Quiz, permitindo uma identificação visual mais customizada com o tema do app

**RecyclerView:** Recurso amplamente disseminado em aplicativos, como o facebook e ifood. No app permite a visualização mais adequada dos comics e heróis da Marvel

**Material Design:** Uso de cores e fontes que possuem harmonização adequada, gerando uma identificação visual com o universo Marvel.

------------
<br>

#### O que gostaríamos de ter feito?

**Clean Code e Clean Architecture:** Facilitaria a manutenção do código e traria estabilidade para a aplicação

**Pontuação Dinâmica do Quiz:** Permitiria o usuário saber sua pontuação em tempo real

**Paginação do RecyclerView:** Possibilitaria inflar no recyclerview do app todos os comics e heróis disponíveis na API da Marvel 

**Auto Complete no Search:** Facilitaria a busca de heróis e comics pelo usuário;
