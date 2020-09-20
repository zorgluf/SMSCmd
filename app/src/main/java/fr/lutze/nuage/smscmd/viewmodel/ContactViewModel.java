package fr.lutze.nuage.smscmd.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import fr.lutze.nuage.smscmd.model.SMSCmdDatabase;
import fr.lutze.nuage.smscmd.model.entity.Contact;

public class ContactViewModel extends ViewModel {

    private Contact contact;
    private MutableLiveData<String> ldName = new MutableLiveData<>();
    private MutableLiveData<String> ldPhoneNumber = new MutableLiveData<>();

    public ContactViewModel(Contact contact) {
        this.contact = contact;
        ldName.postValue(contact.cn);
        ldPhoneNumber.postValue(contact.phone);
    }

    public ContactViewModel(SMSCmdDatabase db, int contactUid) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                contact = db.contactDao().findById(contactUid);
                ldName.postValue(contact.cn);
                ldPhoneNumber.postValue(contact.phone);
            }
        }).start();
    }

    public LiveData<String> getLdName() {
        return ldName;
    }

    public LiveData<String> getLdPhoneNumber() {
        return ldPhoneNumber;
    }

    public Contact getContact() {
        return contact;
    }
}
