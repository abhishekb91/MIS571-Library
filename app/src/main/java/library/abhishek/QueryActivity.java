package library.abhishek;

import library.abhishek.constant.SQLCommand;
import library.abhishek.util.DBOperator;
import library.abhishek.view.TableView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by abhishek on 10/9/2016.
 */

public class QueryActivity extends AppCompatActivity {
    Button backBtn, resultBtn, showlistBtn;
    Spinner querySpinner;
    ScrollView scrollView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.query_abhishekbhandari_layout);
        //copy database file
        try {
            DBOperator.copyDB(getBaseContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Back to Main Page Button
        backBtn = (Button) findViewById(R.id.goBack_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //go back to main screen
                finish();
            }
        });

        //Display Result Button
        resultBtn = (Button) findViewById(R.id.showresult_btn);
        resultBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //show query result
                int pos = querySpinner.getSelectedItemPosition();
                if (pos == Spinner.INVALID_POSITION) {
                    //User doesn't choose any query, show warning
                    Toast.makeText(QueryActivity.this, "Please choose a query!", Toast.LENGTH_SHORT).show();
                    return;
                }
                scrollView.removeAllViews();

                String sql = "";

                //Setting Query according to the option selected
                switch (pos) {
                    case 0: //show all books
                        sql = SQLCommand.BOOKS;
                        break;
                    case 1: //list the call numbers of books with the title ‘Database Management’
                        sql = SQLCommand.BOOK_DB;
                        break;
                    case 2: //list all Students
                        sql = SQLCommand.STUDENTS;
                        break;
                    case 3: //List the call numbers of books with the title ‘Fifth Discipline’
                        sql = SQLCommand.BOOK_FD;
                        break;
                    case 4: //List of books taken by student = 111
                        sql = SQLCommand.BOOKS_ISSUED_STUDENT;
                        break;
                    case 5: //List of Students Who have checked out Books
                        sql = SQLCommand.STUDENTS_CHECKOUT_BOOKS;
                        break;
                    case 6: //List the call numbers of books with the title ‘Software Engineering’
                        sql = SQLCommand.BOOK_SE;
                        break;
                    default://In default case, showing all books
                        sql = SQLCommand.BOOKS;
                        break;
                }

                Cursor cursor = DBOperator.getInstance().execQuery(sql);
                TableView tableView = new TableView(QueryActivity.this, cursor);
                scrollView.addView(tableView);
            }
        });

        //Display Result Button
        showlistBtn = (Button) findViewById(R.id.showcheckoutlist_btn);
        showlistBtn.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 // show checkout list result
                 String sql = SQLCommand.CHECKOUT_LIST;
                 Intent intent = new Intent(getApplicationContext(), ShowlistActivity.class);
                 intent.putExtra("sql", sql);
                 startActivity(intent);
             }
        });
        querySpinner = (Spinner) findViewById(R.id.querylist_spinner);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
    }

}
