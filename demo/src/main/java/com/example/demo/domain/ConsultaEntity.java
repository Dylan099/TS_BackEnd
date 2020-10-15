package com.example.demo.domain;

import javax.persistence.*;

@Entity
@Table(name = "consulta", schema = "tds", catalog = "")
public class ConsultaEntity {
    private int idConsulta;
    private String dateConsulta;
    private String breathingProblem;
    private String fever;
    private String dryCough;
    private String soreThroat;
    private String runningSe;
    private String asthma;
    private String chronicLungDisease;
    private String headache;
    private String heartDisease;
    private String diabetes;
    private String hyperTension;
    private String fatigue;
    private String gastrointestinal;
    private String abroadTravel;
    private String contactWithCovidPatient;
    private String attendedLargeGathering;
    private String visitedPublicExposedPlaces;
    private String familyWorkingInPublicExposedPlaces;
    private String wearingMasks;
    private String sanitizationFromMarket;
    private String covid;

    @Id
    @Column(name = "id_consulta")
    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    @Basic
    @Column(name = "date_consulta")
    public String getDateConsulta() {
        return dateConsulta;
    }

    public void setDateConsulta(String dateConsulta) {
        this.dateConsulta = dateConsulta;
    }

    @Basic
    @Column(name = "breathing_problem")
    public String getBreathingProblem() {
        return breathingProblem;
    }

    public void setBreathingProblem(String breathingProblem) {
        this.breathingProblem = breathingProblem;
    }

    @Basic
    @Column(name = "fever")
    public String getFever() {
        return fever;
    }

    public void setFever(String fever) {
        this.fever = fever;
    }

    @Basic
    @Column(name = "dry_cough")
    public String getDryCough() {
        return dryCough;
    }

    public void setDryCough(String dryCough) {
        this.dryCough = dryCough;
    }

    @Basic
    @Column(name = "sore_throat")
    public String getSoreThroat() {
        return soreThroat;
    }

    public void setSoreThroat(String soreThroat) {
        this.soreThroat = soreThroat;
    }

    @Basic
    @Column(name = "running_se")
    public String getRunningSe() {
        return runningSe;
    }

    public void setRunningSe(String runningSe) {
        this.runningSe = runningSe;
    }

    @Basic
    @Column(name = "asthma")
    public String getAsthma() {
        return asthma;
    }

    public void setAsthma(String asthma) {
        this.asthma = asthma;
    }

    @Basic
    @Column(name = "chronic_lung_disease")
    public String getChronicLungDisease() {
        return chronicLungDisease;
    }

    public void setChronicLungDisease(String chronicLungDisease) {
        this.chronicLungDisease = chronicLungDisease;
    }

    @Basic
    @Column(name = "headache")
    public String getHeadache() {
        return headache;
    }

    public void setHeadache(String headache) {
        this.headache = headache;
    }

    @Basic
    @Column(name = "heart_disease")
    public String getHeartDisease() {
        return heartDisease;
    }

    public void setHeartDisease(String heartDisease) {
        this.heartDisease = heartDisease;
    }

    @Basic
    @Column(name = "diabetes")
    public String getDiabetes() {
        return diabetes;
    }

    public void setDiabetes(String diabetes) {
        this.diabetes = diabetes;
    }

    @Basic
    @Column(name = "hyper_tension")
    public String getHyperTension() {
        return hyperTension;
    }

    public void setHyperTension(String hyperTension) {
        this.hyperTension = hyperTension;
    }

    @Basic
    @Column(name = "fatigue")
    public String getFatigue() {
        return fatigue;
    }

    public void setFatigue(String fatigue) {
        this.fatigue = fatigue;
    }

    @Basic
    @Column(name = "gastrointestinal")
    public String getGastrointestinal() {
        return gastrointestinal;
    }

    public void setGastrointestinal(String gastrointestinal) {
        this.gastrointestinal = gastrointestinal;
    }

    @Basic
    @Column(name = "abroad_travel")
    public String getAbroadTravel() {
        return abroadTravel;
    }

    public void setAbroadTravel(String abroadTravel) {
        this.abroadTravel = abroadTravel;
    }

    @Basic
    @Column(name = "contact_with_covid_patient")
    public String getContactWithCovidPatient() {
        return contactWithCovidPatient;
    }

    public void setContactWithCovidPatient(String contactWithCovidPatient) {
        this.contactWithCovidPatient = contactWithCovidPatient;
    }

    @Basic
    @Column(name = "attended_large_gathering")
    public String getAttendedLargeGathering() {
        return attendedLargeGathering;
    }

    public void setAttendedLargeGathering(String attendedLargeGathering) {
        this.attendedLargeGathering = attendedLargeGathering;
    }

    @Basic
    @Column(name = "visited_public_exposed_places")
    public String getVisitedPublicExposedPlaces() {
        return visitedPublicExposedPlaces;
    }

    public void setVisitedPublicExposedPlaces(String visitedPublicExposedPlaces) {
        this.visitedPublicExposedPlaces = visitedPublicExposedPlaces;
    }

    @Basic
    @Column(name = "family_working_in_public_exposed_places")
    public String getFamilyWorkingInPublicExposedPlaces() {
        return familyWorkingInPublicExposedPlaces;
    }

    public void setFamilyWorkingInPublicExposedPlaces(String familyWorkingInPublicExposedPlaces) {
        this.familyWorkingInPublicExposedPlaces = familyWorkingInPublicExposedPlaces;
    }

    @Basic
    @Column(name = "wearing_masks")
    public String getWearingMasks() {
        return wearingMasks;
    }

    public void setWearingMasks(String wearingMasks) {
        this.wearingMasks = wearingMasks;
    }

    @Basic
    @Column(name = "sanitization_from_market")
    public String getSanitizationFromMarket() {
        return sanitizationFromMarket;
    }

    public void setSanitizationFromMarket(String sanitizationFromMarket) {
        this.sanitizationFromMarket = sanitizationFromMarket;
    }

    @Basic
    @Column(name = "covid")
    public String getCovid() {
        return covid;
    }

    public void setCovid(String covid) {
        this.covid = covid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConsultaEntity that = (ConsultaEntity) o;

        if (idConsulta != that.idConsulta) return false;
        if (dateConsulta != null ? !dateConsulta.equals(that.dateConsulta) : that.dateConsulta != null) return false;
        if (breathingProblem != null ? !breathingProblem.equals(that.breathingProblem) : that.breathingProblem != null)
            return false;
        if (fever != null ? !fever.equals(that.fever) : that.fever != null) return false;
        if (dryCough != null ? !dryCough.equals(that.dryCough) : that.dryCough != null) return false;
        if (soreThroat != null ? !soreThroat.equals(that.soreThroat) : that.soreThroat != null) return false;
        if (runningSe != null ? !runningSe.equals(that.runningSe) : that.runningSe != null) return false;
        if (asthma != null ? !asthma.equals(that.asthma) : that.asthma != null) return false;
        if (chronicLungDisease != null ? !chronicLungDisease.equals(that.chronicLungDisease) : that.chronicLungDisease != null)
            return false;
        if (headache != null ? !headache.equals(that.headache) : that.headache != null) return false;
        if (heartDisease != null ? !heartDisease.equals(that.heartDisease) : that.heartDisease != null) return false;
        if (diabetes != null ? !diabetes.equals(that.diabetes) : that.diabetes != null) return false;
        if (hyperTension != null ? !hyperTension.equals(that.hyperTension) : that.hyperTension != null) return false;
        if (fatigue != null ? !fatigue.equals(that.fatigue) : that.fatigue != null) return false;
        if (gastrointestinal != null ? !gastrointestinal.equals(that.gastrointestinal) : that.gastrointestinal != null)
            return false;
        if (abroadTravel != null ? !abroadTravel.equals(that.abroadTravel) : that.abroadTravel != null) return false;
        if (contactWithCovidPatient != null ? !contactWithCovidPatient.equals(that.contactWithCovidPatient) : that.contactWithCovidPatient != null)
            return false;
        if (attendedLargeGathering != null ? !attendedLargeGathering.equals(that.attendedLargeGathering) : that.attendedLargeGathering != null)
            return false;
        if (visitedPublicExposedPlaces != null ? !visitedPublicExposedPlaces.equals(that.visitedPublicExposedPlaces) : that.visitedPublicExposedPlaces != null)
            return false;
        if (familyWorkingInPublicExposedPlaces != null ? !familyWorkingInPublicExposedPlaces.equals(that.familyWorkingInPublicExposedPlaces) : that.familyWorkingInPublicExposedPlaces != null)
            return false;
        if (wearingMasks != null ? !wearingMasks.equals(that.wearingMasks) : that.wearingMasks != null) return false;
        if (sanitizationFromMarket != null ? !sanitizationFromMarket.equals(that.sanitizationFromMarket) : that.sanitizationFromMarket != null)
            return false;
        if (covid != null ? !covid.equals(that.covid) : that.covid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idConsulta;
        result = 31 * result + (dateConsulta != null ? dateConsulta.hashCode() : 0);
        result = 31 * result + (breathingProblem != null ? breathingProblem.hashCode() : 0);
        result = 31 * result + (fever != null ? fever.hashCode() : 0);
        result = 31 * result + (dryCough != null ? dryCough.hashCode() : 0);
        result = 31 * result + (soreThroat != null ? soreThroat.hashCode() : 0);
        result = 31 * result + (runningSe != null ? runningSe.hashCode() : 0);
        result = 31 * result + (asthma != null ? asthma.hashCode() : 0);
        result = 31 * result + (chronicLungDisease != null ? chronicLungDisease.hashCode() : 0);
        result = 31 * result + (headache != null ? headache.hashCode() : 0);
        result = 31 * result + (heartDisease != null ? heartDisease.hashCode() : 0);
        result = 31 * result + (diabetes != null ? diabetes.hashCode() : 0);
        result = 31 * result + (hyperTension != null ? hyperTension.hashCode() : 0);
        result = 31 * result + (fatigue != null ? fatigue.hashCode() : 0);
        result = 31 * result + (gastrointestinal != null ? gastrointestinal.hashCode() : 0);
        result = 31 * result + (abroadTravel != null ? abroadTravel.hashCode() : 0);
        result = 31 * result + (contactWithCovidPatient != null ? contactWithCovidPatient.hashCode() : 0);
        result = 31 * result + (attendedLargeGathering != null ? attendedLargeGathering.hashCode() : 0);
        result = 31 * result + (visitedPublicExposedPlaces != null ? visitedPublicExposedPlaces.hashCode() : 0);
        result = 31 * result + (familyWorkingInPublicExposedPlaces != null ? familyWorkingInPublicExposedPlaces.hashCode() : 0);
        result = 31 * result + (wearingMasks != null ? wearingMasks.hashCode() : 0);
        result = 31 * result + (sanitizationFromMarket != null ? sanitizationFromMarket.hashCode() : 0);
        result = 31 * result + (covid != null ? covid.hashCode() : 0);
        return result;
    }
}
