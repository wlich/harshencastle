package kenijey.harshencastle.base;

import kenijey.harshencastle.itemstackhandlers.HarshenItemStackHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;

public abstract class BaseTileEntityHarshenInventory extends BaseHarshenTileEntity implements net.minecraft.util.ITickable, ICapabilityProvider
{
	protected final HarshenItemStackHandler handler;
	protected boolean hasItem = false;
	protected int timer;
	private int dirtyTimer;
	
	public BaseTileEntityHarshenInventory(int size){
		this.handler = new HarshenItemStackHandler(size);
	}
	
	
	@Override
	public void update()
	{
		timer ++;
		tick();
		if(dirtyTimer++ % 10 == 0)
			dirty();
		
	}
	
	public int getTimer()
	{
		return timer;
	}
	
	protected void tick()
	{
	}
	
	public boolean isSlotEmpty(int slot)
	{	 
		return this.handler.getStackInSlot(slot).getItem() == Items.AIR;
	}
	
	
	public boolean setItem(int slot, ItemStack item)
	{
		this.handler.setStackInSlot(slot, item);
		dirty();
		onItemAdded(slot);
		return true;
	}
	
	protected void onItemAdded(int slot)
	{
		
	}
		
	public void setItemAir(int slot)
	{
		this.handler.setStackInSlot(slot, new ItemStack(Blocks.AIR));
		dirty();
	}
	
	public ItemStack getItem(int slot)
	{
		return handler.getStackInSlot(slot);
	}
	
	protected void dirty()
	{
		markDirty();
		IBlockState state = world.getBlockState(pos);
		world.notifyBlockUpdate(pos, state, state, 3);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		if (capability  == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return (T) this.handler;
		return super.getCapability(capability, facing);
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		if (capability  == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return true;
		return super.hasCapability(capability, facing);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		this.handler.deserializeNBT(compound.getCompoundTag("ItemStackHandler"));
		super.readFromNBT(compound);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setTag("ItemStackHandler", this.handler.serializeNBT());
		return super.writeToNBT(nbt);
	}

}

