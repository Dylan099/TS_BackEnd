import pymysql
import pandas as pd
import numpy as np

from mypackage.NeuralLayer import NeuralLayer
from mypackage.DataBase import DataBase

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
        #Forwrard pass
        for l, layer in enumerate (neural_net):   #para cada capa

            z = out[-1][1] @ neural_net[l].w + neural_net[l].b  #suma ponderada
            a = neural_net[l].funcion_activacion[0](z) #activacion    
            out.append((z , a))
        # -----------------------------------------
        if train:
            #Backpropagation pass
            deltas = []
            for l in reversed(range(0, len(neural_net))): #para cada capa
                z = out[l+1][0]
                a = out[l+1][1]
                

                if l == len(neural_net) - 1:
                    costo = l2_cost[1](a,Y)
                    deltas.insert(0, costo * neural_net[l].funcion_activacion[1](a))

                else:
                    deltas.insert(0, deltas[0] @ _w.T * neural_net[l].funcion_activacion[1](a))


                _w = neural_net[l].w
            
                #Actualizar a los gadientes
                b_temp =  neural_net[l].b - np.mean(deltas[0], axis=0, keepdims = True) * lr
                w_temp =  neural_net[l].w - np.array(out[l][1]).T @ deltas[0] * lr
                

                neural_net[l].b = b_temp
                neural_net[l].w = w_temp
                
        return out[-1][1]

        
    def creacion():
        global R
        #Parametros de la red
        n = 6221 #numero de datos en el dataset
        p = 20 #caracteristicas del dataset

        #Datos para el entrenamiento
        myDataBase = DataBase()
        listSymptoms = myDataBase.select_dataset_x()

        X = []
        for l in enumerate (listSymptoms):  
            l = np.array(l[1])
            X.append(l)
        X=  np.array(X)

        listAnswer =myDataBase.select_dataset_y()
        Y = []
        for l in enumerate (listAnswer):  
            Y.append(l[1])
        Y=  np.array(Y)


        #Funcion de activacion
        sigmoide = (lambda x: 1 / (1 + np.exp(-x)),   #funcion sigmoide
                    lambda x: x * (1 - x))            #derivada de la funcion sigmoide

        #Funcion de costos
        l2_cost = (lambda Yp, Yr: (np.mean(np.power((Yp - Yr),2))),
                lambda Yp, Yr: (Yp - Yr))

        #topology = [p,20,25,35,40,19,1]  #la salida debe ser uno

        topology = [p,1,1,1]  #la salida debe ser uno

        neural_net = Prediccion.crear_red_neuronal(topology,sigmoide)


        R = 0
        while R < 0.85:
            pY = Prediccion.train(neural_net, X, Y, l2_cost,0.05)
            R = round(1.000- l2_cost[0](pY, Y),4)
        print("R = ", R)
        
        return neural_net
                

    def predecir(self):


        datos = [[self.BreathingProblem, self.Fever, self.DryCough, self.SoreThroat, self.RunningNose, self.Asthma ,
                    self.ChronicLungDisease, self.Headache, self.HeartDisease, self.Diabetes, self.HyperTension, self.Fatigue ,
                    self.Gastrointestinal, self.AbroadTravel, self.ContactWithCovidPatient, self.AttendedLargeGathering, self.VisitedPublicExposedPlaces ,
                    self.FamilyWorkingInPublicExposedPlaces, self.WearingMasks, self.SanitizationFromMarket]]
        
        datos =  np.array(datos)

        neural_net= Prediccion.creacion()
        
        out = [(None, datos)]
        for l, layer in enumerate (neural_net):   #para cada 

            z = out[-1][1] @ neural_net[l].w + neural_net[l].b  #suma ponderada
            a = neural_net[l].funcion_activacion[0](z) #activacion    
            out.append((z , a))
        # -----------------------------------------
        #return round(a[0][0])
        respuesta = "no "
        if (round(a[0][0])) == 1:
            respuesta = "si "     
        return respuesta, R


#myprediccion = Prediccion(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0)
#ret = myprediccion.predecir()
#print (ret)