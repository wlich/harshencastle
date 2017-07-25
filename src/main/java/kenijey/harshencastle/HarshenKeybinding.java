package kenijey.harshencastle;

import org.lwjgl.input.Keyboard;

import kenijey.harshencastle.handlers.HandlerHarshenInventory;
import kenijey.harshencastle.network.HarshenNetwork;
import kenijey.harshencastle.network.events.NetworkEventHarshenInvToggle;
import kenijey.harshencastle.network.packets.MessagePacketHarshenInvToggle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

public class HarshenKeybinding 
{

	private final KeyBinding key_openSlot;
	
	public HarshenKeybinding()
	{
		key_openSlot = new KeyBinding("key_openSlot.description", Keyboard.KEY_G, "harshencastle");
		ClientRegistry.registerKeyBinding(key_openSlot);

	}
	
	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event)
	{
		voidKeyOpenSlot();
	}
	
	public void voidKeyOpenSlot()
	{
		if(key_openSlot.isPressed())
		{
			if(Minecraft.getMinecraft().player.getHeldItemMainhand().getItem() != Item.getItemFromBlock(Blocks.AIR) || HandlerHarshenInventory.instance.hasItem())
				Minecraft.getMinecraft().player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 1, 1);
			HarshenNetwork.sendToServer(new MessagePacketHarshenInvToggle());
			
		}

	}
	
}
