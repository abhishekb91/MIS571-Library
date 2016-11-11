package library.abhishek;

import library.abhishek.util.DBOperator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.SimpleCursorAdapter;

import static android.R.id.list;

/**
 * Created by abhishek on 11/11/2016.
 */

public class ShowlistActivity extends AppCompatActivity {
    private ListView listView;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showlist_abhishekbhandari);

        listView = (ListView) this.findViewById(R.id.checkout_listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) listView.getItemAtPosition(position);
                String stid = cursor.getString(0);
                String lbtitle = cursor.getString(1);
                String coduedate = cursor.getString(2);
                String coreturned = cursor.getString(3);
                String cofine = cursor.getString(4);
                String stname = cursor.getString(5);
                Toast.makeText(getApplicationContext(),"Student ID: " + stid + "\nStudent Name: " + stname+ "\nBook Title: " + lbtitle + "\nDue Date: "+ coduedate + "\nReturn State: " + coreturned+ "\nFine: $" + cofine, Toast.LENGTH_LONG).show();
            }
        });

        // get the sql string delivered from the QueryActivity
        Intent intent = this.getIntent();
        String sql = intent.getStringExtra("sql");

        // execute the sql
        Cursor cursor = DBOperator.getInstance().execQuery(sql);

        // bind the data to list
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                getApplicationContext(), R.layout.listitem_abhishekbhandari, cursor,
                new String[] { "stname", "coduedate", "lbtitle" }, new int[] {
                R.id.stname, R.id.coduedate, R.id.lbtitle },
                SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);
        listView.setAdapter(adapter);
    }

}
