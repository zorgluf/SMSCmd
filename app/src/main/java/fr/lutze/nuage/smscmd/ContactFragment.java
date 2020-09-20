package fr.lutze.nuage.smscmd;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.lutze.nuage.smscmd.databinding.FragmentContactBinding;
import fr.lutze.nuage.smscmd.viewmodel.ContactViewModel;
import fr.lutze.nuage.smscmd.viewmodel.ContactsViewModel;

public class ContactFragment extends Fragment {

    private static final String ARG_CONTACT_UID = "contact_uid";

    private ContactViewModel contactViewModel;

    public ContactFragment() {
    }

    public static ContactFragment newInstance(int contactUid) {
        ContactFragment fragment = new ContactFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_CONTACT_UID, contactUid);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            int contactUid = getArguments().getInt(ARG_CONTACT_UID);
            contactViewModel = ContactsViewModel.getInstance(getActivity().getApplication()).getContactViewModel(contactUid);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentContactBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_contact, container, false);
        binding.setContact(contactViewModel);
        return binding.getRoot();
    }
}