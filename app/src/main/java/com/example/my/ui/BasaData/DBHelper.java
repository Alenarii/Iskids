package com.example.my.ui.BasaData;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.my.R;

import java.util.ArrayList;
import java.util.Objects;

public class DBHelper extends SQLiteOpenHelper{

    ArrayList<String> Day_Name = new ArrayList<String>(6);

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "IckiBase2";

    public static final String TABLE_CONTACTS = "SubjBase";
    public static final String TABLE_RAS_1 = "MonBase";
    public static final String TABLE_RAS_2 = "TueBase";
    public static final String TABLE_RAS_3 = "WedBase";
    public static final String TABLE_RAS_4 = "ThuBase";
    public static final String TABLE_RAS_5 = "FriBase";
    public static final String TABLE_RAS_6 = "SatBase";

    public static final String KEY_ID = "_id";
    public static final String KEY_ID_1 = "_id";
    public static final String KEY_ID_2 = "_id";
    public static final String KEY_ID_3 = "_id";
    public static final String KEY_ID_4 = "_id";
    public static final String KEY_ID_5 = "_id";
    public static final String KEY_ID_6 = "_id";

    public static final String KEY_SUB = "namesub";
    public static final String KEY_TEACHER = "nameteacher";
    public static final String KEY_CAB = "namecab";
    public static final String KEY_DOP = "namedop";

    public static final String KEY_NOM_1 = "nomerlesMon";
    public static final String KEY_NOM_2 = "nomerlesTue";
    public static final String KEY_NOM_3 = "nomerlesWed";
    public static final String KEY_NOM_4 = "nomerlesThu";
    public static final String KEY_NOM_5 = "nomerlesFri";
    public static final String KEY_NOM_6 = "nomerlesSat";

    public static final String KEY_RAS_SUB_1 = "subMon";
    public static final String KEY_RAS_SUB_2 = "subTue";
    public static final String KEY_RAS_SUB_3 = "subWed";
    public static final String KEY_RAS_SUB_4 = "subThu";
    public static final String KEY_RAS_SUB_5 = "subFri";
    public static final String KEY_RAS_SUB_6 = "subSat";

    public static final String KEY_RAS_HW_1 = "homeworkMon";
    public static final String KEY_RAS_HW_2 = "homeworkTue";
    public static final String KEY_RAS_HW_3 = "homeworkWed";
    public static final String KEY_RAS_HW_4 = "homeworkThu";
    public static final String KEY_RAS_HW_5 = "homeworkFri";
    public static final String KEY_RAS_HW_6 = "homeworkSat";
    public static final String KEY_RAS_HW_YN_1 = "homeworkMonYesNo";
    public static final String KEY_RAS_HW_YN_2 = "homeworkTueYesNo";
    public static final String KEY_RAS_HW_YN_3 = "homeworkWedYesNo";
    public static final String KEY_RAS_HW_YN_4 = "homeworkThuYesNo";
    public static final String KEY_RAS_HW_YN_5 = "homeworkFriYesNo";
    public static final String KEY_RAS_HW_YN_6 = "homeworkSatYesNo";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Day_Name.add("Понедельник");Day_Name.add("Вторник");Day_Name.add("Среда");Day_Name.add("Четверг");Day_Name.add("Пятница");Day_Name.add("Суббота");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_CONTACTS + "(" + KEY_ID
                + " integer primary key," + KEY_SUB + " text," + KEY_TEACHER + " text," + KEY_CAB + " text," + KEY_DOP + " text" + ")");
        db.execSQL("create table " + TABLE_RAS_1 + "(" + KEY_ID_1 + " integer primary key," + KEY_NOM_1 + " integer," + KEY_RAS_SUB_1 + " text," + KEY_RAS_HW_1 + " text," + KEY_RAS_HW_YN_1 + " text" + ")");
        db.execSQL("create table " + TABLE_RAS_2 + "(" + KEY_ID_2 + " integer primary key," + KEY_NOM_2 + " integer," + KEY_RAS_SUB_2 + " text," + KEY_RAS_HW_2 + " text," + KEY_RAS_HW_YN_2 + " text" + ")");
        db.execSQL("create table " + TABLE_RAS_3 + "(" + KEY_ID_3 + " integer primary key," + KEY_NOM_3 + " integer," + KEY_RAS_SUB_3 + " text," + KEY_RAS_HW_3 + " text," + KEY_RAS_HW_YN_3 + " text" + ")");
        db.execSQL("create table " + TABLE_RAS_4 + "(" + KEY_ID_4 + " integer primary key," + KEY_NOM_4 + " integer," + KEY_RAS_SUB_4 + " text," + KEY_RAS_HW_4 + " text," + KEY_RAS_HW_YN_4 + " text" + ")");
        db.execSQL("create table " + TABLE_RAS_5 + "(" + KEY_ID_5 + " integer primary key," + KEY_NOM_5 + " integer," + KEY_RAS_SUB_5 + " text," + KEY_RAS_HW_5 + " text," + KEY_RAS_HW_YN_5 + " text" + ")");
        db.execSQL("create table " + TABLE_RAS_6 + "(" + KEY_ID_6 + " integer primary key," + KEY_NOM_6 + " integer," + KEY_RAS_SUB_6 + " text," + KEY_RAS_HW_6 + " text," + KEY_RAS_HW_YN_6 + " text" + ")");
}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_CONTACTS);
        onCreate(db);
    }

    public void AddSub(Context v, String name, String teacher, String cab, String dop){ //Добавить предмет
        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery("Select * from SubjBase where nameSub = ?", new String[]{name.trim()});
        if(cursor.getCount() > 0) {
            Toast.makeText(v, R.string.AddSubToastNo, Toast.LENGTH_SHORT).show();
        }else {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DBHelper.KEY_SUB, name.trim());
            contentValues.put(DBHelper.KEY_CAB, cab);
            contentValues.put(DBHelper.KEY_TEACHER, teacher);
            contentValues.put(DBHelper.KEY_DOP, dop);
            database.insert(DBHelper.TABLE_CONTACTS, null, contentValues);
            Toast.makeText(v, R.string.AddSubToast, Toast.LENGTH_SHORT).show();
        }
        cursor.close();
    }

    public void AddRas(Context v, String day, Integer nom, String sub){ //Добавить урок в расписание
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Cursor cursor;
        int index = Day_Name.indexOf(day);
        if (index == 0){
            cursor = database.rawQuery("Select * from MonBase where nomerlesMon = ?", new String[]{nom.toString()});
            if(cursor.getCount() > 0) {
                Toast.makeText(v, "Такой номер урока уже есть", Toast.LENGTH_SHORT).show();
            }else {
            contentValues.put(DBHelper.KEY_NOM_1, nom);
            contentValues.put(DBHelper.KEY_RAS_SUB_1, sub);
            database.insert(DBHelper.TABLE_RAS_1, null, contentValues);
                Toast.makeText(v, "Вы успешно добавили предмет в расписание", Toast.LENGTH_SHORT).show();}
        }
        else if (index == 1){
            cursor = database.rawQuery("Select * from TueBase where nomerlesTue = ?", new String[]{nom.toString()});
            if(cursor.getCount() > 0) {
                Toast.makeText(v, "Такой номер урока уже есть", Toast.LENGTH_SHORT).show();
            }else {
            contentValues.put(DBHelper.KEY_NOM_2, nom);
            contentValues.put(DBHelper.KEY_RAS_SUB_2, sub);
            database.insert(DBHelper.TABLE_RAS_2, null, contentValues);
                Toast.makeText(v, "Вы успешно добавили предмет в расписание", Toast.LENGTH_SHORT).show();}
        }
        else if (index == 2){
            cursor = database.rawQuery("Select * from WedBase where nomerlesWed = ?", new String[]{nom.toString()});
            if(cursor.getCount() > 0) {
                Toast.makeText(v, "Такой номер урока уже есть", Toast.LENGTH_SHORT).show();
            }else {
            contentValues.put(DBHelper.KEY_NOM_3, nom);
            contentValues.put(DBHelper.KEY_RAS_SUB_3, sub);
            database.insert(DBHelper.TABLE_RAS_3, null, contentValues);
                Toast.makeText(v, "Вы успешно добавили предмет в расписание", Toast.LENGTH_SHORT).show();}
        }
        else if (index == 3){
            cursor = database.rawQuery("Select * from ThuBase where nomerlesThu = ?", new String[]{nom.toString()});
            if(cursor.getCount() > 0) {
                Toast.makeText(v, "Такой номер урока уже есть", Toast.LENGTH_SHORT).show();
            }else {
            contentValues.put(DBHelper.KEY_NOM_4, nom);
            contentValues.put(DBHelper.KEY_RAS_SUB_4, sub);
            database.insert(DBHelper.TABLE_RAS_4, null, contentValues);
                Toast.makeText(v, "Вы успешно добавили предмет в расписание", Toast.LENGTH_SHORT).show();}
        }
        else if (index == 4){
            cursor = database.rawQuery("Select * from FriBase where nomerlesFri = ?", new String[]{nom.toString()});
            if(cursor.getCount() > 0) {
                Toast.makeText(v, "Такой номер урока уже есть", Toast.LENGTH_SHORT).show();
            }else {
            contentValues.put(DBHelper.KEY_NOM_5, nom);
            contentValues.put(DBHelper.KEY_RAS_SUB_5, sub);
            database.insert(DBHelper.TABLE_RAS_5, null, contentValues);
                Toast.makeText(v, "Вы успешно добавили предмет в расписание", Toast.LENGTH_SHORT).show();}
        }
        else if (index == 5){
            cursor = database.rawQuery("Select * from SatBase where nomerlesSat = ?", new String[]{nom.toString()});
            if(cursor.getCount() > 0) {
                Toast.makeText(v, "Такой номер урока уже есть", Toast.LENGTH_SHORT).show();
            }else {
            contentValues.put(DBHelper.KEY_NOM_6, nom);
            contentValues.put(DBHelper.KEY_RAS_SUB_6, sub);
            database.insert(DBHelper.TABLE_RAS_6, null, contentValues);
                Toast.makeText(v, "Вы успешно добавили предмет в расписание", Toast.LENGTH_SHORT).show();}
        }
        database.close();
    }

    public void AddHomeWork(Context v, String day, String sub, String hw){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        int index = Day_Name.indexOf(day);

        if (index == 0){
            contentValues.put(DBHelper.KEY_RAS_HW_1, hw);
            contentValues.put(DBHelper.KEY_RAS_HW_YN_1, "0");
            database.update(DBHelper.TABLE_RAS_1, contentValues, "subMon = ?", new String[]{sub});
        }
        else if (index == 1){
            contentValues.put(DBHelper.KEY_RAS_HW_2, hw);
            contentValues.put(DBHelper.KEY_RAS_HW_YN_2, "0");
            database.update(DBHelper.TABLE_RAS_2, contentValues, "subTue = ?", new String[]{sub});
        }
        else if (index == 2){
            contentValues.put(DBHelper.KEY_RAS_HW_3, hw);
            contentValues.put(DBHelper.KEY_RAS_HW_YN_3, "0");
            database.update(DBHelper.TABLE_RAS_3, contentValues, "subWed = ?", new String[]{sub});
        }
        else if (index == 3){
            contentValues.put(DBHelper.KEY_RAS_HW_4, hw);
            contentValues.put(DBHelper.KEY_RAS_HW_YN_4, "0");
            database.update(DBHelper.TABLE_RAS_4, contentValues, "subThu = ?", new String[]{sub});
        }
        else if (index == 4){
            contentValues.put(DBHelper.KEY_RAS_HW_5, hw);
            contentValues.put(DBHelper.KEY_RAS_HW_YN_5, "0");
            database.update(DBHelper.TABLE_RAS_5, contentValues, "subFri = ?", new String[]{sub});
        }
        else if (index == 5){
            contentValues.put(DBHelper.KEY_RAS_HW_6, hw);
            contentValues.put(DBHelper.KEY_RAS_HW_YN_6, "0");
            database.update(DBHelper.TABLE_RAS_6, contentValues, "subSat = ?", new String[]{sub});
        }
        database.close();
    }

    public ArrayList<String> ReadSub(Context v){ // для чтения предмета
        SQLiteDatabase database = DBHelper.this.getWritableDatabase();
        Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null, null);
        ArrayList<String> tex = new ArrayList<>();
        if (cursor.moveToFirst()) {
            int subIndex = cursor.getColumnIndex(DBHelper.KEY_SUB);
            do {
                tex.add(cursor.getString(subIndex));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return tex;
    }

    public ArrayList<String> ReadDay(Context view){ //Для чтениея дней
        return Day_Name;
    }

    public ArrayList<String> Get(Context v, String nameSub){ //для диалогового окна

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null, null);
        ArrayList<String> tex = new ArrayList<>();
        if (cursor.moveToFirst()) {
            int subIndex = cursor.getColumnIndex(DBHelper.KEY_SUB);
            int teacherIndex = cursor.getColumnIndex(DBHelper.KEY_TEACHER);
            int cabIndex = cursor.getColumnIndex(DBHelper.KEY_CAB);
            int dopIndex = cursor.getColumnIndex(DBHelper.KEY_DOP);
            do {
                if (Objects.equals(cursor.getString(subIndex).trim(), nameSub.trim())){
                    tex.add(cursor.getString(teacherIndex));
                    tex.add(cursor.getString(cabIndex));
                    tex.add(cursor.getString(dopIndex));
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return tex;
    }

    public ArrayList<String> GetByDay(Context context, String day){ //Для чтения дня полностью
        SQLiteDatabase database = DBHelper.this.getWritableDatabase();
        int index = Day_Name.indexOf(day);
        Cursor cursor;
        ArrayList<String> tex = new ArrayList<String>();
        if (index == 0){
            cursor = database.query(DBHelper.TABLE_RAS_1, null, null, null, null, null, DBHelper.KEY_NOM_1);
            if (cursor.moveToFirst()) {
                int subIndex = cursor.getColumnIndex(DBHelper.KEY_RAS_SUB_1);
                int nomIndex = cursor.getColumnIndex(DBHelper.KEY_NOM_1);
                int howIndex =  cursor.getColumnIndex(DBHelper.KEY_RAS_HW_1);
                int ynIndex = cursor.getColumnIndex(DBHelper.KEY_RAS_HW_YN_1);
                do {
                    tex.add(cursor.getString(ynIndex));
                } while (cursor.moveToNext());
        }
        }
        else if (index == 1){
            cursor = database.query(DBHelper.TABLE_RAS_2, null, null, null, null, null, DBHelper.KEY_NOM_2);
            if (cursor.moveToFirst()) {
                int subIndex = cursor.getColumnIndex(DBHelper.KEY_RAS_SUB_2);
                int nomIndex = cursor.getColumnIndex(DBHelper.KEY_NOM_2);
                int howIndex =  cursor.getColumnIndex(DBHelper.KEY_RAS_HW_2);
                do {
                    tex.add(cursor.getString(nomIndex) + " " + cursor.getString(subIndex) + " " + cursor.getString(howIndex));
                } while (cursor.moveToNext());
            }
        }
        else if (index == 2){
            cursor = database.query(DBHelper.TABLE_RAS_3, null, null, null, null, null, DBHelper.KEY_NOM_3);
            if (cursor.moveToFirst()) {
                int subIndex = cursor.getColumnIndex(DBHelper.KEY_RAS_SUB_3);
                int nomIndex = cursor.getColumnIndex(DBHelper.KEY_NOM_3);
                int howIndex =  cursor.getColumnIndex(DBHelper.KEY_RAS_HW_3);
                do {
                    tex.add(cursor.getString(nomIndex) + " " + cursor.getString(subIndex) + " " + cursor.getString(howIndex));
                } while (cursor.moveToNext());
            }
        }
        else if (index == 3){
            cursor = database.query(DBHelper.TABLE_RAS_4, null, null, null, null, null, DBHelper.KEY_NOM_4);
            if (cursor.moveToFirst()) {
                int subIndex = cursor.getColumnIndex(DBHelper.KEY_RAS_SUB_4);
                int nomIndex = cursor.getColumnIndex(DBHelper.KEY_NOM_4);
                int howIndex =  cursor.getColumnIndex(DBHelper.KEY_RAS_HW_4);
                do {
                    tex.add(cursor.getString(nomIndex) + " " + cursor.getString(subIndex) + " " + cursor.getString(howIndex));
                } while (cursor.moveToNext());
            }
        }
        else if (index == 4){
            cursor = database.query(DBHelper.TABLE_RAS_5, null, null, null, null, null, DBHelper.KEY_NOM_5);
            if (cursor.moveToFirst()) {
                int subIndex = cursor.getColumnIndex(DBHelper.KEY_RAS_SUB_5);
                int nomIndex = cursor.getColumnIndex(DBHelper.KEY_NOM_5);
                int howIndex =  cursor.getColumnIndex(DBHelper.KEY_RAS_HW_5);
                do {
                    tex.add(cursor.getString(nomIndex) + " " + cursor.getString(subIndex) + " " + cursor.getString(howIndex));
                } while (cursor.moveToNext());
            }
        }
        else {
            cursor = database.query(DBHelper.TABLE_RAS_6, null, null, null, null, null, DBHelper.KEY_NOM_6);
            if (cursor.moveToFirst()) {
                int subIndex = cursor.getColumnIndex(DBHelper.KEY_RAS_SUB_6);
                int nomIndex = cursor.getColumnIndex(DBHelper.KEY_NOM_6);
                int howIndex =  cursor.getColumnIndex(DBHelper.KEY_RAS_HW_6);
                do {
                    tex.add(cursor.getString(nomIndex) + " " + cursor.getString(subIndex) + " " + cursor.getString(howIndex));
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        return tex;
    }

    public String GetByDayOnlyHomeW(Context context, String day, String sub){
        SQLiteDatabase database = DBHelper.this.getWritableDatabase();
        int index = Day_Name.indexOf(day);
        Cursor cursor;
        String tex = "";

        if (index == 0){
            cursor = database.query(DBHelper.TABLE_RAS_1, null, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                int subIndex = cursor.getColumnIndex(DBHelper.KEY_RAS_SUB_1);
                int howIndex =  cursor.getColumnIndex(DBHelper.KEY_RAS_HW_1);
                do {
                    if (Objects.equals(cursor.getString(subIndex).trim(), sub) && !Objects.equals(cursor.getString(howIndex), null)) tex = (cursor.getString(howIndex));
                } while (cursor.moveToNext());
            }
        }
        else if (index == 1){
            cursor = database.query(DBHelper.TABLE_RAS_2, null, null, null, null, null, DBHelper.KEY_NOM_2);
            if (cursor.moveToFirst()) {
                int subIndex = cursor.getColumnIndex(DBHelper.KEY_RAS_SUB_2);
                int howIndex =  cursor.getColumnIndex(DBHelper.KEY_RAS_HW_2);
                do {
                    if (Objects.equals(cursor.getString(subIndex).trim(), sub) && !Objects.equals(cursor.getString(howIndex), null)) tex = (cursor.getString(howIndex));
                } while (cursor.moveToNext());
            }
        }
        else if (index == 2){
            cursor = database.query(DBHelper.TABLE_RAS_3, null, null, null, null, null, DBHelper.KEY_NOM_3);
            if (cursor.moveToFirst()) {
                int subIndex = cursor.getColumnIndex(DBHelper.KEY_RAS_SUB_3);
                int nomIndex = cursor.getColumnIndex(DBHelper.KEY_NOM_3);
                int howIndex =  cursor.getColumnIndex(DBHelper.KEY_RAS_HW_3);
                do {
                    if (Objects.equals(cursor.getString(subIndex).trim(), sub) && !Objects.equals(cursor.getString(howIndex), null)) tex = (cursor.getString(howIndex));
                } while (cursor.moveToNext());
            }
        }
        else if (index == 3){
            cursor = database.query(DBHelper.TABLE_RAS_4, null, null, null, null, null, DBHelper.KEY_NOM_4);
            if (cursor.moveToFirst()) {
                int subIndex = cursor.getColumnIndex(DBHelper.KEY_RAS_SUB_4);
                int nomIndex = cursor.getColumnIndex(DBHelper.KEY_NOM_4);
                int howIndex =  cursor.getColumnIndex(DBHelper.KEY_RAS_HW_4);
                do {
                    if (Objects.equals(cursor.getString(subIndex).trim(), sub) && !Objects.equals(cursor.getString(howIndex), null)) tex =(cursor.getString(howIndex));
                } while (cursor.moveToNext());
            }
        }
        else if (index == 4){
            cursor = database.query(DBHelper.TABLE_RAS_5, null, null, null, null, null, DBHelper.KEY_NOM_5);
            if (cursor.moveToFirst()) {
                int subIndex = cursor.getColumnIndex(DBHelper.KEY_RAS_SUB_5);
                int nomIndex = cursor.getColumnIndex(DBHelper.KEY_NOM_5);
                int howIndex =  cursor.getColumnIndex(DBHelper.KEY_RAS_HW_5);
                do {
                    if (Objects.equals(cursor.getString(subIndex).trim(), sub) && !Objects.equals(cursor.getString(howIndex), null)) tex = (cursor.getString(howIndex));
                } while (cursor.moveToNext());
            }
        }
        else {
            cursor = database.query(DBHelper.TABLE_RAS_6, null, null, null, null, null, DBHelper.KEY_NOM_6);
            if (cursor.moveToFirst()) {
                int subIndex = cursor.getColumnIndex(DBHelper.KEY_RAS_SUB_6);
                int nomIndex = cursor.getColumnIndex(DBHelper.KEY_NOM_6);
                int howIndex =  cursor.getColumnIndex(DBHelper.KEY_RAS_HW_6);
                do {
                    if (Objects.equals(cursor.getString(subIndex).trim(), sub) && !Objects.equals(cursor.getString(howIndex), null)) tex =(cursor.getString(howIndex));
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        return tex;
    }
    public ArrayList<String> GetByDayOnlySub(Context context, String day){ //Для чтения дня полностью
        SQLiteDatabase database = DBHelper.this.getWritableDatabase();
        int index = Day_Name.indexOf(day);
        Cursor cursor;
        ArrayList<String> tex = new ArrayList<String>();
        if (index == 0){
            cursor = database.query(DBHelper.TABLE_RAS_1, null, null, null, null, null, DBHelper.KEY_NOM_1);
            if (cursor.moveToFirst()) {
                int subIndex = cursor.getColumnIndex(DBHelper.KEY_RAS_SUB_1);
                do {
                    tex.add(cursor.getString(subIndex));
                } while (cursor.moveToNext());

            }
        }
        else if (index == 1){
            cursor = database.query(DBHelper.TABLE_RAS_2, null, null, null, null, null, DBHelper.KEY_NOM_2);
            if (cursor.moveToFirst()) {
                int subIndex = cursor.getColumnIndex(DBHelper.KEY_RAS_SUB_2);
                do {
                    tex.add(cursor.getString(subIndex));
                } while (cursor.moveToNext());
            }
        }
        if (index == 2){
            cursor = database.query(DBHelper.TABLE_RAS_3, null, null, null, null, null, DBHelper.KEY_NOM_3);
            if (cursor.moveToFirst()) {
                int subIndex = cursor.getColumnIndex(DBHelper.KEY_RAS_SUB_3);
                do {
                    tex.add(cursor.getString(subIndex));
                } while (cursor.moveToNext());
            }
        }
        else if (index == 3){
            cursor = database.query(DBHelper.TABLE_RAS_4, null, null, null, null, null, DBHelper.KEY_NOM_4);
            if (cursor.moveToFirst()) {
                int subIndex = cursor.getColumnIndex(DBHelper.KEY_RAS_SUB_4);
                do {
                    tex.add(cursor.getString(subIndex));
                } while (cursor.moveToNext());
            }
        }
        if (index == 4){
            cursor = database.query(DBHelper.TABLE_RAS_5, null, null, null, null, null, DBHelper.KEY_NOM_5);
            if (cursor.moveToFirst()) {
                int subIndex = cursor.getColumnIndex(DBHelper.KEY_RAS_SUB_5);
                do {
                    tex.add(cursor.getString(subIndex));
                } while (cursor.moveToNext());
            }
        }
        else {
            cursor = database.query(DBHelper.TABLE_RAS_6, null, null, null, null, null, DBHelper.KEY_NOM_6);
            if (cursor.moveToFirst()) {
                int subIndex = cursor.getColumnIndex(DBHelper.KEY_RAS_SUB_6);
                do {
                    tex.add(cursor.getString(subIndex));
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        return tex;
    }

    public void CleanSub(){
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(DBHelper.TABLE_CONTACTS, null, null);
        database.close();
    }
    public void CleanRas(){
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(DBHelper.TABLE_RAS_1, null, null);
        database.delete(DBHelper.TABLE_RAS_2, null, null);
        database.delete(DBHelper.TABLE_RAS_3, null, null);
        database.delete(DBHelper.TABLE_RAS_4, null, null);
        database.delete(DBHelper.TABLE_RAS_5, null, null);
        database.delete(DBHelper.TABLE_RAS_6, null, null);
        database.close();
    }
    public void CleanRasOne(String day){
        int index = Day_Name.indexOf(day);
        SQLiteDatabase database = this.getWritableDatabase();
        if (index == 0) database.delete(DBHelper.TABLE_RAS_1, null, null);
        if (index == 1) database.delete(DBHelper.TABLE_RAS_2, null, null);
        if (index == 2) database.delete(DBHelper.TABLE_RAS_3, null, null);
        if (index == 3) database.delete(DBHelper.TABLE_RAS_4, null, null);
        if (index == 4) database.delete(DBHelper.TABLE_RAS_5, null, null);
        if (index == 5) database.delete(DBHelper.TABLE_RAS_6, null, null);
        database.close();
    }
    public void CleanSubOne(Context context, String sub){
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(TABLE_CONTACTS, "namesub=?", new String[]{sub});
        database.close();
    }
    public void CleanHWOne(String day) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        int index = Day_Name.indexOf(day);
        Cursor cursor;
        if (index == 0){
            cursor = database.query(DBHelper.TABLE_RAS_1, null, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    cv.put(KEY_RAS_HW_1, "");
                    cv.put(KEY_RAS_HW_YN_1, "0");
                    database.update(TABLE_RAS_1, cv,null,null);
                } while (cursor.moveToNext());
            }
        }
        else if (index == 1){
            cursor = database.query(DBHelper.TABLE_RAS_2, null, null, null, null, null, DBHelper.KEY_NOM_2);
            if (cursor.moveToFirst()) {
                do {
                    cv.put(KEY_RAS_HW_2, "");
                    cv.put(KEY_RAS_HW_YN_2, "0");
                    database.update(TABLE_RAS_2, cv,null,null);
                } while (cursor.moveToNext());
            }
        }
        else if (index == 2){
            cursor = database.query(DBHelper.TABLE_RAS_3, null, null, null, null, null, DBHelper.KEY_NOM_3);
            if (cursor.moveToFirst()) {
                do {
                    cv.put(KEY_RAS_HW_3, "");
                    cv.put(KEY_RAS_HW_YN_3, "0");
                    database.update(TABLE_RAS_3, cv,null,null);
                } while (cursor.moveToNext());
            }
        }
        else if (index == 3){
            cursor = database.query(DBHelper.TABLE_RAS_4, null, null, null, null, null, DBHelper.KEY_NOM_4);
            if (cursor.moveToFirst()) {
                do {
                    cv.put(KEY_RAS_HW_4, "");
                    cv.put(KEY_RAS_HW_YN_4, "0");
                    database.update(TABLE_RAS_4, cv,null,null);
                } while (cursor.moveToNext());
            }
        }
        else if (index == 4){
            cursor = database.query(DBHelper.TABLE_RAS_5, null, null, null, null, null, DBHelper.KEY_NOM_5);
            if (cursor.moveToFirst()) {
                do {
                    cv.put(KEY_RAS_HW_5, "");
                    cv.put(KEY_RAS_HW_YN_5, "0");
                    database.update(TABLE_RAS_5, cv,null,null);
                } while (cursor.moveToNext());
            }
        }
        else {
            cursor = database.query(DBHelper.TABLE_RAS_6, null, null, null, null, null, DBHelper.KEY_NOM_6);
            if (cursor.moveToFirst()) {
                do {
                    cv.put(KEY_RAS_HW_6, "");
                    cv.put(KEY_RAS_HW_YN_6, "0");
                    database.update(TABLE_RAS_6, cv,null,null);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        database.close();
    }

    public void CleanHW(Context context){
        CleanHWOne(Day_Name.get(0));
        CleanHWOne(Day_Name.get(1));
        CleanHWOne(Day_Name.get(2));
        CleanHWOne(Day_Name.get(3));
        CleanHWOne(Day_Name.get(4));
        CleanHWOne(Day_Name.get(5));
    }
    public void CleanAll(){
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(DBHelper.TABLE_CONTACTS, null, null);
        database.delete(DBHelper.TABLE_RAS_1, null, null);
        database.delete(DBHelper.TABLE_RAS_2, null, null);
        database.delete(DBHelper.TABLE_RAS_3, null, null);
        database.delete(DBHelper.TABLE_RAS_4, null, null);
        database.delete(DBHelper.TABLE_RAS_5, null, null);
        database.delete(DBHelper.TABLE_RAS_6, null, null);
        database.close();

    }

}