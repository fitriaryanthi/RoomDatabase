package com.example.tugas2;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

@Dao
public interface MobilDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertMobil(Mobil mobil);

    @Update
    int updateMobil(Mobil mobil);

    @Delete
    int deleteMobil(Mobil mobil);

    @Query("SELECT * FROM tmobil")
    Mobil[] selectAllMobils();

    @Query("SELECT * FROM tmobil WHERE mobilId = :id LIMIT 1")
    Mobil selectMobilDetail(int id);

}
