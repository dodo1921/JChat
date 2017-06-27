package in.jewelchat.jewelchat;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import in.jewelchat.jewelchat.screens.FragmentAchievements;
import in.jewelchat.jewelchat.screens.FragmentChatList;
import in.jewelchat.jewelchat.screens.FragmentTasks;
import in.jewelchat.jewelchat.service.GameStateLoadService;

/**
 * Created by mayukhchakraborty on 20/06/17.
 */

public class JewelChat extends BaseNetworkActivity {

	private SectionsPagerAdapter mSectionsPagerAdapter;
	private ViewPager mViewPager;
	private TabLayout tabLayout;


	private View tasksView;
	private View chatListView;
	private View achievementView;

	@Override
	public void onClick(View view) {

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jewelchat);

		rootLayout = (CoordinatorLayout) findViewById(R.id.main_content);

		setUpAppbar();

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
		mSectionsPagerAdapter.addFrag( "Chats");
		mSectionsPagerAdapter.addFrag( "Tasks");
		mSectionsPagerAdapter.addFrag( "Achievements");

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.container);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		mViewPager.setOffscreenPageLimit(1);


		tabLayout = (TabLayout) findViewById(R.id.tabs);
		tabLayout.setupWithViewPager(mViewPager);
		tabLayout.getTabAt(JewelChatApp.getSharedPref().getInt(JewelChatPrefs.LAST_TAB, 0)).select();



		tabLayout.addOnTabSelectedListener(
				new TabLayout.ViewPagerOnTabSelectedListener(mViewPager) {
					@Override
					public void onTabSelected(TabLayout.Tab tab) {
						super.onTabSelected(tab);
						JewelChatApp.getSharedPref().edit().putInt(JewelChatPrefs.LAST_TAB, tab.getPosition()).apply();

					}
				});

		Intent service = new Intent(getApplicationContext(), GameStateLoadService.class);
		startService(service);

	}



	public class SectionsPagerAdapter extends FragmentStatePagerAdapter {


		private final List<String> mFragmentTitleList = new ArrayList<>();

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			if(position==0)
				return new FragmentChatList();
			else if(position==1)
				return new FragmentTasks();
			else if(position==2)
				return new FragmentAchievements();

			return null;

		}

		@Override
		public int getCount() {

			return 3;
		}

		public void addFrag( String title) {

			mFragmentTitleList.add(title);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return mFragmentTitleList.get(position);
		}

	}


}
