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
import java.text.SimpleDateFormat;
import java.util.Date;

public class Screenie
{

	public static File SAVE_DIRECTORY;
	public static final File DEFAULT_SAVE_DIR = new File(Shell32Util.getFolderPath(ShlObj.CSIDL_MYPICTURES) + "/Screenies/");
	private static SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH;mm;ss");


	public Screenie()
	{
		BufferedImage capture = null;
		try
		{
			WinDef.RECT rect = new WinDef.RECT();
			User32.INSTANCE.GetWindowRect(User32.INSTANCE.GetForegroundWindow(), rect);
			Rectangle screenRect = new Rectangle(rect.toRectangle());
			capture = new Robot().createScreenCapture(screenRect);
		} catch (AWTException e)
		{
			System.err.println("Could not take screenie");
			e.printStackTrace();
		}


		if (SAVE_DIRECTORY == null)
			SAVE_DIRECTORY = DEFAULT_SAVE_DIR;
		try
		{
			File saveFile = new File(SAVE_DIRECTORY.getAbsolutePath() + "\\" + format.format(new Date()) + ".png");
			saveFile.delete();
			saveFile.createNewFile();

			ImageIO.write(capture, "png", saveFile);
		} catch (IOException e)
		{
			System.err.println("Could not save screenie");
			e.printStackTrace();
		}

		Tray.INSTANCE.flash();

	}


}
