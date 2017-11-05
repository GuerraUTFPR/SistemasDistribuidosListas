#!/usr/bin/env python
# -*- coding: utf-8 -*-


# Descrição: este código implementa um servidor RPC, tais como os métodos definido na interface .proto
# Autor: Matheus Sapia Guerra
# Data de criação: 31/10/2017



from concurrent import futures
import time

import grpc

import agenda_pb2
import agenda_pb2_grpc

_ONE_DAY_IN_SECONDS = 60 * 60 * 24


#class contato
class Contato(object):
	"""Contato"""
	def __init__(self, nome, telefone):
		#super(Contato, self).__init__()
		self.nome = nome
		self.telefone = telefone
		


# implementação da interface Agedna
class Agenda(agenda_pb2_grpc.AgendaServicer):
	ArrayContato = []

	def AddContato(self, request, context):
		c = Contato(str(request.nome), str(request.telefone))
		self.ArrayContato.append(c)
		return agenda_pb2.Msg(mensagem = "Contato adcionado\n" + request.nome + "\n" + request.telefone)

	def RmContato(self, request, context):
		c = Contato(str(request.nome), str(request.telefone))

		for i in range(len(self.ArrayContato)):
			if(self.ArrayContato[i].telefone == c.telefone):
				self.ArrayContato.pop(i)
				return agenda_pb2.Msg(mensagem = "Contato removido\n" + request.nome + "\n" + request.telefone)
			else:	
				return agenda_pb2.Msg(mensagem = "Contato não removido\n")

	def ListContato(self, request, context):
		return ArrayContato


def serve():
	server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
	agenda_pb2_grpc.add_AgendaServicer_to_server(Agenda(), server)
	server.add_insecure_port('[::]:50051')
	server.start()
	try:
		while True:
			time.sleep(_ONE_DAY_IN_SECONDS)
	except KeyboardInterrupt:
		server.stop(0)

if __name__ == '__main__':
	serve()
