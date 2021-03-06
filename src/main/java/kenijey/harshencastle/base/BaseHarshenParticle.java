package kenijey.harshencastle.base;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;

public abstract class BaseHarshenParticle extends Particle
{
	private boolean disableMoving;
	
	protected abstract int getXIndex();
	protected abstract int getYIndex();
	
    public BaseHarshenParticle(World world, double xCoordIn, double yCoordIn, double zCoordIn, double motionXIn, double motionYIn, double motionZIn, float par14, boolean disableMoving)
    {
        super(world, xCoordIn, yCoordIn, zCoordIn, 0.0D, 0.0D, 0.0D);
        
        this.particleTextureIndexX = getXIndex();
        this.particleTextureIndexY = getYIndex();
        this.motionX *= 0.10000000149011612D;
        this.motionY *= 0.10000000149011612D;
        this.motionZ *= 0.10000000149011612D;
        this.motionX += motionXIn;
        this.motionY += motionYIn;
        this.motionZ += motionZIn;
        this.particleScale *= 0.75F;
        this.particleScale *= par14;
        this.particleMaxAge = (int)((8.0D / (Math.random() * 0.8D + 0.2D)) * 8);
        this.particleMaxAge = (int)((float)this.particleMaxAge * par14);
        this.particleAge = (particleMaxAge / 2) + (int)((particleMaxAge / 2) * world.rand.nextInt(7));
        this.particleAlpha = 1.0F;
        this.particleRed = 1.0F;
        this.particleGreen = 1.0F;
        this.particleBlue = 1.0F;
        this.canCollide = false;
        this.disableMoving = disableMoving;
    }
	@Override
    public int getFXLayer()
    {
        return 2;
    }
	
	protected abstract ResourceLocation getLocation();
    
    @Override
    public void renderParticle(BufferBuilder buffer, Entity entity, float partialTicks, float rotX, float rotXZ, float rotZ, float rotYZ, float rotXY)
    {
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(getLocation());
        
        float scaleMultiplier = ((float)this.particleAge + partialTicks) / (float)this.particleMaxAge * 32.0F;
        scaleMultiplier = MathHelper.clamp(scaleMultiplier, 0.0F, 1.0F);
        this.particleScale = this.particleScale * scaleMultiplier;
        
        GlStateManager.depthMask(false);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 1);

        super.renderParticle(buffer, entity, partialTicks, rotX, rotXZ, rotZ, rotYZ, rotXY);

        GlStateManager.disableBlend();
        GlStateManager.depthMask(true);

    }
    
    @Override
    public int getBrightnessForRender(float p_189214_1_)
    {
    	BlockPos blockpos = new BlockPos(this.posX, this.posY, this.posZ);
        return this.world.isBlockLoaded(blockpos) ? getLightCombonation(blockpos, 0) : 0;
    }
    
    private int getLightCombonation(BlockPos pos, int lightValue)
    {
    	int i = world.getLightFromNeighborsFor(EnumSkyBlock.SKY, pos);
        int j = world.getLightFromNeighborsFor(EnumSkyBlock.BLOCK, pos.add(0, 1, 0));

        if (j < lightValue)
        {
            j = lightValue;
        }

        return i << 20 | j << 4;
    }
    
    @Override
    public void onUpdate()
    {
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;

        if (particleAge++ >= particleMaxAge)
        {
            this.setExpired();
        }

        this.particleTextureIndexX = 7 - particleAge * 8 / particleMaxAge;
        if(disableMoving)
    	{
        	motionX = 0;
        	motionY = 0;
        	motionZ = 0;
        	if(world.isAirBlock(new BlockPos(posX, posY, posZ)))
        		this.setExpired();
    	}
        this.move(motionX, motionY, motionZ);
        if (posY == prevPosY)
        {
            motionX *= 1.1D;
            motionZ *= 1.1D;
        }

        motionX *= 0.9599999785423279D;
        motionY *= 0.9599999785423279D;
        motionZ *= 0.9599999785423279D;

        if (this.onGround)
        {
            motionX *= 0.699999988079071D;
            motionZ *= 0.699999988079071D;
        }
    }
    
    
}
