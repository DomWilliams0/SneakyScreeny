package net.chunk64.sneaky.gui.menus;

import net.chunk64.sneaky.hotkey.HotKey;
import net.chunk64.sneaky.hotkey.HotKeyListener;
import net.chunk64.sneaky.util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HotkeyMenu extends JMenuItem
{

	public HotkeyMenu()
	{
		setText("Hotkey");
		setMnemonic('h');

		final KeyChooser keyChooser = new KeyChooser();
		final Object[] choices = setToCurrent(keyChooser.list);

		addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showOptionDialog(null, keyChooser, "Hotkey Chooser", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, choices, null);
				HotKey.CURRENT = keyChooser.createHotKey(choices);
				HotKeyListener.updateHotkey();
			}
		});
	}

	private Object[] setToCurrent(JComboBox hotkeySelection)
	{
		Object[] buttons = new Object[]{new JRadioButton("ALT"), new JRadioButton("SHIFT"), new JRadioButton("CTRL"), "Done"};
		for (int i = 0; i < 3; i++)
			((JRadioButton) buttons[i]).setSelected(HotKey.CURRENT.getModifiersArray()[i]);

		hotkeySelection.setSelectedItem(HotKey.CURRENT.getCharacter());

		return buttons;

	}

	private class KeyChooser extends JPanel
	{
		private JComboBox<Character> list;

		private KeyChooser()
		{
			JLabel label = new JLabel("Choose your hotkey: ");
			add(label, BorderLayout.NORTH);

			list = new JComboBox<Character>(Utils.getVkCharacters());
			list.setEditable(false);

			DefaultListCellRenderer cellRenderer = new DefaultListCellRenderer();
			cellRenderer.setHorizontalAlignment(CENTER);
			list.setRenderer(cellRenderer);

			add(list, BorderLayout.SOUTH);
		}

		private HotKey createHotKey(Object[] buttons)
		{
			HotKey hotKey = new HotKey((Character) list.getSelectedItem());
			for (int i = 0; i < buttons.length - 1; i++)
				hotKey.getModifiersArray()[i] = ((JRadioButton) buttons[i]).isSelected();
			hotKey.summariseModifiers();
			return hotKey;
		}
	}
}
