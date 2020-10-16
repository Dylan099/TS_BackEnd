import numpy as np

#Clase de la capa de la red
class NeuralLayer():
  def __init__(self, m_conexiones, n_neuronas, funcion_activacion):
    self.funcion_activacion=funcion_activacion
    self.b = np.random.rand(1,n_neuronas)*2 -1
    self.w =np.random.rand(m_conexiones,n_neuronas )*2 -1
