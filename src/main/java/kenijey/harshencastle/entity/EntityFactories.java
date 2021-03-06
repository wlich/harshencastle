package kenijey.harshencastle.entity;

import kenijey.harshencastle.entityrender.RenderSoulPart;
import kenijey.harshencastle.entityrender.RenderSoullessKnight;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class EntityFactories 
{
    public static class FactorySoulPart implements IRenderFactory<EntitySoulPart> 
    {
        @Override
        public Render<? super EntitySoulPart> createRenderFor(RenderManager manager) {
          return new RenderSoulPart(manager);
        }
    }

    public static class FactorySoullessKnight implements IRenderFactory<EntitySoullessKnight> 
    {
    	@Override
        public Render<? super EntitySoullessKnight> createRenderFor(RenderManager manager) {
          return new RenderSoullessKnight(manager);
        }
    }
}
