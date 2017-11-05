#!/usr/bin/env python
# -*- coding: utf-8 -*-

# Descrição: este código implementa um cliente, fazendo chamades de metodos remotamente
# Autor: Matheus Sapia Guerra
# Data de criação: 31/10/2017


from __future__ import print_function
import grpc

import agenda_pb2
import agenda_pb2_grpc

def run():
  channel = grpc.insecure_channel('localhost:50051')
  stub = agenda_pb2_grpc.AgendaStub(channel)

  response = stub.AddContato(agenda_pb2.Contato(nome='yudi',telefone='123456'))
  print("Servidor: \n" + response.mensagem)
  response = stub.RmContato(agenda_pb2.Contato(nome='yudi',telefone='123456'))
  print("Servidor: \n" + response.mensagem)


if __name__ == '__main__':
  run()
