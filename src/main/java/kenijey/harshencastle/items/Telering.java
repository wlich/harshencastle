package kenijey.harshencastle.items;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class Telering extends Item
{
	public Telering()
	{
		setUnlocalizedName("telering");
		setRegistryName("telering");
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("\u00A75" + new TextComponentTranslation("accessoryitem").getFormattedText() + " \u00A77" + new TextComponentTranslation("ring").getFormattedText());
		tooltip.add(" ");
		tooltip.add("\u00A73" + new TextComponentTranslation("telering1").getFormattedText());
		tooltip.add("\u00a73" + new TextComponentTranslation("telering2").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
}