package net.eaglerforge;

import java.io.IOException;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import net.lax1dude.eaglercraft.v1_8.Keyboard;
import net.lax1dude.eaglercraft.v1_8.Mouse;
import net.lax1dude.eaglercraft.v1_8.internal.EnumCursorType;
import net.lax1dude.eaglercraft.v1_8.log4j.LogManager;
import net.lax1dude.eaglercraft.v1_8.log4j.Logger;
import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.lax1dude.eaglercraft.v1_8.sp.gui.GuiNetworkSettingsButton;
import net.lax1dude.eaglercraft.v1_8.sp.gui.GuiScreenConnectOption;
import net.lax1dude.eaglercraft.v1_8.sp.gui.GuiScreenLANConnecting;
import net.lax1dude.eaglercraft.v1_8.sp.lan.LANServerList;
import net.lax1dude.eaglercraft.v1_8.sp.relay.RelayServer;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiScreenAddServer;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.ServerList;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

/**+
 * This portion of EaglercraftX contains deobfuscated Minecraft 1.8 source code.
 * 
 * Minecraft 1.8.8 bytecode is (c) 2015 Mojang AB. "Do not distribute!"
 * Mod Coder Pack v9.18 deobfuscation configs are (c) Copyright by the MCP Team
 * 
 * EaglercraftX 1.8 patch files (c) 2022-2024 lax1dude, ayunami2000. All Rights Reserved.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * 
 */
public class GuiMods extends GuiScreen implements GuiYesNoCallback {
	private static final Logger logger = LogManager.getLogger();
	private GuiScreen parentScreen;
	private ServerList savedServerList;
	private GuiButton btnEditServer;
	private GuiButton btnSelectServer;
	private GuiButton btnDeleteServer;
	private boolean deletingServer;
	private boolean addingServer;
	private boolean editingServer;
	private boolean directConnect;
	private String hoveringText;


	private boolean initialized;
	private static long lastRefreshCommit = 0l;

	private static LANServerList lanServerList = null;

	public int ticksOpened;

	private final GuiNetworkSettingsButton relaysButton;

	public GuiMods(GuiScreen parentScreen) {
		this.parentScreen = parentScreen;
		this.relaysButton = new GuiNetworkSettingsButton(this);
	}

	/**+
	 * Adds the buttons (and other controls) to the screen in
	 * question. Called when the GUI is displayed and when the
	 * window resizes, the buttonList is cleared beforehand.
	 */
	public void initGui() {
		Keyboard.enableRepeatEvents(true);
		this.buttonList.clear();
		if (!this.initialized) {
			this.initialized = true;
		} else {
		}

		this.createButtons();
	}

	/**+
	 * Handles mouse input.
	 */
	public void handleMouseInput() throws IOException {
		super.handleMouseInput();
	}

	public void createButtons() {
		this.buttonList.add(this.btnDeleteServer = new GuiButton(2, this.width / 2 - 230, this.height - 28, 150, 20,
				I18n.format("eaglerforge.menu.mods.removemod", new Object[0])));
    	this.buttonList.add(new GuiButton(8, this.width / 2 + 4 - 77, this.height - 28, 150, 20,
				I18n.format("eaglerforge.menu.mods.addmod", new Object[0])));
		this.buttonList.add(new GuiButton(0, this.width / 2 + 4 + 80, this.height - 28, 150, 20,
				I18n.format("gui.done", new Object[0])));
	}

	/**+
	 * Called from the main game loop to update the screen.
	 */
	public void updateScreen() {
		super.updateScreen();
		++ticksOpened;
	}

	/**+
	 * Called when the screen is unloaded. Used to disable keyboard
	 * repeat events
	 */
	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

	/**+
	 * Called by the controls from the buttonList when activated.
	 * (Mouse pressed for buttons)
	 */
	protected void actionPerformed(GuiButton parGuiButton) {
		if (parGuiButton.enabled) {
			if (parGuiButton.id == 2) {
			} else if (parGuiButton.id == 1) {
			} else if (parGuiButton.id == 4) {
			} else if (parGuiButton.id == 3) {
			} else if (parGuiButton.id == 7) {
			} else if (parGuiButton.id == 0) {
				this.mc.displayGuiScreen(this.parentScreen);
			} else if (parGuiButton.id == 8) {
			}

		}
	}

	public void refreshServerList() {
		this.mc.displayGuiScreen(new GuiMods(this.parentScreen));
	}

	public void confirmClicked(boolean flag, int var2) {
		if (this.deletingServer) {
			this.deletingServer = false;
			long millis = System.currentTimeMillis();
			if (millis - lastRefreshCommit > 700l) {
				lastRefreshCommit = millis;
				this.refreshServerList();
			}
		} else if (this.directConnect) {
			this.directConnect = false;
		} else if (this.addingServer) {
			this.addingServer = false;
			long millis = System.currentTimeMillis();
			if (millis - lastRefreshCommit > 700l) {
				lastRefreshCommit = millis;
				this.refreshServerList();
			}
		} else if (this.editingServer) {
			this.editingServer = false;
			long millis = System.currentTimeMillis();
			if (millis - lastRefreshCommit > 700l) {
				lastRefreshCommit = millis;
				this.refreshServerList();
			}
		}
	}


	/**+
	 * Draws the screen and all the components in it. Args : mouseX,
	 * mouseY, renderPartialTicks
	 */
	public void drawScreen(int i, int j, float f) {
		this.hoveringText = null;
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRendererObj, I18n.format("eaglerforge.menu.mods.title", new Object[0]), this.width / 2,
				20, 16777215);
		super.drawScreen(i, j, f);
		if (this.hoveringText != null) {
			this.drawHoveringText(Lists.newArrayList(Splitter.on("\n").split(this.hoveringText)), i, j);
		}
	}



	private void connectToServer(ServerData server) {
		this.mc.displayGuiScreen(new GuiConnecting(this, this.mc, server));
	}


	public void setHoveringText(String parString1) {
		this.hoveringText = parString1;
	}

	/**+
	 * Called when the mouse is clicked. Args : mouseX, mouseY,
	 * clickedButton
	 */
	protected void mouseClicked(int parInt1, int parInt2, int parInt3) {
		relaysButton.mouseClicked(parInt1, parInt2, parInt3);
		super.mouseClicked(parInt1, parInt2, parInt3);
	}


}