# Descrição: Este projeto tem por objetivo implementar uma agenda usando RPC
# Autor: Matheus Sapia Guerra
# Data de criação: 31/10/2017
# Trabalho de RPC da disciplina de Sistemas Distribuidos
# Prof Dr. Rodrigo Campiolo

Antes de tudo é nessário baixar as bibliotecas:
	pip install grpcio
	pip install grpcio-tools

1. Como compilar:
	O projeto da forma como foi enviado, já tem os arquivos agenda_pb2.py e agenda_pb2_grpc.py gerados. Caso não tenha, é necessário compilar o agenda.proto como o seguinte comando:
		# python -m grpc_tools.protoc  -I . --python_out=. --grpc_python_out=. agenda.proto

2. Como executar:
	Para executar o projeto, é necessário seguir os seguintes passos:
	2.1 Abrir um terminal e inicializar o servidor com o comando:
		$ python agenda_server.py

	2.2 Abrir outro terminal e iniciar o cliente:
		$ python agenda_cliente.py

3. Bibliotecas usadas:
	from concurrent import futures
	import time
	import grpc
	import agenda_pb2
	import agenda_pb2_grpc
	from __future__ import print_function

4. Observações:
	4.1 O servidor só tem implementado o adicionar e remover contato, faltou a listagem de contatos.

	4.2 A adição e remoção de contatos está acontecendo de forma estática (linhas 19 a 22 do arquivo agenda_cliente.py)
