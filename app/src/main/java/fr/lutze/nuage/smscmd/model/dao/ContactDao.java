package fr.lutze.nuage.smscmd.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import fr.lutze.nuage.smscmd.model.entity.Contact;

@Dao
public interface ContactDao {
    @Query("SELECT * FROM contact")
    LiveData<List<Contact>> getAll();

    @Query("SELECT * FROM contact WHERE uid LIKE :uid LIMIT 1")
    LiveData<Contact> findLdById(int uid);

    @Query("SELECT * FROM contact WHERE uid LIKE :uid LIMIT 1")
    Contact findById(int uid);

    @Insert
    void insertAll(Contact... contacts);

    @Delete
    void delete(Contact contact);
}
