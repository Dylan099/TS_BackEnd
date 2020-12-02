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
        sql = 'SELECT breathing_problem,fever,dry_cough,sore_throat,running_se,asthma,chronic_lung_disease,headache,heart_disease,diabetes,hyper_tension,fatigue,gastrointestinal,abroad_travel,contact_with_covid_patient,attended_large_gathering,visited_public_exposed_places,family_working_in_public_exposed_places,wearing_masks,sanitization_from_market FROM dataset'
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