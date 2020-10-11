import numpy as np
import pandas as pd
import pymysql

from DataBase import DataBase
from NeuralLayer import NeuralLayer

class Prediccion:

    def __init__(self, BreathingProblem,Fever,DryCough,SoreThroat,RunningNose,Asthma,ChronicLungDisease,Headache,HeartDisease,Diabetes,HyperTension,Fatigue ,Gastrointestinal ,AbroadTravel,ContactWithCovidPatient,AttendedLargeGathering,VisitedPublicExposedPlaces,FamilyWorkingInPublicExposedPlaces,WearingMasks,SanitizationFromMarket):
        self.BreathingProblem =BreathingProblem
        self.Fever =Fever
        self.DryCough =DryCough
        self.SoreThroat =SoreThroat
        self.RunningNose =RunningNose
        self.Asthma =Asthma
        self.ChronicLungDisease =ChronicLungDisease
        self.Headache =Headache
        self.HeartDisease =HeartDisease
        self.Diabetes =Diabetes
        self.HyperTension =HyperTension
        self.Fatigue  =Fatigue 
        self.Gastrointestinal  =Gastrointestinal 
        self.AbroadTravel =AbroadTravel
        self.ContactWithCovidPatient =ContactWithCovidPatient
        self.AttendedLargeGathering =AttendedLargeGathering
        self.VisitedPublicExposedPlaces =VisitedPublicExposedPlaces
        self.FamilyWorkingInPublicExposedPlaces =FamilyWorkingInPublicExposedPlaces
        self.WearingMasks =WearingMasks
        self.SanitizationFromMarket =SanitizationFromMarket

    def crear_red_neuronal(topology,funcion_activacion):
        nn =[]  #contiene las capas de la red neuronal
        for l, layer in enumerate(topology[:-1]):
            myNeuralLayer = NeuralLayer (topology[l],topology[l+1],funcion_activacion)
            nn.append(myNeuralLayer)
        return nn


    def train(neural_net, X, Y, l2_cost, lr=0.5, train=True):
        out = [(None, X)]

        print("entro........................ Forwrard")

        #Forwrard pass
        for l, layer in enumerate (neural_net):   #para cada capa

            z = out[-1][1] @ neural_net[l].w + neural_net[l].b  #suma ponderada
            a = neural_net[l].funcion_activacion[0](z) #activacion    
            out.append((z , a))
        # -----------------------------------------
        
        print("salio........................ Forwrard")

        if train:
            print("entro........................ train")
            #Backpropagation pass
            deltas = []
            for l in reversed(range(0, len(neural_net))): #para cada capa
                print("entro........................ for")
                z = out[l+1][0]
                a = out[l+1][1]
                if l == len(neural_net) - 1:
                    print("entro ........................ if")
                    deltas.insert(0, l2_cost[1](a,Y) * neural_net[l].funcion_activacion[1](a))
                else:
                    print("entro ........................ else")
                    deltas.insert(0, deltas[0] @ _w.T * neural_net[l].funcion_activacion[1](a))
                
                print("salio........................ deltas")

                _w = neural_net[l].w

                #Actualizar a los gadientes
                neural_net[l].b = neural_net[l].b - np.mean(deltas[0], axis=0, keepdims = True) * lr
                neural_net[l].w = neural_net[l].w - out[l][1].T @ deltas[0] * lr
            
            print("salio........................ train")
  
        return out[-1][1]

        
    def creacion():
        #Parametros de la red
        n = 5434 #numero de datos en el dataset
        p = 19 #caracteristicas del dataset

        #Datos para el entrenamiento
        myDataBase = DataBase()
        listSymptoms = myDataBase.select_dataset_x()
        X = []
        for l in enumerate (listSymptoms):  
            l = np.array(l[1])
            X.append(l)
            
        listAnswer =myDataBase.select_dataset_y()

        Y = []
        for l in enumerate (listAnswer):  
            l = np.array (l[1], dtype = np.int64 )
            Y.append(l)
        Y = pd.Series(Y)

        #Funcion de activacion
        sigmoide = (lambda x: 1 / (1 + np.exp(-x)),   #funcion sigmoide
                    lambda x: x * (1 - x))            #derivada de la funcion sigmoide

        #Funcion de costos
        l2_cost = (lambda Yp, Yr: (np.mean(np.power((Yp - Yr),2))),
                lambda Yp, Yr: (Yp - Yr))

        topology = [p,20,25,35,40,19,1]  #la salida debe ser uno

        print("entro........................ crear_red_neuronal")

        neural_net = Prediccion.crear_red_neuronal(topology,sigmoide)

        print("salio........................ crear_red_neuronal")

        for i in range(2):
            pY = Prediccion.train(neural_net, X, Y, l2_cost,0.05)

        R = 1.000- l2_cost[0](pY, Y)
        print("R = ", R)
        
        return neural_net
                

    def predecir(self):

        datos = [[self.BreathingProblem ,self.Fever ,self.DryCough ,self.SoreThroat ,self.RunningNose ,self.Asthma ,self.ChronicLungDisease ,self.Headache ,self.HeartDisease ,self.Diabetes ,self.HyperTension ,self.Fatigue  ,self.Gastrointestinal  ,self.AbroadTravel ,self.ContactWithCovidPatient ,self.AttendedLargeGathering ,self.VisitedPublicExposedPlaces ,self.FamilyWorkingInPublicExposedPlaces ,self.WearingMasks ,self.SanitizationFromMarket]]
        datos =  np.array(datos)

        neural_net= Prediccion.creacion()

        print("salio........................ creacion")
        
        out = [(None, datos)]
        for l, layer in enumerate (neural_net):   #para cada capa
            z = out[-1][1] @ neural_net[l].w + neural_net[l].b  #suma ponderada
            a = neural_net[l].funcion_activacion[0](z) #activacion    
            out.append((z , a))
        # -----------------------------------------
        #return round(a[0][0])
        respuesta = "si"
        if (round(a[0][0])) == 0:
            respuesta = "no"       
        print(respuesta) 
        return respuesta


myprediccion = Prediccion(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0)
ret = myprediccion.predecir()
print (ret)
