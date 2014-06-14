//package net.chunk64.sneaky.hotkey;
//
//import com.sun.jna.platform.win32.User32;
//import com.sun.jna.platform.win32.WinUser;
//
//public class Listener
//{
//
//	public static Listener INSTANCE;
//	private static final int ID = 0;
//
//	private volatile boolean running;
//	private HotKey hotkey;
//	private Thread thread;
//
//	private Listener(final HotKey hotkey)
//	{
//		this.hotkey = hotkey;
//		INSTANCE = this;
//		running = true;
//
//		thread = new Thread(new Runnable()
//		{
//			@Override
//			public void run()
//			{
//				User32 lib = User32.INSTANCE;
//
//				System.out.println("unregistered last: " + lib.UnregisterHotKey(null, ID));
//
//				boolean register = lib.RegisterHotKey(null, ID, hotkey.summariseModifiers(), hotkey.getVk());
//				if (!register)
//					throw new IllegalStateException("Could not register hotkey!");
//
//				System.out.println("registered hotkey " + hotkey);
//
//
//				WinUser.MSG msg = new WinUser.MSG();
//				while (!thread.isInterrupted())
//				{
//					int x = lib.GetMessage(msg, null, 0, 0);
//					System.out.println(x);
//				}
//
//
////				while ((lib.GetMessage(msg, null, 0, 0) != -1))
////				{
////					if (!running)
////						return;
////
////					System.out.println("boop");
////				}
//
//			}
//		});
//		thread.start();
//	}
//
//	public void stop()
//	{
//		running = false;
//		boolean unregister = User32.INSTANCE.UnregisterHotKey(null, ID);
//		if (!unregister)
//			System.out.println("could not unregister");
//	}
//
//	public static void restart()
//	{
//		if (INSTANCE != null)
//		{
//			if (INSTANCE.hotkey.equals(HotKey.CURRENT))
//				return;
//			INSTANCE.stop();
//		}
//		INSTANCE = new Listener(HotKey.CURRENT);
//		System.out.println("new made");
//	}
//
//}
