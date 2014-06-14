package net.chunk64.sneaky.util;

import org.jnativehook.keyboard.NativeKeyEvent;

import java.util.TreeMap;

public class Utils
{
	private static final TreeMap<Integer, Character> VK;

	static
	{
		VK = new TreeMap<Integer, Character>();
		VK.put(NativeKeyEvent.VK_NUMBER_SIGN, '#');
		VK.put(NativeKeyEvent.VK_ASTERISK, '*');
		VK.put(NativeKeyEvent.VK_ADD, '+');
		VK.put(NativeKeyEvent.VK_COMMA, ',');
		VK.put(NativeKeyEvent.VK_MINUS, '-');
		VK.put(NativeKeyEvent.VK_PERIOD, '.');
		VK.put(NativeKeyEvent.VK_SLASH, '/');
		VK.put(NativeKeyEvent.VK_SEMICOLON, ';');
		VK.put(NativeKeyEvent.VK_EQUALS, '=');
		VK.put(NativeKeyEvent.VK_RIGHT_PARENTHESIS, ']');
		VK.put(NativeKeyEvent.VK_LEFT_PARENTHESIS, '[');
		VK.put(NativeKeyEvent.VK_BACK_SLASH, '\'');
		VK.put(NativeKeyEvent.VK_0, '0');
		VK.put(NativeKeyEvent.VK_1, '1');
		VK.put(NativeKeyEvent.VK_2, '2');
		VK.put(NativeKeyEvent.VK_3, '3');
		VK.put(NativeKeyEvent.VK_4, '4');
		VK.put(NativeKeyEvent.VK_5, '5');
		VK.put(NativeKeyEvent.VK_6, '6');
		VK.put(NativeKeyEvent.VK_7, '7');
		VK.put(NativeKeyEvent.VK_8, '8');
		VK.put(NativeKeyEvent.VK_9, '9');
		VK.put(NativeKeyEvent.VK_A, 'a');
		VK.put(NativeKeyEvent.VK_B, 'b');
		VK.put(NativeKeyEvent.VK_C, 'c');
		VK.put(NativeKeyEvent.VK_D, 'd');
		VK.put(NativeKeyEvent.VK_E, 'e');
		VK.put(NativeKeyEvent.VK_F, 'f');
		VK.put(NativeKeyEvent.VK_G, 'g');
		VK.put(NativeKeyEvent.VK_H, 'h');
		VK.put(NativeKeyEvent.VK_I, 'i');
		VK.put(NativeKeyEvent.VK_J, 'j');
		VK.put(NativeKeyEvent.VK_K, 'k');
		VK.put(NativeKeyEvent.VK_L, 'l');
		VK.put(NativeKeyEvent.VK_M, 'm');
		VK.put(NativeKeyEvent.VK_N, 'n');
		VK.put(NativeKeyEvent.VK_O, 'o');
		VK.put(NativeKeyEvent.VK_P, 'p');
		VK.put(NativeKeyEvent.VK_Q, 'q');
		VK.put(NativeKeyEvent.VK_R, 'r');
		VK.put(NativeKeyEvent.VK_S, 's');
		VK.put(NativeKeyEvent.VK_T, 't');
		VK.put(NativeKeyEvent.VK_U, 'u');
		VK.put(NativeKeyEvent.VK_V, 'v');
		VK.put(NativeKeyEvent.VK_W, 'w');
		VK.put(NativeKeyEvent.VK_X, 'x');
		VK.put(NativeKeyEvent.VK_Y, 'y');
		VK.put(NativeKeyEvent.VK_Z, 'z');

	}

	public static Character getCharacterFromVk(int vk)
	{
		return VK.get(vk);
	}

	public static Character[] getVkCharacters()
	{
		return VK.values().toArray(new Character[VK.size()]);
	}

	public static int getVkFromCharacter(char c)
	{
		switch (c)
		{
			case ']':
				return NativeKeyEvent.VK_RIGHT_PARENTHESIS;
			case ';':
				return NativeKeyEvent.VK_SEMICOLON;
			case '\\':
				return NativeKeyEvent.VK_BACK_SLASH;
			case '.':
				return NativeKeyEvent.VK_PERIOD;
			case '#':
				return NativeKeyEvent.VK_NUMBER_SIGN;
			case '/':
				return NativeKeyEvent.VK_SLASH;
			case '[':
				return NativeKeyEvent.VK_LEFT_PARENTHESIS;
			case '=':
				return NativeKeyEvent.VK_EQUALS;
			case ',':
				return NativeKeyEvent.VK_COMMA;
			case '-':
				return NativeKeyEvent.VK_SUBTRACT;
			case '+':
				return NativeKeyEvent.VK_ADD;
			case '*':
				return NativeKeyEvent.VK_MULTIPLY;
			default:
				try
				{
					return (Integer) NativeKeyEvent.class.getField("VK_" + Character.toUpperCase(c)).get(null);
				} catch (Exception e)
				{
					e.printStackTrace();
					return -1;
				}
		}
	}
}
