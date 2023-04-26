<span style="color:red">ATENÇÃO: NO FINAL DO TRABALHO, PRECISAMOS RENOMEAR OS AUTORES DE CADA CÓDIGO PARA O PROFESSOR PODER AVALIAR. EU DELETEI O NOME DO AUTOR DOS QUE EU(JÚLIA) FIZ, PARA DEIXARMOS PARA FAZER NO FINAL DE TUDO.</span>



<img src="https://github.com/igoralmeidadasilva/MaisSaudePlus/blob/main/src/maissaudeplus/view/images/logo1.jpg" alt="Logotipo" width="200px" height="200px">



# Mais Saúde Plus
Grupo: Igor Almeida, Júlia Borges e Raphael Pavani

### Instruções
Este projeto ainda está **incompleto** e necessita de algumas alterações feitas pelo grupo, como por exemplo, nenhuma classe ou DAO está preenchido. Abaixo listarei algumas instruções de implementação para serem feitas.

* A criação do banco de dados está presente dentro do arquivo "script.sql". Nele você encontrará todo o script de criação do banco de dados com as seguintes alterações feitas por mim:
    * Mudei o tipo dos códigos (cod_paciente, cod_medico, etc) para SERIAL, uma vez que desta forma eles serão auto incrementados pelo Postgres.
    * Adicionei, inicialmente, 3 inserções de médicos e 5 inserções de pacientes (no futuro serão acrescentadas mais inserções em outras tabelas) com nomes e dados fictícios, afim de popular o banco para testes e para a apresentação do trabalho. Peço que o restante do grupo também adicione algumas inserções para que existam mais dados no banco.
    * Também criei um usuário para utilizar o banco de dados no lugar do usuário "postgres". A query de criação deste usuário, assim como as permissões dele, estão presentes no "script.sql".
* Para acessar o banco de dados pelo programa, coloquei os dados (URL, usuário e senha) em um arquivo chamado conf.properties dentro do diretório \src\maissaudeplus\propriedades. Fiz isso com a intenção de deixar o trabalho um pouco mais profissional, uma vez que deixar esses dados no código não é uma boa prática de programação. Cuidando e adotando esse método, o professor pode conceder pontos extras.
* Como neste trabalho existiram dois perfis de usuários, resolvi separar em subdiretórios alguns arquivos referentes a esses perfis. Logo, dentro da pasta "view" existe o diretório "funcionario" e "medico", assim como no diretório "controller" também existem as pastas "funcionario" e "medico".
* Dentro do código fonte, tentei comentar de forma breve e direta o código.
* Dentro do projeto no diretório "libs", adicionei todas as bibliotecas para o software funcionar no estado atual e também bibliotecas para o funcionamento futuro
* Dentro do diretório "view" está o arquivo "style.css". Caso queira fazer alguma alteração no estilo adotado, peço que utilize esse arquivo.
* Dentro do diretório "view/funcionario" existe o arquivo "FXMLAnchorPaneFuncionarioAgendarConsulta.fxml"(favor seguir a mesma nomenclatura), este arquivo ainda está incompleto, sugiro que o compiem e usem como base para a construção de outros fxml uma vez que neste arquivo eu já coloquei todas as configurações (muitas e insuportaveis) para redimensionalizar a tela.
* Eu sei que alguns ícones não estão bons, alguns até mesmo estão com baixa qualidade. Porém, por motivos de padronização e falta de tempo, sugiro que essa mudança, assim como outras menores, sejam adotadas no fim do projeto.
* Na tela inicial de seleção de perfis exite uma opção ainda não implementada oferendo ajuda, está opção está ai poís o professor de Engenharia de Software havia comentado dela, porém ela não é obrigatoria e pode ser facilmente excluida, pensem nela como uma sugestão.


Sem mais, estou à disposição para qualquer dúvida.
        





