import numpy as np
import pandas as pd
import pymysql
import socket

from mypackage.Prediccion import Prediccion
from mypackage.DataBase  import DataBase
from mypackage.NeuralLayer import NeuralLayer

def bytes_to_int(bytes):
    result = 0
    for b in bytes:
        result = result * 256 + int(b)
    print(result)
    return result

def recibirTodo(sock):
    global myprediccion
    buff_size=1

    myprediccion = Prediccion(
        bytes_to_int( sock.recv(buff_size)),
        bytes_to_int( sock.recv(buff_size)),
        bytes_to_int( sock.recv(buff_size)),
        bytes_to_int( sock.recv(buff_size)),
        bytes_to_int( sock.recv(buff_size)),
        bytes_to_int( sock.recv(buff_size)),
        bytes_to_int( sock.recv(buff_size)),
        bytes_to_int( sock.recv(buff_size)),
        bytes_to_int( sock.recv(buff_size)),
        bytes_to_int( sock.recv(buff_size)),
        bytes_to_int( sock.recv(buff_size)),
        bytes_to_int( sock.recv(buff_size)),
        bytes_to_int( sock.recv(buff_size)),
        bytes_to_int( sock.recv(buff_size)),
        bytes_to_int( sock.recv(buff_size)),
        bytes_to_int( sock.recv(buff_size)),
        bytes_to_int( sock.recv(buff_size)),
        bytes_to_int( sock.recv(buff_size)),
        bytes_to_int( sock.recv(buff_size)),
        bytes_to_int( sock.recv(buff_size)))
    print("recibido")

def prediccion_paciente():
    print("prediccion_paciente")
    ret = myprediccion.predecir()
    print(ret)
    return ret



mi_socket = socket.socket()
mi_socket.bind (('localhost',500))
mi_socket.listen(50)
while True:
	conexion, direccion=mi_socket.accept()
	print ("Conexion hecha")
	recibirTodo(conexion)
	mensajeSalida, R = prediccion_paciente()
	print ("mensajeSalida ..........",mensajeSalida)
	conexion.send(bytes(mensajeSalida,'UTF-8'))
	conexion.send(bytes(str(R),'UTF-8'))
	conexion.close()

#No borrar los print!!!!!!!!!!!!!!!!!
