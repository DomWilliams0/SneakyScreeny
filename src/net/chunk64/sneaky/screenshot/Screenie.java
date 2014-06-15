package net.chunk64.sneaky.screenshot;

import com.sun.jna.platform.win32.Shell32Util;
import com.sun.jna.platform.win32.ShlObj;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Screenie
{

	public static final File DEFAULT_SAVE_DIR = new File(Shell32Util.getFolderPath(ShlObj.CSIDL_MYPICTURES) + "/Screenies/");
	public static File SAVE_DIRECTORY;
	public static int NEXT_ID;
	private static Robot robot;

	static
	{
		try
		{
			robot = new Robot();
		} catch (AWTException e)
		{
			System.err.println("Could not create robot");
			e.printStackTrace();
		}
	}

	public Screenie()
	{
		WinDef.RECT rect = new WinDef.RECT();
		User32.INSTANCE.GetWindowRect(User32.INSTANCE.GetForegroundWindow(), rect);
		Rectangle screenRect = new Rectangle(rect.toRectangle());
		BufferedImage capture = robot.createScreenCapture(screenRect);


		if (SAVE_DIRECTORY == null)
			SAVE_DIRECTORY = DEFAULT_SAVE_DIR;
		try
		{
			String path = SAVE_DIRECTORY.getAbsolutePath() + "\\" + "Screeny-";
			File saveFile = null;
			while (saveFile == null || saveFile.exists())
				saveFile = new File(path + ++NEXT_ID + ".png");

			saveFile.createNewFile();

			ImageIO.write(capture, "png", saveFile);
		} catch (IOException e)
		{
			System.err.println("Could not save screeny");
			e.printStackTrace();
		}

		Tray.INSTANCE.flash();

	}


}
