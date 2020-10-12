import numpy as np
import pandas as pd
import pymysql
import socket

from mypackage.Prediccion import Prediccion
from mypackage.DataBase  import DataBase
from mypackage.NeuralLayer import NeuralLayer

def recibirTodo(sock):
    global myprediccion
    buff_size=10096
    myprediccion = Prediccion(sock.recv(buff_size),sock.recv(buff_size),sock.recv(buff_size),sock.recv(buff_size),sock.recv(buff_size),sock.recv(buff_size),sock.recv(buff_size),sock.recv(buff_size),sock.recv(buff_size),sock.recv(buff_size),sock.recv(buff_size),sock.recv(buff_size),sock.recv(buff_size),sock.recv(buff_size),sock.recv(buff_size),sock.recv(buff_size),sock.recv(buff_size),sock.recv(buff_size),sock.recv(buff_size),sock.recv(buff_size))

def prediccion_paciente():
    ret = myprediccion.predecir()
    print (ret)
    return ret



mi_socket = socket.socket()
mi_socket.bind (('localhost',500))
mi_socket.listen(5)
while True:
	conexion, direccion=mi_socket.accept()
	print ("Conexion hecha")
	recibirTodo(conexion)
	mensajeSalida=prediccion_paciente()
	conexion.send(bytes(mensajeSalida,'UTF-8'))
	conexion.close()


#No borrar los print!!!!!!!!!!!!!!!!!
