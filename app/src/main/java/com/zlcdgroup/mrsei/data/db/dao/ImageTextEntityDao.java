package com.zlcdgroup.mrsei.data.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.zlcdgroup.mrsei.data.entity.ImageTextEntity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "tb_imagetext".
*/
public class ImageTextEntityDao extends AbstractDao<ImageTextEntity, Long> {

    public static final String TABLENAME = "tb_imagetext";

    /**
     * Properties of entity ImageTextEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Text = new Property(1, String.class, "text", false, "TEXT");
        public final static Property LocalFilePath = new Property(2, String.class, "localFilePath", false, "LOCAL_FILE_PATH");
        public final static Property BySort = new Property(3, int.class, "bySort", false, "BY_SORT");
        public final static Property ServerFilePath = new Property(4, String.class, "serverFilePath", false, "SERVER_FILE_PATH");
    }


    public ImageTextEntityDao(DaoConfig config) {
        super(config);
    }
    
    public ImageTextEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"tb_imagetext\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"TEXT\" TEXT," + // 1: text
                "\"LOCAL_FILE_PATH\" TEXT," + // 2: localFilePath
                "\"BY_SORT\" INTEGER NOT NULL ," + // 3: bySort
                "\"SERVER_FILE_PATH\" TEXT);"); // 4: serverFilePath
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"tb_imagetext\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ImageTextEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String text = entity.getText();
        if (text != null) {
            stmt.bindString(2, text);
        }
 
        String localFilePath = entity.getLocalFilePath();
        if (localFilePath != null) {
            stmt.bindString(3, localFilePath);
        }
        stmt.bindLong(4, entity.getBySort());
 
        String serverFilePath = entity.getServerFilePath();
        if (serverFilePath != null) {
            stmt.bindString(5, serverFilePath);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ImageTextEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String text = entity.getText();
        if (text != null) {
            stmt.bindString(2, text);
        }
 
        String localFilePath = entity.getLocalFilePath();
        if (localFilePath != null) {
            stmt.bindString(3, localFilePath);
        }
        stmt.bindLong(4, entity.getBySort());
 
        String serverFilePath = entity.getServerFilePath();
        if (serverFilePath != null) {
            stmt.bindString(5, serverFilePath);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public ImageTextEntity readEntity(Cursor cursor, int offset) {
        ImageTextEntity entity = new ImageTextEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // text
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // localFilePath
            cursor.getInt(offset + 3), // bySort
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4) // serverFilePath
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ImageTextEntity entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setText(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setLocalFilePath(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setBySort(cursor.getInt(offset + 3));
        entity.setServerFilePath(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(ImageTextEntity entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(ImageTextEntity entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ImageTextEntity entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
