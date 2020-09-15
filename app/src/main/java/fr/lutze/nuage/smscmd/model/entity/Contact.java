package fr.lutze.nuage.smscmd.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Contact {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "common_name")
    public String cn;

    @ColumnInfo(name = "phone")
    public String phone;

}
