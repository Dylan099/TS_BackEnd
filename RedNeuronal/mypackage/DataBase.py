import pymysql
import pandas as pd
import numpy as np


class DataBase:
    def __init__(self):
        self.connectiondb = pymysql.connect(
            host = 'localhost',
            user = 'root',
            password = '',
            db = 'covid'
        )

        self.cursor = self.connectiondb.cursor()
    
    def select_dataset_x(self):
        sql = 'SELECT BreathingProblem,Fever,DryCough,SoreThroat,Running0se,Asthma,ChronicLungDisease,Headache,HeartDisease,Diabetes,HyperTension,Fatigue,Gastrointestinal,AbroadTravel,ContactWithCovidPatient,AttendedLargeGathering,VisitedPublicExposedPlaces, FamilyWorkingInPublicExposedPlaces,WearingMasks,SanitizationFromMarket FROM dataset'
        ret = []
        try:
            self.cursor.execute(sql)
            listSymptoms = self.cursor.fetchall()
            ret = listSymptoms
        except Exception as e:
            raise
        return ret

    def select_dataset_y(self):
        sql = 'SELECT covid FROM dataset'
        ret = []
        try:
            self.cursor.execute(sql)
            listAnswer = self.cursor.fetchall()
            ret = listAnswer
        except Exception as e:
            raise 
        self.connectiondb.close() 
        return ret