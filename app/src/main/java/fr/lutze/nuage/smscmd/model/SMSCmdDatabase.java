package fr.lutze.nuage.smscmd.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import fr.lutze.nuage.smscmd.model.dao.ContactDao;
import fr.lutze.nuage.smscmd.model.entity.Contact;

@Database(entities = {Contact.class}, version = 2)
public abstract class SMSCmdDatabase extends RoomDatabase {
    public abstract ContactDao contactDao();
}