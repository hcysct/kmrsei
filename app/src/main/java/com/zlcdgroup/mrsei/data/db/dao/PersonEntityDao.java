package com.zlcdgroup.mrsei.data.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.zlcdgroup.mrsei.data.entity.PersonEntity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "tb_person".
*/
public class PersonEntityDao extends AbstractDao<PersonEntity, Long> {

    public static final String TABLENAME = "tb_person";

    /**
     * Properties of entity PersonEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property PersonId = new Property(1, String.class, "personId", false, "PERSON_ID");
        public final static Property PersonAccount = new Property(2, String.class, "personAccount", false, "PERSON_ACCOUNT");
        public final static Property PersonPassword = new Property(3, String.class, "personPassword", false, "PERSON_PASSWORD");
        public final static Property PersonName = new Property(4, String.class, "personName", false, "PERSON_NAME");
        public final static Property PersonIdCard = new Property(5, String.class, "personIdCard", false, "PERSON_ID_CARD");
        public final static Property PersonTel = new Property(6, String.class, "personTel", false, "PERSON_TEL");
        public final static Property PersonUsestatus = new Property(7, Integer.class, "personUsestatus", false, "PERSON_USESTATUS");
        public final static Property PersonMemo = new Property(8, String.class, "personMemo", false, "PERSON_MEMO");
        public final static Property PersonWorknumber = new Property(9, String.class, "personWorknumber", false, "PERSON_WORKNUMBER");
        public final static Property PersonRfid = new Property(10, String.class, "personRfid", false, "PERSON_RFID");
        public final static Property PersonType = new Property(11, Integer.class, "personType", false, "PERSON_TYPE");
        public final static Property PersonBindAuth = new Property(12, Integer.class, "personBindAuth", false, "PERSON_BIND_AUTH");
        public final static Property LoginTime = new Property(13, Long.class, "loginTime", false, "LOGIN_TIME");
    }


    public PersonEntityDao(DaoConfig config) {
        super(config);
    }
    
    public PersonEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"tb_person\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"PERSON_ID\" TEXT," + // 1: personId
                "\"PERSON_ACCOUNT\" TEXT," + // 2: personAccount
                "\"PERSON_PASSWORD\" TEXT," + // 3: personPassword
                "\"PERSON_NAME\" TEXT," + // 4: personName
                "\"PERSON_ID_CARD\" TEXT," + // 5: personIdCard
                "\"PERSON_TEL\" TEXT," + // 6: personTel
                "\"PERSON_USESTATUS\" INTEGER," + // 7: personUsestatus
                "\"PERSON_MEMO\" TEXT," + // 8: personMemo
                "\"PERSON_WORKNUMBER\" TEXT," + // 9: personWorknumber
                "\"PERSON_RFID\" TEXT," + // 10: personRfid
                "\"PERSON_TYPE\" INTEGER," + // 11: personType
                "\"PERSON_BIND_AUTH\" INTEGER," + // 12: personBindAuth
                "\"LOGIN_TIME\" INTEGER);"); // 13: loginTime
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"tb_person\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, PersonEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String personId = entity.getPersonId();
        if (personId != null) {
            stmt.bindString(2, personId);
        }
 
        String personAccount = entity.getPersonAccount();
        if (personAccount != null) {
            stmt.bindString(3, personAccount);
        }
 
        String personPassword = entity.getPersonPassword();
        if (personPassword != null) {
            stmt.bindString(4, personPassword);
        }
 
        String personName = entity.getPersonName();
        if (personName != null) {
            stmt.bindString(5, personName);
        }
 
        String personIdCard = entity.getPersonIdCard();
        if (personIdCard != null) {
            stmt.bindString(6, personIdCard);
        }
 
        String personTel = entity.getPersonTel();
        if (personTel != null) {
            stmt.bindString(7, personTel);
        }
 
        Integer personUsestatus = entity.getPersonUsestatus();
        if (personUsestatus != null) {
            stmt.bindLong(8, personUsestatus);
        }
 
        String personMemo = entity.getPersonMemo();
        if (personMemo != null) {
            stmt.bindString(9, personMemo);
        }
 
        String personWorknumber = entity.getPersonWorknumber();
        if (personWorknumber != null) {
            stmt.bindString(10, personWorknumber);
        }
 
        String personRfid = entity.getPersonRfid();
        if (personRfid != null) {
            stmt.bindString(11, personRfid);
        }
 
        Integer personType = entity.getPersonType();
        if (personType != null) {
            stmt.bindLong(12, personType);
        }
 
        Integer personBindAuth = entity.getPersonBindAuth();
        if (personBindAuth != null) {
            stmt.bindLong(13, personBindAuth);
        }
 
        Long loginTime = entity.getLoginTime();
        if (loginTime != null) {
            stmt.bindLong(14, loginTime);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, PersonEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String personId = entity.getPersonId();
        if (personId != null) {
            stmt.bindString(2, personId);
        }
 
        String personAccount = entity.getPersonAccount();
        if (personAccount != null) {
            stmt.bindString(3, personAccount);
        }
 
        String personPassword = entity.getPersonPassword();
        if (personPassword != null) {
            stmt.bindString(4, personPassword);
        }
 
        String personName = entity.getPersonName();
        if (personName != null) {
            stmt.bindString(5, personName);
        }
 
        String personIdCard = entity.getPersonIdCard();
        if (personIdCard != null) {
            stmt.bindString(6, personIdCard);
        }
 
        String personTel = entity.getPersonTel();
        if (personTel != null) {
            stmt.bindString(7, personTel);
        }
 
        Integer personUsestatus = entity.getPersonUsestatus();
        if (personUsestatus != null) {
            stmt.bindLong(8, personUsestatus);
        }
 
        String personMemo = entity.getPersonMemo();
        if (personMemo != null) {
            stmt.bindString(9, personMemo);
        }
 
        String personWorknumber = entity.getPersonWorknumber();
        if (personWorknumber != null) {
            stmt.bindString(10, personWorknumber);
        }
 
        String personRfid = entity.getPersonRfid();
        if (personRfid != null) {
            stmt.bindString(11, personRfid);
        }
 
        Integer personType = entity.getPersonType();
        if (personType != null) {
            stmt.bindLong(12, personType);
        }
 
        Integer personBindAuth = entity.getPersonBindAuth();
        if (personBindAuth != null) {
            stmt.bindLong(13, personBindAuth);
        }
 
        Long loginTime = entity.getLoginTime();
        if (loginTime != null) {
            stmt.bindLong(14, loginTime);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public PersonEntity readEntity(Cursor cursor, int offset) {
        PersonEntity entity = new PersonEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // personId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // personAccount
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // personPassword
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // personName
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // personIdCard
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // personTel
            cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7), // personUsestatus
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // personMemo
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // personWorknumber
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // personRfid
            cursor.isNull(offset + 11) ? null : cursor.getInt(offset + 11), // personType
            cursor.isNull(offset + 12) ? null : cursor.getInt(offset + 12), // personBindAuth
            cursor.isNull(offset + 13) ? null : cursor.getLong(offset + 13) // loginTime
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, PersonEntity entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setPersonId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPersonAccount(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setPersonPassword(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setPersonName(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setPersonIdCard(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setPersonTel(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setPersonUsestatus(cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7));
        entity.setPersonMemo(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setPersonWorknumber(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setPersonRfid(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setPersonType(cursor.isNull(offset + 11) ? null : cursor.getInt(offset + 11));
        entity.setPersonBindAuth(cursor.isNull(offset + 12) ? null : cursor.getInt(offset + 12));
        entity.setLoginTime(cursor.isNull(offset + 13) ? null : cursor.getLong(offset + 13));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(PersonEntity entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(PersonEntity entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(PersonEntity entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
