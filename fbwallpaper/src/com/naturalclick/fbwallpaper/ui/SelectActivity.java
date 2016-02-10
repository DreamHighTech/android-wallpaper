package com.naturalclick.fbwallpaper.ui;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.naturalclick.fbwallpaper.MyApp;
import com.naturalclick.fbwallpaper.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;


public class SelectActivity extends Activity {

	ListView listView;
	
	private int type = 0;
	
	int imageids1[] = {R.drawable.shot_center
					, R.drawable.shot_right
					, R.drawable.shot_left};
	String titles1[] = {"Goal"
					, "Right Miss"
					, "Post Hit"};
	int assetsNums1[] = {0, 1, 2};
	
	int imageids2[] = {R.drawable.passing_air
			, R.drawable.running_outside
			, R.drawable.running_through};
	String titles2[] = {"Air-Pass"
			, "Running-Outside"
			, "Running-Through"};
	int assetsNums2[] = {3, 4, 5};
	
	int imageids[] = {};
	String titles[] = {};
	int assetsNums[] = {};
	
	ViewPager mViewPager;
	SelectAdapter mAdapter;
	ImageButton btnNext;
	ImageButton btnPrev;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        
        type = getIntent().getIntExtra("type", 0);
        if (type == 0) {			
        	imageids = imageids1;
        	titles = titles1;
        	assetsNums = assetsNums1;
		}else{
			imageids = imageids2;
        	titles = titles2;
        	assetsNums = assetsNums2;
		}
        
        btnNext = (ImageButton)findViewById(R.id.btnNext);
        btnPrev = (ImageButton)findViewById(R.id.btnPrev);
        mViewPager = (ViewPager)findViewById(R.id.contentPager);
        mAdapter = new SelectAdapter(this);
        mViewPager.setAdapter(mAdapter);
        btnPrev.setVisibility(View.INVISIBLE);
        if (imageids.length == 0) {
			findViewById(R.id.setwallpaper).setVisibility(View.INVISIBLE);
		}
        mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				if (arg0 == 0) {
					btnPrev.setVisibility(View.INVISIBLE);
				}else{
					btnPrev.setVisibility(View.VISIBLE);
				}
				if (arg0 == mAdapter.getCount() - 1) {
					btnNext.setVisibility(View.INVISIBLE);
				}else{
					btnNext.setVisibility(View.VISIBLE);
				}
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
        
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);        
    }
    
    public void onClick(View v) {
    	int viewId = v.getId();
    	switch (viewId) {
		case R.id.btnNext:
			onNext();
			break;
		case R.id.btnPrev:
			onPrev();
			break;
		case R.id.setwallpaper:
			onSetWallPaper();
			break;
		default:
			break;
		}
    }
    
    private void onSetWallPaper() {
		// TODO Auto-generated method stub
    	int index = mViewPager.getCurrentItem();
		MyApp.config().setInt("videoassetnumber", assetsNums[index]);
		Intent localIntent = new Intent();
	    localIntent.setAction("android.service.wallpaper.LIVE_WALLPAPER_CHOOSER");
	    startActivity(localIntent);
	}

	private void onPrev() {
		// TODO Auto-generated method stub
		if (mViewPager.getCurrentItem() > 0)
		{
			mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1, true);
		}
	}

	private void onNext() {
		// TODO Auto-generated method stub
		if (mViewPager.getCurrentItem() < mAdapter.getCount() - 1)
		{
			mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
		}
	}

	class SelectAdapter extends PagerAdapter {
    	
    	Context mContext;
    	LayoutInflater mInflater;
    	
    	public SelectAdapter(Context cxt) {
    		mContext = cxt;
    		mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    	}
    	
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return imageids.length;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return (arg0 == arg1);
		} 
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			((ViewPager)container).removeView((View)object);
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			View view = mInflater.inflate(R.layout.item_type, container, false);
			ImageView preview = (ImageView) view.findViewById(R.id.previewimage);
			preview.setImageResource(imageids[position]);
			TextView title = (TextView)view.findViewById(R.id.title);
			title.setText(titles[position]);
			((ViewPager)container).addView(view, 0);
			return view;
		}
		
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
