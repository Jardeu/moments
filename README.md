# Moments
Moments é um aplicativo projetado para permitir que os usuários registrem e revisitem momentos especiais de suas vidas.

## Como funciona
O aplicativo é um diário de memórias onde cada memória possui uma imagem, um título, uma descrição e uma data.

A aplicação possui uma entidade chamada `Memory` que é a memória que o usuário vai registrar e uma outra entidade chamada `Tag` que são etiquetas que identificam uma Memory.

Esses dados são salvos em um banco de dados SQLite. 

O acesso ao banco de dados é feito ao iniciar a aplicação, lendo os dados de Memory e Tag e salvando em listas, todas as manipulações são feitas nas listas e ao finalizar a aplicação os dados são salvos no banco.

## Intefaces
As interfaces podem ser encontradas aqui: [UI Figma](https://www.figma.com/file/8aXJch8VFjRCc2jfOBUZuH/Moments---Memory-Journal?type=design&node-id=0%3A1&mode=design&t=tM5XUphNjPb5yg7f-1)

## Como rodar o projeto
