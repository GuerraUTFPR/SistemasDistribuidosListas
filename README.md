# Lista 3 de Sistemas Distribuidos (Comunicação TCP)

1) Modificar o código para que o cliente possa enviar mensagens (ler o que foi digitado) e o servidor envia a mensagem 'RECEBIDO'. Quando o cliente enviar 'SAIR',  o servidor vai enviar 'ACKSAIR' e finalizar a thread. Use o TCP.

2) Fazer um código para o Cliente e Servidor se comunicarem. O cliente envia e recebe mensagens. O servidor envia e recebe mensagens. Quando algum dos dois enviar 'SAIR', a comunicação entre eles deve ser finalizada. Use o TCP.


3) Faça uma interface gráfica para os clientes de chat e que possibilite vários clientes enviarem e visualizarem mensagens de os outros clientes conectados. Use o TCP.

4)  Faça um servidor para processar as seguintes mensagens dos clientes. O servidor deve suportar mensagens de múltiplos clientes. Use o TCP.
As mensagens estão no formato String UTF:
TIME
* Retorna a hora do sistema como uma String UTF no formato HH:MM:SS
DATE
* Retorna a data do sistema como uma String UTF no formato DD/MM/AAAA
FILES
* Retorna os arquivos da pasta definida por padrao (p. ex. /home/user/shared)
* retorna um inteiro indicando o número de arquivos
* envia o nome de um arquivo por vez como uma String UTF
DOWN nome-arquivo
* Faz o download do arquivo nome-arquivo
* retorna 0 se nome não existe ou retorna o tamanho do arquivo
* lê o número de bytes indicado por tamanho do arquivo e grava em um diretório padrão
EXIT
* Finaliza a conexão

5) Fazer uma aplicação com um servidor que gerencia a comunicação entre dois clientes usando TCP. Para cada cliente é criada uma thread no servidor. A comunicação entre as threads deve usar recursos como pipe ou memória compartilhada.

##Lista 4 de Sistemas Distribuidos (Comunicação UDP)

1) Fazer o chat P2P usando UDP.

2) Fazer um sistema de upload de arquivos via UDP. Um servidor UDP deverá receber as partes dos arquivos (1024 bytes), verificar ao final a integridade via um checksum e armazenar o arquivo em uma pasta padrão.
