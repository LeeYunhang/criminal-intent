package com.mrcode.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mrcode on 16-6-5.
 */
public class CrimePagerActivity extends FragmentActivity {

    @BindView(R.id.activity_crime_pager_view_pager) ViewPager mViewPager;

    List<Crime> mCrimes;
    public static final String EXTRA_CRIME_ID = "com.mrcodex.android.criminalintent.crime_id";

    @Override protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_crime_pager);
        ButterKnife.bind(this);

        mCrimes = CrimeLab.get(this).getCrimes();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override public Fragment getItem(int position) {
                Crime crime = mCrimes.get(position);

                return CrimeFragment.newInstance(crime.getID());
            }

            @Override public int getCount() {
                return mCrimes.size();
            }
        });

        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);

        for(int i = 0; i < mCrimes.size(); ++i){
            if(mCrimes.get(i).getID().equals(crimeId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }

    public static Intent newIntent(Context packageContext, UUID crimeId){
        Intent intent = new Intent(packageContext, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }
}
