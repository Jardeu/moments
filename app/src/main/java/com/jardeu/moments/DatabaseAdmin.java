package com.jardeu.moments;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jardeu.moments.model.Memory;
import com.jardeu.moments.model.Tag;

public class DatabaseAdmin extends SQLiteOpenHelper {
    public DatabaseAdmin(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE memories(_id INTEGER PRIMARY KEY, title TEXT, description TEXT, date TEXT, image TEXT)");
        db.execSQL("CREATE TABLE tags(_id INTEGER PRIMARY KEY, name TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase banco, int i, int i1) {

    }

    public static void readMemoriesData(SQLiteDatabase db) {
        try (Cursor result = db.rawQuery("SELECT * FROM memories", null)) {
            if (result != null && result.moveToFirst()) {
                do {
                    Memory m = new Memory();
                    m.setTitle(result.getString(1));
                    m.setDescription(result.getString(2));
                    m.setDate(result.getString(3));
                    m.setImage(result.getString(4));

                    System.out.println(result.getString(0));
                    System.out.println(result.getString(1));
                    System.out.println(result.getString(2));
                    System.out.println(result.getString(3));

                    Memory.memoriesList.add(m);
                } while (result.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public static void saveMemories(SQLiteDatabase db) {
        try {
            db.delete("memories", null, null);

            for (Memory item : Memory.memoriesList) {
                ContentValues registro = new ContentValues();
                registro.put("title", item.getTitle());
                registro.put("description", item.getDescription());
                registro.put("date", item.getDate());
                registro.put("image", item.getImage());

                db.insert("memories", null, registro);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public static void readTagsData(SQLiteDatabase db) {
        try (Cursor result = db.rawQuery("SELECT * FROM tags", null)) {
            if (result != null && result.moveToFirst()) {
                do {
                    Tag t = new Tag();
                    t.setName(result.getString(0));

                    Tag.tagsList.add(t);
                } while (result.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public static void saveTags(SQLiteDatabase db) {
        try {
            db.delete("tags", null, null);

            for (Memory item : Memory.memoriesList) {
                ContentValues registro = new ContentValues();
                registro.put("title", item.getTitle());

                db.insert("tags", null, registro);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }
}
