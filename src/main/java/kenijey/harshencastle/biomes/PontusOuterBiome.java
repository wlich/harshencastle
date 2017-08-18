package kenijey.harshencastle.biomes;

import java.util.Random;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.base.BaseLargeTreeGenerator;
import kenijey.harshencastle.base.BasePontusResourceBiome;
import kenijey.harshencastle.dimensions.PontusBiomeDecorator;
import kenijey.harshencastle.worldgenerators.pontus.NullGenerator;
import kenijey.harshencastle.worldgenerators.pontus.PontusWorldGeneratorDestroyedPlants;
import kenijey.harshencastle.worldgenerators.pontus.PontusWorldGeneratorStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class PontusOuterBiome extends BasePontusResourceBiome {
		
	public PontusOuterBiome() {
		super(new Biome.BiomeProperties("Pontus_Chaotic").setTemperature(5f).setRainDisabled().setBaseHeight(10f).setHeightVariation(0.5f));
		
		setRegistryName(HarshenCastle.MODID, "Pontus_Chaotic");

		this.spawnableCaveCreatureList.clear();
		this.spawnableCaveCreatureList.add(new SpawnListEntry(EntityEnderman.class, 10, 2, 7));
		
		this.spawnableCreatureList.clear();
		this.spawnableCreatureList.add(new SpawnListEntry(EntityEndermite.class, 100, 5, 17));

		this.decorator = new PontusBiomeDecorator();
		this.decorator.extraTreeChance = 0.5f;
		
		
	}
	
	
	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		return new BaseLargeTreeGenerator(false, HarshenBlocks.pontus_chaotic_leaves.getDefaultState(), HarshenBlocks.pontus_chaotic_wood);
	}
	
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random rand) {
		return new PontusWorldGeneratorDestroyedPlants();
	}
	
	@Override
	public void decorate(World worldIn, Random rand, BlockPos pos) {
		super.decorate(worldIn, rand, pos);
		for (int i = 0; i < 13; ++i)
        {
            int j = rand.nextInt(16) + 8;
            int k = rand.nextInt(16) + 8;
            new PontusWorldGeneratorStone(Blocks.OBSIDIAN.getDefaultState()).generate(worldIn, rand, worldIn.getTopSolidOrLiquidBlock(pos.add(j, 0, k)));
        }
	}
	
	@Override
	public int getLevel() {
		return 1;
	}

}