package com.example.mydatabasehelper;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class DatabaseProvider extends ContentProvider {

    public static final int BOOK_DIR = 0;
    public static final int BOOK_ITEM = 1;
    public static final int CATEGORY_DIR = 2;
    public static final int CATEGORY_ITEM = 3;
    public static final String AUTHORITY = "com.example.mydatabasehelper" +
            ".provider";
    private static UriMatcher uriMatcher;
    //
    private MyDatabaseHelper dbHelper;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(AUTHORITY, "book", BOOK_DIR);
        uriMatcher.addURI(AUTHORITY, "book/#", BOOK_ITEM);
        uriMatcher.addURI(AUTHORITY, "category", CATEGORY_DIR);
        uriMatcher.addURI(AUTHORITY, "category/#", CATEGORY_ITEM);
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();  //获取数据库
        int deleteRows = 0;     //删除了几row
        switch(uriMatcher.match(uri))
        {
            case BOOK_DIR:
                deleteRows = db.delete("Book",selection,selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId =  uri.getPathSegments().get(1);
                deleteRows = db.delete("Book","id = ?",
                        new String[]{bookId});
                break;
            case CATEGORY_DIR:
                //更新整个队列
                deleteRows =db.delete("Category",selection,
                        selectionArgs);
                break;
            case CATEGORY_ITEM:
                //更新单个item
                String categoryId = uri.getPathSegments().get(1);
                deleteRows = db.delete("Category","id = ?",
                        new String[]{categoryId});
                break;
            default:
                break;
        }
        return deleteRows;

    }

    @Override
    public String getType(Uri uri) {
        //返回uri的mimetype
        switch (uriMatcher.match(uri))
        {
            case BOOK_DIR:
                return "vnd.android.cursor.dir/"+AUTHORITY+"Book";
            case BOOK_ITEM:
                return "vnd.android.cursor.item/"+AUTHORITY+"Book";
            case CATEGORY_DIR:
                return "vnd.android.cursor.dir/"+AUTHORITY+"Category";
            case CATEGORY_ITEM:
                return "vnd.android.cursor.item/"+AUTHORITY+"Category";
            default:
                return null;
        }

    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        //添加数据
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Uri uriReturn = null;
        switch (uriMatcher.match(uriReturn)) {
            case BOOK_DIR:
            case BOOK_ITEM:
                Long newBookId = db.insert("Book", null, values);
                //解析uri
                uriReturn = Uri.parse("content://" + AUTHORITY + "/book/");
                break;
            case CATEGORY_DIR:
            case CATEGORY_ITEM:
                Long newCategoryId = db.insert("Category", null, values);
                uriReturn = Uri.parse("content://" + AUTHORITY + "/category/");
                break;
            default:
                break;
        }
        return uriReturn;


    }

    @Override
    public boolean onCreate() {
        //创建database
        if(null == dbHelper) {
            dbHelper = new MyDatabaseHelper(getContext(), "BookStore.db", null, 6);
        }
        // TODO: Implement this to initialize your content provider on startup.
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();  //获取数据库
        Cursor cursor = null;    //获取游标
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                cursor = db.query("Book", projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                cursor = db.query("Book", projection, "id=?",
                        new String[]{bookId}, null, null, sortOrder);
                break;
            case CATEGORY_DIR:
                cursor = db.query("Category", projection, selection, selectionArgs
                        , null, null, sortOrder);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                cursor = db.query("Category", projection, "id=?", new String[]{categoryId}
                        , null, null, sortOrder);
                break;
            default:
                break;
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {

        SQLiteDatabase db = dbHelper.getReadableDatabase();  //获取数据库
        int updateRows = 0;     //更新了几row
        switch(uriMatcher.match(uri))
        {
            case BOOK_DIR:
                updateRows = db.update("Book",values,selection,selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId =  uri.getPathSegments().get(1);
                updateRows = db.update("Book",values,"id = ?",
                        new String[]{bookId});
                break;
            case CATEGORY_DIR:
                //更新整个队列
                updateRows =db.update("Category",values,selection,
                        selectionArgs);
                break;
            case CATEGORY_ITEM:
                //更新单个item
                String categoryId = uri.getPathSegments().get(1);
                updateRows = db.update("Category",values,"id = ?",
                        new String[]{categoryId});
                break;
            default:
                break;
        }
        return updateRows;

    }
}