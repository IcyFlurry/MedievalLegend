package ModMedievalLegend;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderCerf extends RenderLiving {

	protected static final ResourceLocation texture = new ResourceLocation("medievallegend:textures/entity/Cerf.png");

	
	public RenderCerf(ModelCerf modelCerf, float tailleOmbre) {
		super(modelCerf, tailleOmbre);
	}
	protected ResourceLocation getCerfTextures(EntityCerf Cerf)
    {
        return texture;
    }

    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.getCerfTextures((EntityCerf)par1Entity);
    }
    
}
