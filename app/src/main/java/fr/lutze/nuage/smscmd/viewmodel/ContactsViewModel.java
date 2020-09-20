package fr.lutze.nuage.smscmd.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.room.Room;

import java.util.List;

import fr.lutze.nuage.smscmd.model.SMSCmdDatabase;
import fr.lutze.nuage.smscmd.model.entity.Contact;

public class ContactsViewModel extends AndroidViewModel {

    private SMSCmdDatabase db;
    private LiveData<List<Contact>> ldContacts;
    private static ContactsViewModel contactsViewModel;

    public ContactsViewModel(@NonNull Application application) {
        super(application);
        //init db
        db = Room.databaseBuilder(application,SMSCmdDatabase.class, "smscmddata")
                .fallbackToDestructiveMigration()
                .build();
        //init livedata
        ldContacts = Transformations.map(db.contactDao().getAll(), contacts -> {
            return contacts;
        });
    }

    public static ContactsViewModel getInstance(Application application) {
        if (contactsViewModel == null) {
            contactsViewModel = new ContactsViewModel(application);
        }
        return contactsViewModel;
    }

    public ContactViewModel getContactViewModel(int contactUid) {
        return new ContactViewModel(db,contactUid);
    }

    public LiveData<List<Contact>> getLdContacts() {
        return ldContacts;
    }

    public void addContact(String name, String phoneNumber) {
        Contact c = new Contact();
        c.cn = name;
        c.phone = phoneNumber;
        new Thread(new Runnable() {
            @Override
            public void run() {
                db.contactDao().insertAll(c);
            }
        }).start();
    }
}
