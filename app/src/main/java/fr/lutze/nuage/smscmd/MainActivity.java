package fr.lutze.nuage.smscmd;

import android.Manifest;
import android.app.FragmentTransaction;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import fr.lutze.nuage.smscmd.view.ContactAdapter;
import fr.lutze.nuage.smscmd.view.ImportContactsFragment;
import fr.lutze.nuage.smscmd.viewmodel.ContactsViewModel;

public class MainActivity extends AppCompatActivity {

    private ContactsViewModel contactsViewModel;
    private ContactAdapter contactAdapter;

    private int PERMISSIONS_REQUEST_READ_CONTACTS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //create VM
        contactsViewModel = new ContactsViewModel(getApplication());

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ask contact read permission
                if (ContextCompat.checkSelfPermission(view.getContext(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
                } else {
                    ImportContactsFragment dialog = new ImportContactsFragment(contactsViewModel);
                    dialog.show(getSupportFragmentManager(), "importcontactdialog");
                }
            }
        });

        //update list
        RecyclerView rv = findViewById(R.id.contacts_recyclerview);
        rv.setLayoutManager(new LinearLayoutManager(this));
        contactAdapter = new ContactAdapter();
        rv.setAdapter(contactAdapter);
        contactsViewModel.getLdContacts().observe(this, contactList -> {
            contactAdapter.update(contactList);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}