package com.asik.courserating;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class StartingPoint extends ListActivity {

	private static String[] args = { "Software Systems Engineering course summer semester", "Another Course with Big Name to Test", "three", "one", "two", "three", "one", "two", "three", "one", "two", "three", "one", "two", "three" };
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_starting_point);
		
		ListView myListView = (ListView) findViewById(android.R.id.list);
		
		setListAdapter(new MyAdapter());
		
		myListView.setOnItemClickListener(onListClick);
		
	}
	
	private AdapterView.OnItemClickListener onListClick= new AdapterView.OnItemClickListener() {
		public void onItemClick (AdapterView<?> parent, View view, int position, long id){
			Intent i = new Intent(StartingPoint.this, Lecture.class);
			//i.putExtra(ID_EXTRA, String.valueOf(id));
			startActivity(i);
		}
	};

	class MyAdapter extends ArrayAdapter<String> {
		public MyAdapter() {
			super(StartingPoint.this, R.layout.course_item, args);
			// TODO Auto-generated constructor stub
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflate = getLayoutInflater();
			View row = inflate.inflate(R.layout.course_item, parent, false);
			TextView label = (TextView) row.findViewById(R.id.courseName);
			label.setText(args[position]);
			return row;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.starting_point, menu);
		return true;
	}
}
