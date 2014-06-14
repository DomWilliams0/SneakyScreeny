package net.chunk64.sneaky.hotkey;

import net.chunk64.sneaky.screenshot.Screenie;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class HotKeyListener implements NativeKeyListener
{

	public static HotKeyListener INSTANCE;
	private HotKey hotKey;
	private long lastScreenie;


	public HotKeyListener()
	{
		INSTANCE = this;
		lastScreenie = System.currentTimeMillis();
		updateHotkey();

		try
		{
			GlobalScreen.registerNativeHook();
			GlobalScreen.getInstance().addNativeKeyListener(this);
		} catch (NativeHookException e)
		{
			e.printStackTrace();
			System.exit(-1);
		}

	}

	public void stop()
	{
		GlobalScreen.getInstance().removeNativeKeyListener(this);
		GlobalScreen.unregisterNativeHook();
	}


	@Override
	public void nativeKeyReleased(NativeKeyEvent e)
	{
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent e)
	{
	}

	@Override
	public void nativeKeyPressed(NativeKeyEvent e)
	{
		System.out.println("-press-");
		if (e.getKeyCode() == hotKey.getVk())
		{
			if (e.getModifiers() == hotKey.getModifiers())
			{
				long current = System.currentTimeMillis();
				long diff = current - lastScreenie;
				if (diff < 500)
					return;
				lastScreenie = current;

				new Screenie();
				System.out.println("boop");


			}
		}
	}

	public static void updateHotkey()
	{
		INSTANCE.hotKey = HotKey.CURRENT;
		//		System.out.println("listening for " + INSTANCE.hotKey);
	}
}
