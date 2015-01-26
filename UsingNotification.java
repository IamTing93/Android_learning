import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}
	
	private void init() {
		findViewById(R.id.btnShowNotification).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
                /*
                 * 创建一个Notification, 需要建立Notificationmanager来管理Notification。
                 * 这里使用Notification的内部类Builder来生成Notification。
                 * 因为使用Builder是现在推荐的用法。
                 * 不明用法参考官方文档。
                 */

                // 使用系统自带的getSystemService（）来获取一个NotificationManager对象，官方文档不推荐直接使用
                // 其构造函数来获取NotificationManager。
				NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                // 用Notification的内部类来生成一个builder,来获取Notification。
				Notification.Builder builder = new Notification.Builder(MainActivity.this);

                // 这个intent是用于当在通知栏点击Notification项时，直接跳转到指定的Activity。
				Intent intent = new Intent(MainActivity.this, Other.class);

                // pindIntent是用于不同程序间的跳转？（这里需要学习pendIntent的用法）
				PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);

                // 获取Notification
                // .setAutoCancel()用于当点击Notification显示，上方的提示图标清除
				Notification notification = builder.setAutoCancel(true)

                                                    // .setTicker()用于当有通知时，出现提示文字的内容
                                                    .setTicker("Test")
                                                    // .setSmallIcon()用于设置通知栏中图片
                                                    .setSmallIcon(R.drawable.ic_launcher)
                                                    // 设置标题及内容
                                                    .setContentTitle("加薪通知").setContentText("你被加薪-100元")
                                                    // 设置通知的时间
					                                .setWhen(System.currentTimeMillis())
                                                    // 设置点击时跳转到的Activity
                                                    .setContentIntent(pendingIntent)
                                                    // 这里设置了响铃和震动
                                                    .setDefaults(Notification.DEFAULT_ALL)
                                                    // 生成Notification
                                                    .build();
				// 把Notiication交给NotificationManager管理，提示用户
                // notify()的第一个参数是该notification的唯一ID,与该Notification绑定在一起
				manager.notify(1, notification);
                
                /*
                 * 当然，这里显示的Notification的布局还可以自定义，使用RemoteView，但由于不熟练，所以这里先不写
                 */
			}
		});
	}
	
}
