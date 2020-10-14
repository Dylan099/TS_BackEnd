package com.example.demo.domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "consulta", schema = "tds", catalog = "")
public class ConsultaEntity {
    private int idConsulta;
    private Date dateConsulta;
    private int breathingProblem;
    private int fever;
    private int dryCough;
    private int soreThroat;
    private int runningSe;
    private int asthma;
    private int chronicLungDisease;
    private int headache;
    private int heartDisease;
    private int diabetes;
    private int hyperTension;
    private int fatigue;
    private int gastrointestinal;
    private int abroadTravel;
    private int contactWithCovidPatient;
    private int attendedLargeGathering;
    private int visitedPublicExposedPlaces;
    private int familyWorkingInPublicExposedPlaces;
    private int wearingMasks;
    private int sanitizationFromMarket;
    private int covid;

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
    public Date getDateConsulta() {
        return dateConsulta;
    }

    public void setDateConsulta(Date dateConsulta) {
        this.dateConsulta = dateConsulta;
    }

    @Basic
    @Column(name = "breathing_problem")
    public int getBreathingProblem() {
        return breathingProblem;
    }

    public void setBreathingProblem(int breathingProblem) {
        this.breathingProblem = breathingProblem;
    }

    @Basic
    @Column(name = "fever")
    public int getFever() {
        return fever;
    }

    public void setFever(int fever) {
        this.fever = fever;
    }

    @Basic
    @Column(name = "dry_cough")
    public int getDryCough() {
        return dryCough;
    }

    public void setDryCough(int dryCough) {
        this.dryCough = dryCough;
    }

    @Basic
    @Column(name = "sore_throat")
    public int getSoreThroat() {
        return soreThroat;
    }

    public void setSoreThroat(int soreThroat) {
        this.soreThroat = soreThroat;
    }

    @Basic
    @Column(name = "running_se")
    public int getRunningSe() {
        return runningSe;
    }

    public void setRunningSe(int runningSe) {
        this.runningSe = runningSe;
    }

    @Basic
    @Column(name = "asthma")
    public int getAsthma() {
        return asthma;
    }

    public void setAsthma(int asthma) {
        this.asthma = asthma;
    }

    @Basic
    @Column(name = "chronic_lung_disease")
    public int getChronicLungDisease() {
        return chronicLungDisease;
    }

    public void setChronicLungDisease(int chronicLungDisease) {
        this.chronicLungDisease = chronicLungDisease;
    }

    @Basic
    @Column(name = "headache")
    public int getHeadache() {
        return headache;
    }

    public void setHeadache(int headache) {
        this.headache = headache;
    }

    @Basic
    @Column(name = "heart_disease")
    public int getHeartDisease() {
        return heartDisease;
    }

    public void setHeartDisease(int heartDisease) {
        this.heartDisease = heartDisease;
    }

    @Basic
    @Column(name = "diabetes")
    public int getDiabetes() {
        return diabetes;
    }

    public void setDiabetes(int diabetes) {
        this.diabetes = diabetes;
    }

    @Basic
    @Column(name = "hyper_tension")
    public int getHyperTension() {
        return hyperTension;
    }

    public void setHyperTension(int hyperTension) {
        this.hyperTension = hyperTension;
    }

    @Basic
    @Column(name = "fatigue")
    public int getFatigue() {
        return fatigue;
    }

    public void setFatigue(int fatigue) {
        this.fatigue = fatigue;
    }

    @Basic
    @Column(name = "gastrointestinal")
    public int getGastrointestinal() {
        return gastrointestinal;
    }

    public void setGastrointestinal(int gastrointestinal) {
        this.gastrointestinal = gastrointestinal;
    }

    @Basic
    @Column(name = "abroad_travel")
    public int getAbroadTravel() {
        return abroadTravel;
    }

    public void setAbroadTravel(int abroadTravel) {
        this.abroadTravel = abroadTravel;
    }

    @Basic
    @Column(name = "contact_with_covid_patient")
    public int getContactWithCovidPatient() {
        return contactWithCovidPatient;
    }

    public void setContactWithCovidPatient(int contactWithCovidPatient) {
        this.contactWithCovidPatient = contactWithCovidPatient;
    }

    @Basic
    @Column(name = "attended_large_gathering")
    public int getAttendedLargeGathering() {
        return attendedLargeGathering;
    }

    public void setAttendedLargeGathering(int attendedLargeGathering) {
        this.attendedLargeGathering = attendedLargeGathering;
    }

    @Basic
    @Column(name = "visited_public_exposed_places")
    public int getVisitedPublicExposedPlaces() {
        return visitedPublicExposedPlaces;
    }

    public void setVisitedPublicExposedPlaces(int visitedPublicExposedPlaces) {
        this.visitedPublicExposedPlaces = visitedPublicExposedPlaces;
    }

    @Basic
    @Column(name = "family_working_in_public_exposed_places")
    public int getFamilyWorkingInPublicExposedPlaces() {
        return familyWorkingInPublicExposedPlaces;
    }

    public void setFamilyWorkingInPublicExposedPlaces(int familyWorkingInPublicExposedPlaces) {
        this.familyWorkingInPublicExposedPlaces = familyWorkingInPublicExposedPlaces;
    }

    @Basic
    @Column(name = "wearing_masks")
    public int getWearingMasks() {
        return wearingMasks;
    }

    public void setWearingMasks(int wearingMasks) {
        this.wearingMasks = wearingMasks;
    }

    @Basic
    @Column(name = "sanitization_from_market")
    public int getSanitizationFromMarket() {
        return sanitizationFromMarket;
    }

    public void setSanitizationFromMarket(int sanitizationFromMarket) {
        this.sanitizationFromMarket = sanitizationFromMarket;
    }

    @Basic
    @Column(name = "covid")
    public int getCovid() {
        return covid;
    }

    public void setCovid(int covid) {
        this.covid = covid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConsultaEntity that = (ConsultaEntity) o;

        if (idConsulta != that.idConsulta) return false;
        if (breathingProblem != that.breathingProblem) return false;
        if (fever != that.fever) return false;
        if (dryCough != that.dryCough) return false;
        if (soreThroat != that.soreThroat) return false;
        if (runningSe != that.runningSe) return false;
        if (asthma != that.asthma) return false;
        if (chronicLungDisease != that.chronicLungDisease) return false;
        if (headache != that.headache) return false;
        if (heartDisease != that.heartDisease) return false;
        if (diabetes != that.diabetes) return false;
        if (hyperTension != that.hyperTension) return false;
        if (fatigue != that.fatigue) return false;
        if (gastrointestinal != that.gastrointestinal) return false;
        if (abroadTravel != that.abroadTravel) return false;
        if (contactWithCovidPatient != that.contactWithCovidPatient) return false;
        if (attendedLargeGathering != that.attendedLargeGathering) return false;
        if (visitedPublicExposedPlaces != that.visitedPublicExposedPlaces) return false;
        if (familyWorkingInPublicExposedPlaces != that.familyWorkingInPublicExposedPlaces) return false;
        if (wearingMasks != that.wearingMasks) return false;
        if (sanitizationFromMarket != that.sanitizationFromMarket) return false;
        if (covid != that.covid) return false;
        if (dateConsulta != null ? !dateConsulta.equals(that.dateConsulta) : that.dateConsulta != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idConsulta;
        result = 31 * result + (dateConsulta != null ? dateConsulta.hashCode() : 0);
        result = 31 * result + breathingProblem;
        result = 31 * result + fever;
        result = 31 * result + dryCough;
        result = 31 * result + soreThroat;
        result = 31 * result + runningSe;
        result = 31 * result + asthma;
        result = 31 * result + chronicLungDisease;
        result = 31 * result + headache;
        result = 31 * result + heartDisease;
        result = 31 * result + diabetes;
        result = 31 * result + hyperTension;
        result = 31 * result + fatigue;
        result = 31 * result + gastrointestinal;
        result = 31 * result + abroadTravel;
        result = 31 * result + contactWithCovidPatient;
        result = 31 * result + attendedLargeGathering;
        result = 31 * result + visitedPublicExposedPlaces;
        result = 31 * result + familyWorkingInPublicExposedPlaces;
        result = 31 * result + wearingMasks;
        result = 31 * result + sanitizationFromMarket;
        result = 31 * result + covid;
        return result;
    }
}
