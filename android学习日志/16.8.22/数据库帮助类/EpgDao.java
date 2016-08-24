package com.rojao.rojaoiadv.dbhelper;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.rojao.rojaoiadv.beans.Epg;
import com.rojao.socket.util.Logger;

public class EpgDao
{
    private static String DBName = "epg.db";


    private SQLiteDatabase db;

    private EpgOpenHelper epgHelper;

    private ArrayList<Long> difAssetType;

    public EpgDao(Context context)
    {
        epgHelper= new EpgOpenHelper(context);
        db = epgHelper.getWritableDatabase();
    }

    public Boolean haveEpgInfo(Long assetType)
    {
        Boolean b = false;
        Cursor cursor = db.query(EpgOpenHelper.T_EPG, null,
                EpgOpenHelper.EPGTable.ASSETTYPE + "=?", new String[]
                { assetType + "" }, null, null, null);

        if (cursor != null)
        {
            b = cursor.moveToFirst();
        }
        cursor.close();
        close();
        Logger.debug("HaveEpgInfo:" + b);
        return b;
    }

    public Boolean haveInfo()
    {
        Boolean b = false;
        Cursor cursor = db.query(EpgOpenHelper.T_EPG, null, null, null, null,
                null, null);
        if (cursor != null)
        {
            b = cursor.moveToFirst();
        }
        cursor.close();
        Logger.debug("HaveInfo:" + b);
        return b;
    }

    public int delEpgInfo(Long assetType)
    {
        int id = db.delete(EpgOpenHelper.T_EPG,
                EpgOpenHelper.EPGTable.ASSETTYPE + "=?", new String[]
                { assetType + "" });
        close();
        Logger.debug("DelUserInfo:" + id + "");
        return id;
    }

    public void close()
    {
        epgHelper.close();
    }

    public Long saveUserInfo(Epg epg)
    {
        ContentValues values = new ContentValues();
        values.put(EpgOpenHelper.EPGTable.ADVSUBTYPE, epg.getAdvSubType());
        values.put(EpgOpenHelper.EPGTable.ASSETTYPE, epg.getAssetType());
        values.put(EpgOpenHelper.EPGTable.CHANNELS, epg.getChannels());
        values.put(EpgOpenHelper.EPGTable.FILELOCALPATH, epg.getFileLocalPath());
        values.put(EpgOpenHelper.EPGTable.FILEPATH, epg.getFilePath());
        values.put(EpgOpenHelper.EPGTable.HREF, epg.getHref());
        Long uid = db.insert(EpgOpenHelper.T_EPG, EpgOpenHelper.EPGTable._ID,
                values);
        Logger.debug("SaveUserInfo:" + uid + "");
        return uid;
    }

    public void saveAll(List<Epg> epgList)
    {
        for (int i = 0; i < epgList.size(); i++)
        {
            ContentValues values = new ContentValues();
            values.put(EpgOpenHelper.EPGTable.ADVSUBTYPE, epgList.get(i)
                    .getAdvSubType());
            values.put(EpgOpenHelper.EPGTable.ASSETTYPE, epgList.get(i)
                    .getAssetType());
            values.put(EpgOpenHelper.EPGTable.CHANNELS, epgList.get(i)
                    .getChannels());
            values.put(EpgOpenHelper.EPGTable.FILELOCALPATH, epgList.get(i)
                    .getFileLocalPath());
            values.put(EpgOpenHelper.EPGTable.FILEPATH, epgList.get(i)
                    .getFilePath());
            values.put(EpgOpenHelper.EPGTable.HREF, epgList.get(i).getHref());
            Long uid = db.insert(EpgOpenHelper.T_EPG,
                    EpgOpenHelper.EPGTable._ID, values);
            Logger.debug("SaveUserInfo:" + uid + "");
        }
    }

    public ArrayList<Long> chooseDifInfo(List<Epg> epgList)
    {
        for (int i = 0; i < epgList.size(); i++)
        {
            if (haveEpgInfo(epgList.get(i).getAssetType()))
            {
                continue;
            }
            else
            {
                difAssetType.set(i, epgList.get(i).getAssetType());
            }
        }
        return difAssetType;
    }
    
    public ArrayList<Long> chooseDifInfoFromLocal(List<Epg> epgList)
    {
        for (int i = 0; i < epgList.size(); i++)
        {
            if (haveEpgInfo(epgList.get(i).getAssetType()))
            {
                db.query(EpgOpenHelper.T_EPG, new String[] {"assetType"}, " assetType NOT IN ('connect','answer')", 
                        null, null, null, null);
            }
            else
            {
                continue;
            }
        }
        return difAssetType;
    }

}
