# Mais Saúde Plus
Grupo: Igor Almeida, Júlia Borges, Matheus e Raphael Pavani

O programa foi feito sob medida para a clínica Mais Saúde+, um consultório fechado com necessidade de soluções organizacionais, para cadastro de médicos, pacientes e consultas e a realização das mesmas.

O software possui 2 tipos de usuário: Médico e Funcionário, cada um com funções que interagem entre si.

Como os nomes sugerem, Funcionário é o usuário genérico responsável pelo cadastro de Médicos, Pacientes e as Consultas.
Médicos conseguem ler os relatórios e consultar todos os gráficos disponíveis, bem como realizar as consultas marcadas.

### Instruções

* O modo de funcionamento do consultório Mais Saúde+ indicam a necessidade de apenas 2 usuários para o software: Médico e Funcionário. E por se tratar de um consultório local e de risco nulo, é aceitável que os usuários não tenham senhas e login ou qualquer tipo de controle de acesso.
* A tela inicial do software pede que o usuário escolha um dos doi tipos de conta, mas o tipo de acesso pode ser alterado após a escolha.
* Ao selecionar o tipo de usuário, o programa apresenta a tela inicial do Mais Saúde News, com notícias recentes do mundo da medicina, e oferece botões com as funções exclusivas de cada usuário.
* Ambos usuários podem consultar dados de consultas, usuários e relatórios.
* Caso queira Trocar de Conta por qualquer motivo, há um botão que o permite fazê-lo rapidamente, e ao terminar o uso o programa pode ser rapidamente encerrado com o botão Sair.

### Instruções: Funcionário

* A principal função do Funcionário é o cadastro.
* Eles podem cadastrar médicos, pacientes e, usando os cadastros existentes, agendar consultas.
* Podem acessar e alterar dados de médicos, pacientes e consultas que já estão registradas.

### Instruções: Médico

* A principal função do Médico é a realização da consulta.
* Médicos podem acessar consultas agendadas e atribuir a elas procedimentos e medicamentos, declarando sua conclusão.
* Consultas precisam ser agendadas com antecedencia para serem realizadas, e todo agendamento precisa de um médico a ele associado.

### Making Of

* O sql da criação do banco de dados é o arquivo "script.sql". Nele se encontra todo o script de criação do banco de dados com as seguintes alterações:
	* O tipo dos códigos (cod_paciente, cod_medico, etc) é SERIAL, pois serão auto incrementados pelo Postgres.
	* Um usuário para uso do BD foi criado e a query de criação deste usuário, assim como as permissões dele, estão presentes no "script.sql".
	* Para acessar o banco de dados pelo programa os dados (URL, usuário e senha) foram colocados em um arquivo chamado conf.properties dentro do diretório \src\maissaudeplus\propriedades.
	* Como existem dois perfis de usuários, foram separados em subdiretórios alguns arquivos referentes a esses perfis. Logo, por exemplo, dentro da pasta "view" existem os diretórios "funcionario" e "medico", assim como no diretório "controller" também existem as pastas "funcionario" e "medico".
* Dentro do projeto no diretório "libs" estão todas as bibliotecas para o software no estado atual, contendo todas suas funcionalidades.
* Dentro do diretório "view" está o arquivo "style.css". Caso queira fazer alguma alteração no estilo adotado, pode se utilizar esse arquivo.
* Dentro do diretório "view/funcionario" existe o arquivo "FXMLAnchorPaneFuncionarioAgendarConsulta.fxml", que foi utilizado como template para a criação das outras telas semelhantes, principalmente suas configurações de tela e redimensionamento.