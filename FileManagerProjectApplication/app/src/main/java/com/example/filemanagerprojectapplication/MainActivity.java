package com.example.filemanagerprojectapplication;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.filemanagerprojectapplication.fragments.InternalFragment;
import com.example.filemangerprojectaplication.R;
import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public List<StorageVolume> storageList = new ArrayList<>(2);

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = findViewById(R.id.nav_view);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        File[] files = null;
        StorageManager sm = (StorageManager) getSystemService(Context.STORAGE_SERVICE);
        List<StorageVolume> storageVolumes = sm.getStorageVolumes();

        storageList = sm.getStorageVolumes();

        NavigationView nv = findViewById(R.id.nav_view);
        var menu = nv.getMenu();
        menu.clear();

        for (StorageVolume volume : storageList) {

            String description = volume.getDescription(this);

            //MenuItem item = menu.add(Menu.NONE, storageList.indexOf(volume), Menu.NONE, description);
            MenuItem item = menu.add(R.id.group_storages, storageList.indexOf(volume), Menu.NONE, description);

            item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(@NonNull MenuItem item) {
                    openFileFragment(item);
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return false;
                }
            });
        }
    }

    private void openFileFragment(MenuItem i) {
        Bundle bundle = new Bundle();
        var sv = storageList.get(i.getItemId());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            bundle.putString("path", sv.getDirectory().getAbsolutePath());
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
            bundle.putString("path", getLegacyStoragePath(sv));
        }
        Fragment fragment = new InternalFragment();
        fragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    private String getLegacyStoragePath(StorageVolume sv) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Method getPathMethod = StorageVolume.class.getMethod("getPath");
                return (String) getPathMethod.invoke(sv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}