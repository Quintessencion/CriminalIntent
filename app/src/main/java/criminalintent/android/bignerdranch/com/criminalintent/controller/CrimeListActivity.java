package criminalintent.android.bignerdranch.com.criminalintent.controller;

import android.support.v4.app.Fragment;

import criminalintent.android.bignerdranch.com.criminalintent.fragment.CrimeListFragment;

public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
