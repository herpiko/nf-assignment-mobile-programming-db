import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by herpiko on 15-12-11.
 */
public class BookHelper extends SQLiteOpenHelper {

    final static String DBNAME = "book.db";
    final static int DBVERSION =1;


    public BookHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DBNAME, null, DBVERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String query_create = "CREATE TABLE book_entries(_id int primary key autoincrement," +
                "title text" +
                "author text);";
        db.execSQL(query_create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query_drop = "DROP TABLE IF EXISTS book_entries";
        db.execSQL(query_drop);
        onCreate(db);
    }
}
