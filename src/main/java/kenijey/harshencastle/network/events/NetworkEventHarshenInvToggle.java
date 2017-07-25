package kenijey.harshencastle.network.events;

import kenijey.harshencastle.handlers.HandlerHarshenInventoryClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

public class NetworkEventHarshenInvToggle {
	
	public static void go(EntityPlayer player)
	{
		ItemStack stack = player.getHeldItemMainhand();
		ItemStack newStack = stack.copy();
		int count  = stack.getCount();
		HandlerHarshenInventoryClient inv = HandlerHarshenInventoryClient.instance;
		if(stack.getItem() == Item.getItemFromBlock(Blocks.AIR))
		{
			ItemStack item = inv.getItem();
			inv.delItem();
			player.setHeldItem(EnumHand.MAIN_HAND, item);
			inv.save(player);
			return;
		}
		if(inv.hasItem())
		{
			if(stack.getItem() != inv.getItem().getItem() || stack.getCount() == 64)
				return;
			count += 2;
			inv.delItem();
		}
		else
		{
			inv.setItem(player, stack);
		}
		newStack.setCount(count - 1);
		inv.save(player);
		player.setHeldItem(EnumHand.MAIN_HAND, newStack);
	}

}