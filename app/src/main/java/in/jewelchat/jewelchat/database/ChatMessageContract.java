package in.jewelchat.jewelchat.database;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by mayukhchakraborty on 04/03/16.
 */
public class ChatMessageContract implements BaseColumns {

	public static final String KEY_ROWID = BaseColumns._ID;
	public static final String MSG_TYPE = "type";	//4--new group created msg.
	public static final String CHAT_ROOM = "chatroom";
	public static final String CREATOR_ID = "creatorId";//group id if it is a group
	public static final String SENDER_PHONE = "sender_phone";
	public static final String SENDER_MSG_ID = "msgId";
	public static final String CREATED_TIME = "timeCreated";
	public static final String IS_READ = "isRead";
	public static final String TIME_READ = "timeRead";
	public static final String IS_DELIVERED = "isDelivered";
	public static final String TIME_DELIVERED = "timeDelivered";
	public static final String IS_SUBMITTED = "isSubmitted";
	public static final String TIME_SUBMITTED = "timeSubmitted";
	public static final String MSG_TEXT = "msgtext";
	public static final String IMAGE_BLOB = "imageBlob";
	public static final String IS_IMAGE_DOWNLOADED = "isImageDownloaded";
	public static final String IS_IMAGE_UPLOADED = "isImageUploaded";
	public static final String IMAGE_PATH_LOCAL = "imagePathLocal";
	public static final String IMAGE_PATH_CLOUD = "imagePathCloud";
	public static final String VIDEO_BLOB = "videoBlob";
	public static final String IS_VIDEO_DOWNLOADED = "isVideoDownloaded";
	public static final String IS_VIDEO_UPLOADED = "isVideoUploaded";
	public static final String VIDEO_PATH_LOCAL = "VideoPathLocal";
	public static final String VIDEO_PATH_CLOUD = "VideoPathCloud";
	public static final String IS_GROUP_MSG = "isGroupMsg";
	public static final String JEWEL_TYPE = "jewelType";
	public static final String IS_JEWEL_PICKED = "isJewelPicked";


	public static final String SQLITE_TABLE_NAME = "ChatMessage";

	private static final String DATABASE_CREATE = "CREATE TABLE if not exists "
			+ SQLITE_TABLE_NAME + " (" +
			KEY_ROWID + " integer PRIMARY KEY autoincrement," +
			MSG_TYPE + "  INTEGER"	+ "," +
			CREATED_TIME + "  INTEGER" + "," +
			CHAT_ROOM	+ "  INTEGER" + "," +
			CREATOR_ID	+ "  INTEGER" + "," +
			SENDER_PHONE + " INTEGER" + "," +
			SENDER_MSG_ID + "  INTEGER" + "," +
			IS_READ	+ "  INTEGER" + "," +
			TIME_READ + "  INTEGER" + "," +
			IS_DELIVERED + "  INTEGER" + "," +
			TIME_DELIVERED + "  INTEGER" + "," +
			IS_SUBMITTED + "  INTEGER" + "," +
			TIME_SUBMITTED + "  INTEGER" + "," +
			IS_GROUP_MSG + "  INTEGER" + "," +
			JEWEL_TYPE + "  INTEGER" + "," +
			IS_JEWEL_PICKED + "  INTEGER" + "," +
			MSG_TEXT + "  TEXT" + "," +
			IMAGE_BLOB + "  BLOB" + "," +
			IS_IMAGE_DOWNLOADED + "  INTEGER" + "," +
			IS_IMAGE_UPLOADED + " INTEGER" + ","+
			IMAGE_PATH_LOCAL + "  INTEGER" + "," +
			IMAGE_PATH_CLOUD + "  INTEGER" + "," +
			VIDEO_BLOB + "  BLOB" + "," +
			IS_VIDEO_DOWNLOADED + "  INTEGER" + "," +
			IS_VIDEO_UPLOADED + " INTEGER" + ","+
			VIDEO_PATH_LOCAL + "  INTEGER" + "," +
			VIDEO_PATH_CLOUD + "  INTEGER" + ",	 unique ( " + CREATED_TIME+", " + CREATOR_ID + ", " +SENDER_MSG_ID+ " ) )";

	public static void onCreate(SQLiteDatabase db) {
		Log.i("ChatMessage", "OnCreate");
		db.execSQL(DATABASE_CREATE);
	}

	public static void onUpgrade(SQLiteDatabase db, int oldVersion,
	                             int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + SQLITE_TABLE_NAME);
		onCreate(db);
	}

}
