package sekhar.mitra.dora.blink.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.TextView;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import java.util.ArrayList;
import java.util.List;
import sekhar.mitra.dora.blink.R;
import sekhar.mitra.dora.blink.fragments.Chats;
import sekhar.mitra.dora.blink.fragments.Favourite;

public class MainActivity extends AppCompatActivity {

  private Toolbar toolbar;
  private TabLayout tabLayout;
  private ViewPager viewPager;
  private static String FRAGMENT_ONE_NAME = "Chat";
  private static String FRAGMENT_TWO_NAME = "Favourites";

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Fabric.with(this, new Crashlytics());
    setContentView(R.layout.activity_main);
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    viewPager = (ViewPager) findViewById(R.id.viewpager);
    setupViewPager(viewPager);
    tabLayout = (TabLayout) findViewById(R.id.tabs);
    tabLayout.setupWithViewPager(viewPager);
    setupTabIcons();
  }

  private void setupTabIcons() {
    tabLayout.getTabAt(0).setIcon(R.drawable.ic_chat_white_24dp);
    tabLayout.getTabAt(1).setIcon(R.drawable.ic_favorite_white_24dp);
  }

  private void setupViewPager(ViewPager viewPager) {
    ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
    adapter.addFrag(new Chats(), FRAGMENT_ONE_NAME);
    adapter.addFrag(new Favourite(), FRAGMENT_TWO_NAME);
    viewPager.setAdapter(adapter);
  }

  class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager manager) {
      super(manager);
    }

    @Override public Fragment getItem(int position) {
      return mFragmentList.get(position);
    }

    @Override public int getCount() {
      return mFragmentList.size();
    }

    public void addFrag(Fragment fragment, String title) {
      mFragmentList.add(fragment);
      mFragmentTitleList.add(title);
    }

    @Override public CharSequence getPageTitle(int position) {
      return null;
    }
  }
}
