package com.rojao.rojaoiadv.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * 创建数据库类
 *
 * <p>detailed comment
 * @author naiwu 2016-8-23
 * @see
 * @since 1.0
 */
public class EpgOpenHelper extends SQLiteOpenHelper
{
    public static final String T_EPG = "epg";

    public class EPGTable implements BaseColumns
    {// 就是会默认给我们添加一列 _id
        public static final String ASSETTYPE = "assetType";

        public static final String CHANNELS = "channels";

        public static final String FILEPATH = "filePath";
        
        public static final String FILELOCALPATH = "fileLocalPath";

        public static final String HREF = "href";
        
        public static final String ADVSUBTYPE = "advSubType";
    }

    public EpgOpenHelper(Context context)
    {
        super(context, "epg.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String sql = "CREATE TABLE " + T_EPG
                + "(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + EPGTable.ASSETTYPE + " INTEGER, " + EPGTable.CHANNELS
                + " TEXT, " + EPGTable.FILEPATH + " TEXT, "+ EPGTable.ADVSUBTYPE
                + " TEXT, "+ EPGTable.FILELOCALPATH + " TEXT, "+ EPGTable.HREF + " TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}
