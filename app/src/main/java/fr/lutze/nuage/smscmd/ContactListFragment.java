package fr.lutze.nuage.smscmd;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.lutze.nuage.smscmd.view.ContactAdapter;
import fr.lutze.nuage.smscmd.viewmodel.ContactViewModel;
import fr.lutze.nuage.smscmd.viewmodel.ContactsViewModel;

public class ContactListFragment extends Fragment {


    private ContactsViewModel contactsViewModel;
    private ContactAdapter contactAdapter;

    public ContactListFragment() {
        // Required empty public constructor
    }


    public static ContactListFragment newInstance(int contactUid) {
        ContactListFragment fragment = new ContactListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //create VM
        contactsViewModel = new ContactsViewModel(getActivity().getApplication());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_contact_list, container, false);

        //update list
        RecyclerView rv = v.findViewById(R.id.contacts_recyclerview);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        contactAdapter = new ContactAdapter();
        contactAdapter.setListener(new ContactAdapter.ContactAdapterListener() {
            @Override
            public void OnContactClicked(ContactViewModel contact) {
                NavController navController = Navigation.findNavController(rv);
                ContactListFragmentDirections.ActionContactListFragmentToContactFragment action = ContactListFragmentDirections.actionContactListFragmentToContactFragment(contact.getContact().uid);
                navController.navigate(action);
            }
        });
        rv.setAdapter(contactAdapter);
        contactsViewModel.getLdContacts().observe(getViewLifecycleOwner(), contactList -> {
            contactAdapter.update(contactList);
        });
        return v;
    }
}