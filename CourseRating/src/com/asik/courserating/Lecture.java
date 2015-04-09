package com.asik.courserating;

import com.asik.courserating.StartingPoint.MyAdapter;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Lecture extends ListActivity implements View.OnClickListener {

	Button btnRating;
	Button btnDescription;
	Button btnRateThisCourse;

	private static String[] args = { "Anonymous Username",
			"Anonymous Username2", "Anonymous Username3",
			"Anonymous Username4", "Anonymous Username5",
			"Anonymous Username6", "Anonymous Username7" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lecture);

		ListView myListView = (ListView) findViewById(android.R.id.list);
		setListAdapter(new MyAdapter());
		myListView.setOnItemClickListener(onListClick);

		btnRating = (Button) findViewById(R.id.btnRating);
		btnRating.setOnClickListener(this);
		btnDescription = (Button) findViewById(R.id.btnDescription);
		btnDescription.setOnClickListener(this);
		btnRateThisCourse = (Button)findViewById(R.id.btnRateThisCourse);
		btnRateThisCourse.setOnClickListener(this);
	}

	private AdapterView.OnItemClickListener onListClick = new AdapterView.OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Intent i = new Intent(Lecture.this, CommentDetails.class);
			// i.putExtra(ID_EXTRA, String.valueOf(id));
			startActivity(i);
		}
	};

	class MyAdapter extends ArrayAdapter<String> {
		public MyAdapter() {
			super(Lecture.this, R.layout.comment_item, args);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflate = getLayoutInflater();
			View row = inflate.inflate(R.layout.comment_item, parent, false);
			TextView label = (TextView) row.findViewById(R.id.userName);
			label.setText(args[position]);
			return row;
		}
	}

	private void btnRatingClick() {
		startActivity(new Intent(Lecture.this, ShowRating.class));
	}

	private void btnDescriptionClick() {
		startActivity(new Intent(Lecture.this, Description.class));
	}
	
	private void btnRateThisCourseClick(){
		startActivity(new Intent(Lecture.this, RateCourse.class));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnRating:
			btnRatingClick();
			break;
		case R.id.btnDescription:
			btnDescriptionClick();
			break;
		case R.id.btnRateThisCourse:
			btnRateThisCourseClick();
			break;
		}

	}
}
