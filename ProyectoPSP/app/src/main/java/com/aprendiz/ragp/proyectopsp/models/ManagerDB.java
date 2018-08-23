package com.aprendiz.ragp.proyectopsp.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ManagerDB {
    Context context;
    GestorDB gestorDB;
    SQLiteDatabase db;

    public ManagerDB(Context context) {
        this.context = context;
    }

    public void openReadDB(){
        gestorDB = new GestorDB(context);
        db=gestorDB.getReadableDatabase();

    }

    public void openWriteDB(){
        gestorDB = new GestorDB(context);
        db=gestorDB.getWritableDatabase();

    }

    public void insertProject(CProyecto cProyecto){
        openWriteDB();
        ContentValues values = new ContentValues();
        values.put("NOMBRE",cProyecto.getNombre());
        db.insert("PROJECT",null,values);
        closeDB();

    }
    public void insertTimeLog(CTimeLog cTimeLog){
        openWriteDB();
        ContentValues values = new ContentValues();
        values.put("PHASE",cTimeLog.getPhase());
        values.put("START",cTimeLog.getStart());
        values.put("INTERRUPCION",cTimeLog.getInterruptions());
        values.put("STOP",cTimeLog.getStop());
        values.put("DELTA",cTimeLog.getDelta());
        values.put("COMMENTS",cTimeLog.getComments());
        values.put("PROJECT",cTimeLog.getProject());
        db.insert("TIMELOG",null,values);
        closeDB();
    }


    public void insertDefectLog(CDefectLog cDefectLog){
        openWriteDB();
        ContentValues values = new ContentValues();
        values.put("DATE",cDefectLog.getDate());
        values.put("TYPE",cDefectLog.getType());
        values.put("PHASEI",cDefectLog.getPhaseI());
        values.put("PHASER",cDefectLog.getPhaseR());
        values.put("FIXTIME",cDefectLog.getFixTime());
        values.put("COMMENTS",cDefectLog.getComments());
        values.put("PROJECT",cDefectLog.getProject());
        db.insert("DEFECTLOG",null,values);
        closeDB();
    }

    public void insertPPS(CPPS cpps){
        openWriteDB();
        ContentValues values = new ContentValues();
        values.put("TIME",cpps.getTime());
        values.put("PROJECT",cpps.getProject());
        db.insert("PPS",null,values);
        closeDB();

    }

    public void updateTimeLog(CTimeLog cTimeLog){
        openWriteDB();
        ContentValues values = new ContentValues();
        values.put("PHASE",cTimeLog.getPhase());
        values.put("START",cTimeLog.getStart());
        values.put("INTERRUPCION",cTimeLog.getInterruptions());
        values.put("STOP",cTimeLog.getStop());
        values.put("DELTA",cTimeLog.getDelta());
        values.put("COMMENTS",cTimeLog.getComments());
        db.update("TIMELOG",values,"IDTIMELOG= "+cTimeLog.getId()+";",null);
        closeDB();
    }


    public void updateDefectLog(CDefectLog cDefectLog){
        openWriteDB();
        ContentValues values = new ContentValues();
        values.put("DATE",cDefectLog.getDate());
        values.put("TYPE",cDefectLog.getType());
        values.put("PHASEI",cDefectLog.getPhaseI());
        values.put("PHASER",cDefectLog.getPhaseR());
        values.put("FIXTIME",cDefectLog.getFixTime());
        values.put("COMMENTS",cDefectLog.getComments());
        db.update("DEFECTLOG",values,"IDDEFECTLOG= "+cDefectLog.getId()+";",null);
        closeDB();
    }

    public void updatePPS(CPPS cpps){
        openWriteDB();
        ContentValues values = new ContentValues();
        values.put("TIME",cpps.getTime());
        db.update("PPS",values,"IDPPS= "+cpps.getProject()+";",null);
        closeDB();

    }


    public void deleteTimeLog(CTimeLog cTimeLog){
        openWriteDB();
        db.delete("TIMELOG","PROJECT= "+cTimeLog.getId()+";",null);
        closeDB();
    }


    public void deleteDefectLog(CDefectLog cDefectLog){
        openWriteDB();
        db.delete("TIMELOG","PROJECT= "+cDefectLog.getId()+";",null);
        closeDB();
    }

    public List<CProyecto> selectProject(){
        List<CProyecto> results = new ArrayList<>();
        openReadDB();
        Cursor cursor = db.rawQuery("SELECT * FROM PROJECT ORDER BY IDPROJECT DESC;",null);
        if (cursor.moveToFirst()){
            do {
                CProyecto cProyecto = new CProyecto();
                cProyecto.setId(cursor.getInt(0));
                cProyecto.setNombre(cursor.getString(1));
                results.add(cProyecto);

            }while (cursor.moveToNext());
        }
        return results;
    }

    public List<CTimeLog> selectCTimeLog(int project){
        openReadDB();
        List<CTimeLog> results = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM TIMELOG WHERE PROJECT="+project+";",null);
        if (cursor.moveToFirst()){
            do {
                CTimeLog cTimeLog = new CTimeLog();
                cTimeLog.setId(cursor.getInt(0));
                cTimeLog.setPhase(cursor.getString(1));
                cTimeLog.setStart(cursor.getString(2));
                cTimeLog.setInterruptions(cursor.getInt(3));
                cTimeLog.setStop(cursor.getString(4));
                cTimeLog.setDelta(cursor.getInt(5));
                cTimeLog.setComments(cursor.getString(6));
                cTimeLog.setProject(cursor.getInt(7));
                results.add(cTimeLog);
            }while (cursor.moveToNext());
        }

        cursor.close();
        closeDB();

        return results;
    }

    public List<CDefectLog> selectCDefectLog(int project){
        openReadDB();
        List<CDefectLog> results = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM DEFECTLOG WHERE PROJECT="+project+";",null);
        if (cursor.moveToFirst()){
            do {
                CDefectLog cDefectLog = new CDefectLog();
                cDefectLog.setId(cursor.getInt(0));
                cDefectLog.setDate(cursor.getString(1));
                cDefectLog.setType(cursor.getString(2));
                cDefectLog.setPhaseI(cursor.getString(3));
                cDefectLog.setPhaseR(cursor.getString(4));
                cDefectLog.setFixTime(cursor.getString(5));
                cDefectLog.setComments(cursor.getString(6));
                cDefectLog.setProject(cursor.getInt(7));
                results.add(cDefectLog);
            }while (cursor.moveToNext());
        }

        cursor.close();
        closeDB();

        return results;
    }


    public List<Results> timeinPhases(int project){
        List<Results> results = new ArrayList<>();
        openReadDB();

        Cursor cursor = db.rawQuery("SELECT TIME FROM PPS WHERE PROJECT ="+project+";", null);
        int tiempo=0;
        if (cursor.moveToFirst()){
            do {

                tiempo=cursor.getInt(0);

            }while (cursor.moveToNext());
        }
        cursor.close();
        for (int i=0; i<Constants.listaDeFases.length;i++) {
            Cursor cursor1 = db.rawQuery("SELECT DELTA FROM TIMELOG WHERE PROJECT =" + project+" AND PHASE ='"+Constants.listaDeFases[i]+"';", null);
            int total=0;
            if (cursor1.moveToFirst()){
                do {
                    total+=cursor1.getInt(0);
                }while (cursor1.moveToNext());
            }

            Results tmpResults = new Results();
            tmpResults.setName(Constants.listaDeFases[i]);
            tmpResults.setTime(total);
            float tmp1 = total, tmp2 = tiempo;
            double resultado=0;
            try {
                resultado=(tmp1/tmp2)*100;
            }catch (Exception e){
                resultado=0;
            }
            BigDecimal bigDecimal = new BigDecimal(resultado);
            float tmpR = bigDecimal.setScale(2, RoundingMode.HALF_UP).floatValue();
            tmpResults.setPercentage(tmpR);
            results.add(tmpResults);
            cursor1.close();

        }

        closeDB();

        return results;
    }


    public List<Results> defectIinPhases(int project){
        List<Results> results = new ArrayList<>();
        openReadDB();

        Cursor cursor = db.rawQuery("SELECT * FROM DEFECTLOG WHERE PROJECT ="+project+";", null);
        int tiempo=0;
        if (cursor.moveToFirst()){
            do {

                tiempo++;

            }while (cursor.moveToNext());
        }
        cursor.close();
        for (int i=0; i<Constants.listaDeFases.length;i++) {
            Cursor cursor1 = db.rawQuery("SELECT * FROM DEFECTLOG WHERE PROJECT =" + project+" AND PHASEI ='"+Constants.listaDeFases[i]+"';", null);
            int total=0;
            if (cursor1.moveToFirst()){
                do {
                    total++;
                }while (cursor1.moveToNext());
            }

            Results tmpResults = new Results();
            tmpResults.setName(Constants.listaDeFases[i]);
            tmpResults.setTime(total);
            float tmp1 = total, tmp2 = tiempo;
            double resultado=0;
            try {
                resultado=(tmp1/tmp2)*100;
            }catch (Exception e){
                resultado=0;
            }
            BigDecimal bigDecimal = new BigDecimal(resultado);
            float tmpR = bigDecimal.setScale(2, RoundingMode.HALF_UP).floatValue();
            tmpResults.setPercentage(tmpR);
            results.add(tmpResults);
            cursor1.close();

        }

        closeDB();

        return results;
    }

    public List<Results> defectRinPhases(int project){
        List<Results> results = new ArrayList<>();
        openReadDB();

        Cursor cursor = db.rawQuery("SELECT * FROM DEFECTLOG WHERE PROJECT ="+project+";", null);
        int tiempo=0;
        if (cursor.moveToFirst()){
            do {

                tiempo++;

            }while (cursor.moveToNext());
        }
        cursor.close();
        for (int i=0; i<Constants.listaDeFases.length;i++) {
            Cursor cursor1 = db.rawQuery("SELECT * FROM DEFECTLOG WHERE PROJECT =" + project+" AND PHASER ='"+Constants.listaDeFases[i]+"';", null);
            int total=0;
            if (cursor1.moveToFirst()){
                do {
                    total++;
                }while (cursor1.moveToNext());
            }

            Results tmpResults = new Results();
            tmpResults.setName(Constants.listaDeFases[i]);
            tmpResults.setTime(total);
            float tmp1 = total, tmp2 = tiempo;
            double resultado=0;
            try {
                resultado=(tmp1/tmp2)*100;
            }catch (Exception e){
                resultado=0;
            }
            BigDecimal bigDecimal = new BigDecimal(resultado);
            float tmpR = bigDecimal.setScale(2, RoundingMode.HALF_UP).floatValue();
            tmpResults.setPercentage(tmpR);
            results.add(tmpResults);
            cursor1.close();

        }

        closeDB();

        return results;
    }






    public void closeDB(){
        db.close();
    }


}
