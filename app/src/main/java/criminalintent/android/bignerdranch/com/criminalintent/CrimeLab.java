package criminalintent.android.bignerdranch.com.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CrimeLab {

    private static CrimeLab sCrimeLab;
    private List<Crime> mCrimes;
    private Map<UUID, Crime> mCrimeMap;
    private static int freePosition;

    private CrimeLab(Context context) {
        mCrimes = new ArrayList<>();
//        for (int i = 1; i <= 7; i++) {
//            Crime crime = new Crime();
//            crime.setTitle("Crime #" + i);
//            crime.setSolved(i % 2 == 0);
//            mCrimes.add(crime);
//        }
        mCrimeMap = new HashMap<>();
        for (Crime crime : mCrimes) {
            mCrimeMap.put(crime.getId(), crime);
        }
        setFreePosition();
    }

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    public void addCrime(Crime c) {
        mCrimes.add(c);
        mCrimeMap.put(c.getId(), c);
        setFreePosition();
    }

    public void removeCrime(UUID id) {
        mCrimeMap.remove(id);
        Iterator<Crime> it = mCrimes.iterator();
        while (it.hasNext()) {
            Crime crime = it.next();
            if (crime.getId().equals(id)) {
                it.remove();
                return;
            }
        }
    }

    public List<Crime> getCrimes() {
        return mCrimes;
    }

    public static int getFreePosition() {
        return freePosition;
    }

    public Crime getCrime(UUID id) {
        return mCrimeMap.get(id);
    }

    private void setFreePosition() {
        freePosition = mCrimes.size();
    }
}
