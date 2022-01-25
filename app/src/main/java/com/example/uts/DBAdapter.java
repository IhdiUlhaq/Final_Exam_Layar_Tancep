package com.example.uts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;



    public class DBAdapter {
        myDbHelper myhelper;

        public DBAdapter(Context context) { myhelper = new myDbHelper(context);}

        public void insertData(String name) {
            SQLiteDatabase dbb = myhelper.getWritableDatabase();

            ContentValues cv = new ContentValues();
            cv.put(myDbHelper.NAME, name);

            dbb.insert(myDbHelper.TABLE_NAME, null, cv);



            // if(result == 1) {
            //
            //          Toast.makeText(myhelper.context, "Layar Tancep Gagal ditambahken ke Bookmark", Toast.LENGTH_LONG).show();
            //  }else {
            //    Toast.makeText(myhelper.context, "layar Tancep ditambahken ke Bookmark", Toast.LENGTH_LONG).show();
            // }
        }

        public long removeData(String name) {
            SQLiteDatabase dbb = myhelper.getWritableDatabase();
            String selection = myDbHelper.NAME + " LIKE ? ";
            String[] selectionArgs = {name};
            long id = dbb.delete(myDbHelper.TABLE_NAME, selection, selectionArgs);


        //    if(id >= 1) {
        //    Toast.makeText(myhelper.context, "Layar Tancep terbapus dari Bookmark", Toast.LENGTH_LONG).show();
        //    }else {
        //        Toast.makeText(myhelper.context, "Layar Tancep e udah di hapus", Toast.LENGTH_LONG).show();
        //   }
            return id;
        }

        public Cursor getData() {
            SQLiteDatabase db = myhelper.getWritableDatabase();
            Cursor movie_list = db.rawQuery("SELECT * FROM " + myDbHelper.TABLE_NAME,null);
            return movie_list;
        }

        //Konfigurasi SQLite
        static class myDbHelper extends SQLiteOpenHelper {
            private static final String DATABASE_NAME = "myDatabase";    // Database Name
            private static final String TABLE_NAME = "myTable";   // Table Name
            private static final int DATABASE_Version = 1;    // Database Version
            private static final String UID="_id";     // Column I (Primary Key)
            private static final String NAME = "Name";    //Column II
            private static final String MyPASSWORD= "Password";    // Column III
            private static final String CREATE_TABLE = "CREATE TABLE "+ TABLE_NAME +
                    " ("+ UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ NAME +" VARCHAR(255));";
            private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+ TABLE_NAME;
            private Context context;

            public myDbHelper(Context context) {
                super(context, DATABASE_NAME,null, DATABASE_Version);
                this.context=context;
            }

            @Override
            public void onCreate(SQLiteDatabase db) {
                try {
                    db.execSQL(CREATE_TABLE);
                } catch (Exception e) {
                    Toast.makeText(context,"" + e,
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                try {
                    Toast.makeText(context,"OnUpgrade",
                            Toast.LENGTH_LONG).show();
                    db.execSQL(DROP_TABLE);
                    onCreate(db);
                } catch (Exception e) {
                    Toast.makeText(context,"" + e,
                            Toast.LENGTH_LONG).show();
                }
            }
        }


    }

