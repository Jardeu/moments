package com.jardeu.moments;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jardeu.moments.model.Memory;
import com.jardeu.moments.model.Category;

public class DatabaseAdmin extends SQLiteOpenHelper {
    public DatabaseAdmin(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE categories(id INTEGER PRIMARY KEY, name TEXT)");
        db.execSQL(
                "CREATE TABLE memories(id INTEGER PRIMARY KEY, title TEXT, description TEXT, date TEXT, " +
                 "image TEXT, category_id INTEGER, FOREIGN KEY (category_id) REFERENCES categories(id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase banco, int i, int i1) {

    }

    public static void readMemoriesData(SQLiteDatabase db) {
        try (Cursor result = db.rawQuery("SELECT * FROM memories", null)) {
            if (result != null && result.moveToFirst()) {
                do {
                    Memory m = new Memory();
                    m.setId(result.getInt(0));
                    m.setTitle(result.getString(1));
                    m.setDescription(result.getString(2));
                    m.setDate(result.getString(3));
                    m.setImage(result.getString(4));
                    m.setCategory_id(result.getInt(5));

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

    public static void readCategoriesData(SQLiteDatabase db) {
        try (Cursor result = db.rawQuery("SELECT * FROM categories", null)) {
            if (result != null && result.moveToFirst()) {
                do {
                    Category c = new Category();
                    c.setId(result.getInt(0));
                    c.setName(result.getString(1));

                    Category.categoriesList.add(c);
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

    public static void saveCategories(SQLiteDatabase db) {
        try {
            db.delete("categories", null, null);

            for (Category item : Category.categoriesList) {
                ContentValues registro = new ContentValues();
                registro.put("name", item.getName());

                db.insert("categories", null, registro);
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
