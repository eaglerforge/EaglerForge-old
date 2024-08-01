package net.eaglerforge.gui;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.teavm.jso.JSBody;

import com.google.common.collect.Lists;

import net.lax1dude.eaglercraft.v1_8.EagRuntime;
import net.lax1dude.eaglercraft.v1_8.internal.FileChooserResult;
import net.lax1dude.eaglercraft.v1_8.internal.vfs2.VFile2;
import net.lax1dude.eaglercraft.v1_8.log4j.LogManager;
import net.lax1dude.eaglercraft.v1_8.log4j.Logger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;

public class GuiMods extends GuiScreen {
	private static final Logger logger = LogManager.getLogger();
	private final GuiScreen parentScreen;
	public ArrayList<VFile2> modList;
	public int selectedModIdx = -1;
	private GuiModList rows;
	public Minecraft mc = Minecraft.getMinecraft();
	private GuiButton deleteButton;

	@JSBody(params = { "name" }, script = "return window.prompt(name, '') || '';")
    private static native String prompt(String name);

	public void updateModsList() {
		// what is this 'vfs' thing! doesn't even have ability to index a directory!!
		try {
			VFile2 modListData = new VFile2("mods.txt");
			modList = new ArrayList<VFile2>();
			if (modListData.getAllChars() == null) {
				modListData.setAllChars("");
			}
			String[] filenames = modListData.getAllChars().split("\\|");
			System.out.println(filenames.toString());
			for (int i = 0; i < filenames.length; i++) {
				if (filenames[i] != "") {
					modList.add(new VFile2("mods", filenames[i]));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public GuiMods(GuiScreen parentScreenIn) {
		this.parentScreen = parentScreenIn;
		updateModsList();
	}

	/**
	 * +
	 * Adds the buttons (and other controls) to the screen in
	 * question. Called when the GUI is displayed and when the
	 * window resizes, the buttonList is cleared beforehand.
	 */
	public void initGui() {
		GuiButton btn;
		this.buttonList.add(btn = new GuiOptionButton(1, this.width / 2 - 154, this.height - 24,
				I18n.format("eaglerforge.menu.mods.addmod"
						+ "", new Object[0])));
		this.buttonList.add(btn = new GuiOptionButton(2, this.width / 2, this.height - 24,
				I18n.format("gui.done"
						+ "", new Object[0])));
		this.buttonList.add(deleteButton = new GuiOptionButton(3, this.width / 2 - 154, this.height - 48,
				I18n.format("selectWorld.delete"
						+ "", new Object[0])));
		this.buttonList.add(btn = new GuiOptionButton(4, this.width / 2, this.height - 48,
				I18n.format("eaglerforge.menu.mods.addmodurl"
						+ "", new Object[0])));
		deleteButton.enabled = false;
		rows = new GuiModList(Minecraft.getMinecraft(), this.width, this.height, 48, this.height - 56, 14, this);
		rows.registerScrollButtons(4, 5);
	}

	public void handleMouseInput() throws IOException {
		super.handleMouseInput();
		this.rows.handleMouseInput();
	}

	/**
	 * +
	 * Called by the controls from the buttonList when activated.
	 * (Mouse pressed for buttons)
	 */
	protected void actionPerformed(GuiButton parGuiButton) {
		if (parGuiButton.enabled) {
			if (parGuiButton.id == 2) {
				Minecraft.getMinecraft().displayGuiScreen(parentScreen);
			} else if (parGuiButton.id == 1) {
				EagRuntime.displayFileChooser("text/javascript", "js");
			} else if (parGuiButton.id == 3) {
				VFile2 modListData = new VFile2("mods.txt");
				String[] mods = modListData.getAllChars().split("\\|");
				String[] mods_new = new String[mods.length - 1];

				for (int i = 0, k = 0; i < mods.length; i++) {
					if (i != selectedModIdx) {
						mods_new[k] = mods[i];
						k++;
					}
				}
				modListData.setAllChars(String.join("|", mods_new));

				//After a bunch of debugging, I think this doesn't properly cleanup anything, as indexedDb is still polluted with deleted mods.
				try {
					modList.get(selectedModIdx).delete();
				} catch (Exception e) {
					// remote mod (url)
				}

				updateModsList();
			} else if (parGuiButton.id == 4) {
				String url = GuiMods.prompt("Enter the mod url: ");
				if (url != "" && url != null) {
					VFile2 modListData = new VFile2("mods.txt");
					String[] mods = modListData.getAllChars().split("\\|");
					String[] mods_new = new String[mods.length + 1];

					for (int i = 0; i < mods.length; i++) {
						mods_new[i] = mods[i];
					}
					mods_new[mods.length] = "web@" + url;

					modListData.setAllChars(String.join("|", mods_new));
					updateModsList();
				}
			} else {
				rows.actionPerformed(parGuiButton);
			}
		} else {
			rows.actionPerformed(parGuiButton);
		}
	}

	/**
	 * +
	 * Draws the screen and all the components in it. Args : mouseX,
	 * mouseY, renderPartialTicks
	 */
	public void drawScreen(int i, int j, float f) {
		this.drawBackground(0);
		this.rows.drawScreen(i, j, f);
		this.drawCenteredString(this.fontRendererObj, I18n.format("eaglerforge.menu.mods", new Object[0]),
				this.width / 2,
				8, 0xFFFFFF);
		mc.fontRendererObj.drawSplitString(
				"Warning: Mods can run any Javascript code they want, potentially running malicious code. They can also ip-grab you and wipe all saved Eaglercraft data.",
				0, 24, this.width - 20, 0xFF2200); // I18n.format("eaglerforge.menu.mods.info", new Object[0]) Don't
													// know where
		// to change this, so hardcoded for now :P
		super.drawScreen(i, j, f);
	}

	public void updateScreen() {
		FileChooserResult modFile = null;
		if (EagRuntime.fileChooserHasResult()) {
			modFile = EagRuntime.getFileChooserResult();
			VFile2 idbModFile = new VFile2("mods", modFile.fileName);
			idbModFile.setAllBytes(modFile.fileData);

			VFile2 modListData = new VFile2("mods.txt");
			String[] mods = modListData.getAllChars().split("\\|");
			String[] mods_new = new String[mods.length + 1];

			for (int i = 0; i < mods.length; i++) {
				mods_new[i] = mods[i];
			}
			mods_new[mods.length] = modFile.fileName;

			modListData.setAllChars(String.join("|", mods_new));
			updateModsList();
		}
	}

	private class GuiModList extends GuiSlot {
		private GuiMods parentGui;
		private int slotHeight;

		public GuiModList(Minecraft mcIn, int widthIn, int heightIn, int topIn, int bottomIn, int slotHeightIn,
				GuiMods parent) {
			super(mcIn, widthIn, heightIn, topIn, bottomIn, slotHeightIn);
			parentGui = parent;
			slotHeight = slotHeightIn;
			setEnabled(true);
		}

		@Override
		protected int getContentHeight() {
			return parentGui.modList.size() * slotHeight;
		}

		@Override
		protected int getSize() {
			return parentGui.modList.size();
		}

		@Override
		protected void elementClicked(int index, boolean isDoubleClick, int mouseX, int mouseY) {
			// Handle the event when an element is clicked
			parentGui.selectedModIdx = index;
			parentGui.deleteButton.enabled = true;
		}

		@Override
		protected boolean isSelected(int index) {
			// Return true if the specified element is selected
			return parentGui.selectedModIdx == index;
		}

		@Override
		protected void drawBackground() {
			// Draw the background for the list elements
		}

		@Override
		protected void drawSlot(int entryID, int x, int y, int mouseXIn, int mouseYIn,
				int var6) {
			mc.fontRendererObj.drawStringWithShadow(modList.get(entryID).getName(), x, y, 0xFFFFFF);
		}
	}
}
