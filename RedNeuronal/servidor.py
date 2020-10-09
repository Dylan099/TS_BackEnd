import socket
from mypackage.Prediccion import Prediccion

def recibirTodo(sock):
    global myprediccion
    buff_size=10096
    myprediccion = Prediccion(sock.recv(buff_size),sock.recv(buff_size),sock.recv(buff_size),sock.recv(buff_size),sock.recv(buff_size),sock.recv(buff_size),sock.recv(buff_size),sock.recv(buff_size),sock.recv(buff_size),sock.recv(buff_size),sock.recv(buff_size),sock.recv(buff_size),sock.recv(buff_size),sock.recv(buff_size),sock.recv(buff_size),sock.recv(buff_size),sock.recv(buff_size),sock.recv(buff_size),sock.recv(buff_size),sock.recv(buff_size))

def prediccion_paciente():
    ret = myprediccion.predecir()
    print (ret)
    return ret



mi_socket = socket.socket()
mi_socket.bind (('localhost',50))
mi_socket.listen(5)
while True:
	conexion, direccion=mi_socket.accept()
	print ("Conexion hecha")
	recibirTodo(conexion)
	mensajeSalida=prediccion_paciente()
	conexion.send(bytes(mensajeSalida,'UTF-8'))
	conexion.close()


#No borrar los print!!!!!!!!!!!!!!!!!
