# BlackJack - Jogo 21
## Desafio da matéria de Programação para Dispositivos Móveis, usando Android Studio em Java.
  - O objetivo era desenvolver do zero o jogo 21 (BlackJack)

### Descrição da atividade:

>Quando iniciado o aplicativo deve ir para a Tela de Login. Na tela de login deve ter um campo para digitar o nome do usuário e um campo para digitar a senha. Ambos devem utilizar hints (dicas) para facilitar a interação humano computador. O campo senha deve esconder o que o usuário está digitando. Ponto extra: Checkbox "mostrar senha", ativada mostra o conteúdo do campo senha, desativada esconde o conteúdo do campo senha.

>A Tela de Login também deve ter 2 botões, 1 de logar, e um de cadastro. Se clicar em logar deve comparar com os valores de usuário e senha com os usuários cadastrados, se algum for valido deve legar o usuário para a Tela de Jogo, caso não sejam validos deve apresentar uma mensagem na tela com o texto "Nome de usuário ou senha invalido". Caso aperte no botão de cadastro deve levar o usuário para a Tela de Cadastro.

>Na Tela de Cadastro Deve ser preenchido o nome de usuário, senha, nome completo e e-mail. A tela de cadastro deve ter um botão concluir, que verifica se todos os dados foram preenchidos, e no caso positivo retorna para a tela de login (deve retornar as informações do usuário para que ele seja adicionado a lista de usuários, e possa ser utilizado para login). Caso os campos não tenham sido preenchidos corretamente deve exibir uma mensagem para cada campo não preenchido corretamente. Ponto extra: Deixar o campo não preenchido corretamente com bordas em vermelho.

>A Tela de Jogo deve possuir uma Toolbar com o nome do usuário no titulo, e uma opção de menu de logout (com imagem coerente, e implementação para retornar a tela de login). A Tela de Jogo deve apresentar um jogo de 21 (Black Jack) simplificado. O jogo vai ser sempre o usuário contra o computador. O usuário deve ter as opções de pedir carta ou parar (não deve implementar a opção de dividir as cartas ou de apostas). Após a rodada do usuário (caso ele não tenha ultrapassado 21) o computador deve jogar usando as regras da casa. Ao fim da rodada deve ser informado o vencedor, e uma nova rodada deve ser iniciada. Um contador de vitórias e derrotas deve ser mantido na tela. Ao fazer o logout as informações de vitórias e derrotas devem ser retornadas para serem salvas com as informações do usuário (um novo login deste usuário deve manter a persistência nestas informações).

>As cartas do jogador e do computador devem ser geradas de forma aleatória e podem ser apresentadas de forma textual (A, 2, 3, 4, 5, 6, 7, 8, 9, J, Q, K). Ponto extra: Apresentar as cartas com imagem das cartas de baralho.

### Demonstração: 

![](https://raw.githubusercontent.com/vanessakoch/BlackJack/master/app/src/main/res/drawable/telaBlackJack/videoBlackJack.gif)

![](https://raw.githubusercontent.com/vanessakoch/BlackJack/master/app/src/main/res/drawable/telaBlackJack/tela1.png)
![](https://raw.githubusercontent.com/vanessakoch/BlackJack/master/app/src/main/res/drawable/telaBlackJack/tela2.png)
![](https://raw.githubusercontent.com/vanessakoch/BlackJack/master/app/src/main/res/drawable/telaBlackJack/tela3.png)
