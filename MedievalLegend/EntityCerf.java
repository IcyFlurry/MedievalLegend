package ModMedievalLegend;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntityCerf extends EntityCreature {

	public EntityCerf(World par1World) {
		super(par1World);

	}
	
	@Override
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(40D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.699999988079071D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(8.0D);
    }
	

}
