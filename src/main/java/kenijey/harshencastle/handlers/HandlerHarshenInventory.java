package kenijey.harshencastle.handlers;

import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.enums.inventory.EnumInventorySlots;
import kenijey.harshencastle.interfaces.IHarshenProvider;
import kenijey.harshencastle.itemstackhandlers.HarshenItemStackHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.items.ItemStackHandler;

public class HandlerHarshenInventory 
{	
	int tickTimer = 0;
	
	@SubscribeEvent
	public void playerTick(PlayerTickEvent event)
	{
		tickTimer++;
		if(event.player.world.isRemote)
			return;
		HarshenItemStackHandler handler = HarshenUtils.getHandler(event.player);
		for(int slot = 0; slot < handler.getSlots(); slot++)
			if(handler.getStackInSlot(slot).getItem() instanceof IHarshenProvider)
				((IHarshenProvider)handler.getStackInSlot(slot).getItem()).onTick(event.player, tickTimer);
	}
}
