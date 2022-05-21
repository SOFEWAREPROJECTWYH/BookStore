package com.lut.wyh.BookStore.sqlite;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class ObjectOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "user.db";//数据库文件名

    private volatile static SQLiteDatabase INSTANCE;
    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    public ObjectOpenHelper(Context context) {
        this(context, DB_NAME, null, 1);
        mContext=context;
    }

    public static SQLiteDatabase getInstance() {
        if (INSTANCE==null){
            synchronized (SQLiteDatabase.class){
                if (INSTANCE == null) {
                    INSTANCE = new ObjectOpenHelper(mContext).getWritableDatabase();
                    return INSTANCE;
                }
                return INSTANCE;
            }
        }
        return INSTANCE;

    }
    public ObjectOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public ObjectOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE `user`  (\n" +
                "  `id` int(0) NOT NULL,\n" +
                "  `name` varchar(255) NOT NULL,\n" +
                "  `password` varchar(255) NOT NULL,\n" +
                "  `phonenumber` varchar(11) DEFAULT NULL,\n" +
                "  `email` varchar(255) DEFAULT NULL,\n" +
                "  `authority` int(0) DEFAULT NULL,\n" +
                "  `shoppingtrolley` varchar(10000) DEFAULT NULL,\n" +
                "  `url` varchar(10000) DEFAULT NULL,\n" +
                "  `major` varchar(255) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`id`)\n" +
                ")";
        //创建表
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
