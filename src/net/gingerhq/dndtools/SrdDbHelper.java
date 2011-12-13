package net.gingerhq.dndtools;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import net.gingerhq.dndtools.R;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class SrdDbHelper extends SQLiteOpenHelper
{
	private static final String DATABASE_DIR = "/data/data/net.gingerhq.net/databases/";
	private static final String DATABASE_NAME = "dnd35.db";
	private static final int DATABASE_VERSION = 1;
	
	private final Context context;
	
	public SrdDbHelper(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}
	
	public void onCreate() throws IOException
	{
		/*String outFile = DATABASE_DIR + DATABASE_NAME;
		
		InputStream in = context.getResources().openRawResource(R.raw.dnd35);
		OutputStream out = new FileOutputStream(outFile);
		
		byte[] buffer = new byte[1024];
		int length;
		
		while ((length = in.read(buffer)) > 0)
		{
			out.write(buffer, 0, length);
		}
		
		out.flush();
		out.close();
		in.close();*/
	}
	
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		CharacterTable.onCreate(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		CharacterTable.onUpgrade(db, oldVersion, newVersion);
	}
}
