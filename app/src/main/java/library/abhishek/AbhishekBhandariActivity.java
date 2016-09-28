package library.abhishek;

import library.abhishek.constant.SQLCommand;
import library.abhishek.view.TableView;
import library.abhishek.util.DBOperator;

import android.support.v7.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ScrollView;

public class AbhishekBhandariActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abhishekbhandari_layout);

        //copy database file
        try{
            DBOperator.copyDB(getBaseContext());
        }catch(Exception e){
            e.printStackTrace();
        }
        //implement SQL query and get cursor of resultset
        Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.QUERY_STUDENT);
        TableView tableView = new TableView(this, cursor);
        //show data in tableview
        ScrollView scrollView = (ScrollView)this.findViewById(R.id.scrollView);
        scrollView.addView(tableView);

    }
}
