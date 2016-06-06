package com.mrcode.criminalintent;

import android.content.Context;

import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by mrcode on 16-6-2.
 */
public class CrimeLab {
    private static CrimeLab sCrimeLab;
    private List<Crime> mCrimes = new ArrayList<Crime>();

    public static CrimeLab get(Context context){
        if(sCrimeLab == null){
            sCrimeLab = new CrimeLab(context);
            Crime crime;
            crime = new Crime();
            crime.setTitle("mrcode1");
            sCrimeLab.mCrimes.add(crime);
            crime = new Crime();
            crime.setTitle("mrcode2");
            sCrimeLab.mCrimes.add(crime);

            crime = new Crime();
            crime.setTitle("mrcode3");
            sCrimeLab.mCrimes.add(crime);

            crime = new Crime();
            crime.setTitle("mrcode4");
            sCrimeLab.mCrimes.add(crime);

            crime = new Crime();
            crime.setTitle("mrcode5");
            sCrimeLab.mCrimes.add(crime);
            return sCrimeLab;
        }else{
            return sCrimeLab;
        }
    }

    private CrimeLab(Context context){
        mCrimes = new ArrayList<>();
    }

    public List<Crime> getCrimes(){
        return mCrimes;
    }

    public Crime getCrime(UUID uuid){
        for (Crime crime : mCrimes){
            if(crime.getID().equals(uuid)){
                return crime;
            }
        }

        return null;
    }
}
