/*
BCC36C - Sistemas Distribuídos
Departamento da Computação - DACOM
Universidade Tecnológia Federal do Paraná - UTFPR
Professor: Prof. Dr. Rodrigo Campiolo
Aluno: Matheus Sapia Guerra
*/

Como compilar:
	1. Importar o arquivo AgendaRemota.zip no NetBeans.

	2. Construir o projeto.

	3. Abrir três instancias do terminal, e ir para o diretorio <local onde esta o projeto netbenas>/AgendaRemota/build/classes.
		3.1 No primeiro terminal dar o comando:
			rmiregistry
		3.2 No segundo terminal, inicializar o servidor:
			java -Djava.security.policy=policy.txt -Djava.rmi.server.codebase=file:. Servidor
		3.3 No terceiro terminal, inicilizar o cliente
			java -Djava.security.policy=policy.txt -Djava.rmi.server.codebase=file:. AgendaGUI

Como utilizar:
	1 Após iniciar o cliente, uma interface gráfica aparecerá, contendo os seguintes botões:
		1.1 Login:
			Este botão faz o login do cliente, procura pelo serviço no Binder e lista os compromissos(caso houver).
		2.2 Logout:
			Este botão limpa a interface gráfica.
		2.3 Adicionar:
			Esse botão abre uma nova janela que possui campos para adicionar um novo compromisso.
		2.4 Alterar:
			Para alterar um compromisso, é necessário dar um duplo clique sobre o atributo do compromisso a ser alterado e após clicar no botão "Alterar"
		2.5 Remover:
			Selecionar um registro da tabela e clicar em remover consiste que o mesmo seja apagado.
		2.6 Listar:
			Ao clicar no botão Listar, uma nova janela será aberta, esta deve se colocar a data que se deseja listar ou deixar em branco para listar todos os compromissos.

	2. O Servidor fará um log(sem persistencia) de todas as ações tomada pelo cliente.

Bibliotecas usadas:	
	1. import java.rmi.registry.Registry;
	2. import java.rmi.registry.LocateRegistry;
	3. import java.io.Serializable;
	4. import java.rmi.Remote;
	5. import java.rmi.RemoteException;
	6. import java.rmi.server.UnicastRemoteObject;

