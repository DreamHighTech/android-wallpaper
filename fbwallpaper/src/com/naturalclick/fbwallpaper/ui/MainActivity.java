package com.naturalclick.fbwallpaper.ui;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.naturalclick.fbwallpaper.R;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends Activity {

	ListView listView;
	int imageids[] = {R.drawable.category1, R.drawable.category2};
	String titles[] = {"Field Goals", "Passing Plays"};
	CategoryAdapter listAdapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.listview);
        listAdapter = new CategoryAdapter(this);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, SelectActivity.class);
				intent.putExtra("type", position);
				startActivity(intent);
			}
        	
		});
        
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    class CategoryAdapter extends BaseAdapter {
    	Context mContext;
    	LayoutInflater mInflater;
    	public CategoryAdapter(Context cxt) {
    		mContext = cxt;
    		mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    	}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return imageids.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder holder;
			View view;
			if (convertView == null) {
				view = mInflater.inflate(R.layout.item_category, parent, false);
				holder = new ViewHolder();
				holder.previewView = (ImageView)view.findViewById(R.id.previewimage);
				holder.title = (TextView)view.findViewById(R.id.title);
			}else{
				view = convertView;
				holder = (ViewHolder)convertView.getTag();
			}
			init(holder, position);
			return view;
		}
		
		private void init(ViewHolder holder, int position) {
			// TODO Auto-generated method stub
			holder.previewView.setImageResource(imageids[position]);
			holder.title.setText(titles[position]);
		}

		class ViewHolder {
			ImageView previewView;
			TextView title;
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
