package com.bignerdranch.android.criminalintent;

import CrimeDatabase.CrimeTypeConverters;
import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import java.lang.Class;
import java.lang.Exception;
import java.lang.IllegalStateException;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class CrimeDao_Impl implements CrimeDao {
  private final RoomDatabase __db;

  private final CrimeTypeConverters __crimeTypeConverters = new CrimeTypeConverters();

  public CrimeDao_Impl(RoomDatabase __db) {
    this.__db = __db;
  }

  @Override
  public LiveData<List<Crime>> getCrimes() {
    final String _sql = "SELECT * FROM crime";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"crime"}, false, new Callable<List<Crime>>() {
      @Override
      public List<Crime> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfIsSolved = CursorUtil.getColumnIndexOrThrow(_cursor, "isSolved");
          final List<Crime> _result = new ArrayList<Crime>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Crime _item;
            final UUID _tmpId;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfId);
            }
            final UUID _tmp_1 = __crimeTypeConverters.toUUID(_tmp);
            if(_tmp_1 == null) {
              throw new IllegalStateException("Expected non-null java.util.UUID, but it was null.");
            } else {
              _tmpId = _tmp_1;
            }
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final Date _tmpDate;
            final Long _tmp_2;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getLong(_cursorIndexOfDate);
            }
            final Date _tmp_3 = __crimeTypeConverters.toDate(_tmp_2);
            if(_tmp_3 == null) {
              throw new IllegalStateException("Expected non-null java.util.Date, but it was null.");
            } else {
              _tmpDate = _tmp_3;
            }
            final boolean _tmpIsSolved;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsSolved);
            _tmpIsSolved = _tmp_4 != 0;
            _item = new Crime(_tmpId,_tmpTitle,_tmpDate,_tmpIsSolved);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<Crime> getCrime(final UUID id) {
    final String _sql = "SELECT * FROM crime WHERE id=(?)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final String _tmp = __crimeTypeConverters.fromUUID(id);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"crime"}, false, new Callable<Crime>() {
      @Override
      public Crime call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfIsSolved = CursorUtil.getColumnIndexOrThrow(_cursor, "isSolved");
          final Crime _result;
          if(_cursor.moveToFirst()) {
            final UUID _tmpId;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfId);
            }
            final UUID _tmp_2 = __crimeTypeConverters.toUUID(_tmp_1);
            if(_tmp_2 == null) {
              throw new IllegalStateException("Expected non-null java.util.UUID, but it was null.");
            } else {
              _tmpId = _tmp_2;
            }
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final Date _tmpDate;
            final Long _tmp_3;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getLong(_cursorIndexOfDate);
            }
            final Date _tmp_4 = __crimeTypeConverters.toDate(_tmp_3);
            if(_tmp_4 == null) {
              throw new IllegalStateException("Expected non-null java.util.Date, but it was null.");
            } else {
              _tmpDate = _tmp_4;
            }
            final boolean _tmpIsSolved;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfIsSolved);
            _tmpIsSolved = _tmp_5 != 0;
            _result = new Crime(_tmpId,_tmpTitle,_tmpDate,_tmpIsSolved);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
