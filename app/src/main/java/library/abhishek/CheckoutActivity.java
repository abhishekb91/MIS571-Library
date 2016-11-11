package library.abhishek;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import library.abhishek.constant.SQLCommand;
import library.abhishek.util.DBOperator;
import library.abhishek.view.ChartGenerator;
import library.abhishek.util.Pair;

/**
 * Created by abhis on 10/30/2016.
 */

public class CheckoutActivity extends AppCompatActivity {
    EditText stuIdEdit, bookIdEdit;
    DatePicker datePicker;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout_abhishekbhandari);

        stuIdEdit = (EditText) this.findViewById(R.id.studentID_edittext);
        bookIdEdit = (EditText) this.findViewById(R.id.bookID_edittext);
        datePicker = (DatePicker) this.findViewById(R.id.datePicker1);
    }

    /**
     * This function gets called once user presses checkout btn.
     * It is used to check out a book by user
     *
     * @param view
     */
    public void onCheckoutBtnClick(View view) {
        //Check out a book
        DBOperator.getInstance().execSQL(SQLCommand.CHECK_BOOK, this.getArgs(true));
        Toast.makeText(getBaseContext(), "Checkout successfully", Toast.LENGTH_SHORT).show();
    }

    /**
     * This function gets called once user presses return btn.
     * It is used to return a book by user
     *
     * @param view
     */
    public void onReturnBtnClick(View view) {
        //Return a book
        DBOperator.getInstance().execSQL(SQLCommand.RETURN_BOOK, this.getArgs(false));
        Toast.makeText(getBaseContext(), "Return successfully", Toast.LENGTH_SHORT).show();
    }

    /**
     * This function gets called when user clicks summary button
     * @param view
     */
    public void onSummaryBtnClick(View view) {
        Cursor cursor = DBOperator.getInstance().execQuery(
                SQLCommand.CHECKOUT_SUMMARY);
        List<Pair> pairList = new LinkedList<Pair>();
        for (int i = 1; i <= 12; i++) {
            Pair pair = new Pair(i, 0);
            pairList.add(pair);
        }
        while (cursor.moveToNext()) {
            int location = Integer.parseInt(cursor.getString(0));
            pairList.get(location - 1).setNumber(
                    Double.parseDouble(cursor.getString(1)));
        }
        Intent intent = ChartGenerator.getBarChart(getBaseContext(),
                "Checkout Summary in 2011", pairList);
        this.startActivity(intent);

    }

    /**
     * This function is used to go back to home page
     *
     * @param view
     */
    public void goHomePage(View view) {
        //go back to main screen
        finish();
    }

    /**
     * Get input data
     * including studentID, book callnum, date and returned state
     *
     * @param isCheckout
     * @return
     */
    private String[] getArgs(boolean isCheckout) {
        String args[] = new String[4];
        //stid
        args[0] = stuIdEdit.getText().toString();
        //callnum
        args[1] = bookIdEdit.getText().toString();
        //date
        int year = datePicker.getYear();
        int month = datePicker.getMonth();
        int day = datePicker.getDayOfMonth();
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        //format the date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        args[2] = dateFormat.format(calendar.getTime());
        if (isCheckout) args[3] = "N";
        else args[3] = "Y";
        return args;
    }

}
