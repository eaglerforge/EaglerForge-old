package net.eaglerforge.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;
import java.util.Random;

public class ModManager extends GuiScreen {
  private final Random random = new Random();

  @override
  public void initGui() {
    // Add a button to the GUI. Parameters: id, x, y, width, height, text
    this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 2 - 24, 200, 20, "Play Random Sound"));
    super.initGui();
  }

  @override
  protected void actionPerformed(GuiButton button) throws IOException {
    if (button.id == 0) {
      // List of sound locations. Add more as needed.
      ResourceLocation[] sounds = {
        new ResourceLocation("minecraft", "mob.cow.say"),
        new ResourceLocation("minecraft", "mob.endermen.portal"),
        // Add more Minecraft sound files here
      };
      
      // Play a random sound from the list
      ResourceLocation soundToPlay = sounds[random.nextInt(sounds.length)];
      Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(soundToPlay, 1.0F));
    }
    super.actionPerformed(button);
  }

  @override
  public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    this.drawDefaultBackground();
    super.drawScreen(mouseX, mouseY, partialTicks);
  }

  @override
  public boolean doesGuiPauseGame() {
    return false;
  }
}
