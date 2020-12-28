package com.example.parking;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String COL_1 ="ID";
    public static final String COL_2 ="email";
    public static final String COL_3 ="password";


    public DataBaseHelper(Context context) {
        super(context, "parking.sqLiteDatabase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE registeruser (ID INTEGER PRIMARY  KEY AUTOINCREMENT, email TEXT, password TEXT)");
        sqLiteDatabase.execSQL("Create table city ( cityname text primary key,  citynumber int)");
        sqLiteDatabase.execSQL("Create table park(parkName text primary key, cityNamee text,  parkSpaces int, takenSpaces int,lat double, long double)");
        sqLiteDatabase.execSQL("Create table reservation(rowid int primary key, userR text, cityR text, parkR text, dateR text, timeR text )");
        parkData(sqLiteDatabase);
        cityData(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS registeruser ");
        sqLiteDatabase.execSQL("drop table if exists city ");
        sqLiteDatabase.execSQL("drop table if exists park");
        sqLiteDatabase.execSQL("drop table if exists reservation");
        onCreate(sqLiteDatabase);


    }


    public long addUser(String email, String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",email);
        contentValues.put("password",password);
        long res = sqLiteDatabase.insert("registeruser",null,contentValues);
        sqLiteDatabase.close();
        return  res;
    }

    public boolean checkUser(String email, String password){
        String[] columns = { COL_1 };
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String selection = "email" + "=?" + " and " + "password"+ "=?";
        String[] selectionArgs = { email, password };
        Cursor cursor = sqLiteDatabase.query("registeruser",columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        sqLiteDatabase.close();

        if(count>0)
            return  true;
        else
            return  false;
    }


    public void cityData(SQLiteDatabase sqLiteDatabase) {

        String[] cities = {"Скопје", "Велес", "Битола", "Охрид","Штип", "Гевгелија"};
        int[] numbers = {5, 3, 2, 3, 3, 4};
        // Create a container for the data.
        ContentValues values = new ContentValues();

        for (int i=0; i < cities.length;i++) {
            // Put column/value pairs into the container, overriding existing values.
            values.put("cityname", cities[i]);
            values.put("citynumber", numbers[i]);
            sqLiteDatabase.insert("city", null, values);
        }
    }

    private void parkData(SQLiteDatabase sqLiteDatabase) {
        String[] parkings = {"City Mall", "Capitol Mall", "Беко", "26 јули", "Разловечко Востание",
                "Центар-Велес", "Автобуска Станица-Велес", "Железничка Станица-Велес",
                "Широк Сокак","Т.Ц. Јавор",
                "Пристаниште","Билјанини Извори","Горна Порта",
                "Автобуска Станица-Штип", "8ми Ноември", "Исар",
                "Автобуска Станица-Гевгелија", "Железничка Станица-Гевгелија", "Аполонија", "Суд"
        };

        String[] cityP= {"Скопје", "Скопје", "Скопје", "Скопје", "Скопје",
                "Велес", "Велес", "Велес",
                "Битола","Битола",
                "Охрид","Охрид","Охрид",
                "Штип", "Штип", "Штип",
                "Гевгелија", "Гевгелија", "Гевгелија", "Гевгелија"
        };
        double[] latitude = {42.004074, 41.985801, 41.993219, 41.989598, 41.993522,
                41.715436, 42.715653, 41.723962,
                41.027460, 41.033341,
                41.112347, 41.108390, 41.115140,
                41.741100, 41.745783, 41.736147,
                41.143953, 41.143226, 41.140830, 41.138896,

        };
        double[] longitude = {21.391820, 21.466524, 21.428451, 21.432345, 21.436317,
                21.770641, 21.786007, 21.769022,
                21.336464, 21.336622,
                20.799450, 20,813867, 20.794917,
                22.189383, 22.190553, 22.204870,
                22.511666, 22.512150, 22.504380, 22.502851,

        };
        int[] free = {45, 24, 20, 33, 17,
                15, 29, 34,
                10, 20,
                14, 23, 32,
                25, 10, 20,
                16, 55, 23, 28
        };
        int taken []= {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        ContentValues values = new ContentValues();

        for (int i=0; i < parkings.length;i++) {
            values.put("parkName", parkings[i]);
            values.put("cityNamee", cityP[i]);
            values.put("parkSpaces", free[i]);
            values.put("takenSpaces", taken[i]);
            values.put("lat", latitude[i]);
            values.put("long",longitude[i] );
            sqLiteDatabase.insert("park", null, values);
        }
    }

    public City query(int position) {
        String query = "SELECT * FROM city ORDER BY cityname ASC " + "LIMIT " + position + ",1";

        Cursor cursor = null;
        City entry = new City();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        cursor = sqLiteDatabase.rawQuery(query, null);
        cursor.moveToFirst();
        entry.setCityName(cursor.getString(cursor.getColumnIndex("cityname")));
        entry.setCityNumber(cursor.getColumnIndex("citynumber"));
        cursor.close();
        return entry;

    }
    public Parking querypark(int position) {
        String query1 = "SELECT * FROM park ORDER BY parkName ASC " +
                "LIMIT " + position + ",1";

        Cursor cursor = null;
        Parking entry = new Parking();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        cursor = sqLiteDatabase.rawQuery(query1, null);
        cursor.moveToFirst();

        entry.setParkName(cursor.getString(cursor.getColumnIndex("parkName")));
        entry.setParkCity(cursor.getString(cursor.getColumnIndex("cityNamee")));
        entry.setParkSpaces(cursor.getColumnIndex("parkSpaces"));
        entry.setTakenSpaces(cursor.getColumnIndex("takenSpaces"));
        cursor.close();
        return entry;

    }



    public long count() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        return DatabaseUtils.queryNumEntries(this.getReadableDatabase(), "city");
    }

    public long countpark(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        return DatabaseUtils.queryNumEntries(this.getReadableDatabase(), "park");
    }


    public boolean insertReservation(String user, String city, String park, String date, String time){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("userR", user);
        contentValues.put("cityR", city);
        contentValues.put("parkR", park);
        contentValues.put("dateR", date);
        contentValues.put("timeR", time);
        long ins = sqLiteDatabase.insert("reservation", null, contentValues);
        if(ins == -1)
            return false;
        else return true;
    }


//    public int ReservationPerUser(String user){
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery("Select * from reservation where userR=?", new String[]{user});
//        int count = 0;
//        if (cursor.moveToFirst()) {
//            count = cursor.getCount();
//            cursor.close();
//            return count;
//        } else return 0;
//    }
//

    public int numberResAtDateTime(String date, String time, String park) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from reservation where dateR=? and timeR=? and parkR=?", new String[]{date, time, park});
        int number = 0;
        if(cursor.moveToFirst()){
            number = cursor.getCount();
            cursor.close();
            return number;
        }
        else
            return 0;
    }


    public double latitude (String park){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from park where parkName=?", new String[]{park});
        if(cursor.moveToFirst()){
            return cursor.getDouble(4);
        }
        else
            return 0;
    }

    public double longitude (String park){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from park where parkName=?", new String[]{park});
        if(cursor.moveToFirst()){
            return cursor.getDouble(5);
        }
        else
            return 0;
    }
}
