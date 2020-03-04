package com.example.ui.controledesalas.ui.fragment_main;

import android.net.Uri;
import android.os.Bundle;

import com.example.ui.controledesalas.R;
import com.example.ui.controledesalas.activityfragments.ContaUsuarioFragment;
import com.example.ui.controledesalas.activityfragments.ListaReservasFragment;
import com.example.ui.controledesalas.activityfragments.ListaSalasFragment;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;


import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener{

    //This is our tablayout
    private TabLayout tabLayout;

    //This is our viewPager
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Adding toolbar to the activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //Initializing the tablayout
        tabLayout = (TabLayout) findViewById(R.id.tablayout);

        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("Suas Reservas"));
        tabLayout.addTab(tabLayout.newTab().setText("Salas"));
        tabLayout.addTab(tabLayout.newTab().setText("Sua conta"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        //Creating our pager adapter
        MyPageAdapter adapter = new MyPageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);

        //Adding onTabSelectedListener to swipe views
        tabLayout.setOnTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

}
