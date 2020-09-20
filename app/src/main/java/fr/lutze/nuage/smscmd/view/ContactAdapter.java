package fr.lutze.nuage.smscmd.view;

import android.content.ContextWrapper;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fr.lutze.nuage.smscmd.databinding.ContactItemBinding;
import fr.lutze.nuage.smscmd.databinding.FragmentContactBinding;
import fr.lutze.nuage.smscmd.model.entity.Contact;
import fr.lutze.nuage.smscmd.viewmodel.ContactViewModel;

public class ContactAdapter  extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder>{

    private List<Contact> contactList;
    private ContactAdapterListener listener;

    public ContactAdapter() {
        contactList = new ArrayList<>();
    }

    public void setListener(ContactAdapterListener contactAdapterListener) {
        listener = contactAdapterListener;
    }

    public void update(List<Contact> contactList) {
        this.contactList.clear();
        this.contactList.addAll(contactList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ContactItemBinding itemBinding = ContactItemBinding.inflate(layoutInflater, parent, false);
        itemBinding.setLifecycleOwner((AppCompatActivity)parent.getContext());
        return new ContactViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        if (contactList.size() > position) {
            Contact contact = contactList.get(position);
            holder.bind(new ContactViewModel(contact));
        }
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {
        private ContactItemBinding binding;

        public ContactViewHolder(ContactItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ContactViewModel contact) {
            binding.setContact(contact);
            binding.contactItemLayout.setOnClickListener((view -> {
                if (listener != null) {
                    listener.OnContactClicked(contact);
                }
            }));
            binding.executePendingBindings();
        }
    }

    public interface ContactAdapterListener {
        public void OnContactClicked(ContactViewModel contact);
    }
}
