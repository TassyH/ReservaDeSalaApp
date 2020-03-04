package com.example.ui.controledesalas.ui.fragment_main;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.ui.controledesalas.activityfragments.ContaUsuarioFragment;
import com.example.ui.controledesalas.activityfragments.ListaReservasFragment;
import com.example.ui.controledesalas.activityfragments.ListaSalasFragment;

 public class MyPageAdapter  extends FragmentPagerAdapter {
     int tabCount;

     //Constructor to the class
     public MyPageAdapter(FragmentManager fm, int tabCount) {
         super(fm);
         //Initializing tab count
         this.tabCount = tabCount;
     }

     //Overriding method getItem
     @Override
     public Fragment getItem(int position) {
         //Returning the current tabs
         switch (position) {
             case 0:
                 ListaReservasFragment tab1 = new ListaReservasFragment();
                 return tab1;
             case 1:
                 ListaSalasFragment tab2 = new ListaSalasFragment();
                 return tab2;
             case 2:
                 ContaUsuarioFragment tab3 = new ContaUsuarioFragment();
                 return tab3;
             default:
                 return null;
         }
     }

     //Overriden method getCount to get the number of tabs
     @Override
     public int getCount() {
         return tabCount;
     }
 }