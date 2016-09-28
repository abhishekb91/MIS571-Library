package library.abhishek.view;


import library.abhishek.util.DBOperator;
import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


/**
 * Created by abhishek on 9/26/2016.
 * This class is used to show data in database
 * It is an extension of TableLayout
 */
public class TableView extends TableLayout{
    public TableView(Context context, String tableName)
    {
        super(context);
        String sql = "select * from " + tableName + ";";
        DBOperator op = DBOperator.getInstance();
        Cursor cursor = op.execQuery(sql, null);
        this.extractData(context, cursor);
    }
    public TableView(Context context, Cursor cursor)
    {
        super(context);
        this.extractData(context, cursor);
    }
    /*
     * fill data in table view with a cursor
     */
    private void extractData(Context context,Cursor cursor)
    {
        TextView textView;
        TableRow row;
        boolean first = true;
        while (cursor.moveToNext())
        {
			/*
             * Before displaying the first row,
             * display column names as a header
             */
            if (first){
                textView = new TextView(context);
                String[] columnNames = cursor.getColumnNames();
                StringBuilder strBuilder = new StringBuilder();
                for (int i=0;i<columnNames.length;i++){
                    if (i>0) strBuilder.append("|");
                    strBuilder.append(columnNames[i]);
                }
                textView.setText(strBuilder);
                this.addView(textView);
                //show separation line
                View line = new View(context);
                line.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,2));
                line.setBackgroundColor(0xFF909090);
                this.addView(line);
                first = false;
            }
            //show values in a row
            row = new TableRow(context);
            int length = cursor.getColumnCount();
            for (int i=0;i<length;i++)
            {
                if (i>0){
                    textView = new TextView(context);
                    textView.setText("|");
                    row.addView(textView);
                }
                textView = new TextView(context);
                textView.setText(cursor.getString(i));
                row.addView(textView);
            }
            this.addView(row);
        }
    /*
     * Do not forget to close the cursor!
     * Otherwise database exceptions will be thrown
     */
        cursor.close();
    }

}
