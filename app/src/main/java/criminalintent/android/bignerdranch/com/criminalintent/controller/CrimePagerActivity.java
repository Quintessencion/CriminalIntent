package criminalintent.android.bignerdranch.com.criminalintent.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import java.util.List;
import java.util.UUID;

import criminalintent.android.bignerdranch.com.criminalintent.Crime;
import criminalintent.android.bignerdranch.com.criminalintent.CrimeLab;
import criminalintent.android.bignerdranch.com.criminalintent.R;
import criminalintent.android.bignerdranch.com.criminalintent.fragment.CrimeFragment;

public class CrimePagerActivity extends AppCompatActivity {

    private static final String EXTRA_POSITION = "com.bignerdranch.android.criminalintent.adapter_position";
    private ViewPager mViewPager;
    private List<Crime> mCrimes;
    private Button startPage, lastPage;

    public static Intent newIntent(Context packageContext, int position) {
        Intent intent = new Intent(packageContext, CrimePagerActivity.class);
        intent.putExtra(EXTRA_POSITION, position);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        mViewPager = findViewById(R.id.crime_view_pager);
        mCrimes = CrimeLab.get(this).getCrimes();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                startPage.setEnabled(true);
                lastPage.setEnabled(true);
                return CrimeFragment.newInstance(mCrimes.get(position).getId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });
        mViewPager.setCurrentItem(getIntent().getIntExtra(EXTRA_POSITION, 0));

        startPage = findViewById(R.id.fistPage);
        startPage.setOnClickListener(view -> {
            mViewPager.setCurrentItem(0);
            startPage.setEnabled(false);
            lastPage.setEnabled(true);
        });
        lastPage = findViewById(R.id.lastPage);
        lastPage.setOnClickListener(view -> {
            mViewPager.setCurrentItem(mCrimes.size());
            startPage.setEnabled(true);
            lastPage.setEnabled(false);
        });

//        for (int i = 0; i < mCrimes.size(); i++) {
//            if (mCrimes.get(i).getId().equals(crimeId)) {
//                mViewPager.setCurrentItem(i);
//                break;
//            }
//        }
    }
}

