package com.aprendiz.ragp.proyectopsp.models;

public class Constants {
    public static final String DATABASE_NAME = "psp.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TableProject = "CREATE TABLE PROJECT (IDPROJECT INTEGER PRIMARY KEY AUTOINCREMENT, NOMBRE TEXT);";
    public static final String TableTimeLog = "CREATE TABLE TIMELOG (IDTIMELOG INTEGER PRIMARY KEY AUTOINCREMENT, PHASE TEXT, START TEXT, INTERRUPCION INTEGER, STOP TEXT, DELTA INTEGER, COMMENTS TEXT, PROJECT INTEGER, " +
            "FOREIGN KEY (PROJECT) REFERENCES PROJECT(IDPROJECT));";
    public static final String TableDefectLog = "CREATE TABLE DEFECTLOG (IDDEFECTLOG INTEGER PRIMARY KEY AUTOINCREMENT, DATE TEXT, TYPE TEXT, PHASEI TEXT, PHASER TEXT, FIXTIME TEXT, COMMENTS TEXT, PROJECT INTEGER, " +
            "FOREIGN KEY (PROJECT) REFERENCES PROJECT(IDPROJECT));";

    public static final String TablePPS = "CREATE TABLE PPS (IDPSS INTEGER PRIMARY KEY AUTOINCREMENT, TIME INTEGER, PROJECT INTEGER, " +
            "FOREIGN KEY (PROJECT) REFERENCES PROJECT(IDPROJECT));";

    public static final String [] listaDeFases={
            "PLAN", "DLD", "CODE", "COMPILE", "UT", "PM"
    };

}
