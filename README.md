# Moments
Moments é um aplicativo projetado para permitir que os usuários registrem e revisitem momentos especiais de suas vidas.

## Como funciona
O aplicativo é um diário de memórias onde cada memória possui uma imagem, um título, uma descrição e uma data.

A aplicação possui uma entidade chamada `Memory` que é a memória que o usuário vai registrar e uma outra entidade chamada `Tag` que são etiquetas que identificam uma Memory.

Esses dados são salvos em um banco de dados SQLite. 

O acesso ao banco de dados é feito ao iniciar a aplicação, lendo os dados de Memory e Tag e salvando em listas, todas as manipulações são feitas nas listas e ao finalizar a aplicação os dados são salvos no banco.

## Intefaces de Usuário
As interfaces podem ser encontradas aqui: [UI Figma](https://www.figma.com/file/8aXJch8VFjRCc2jfOBUZuH/Moments---Memory-Journal?type=design&node-id=0%3A1&mode=design&t=tM5XUphNjPb5yg7f-1)

## Como rodar o projeto
1. **Clone o Repositório:**
   ```bash
   git clone https://github.com/Jardeu/moments.git

2. **Abra o Projeto no Android Studio**
   - Abra o Android Studio.
   - Selecione "Open an existing Android Studio project".
   - Navegue até a pasta onde você clonou o repositório e selecione a pasta do projeto.

3. **Conecte um dispositivo Android via USB ou configure um emulador no Android Studio**
4. **Build e Execute o Projeto**
   - Aguarde o Android Studio sincronizar o projeto.
   - Clique no botão "Run" (ícone de play) na barra de ferramentas

5. **Aguarde a instalação do aplicativo no dispositivo ou emulador**
