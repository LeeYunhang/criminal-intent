package com.mrcode.criminalintent;

import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Created by mrcode on 16-6-2.
 */
public class CrimeActivity extends SingleFragmentActivity {
    public static final String EXTRA_CRIME_ID = "com.mrcodex.android.criminalintent.crime_id";
    @Override
    protected Fragment createFragment() {
        UUID crimeId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeId);
    }
}
