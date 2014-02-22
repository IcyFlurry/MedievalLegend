package ModMedievalLegend;

import net.minecraft.client.model.ModelBiped;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;

public class ClientProxy extends CommonProxy {
	
	public static int renderTableID;
	public static int renderBucheID;
	
	@Override
	public void registerRender()
	{
		
		renderTableID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(renderTableID, new RenderCustomBlocks());
		renderBucheID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(renderBucheID, new RenderCustomBlocks());
		RenderingRegistry.registerEntityRenderingHandler(EntityCerf.class, new RenderCerf(new ModelCerf(), 0.5f));
		
		
	}
	

}
