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
    
    def predecir(self):
        si="si"
        print(self.FamilyWorkingInPublicExposedPlaces.decode())
        return si
