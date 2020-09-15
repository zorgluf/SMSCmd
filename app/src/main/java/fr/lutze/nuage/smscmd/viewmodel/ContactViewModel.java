package fr.lutze.nuage.smscmd.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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

    public LiveData<String> getLdName() {
        return ldName;
    }

    public LiveData<String> getLdPhoneNumber() {
        return ldPhoneNumber;
    }
}
