package ModMedievalLegend;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockCocoa;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.BlockOre;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockStep;
import net.minecraft.block.BlockWeb;
import net.minecraft.block.BlockWoodSlab;
import net.minecraft.block.StepSound;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBed;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RemoveRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
 
@Mod(modid = "MedievalLegend", name = "Medieval Legend", version = "1.2.4")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

 
public class Base
{
	@SidedProxy(clientSide = "ModMedievalLegend.ClientProxy", serverSide = "ModMedievalLegend.CommonProxy")
	public static CommonProxy proxy;
	@Instance("MedievalLegend")
	public static Base instance;
	
	
	//Materiaux
	//Lourdes : Cuivre<Fer<Acier<Argent<Mithril
	//Legeres :Tissu<Peau=Fourrure<Cuir<Mailles<Os
	//Tissu<Peau=Fourrure<Cuir<Cuivre<Fer=Mailles<Acier=Os<Argent<Mithril avec fer//15
	static EnumArmorMaterial Argent = net.minecraftforge.common.EnumHelper.addArmorMaterial("Argent", 20, new int[]{5, 5, 5, 4}, 25);//19
	static EnumArmorMaterial Acier = net.minecraftforge.common.EnumHelper.addArmorMaterial("Acier", 20, new int[]{4, 5, 4, 4}, 20);//17
	static EnumArmorMaterial Mithril = net.minecraftforge.common.EnumHelper.addArmorMaterial("Mithril", 20, new int[]{5, 6, 6, 5}, 30);//22
	static EnumArmorMaterial Cuivre = net.minecraftforge.common.EnumHelper.addArmorMaterial("Cuivre", 20, new int[]{3, 3, 3, 3}, 8);//12
	static EnumArmorMaterial FourrureMat = net.minecraftforge.common.EnumHelper.addArmorMaterial("Fourrure", 20, new int[]{2, 2, 2, 2}, 6); //8
	static EnumArmorMaterial PeauMat = net.minecraftforge.common.EnumHelper.addArmorMaterial("Peau", 20, new int[]{2, 2, 2, 2}, 6); //8
	static EnumArmorMaterial OsMat = net.minecraftforge.common.EnumHelper.addArmorMaterial("Os", 20, new int[]{4, 5, 4, 4}, 20); //17
	static EnumArmorMaterial TissuMat = net.minecraftforge.common.EnumHelper.addArmorMaterial("Tissu", 20, new int[]{1, 2, 1, 1}, 6); //5
	static EnumArmorMaterial CuirMat = net.minecraftforge.common.EnumHelper.addArmorMaterial("Cuir", 20, new int[]{3, 3, 3, 2}, 6); //11
	static EnumArmorMaterial MaillesMat = net.minecraftforge.common.EnumHelper.addArmorMaterial("Mailles", 20, new int[]{4, 4, 4, 3}, 10);//15
	
	
	//Blocs
    public static final Block Caisse = (new Block(4000, Material.wood)).setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("Caisse").setTextureName("medievallegend:Caisse").setCreativeTab(CreativeTabs.tabBlock);
    public static final Block Paille = (new Block(4004, Material.grass)).setHardness(0.5F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("Paille").setTextureName("medievallegend:Paille");
    public static final Block EscaliersPaille = (new EscalierCustom(4001, Base.Paille, 0)).setUnlocalizedName("EscaliersPaille");
    public static final Block PlantMalt = new PlantMalt(4002).setUnlocalizedName("PlantMalt"); 
    public static final Block Lanterne = (new Lanterne(4003)).setHardness(0.2F).setResistance(5.0F).setUnlocalizedName("Lanterne").setTextureName("Lanterne").setLightValue(0.7F).setCreativeTab(CreativeTabs.tabDecorations);
    public static final Block LitPaille = (new LitPaille(4005)).setHardness(0.2F).setUnlocalizedName("LitPaille").setTextureName("medievallegend:LitPaille");
    public static final Block Torchis = (new Block(4006, Material.clay)).setHardness(0.5F).setStepSound(Block.soundSnowFootstep).setUnlocalizedName("Torchis").setTextureName("medievallegend:Torchis").setCreativeTab(CreativeTabs.tabBlock);
    public static final Block MineraiCuivre = (new BlockOre(4007)).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("MineraiCuivre").setTextureName("medievallegend:MineraiCuivre");
    public static final Block MineraiArgent = (new BlockOre(4008)).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("MineraiArgent").setTextureName("medievallegend:MineraiArgent");
    public static final Block MineraiMithril = (new BlockOre(4009)).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("MineraiMithril").setTextureName("medievallegend:MineraiMithril");
    public static final Block Table = (new BlocTable(4010, Material.wood)).setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("Table").setTextureName("medievallegend:Caisse").setCreativeTab(CreativeTabs.tabDecorations);
    public static final Block BucheBloc = (new BlocBuche(4011, Material.wood)).setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("BucheBloc").setTextureName("medievallegend:Bois").setCreativeTab(CreativeTabs.tabDecorations);
    public static final Block Lucioles = (new BlockLucioles(4012)).setLightOpacity(1).setHardness(1.0F).setUnlocalizedName("Lucioles").setTextureName("medievallegend:lucioles").setLightValue(0.4F).setCreativeTab(CreativeTabs.tabDecorations);
    
    
    //Items
    public static Item Malt = (new Item(4051)).setUnlocalizedName("Malt").setCreativeTab(CreativeTabs.tabMaterials).setTextureName("medievallegend:Malt");
    public static final Item Biere = (new ItemFood(4050, 3, 0.3F, false)).setUnlocalizedName("Biere").setTextureName("medievallegend:Biere").setCreativeTab(CreativeTabs.tabFood);
    public static Item GrainesMalt = new ItemSeeds(4052, PlantMalt.blockID, Block.tilledField.blockID).setUnlocalizedName("GrainesMalt").setTextureName("medievallegend:GrainesMalt");
    public static Item ItemLitPaille = (new ItemLitPaille(4053)).setMaxStackSize(1).setUnlocalizedName("ItemLitPaille").setTextureName("medievallegend:LitPaille").setCreativeTab(CreativeTabs.tabDecorations);
    public static Item LingotArgent = (new Item(4054)).setUnlocalizedName("LingotArgent").setCreativeTab(CreativeTabs.tabMaterials).setTextureName("medievallegend:LingotArgent");
    public static Item LingotAcier = (new Item(4055)).setUnlocalizedName("LingotAcier").setCreativeTab(CreativeTabs.tabMaterials).setTextureName("medievallegend:LingotAcier");
    public static Item LingotMithril = (new Item(4056)).setUnlocalizedName("LingotMithril").setCreativeTab(CreativeTabs.tabMaterials).setTextureName("medievallegend:LingotMithril");
    public static Item LingotCuivre = (new Item(4057)).setUnlocalizedName("LingotCuivre").setCreativeTab(CreativeTabs.tabMaterials).setTextureName("medievallegend:LingotCuivre");
    public static Item Fourrure = (new Item(4058)).setUnlocalizedName("Fourrure").setCreativeTab(CreativeTabs.tabMaterials).setTextureName("medievallegend:Fourrure");
    public static Item Peau = (new Item(4059)).setUnlocalizedName("Peau").setCreativeTab(CreativeTabs.tabMaterials).setTextureName("medievallegend:Peau");
    public static Item PlaqueOs = (new Item(4060)).setUnlocalizedName("PlaqueOs").setCreativeTab(CreativeTabs.tabMaterials).setTextureName("medievallegend:PlaqueOs");
    public static Item Tissu = (new Item(4062)).setUnlocalizedName("Tissu").setCreativeTab(CreativeTabs.tabMaterials).setTextureName("medievallegend:Tissu");
    public static Item Maillon = (new Item(4063)).setUnlocalizedName("Maillon").setCreativeTab(CreativeTabs.tabMaterials).setTextureName("medievallegend:Maillon");
    public static Item Mailles = (new Item(4064)).setUnlocalizedName("Mailles").setCreativeTab(CreativeTabs.tabMaterials).setTextureName("medievallegend:Mailles");
    public static Item Buche = (new Item(4065)).setUnlocalizedName("Buche").setCreativeTab(CreativeTabs.tabMaterials).setTextureName("medievallegend:Buche");
    
    //Armures
    
    public static Item HeaumeArgent = new ArmureArgent(3500, Argent, 0,0).setUnlocalizedName("HeaumeArgent").setTextureName("medievallegend:HeaumeArgent");
    public static Item PlastronArgent = new ArmureArgent(3501, Argent, 0,1).setUnlocalizedName("PlastronArgent").setTextureName("medievallegend:PlastronArgent");
    public static Item JambesArgent = new ArmureArgent(3502, Argent, 0,2).setUnlocalizedName("JambesArgent").setTextureName("medievallegend:JambesArgent");
    public static Item BottesArgent = new ArmureArgent(3503, Argent, 0,3).setUnlocalizedName("BottesArgent").setTextureName("medievallegend:BottesArgent");
    public static Item HeaumeAcier = new ArmureAcier(3504, Acier, 0,0).setUnlocalizedName("HeaumeAcier").setTextureName("medievallegend:HeaumeAcier");
    public static Item PlastronAcier = new ArmureAcier(3505, Acier, 0,1).setUnlocalizedName("PlastronAcier").setTextureName("medievallegend:PlastronAcier");
    public static Item JambesAcier = new ArmureAcier(3506, Acier, 0,2).setUnlocalizedName("JambesAcier").setTextureName("medievallegend:JambesAcier");
    public static Item BottesAcier = new ArmureAcier(3507, Acier, 0,3).setUnlocalizedName("BottesAcier").setTextureName("medievallegend:BottesAcier");
    public static Item HeaumeMithril = new ArmureMithril(3508, Mithril, 0,0).setUnlocalizedName("HeaumeMithril").setTextureName("medievallegend:HeaumeMithril");
    public static Item PlastronMithril = new ArmureMithril(3509, Mithril, 0,1).setUnlocalizedName("PlastronMithril").setTextureName("medievallegend:PlastronMithril");
    public static Item JambesMithril = new ArmureMithril(3510, Mithril, 0,2).setUnlocalizedName("JambesMithril").setTextureName("medievallegend:JambesMithril");
    public static Item BottesMithril = new ArmureMithril(3511, Mithril, 0,3).setUnlocalizedName("BottesMithril").setTextureName("medievallegend:BottesMithril");
    public static Item HeaumeCuivre = new ArmureCuivre(3512, Cuivre, 0,0).setUnlocalizedName("HeaumeCuivre").setTextureName("medievallegend:HeaumeCuivre");
    public static Item PlastronCuivre = new ArmureCuivre(3513, Cuivre, 0,1).setUnlocalizedName("PlastronCuivre").setTextureName("medievallegend:PlastronCuivre");
    public static Item JambesCuivre = new ArmureCuivre(3514, Cuivre, 0,2).setUnlocalizedName("JambesCuivre").setTextureName("medievallegend:JambesCuivre");
    public static Item BottesCuivre = new ArmureCuivre(3515, Cuivre, 0,3).setUnlocalizedName("BottesCuivre").setTextureName("medievallegend:BottesCuivre");
    public static Item HeaumeFourrure = new ArmureFourrure(3516, FourrureMat, 0,0).setUnlocalizedName("HeaumeFourrure").setTextureName("medievallegend:HeaumeFourrure");
    public static Item PlastronFourrure = new ArmureFourrure(3517, FourrureMat, 0,1).setUnlocalizedName("PlastronFourrure").setTextureName("medievallegend:PlastronFourrure");
    public static Item JambesFourrure = new ArmureFourrure(3518, FourrureMat, 0,2).setUnlocalizedName("JambesFourrure").setTextureName("medievallegend:JambesFourrure");
    public static Item BottesFourrure = new ArmureFourrure(3519, FourrureMat, 0,3).setUnlocalizedName("BottesFourrure").setTextureName("medievallegend:BottesFourrure");
    public static Item HeaumePeau = new ArmurePeau(3520, PeauMat, 0,0).setUnlocalizedName("HeaumePeau").setTextureName("medievallegend:HeaumePeau");
    public static Item PlastronPeau = new ArmurePeau(3521, PeauMat, 0,1).setUnlocalizedName("PlastronPeau").setTextureName("medievallegend:PlastronPeau");
    public static Item JambesPeau = new ArmurePeau(3522, PeauMat, 0,2).setUnlocalizedName("JambesPeau").setTextureName("medievallegend:JambesPeau");
    public static Item BottesPeau = new ArmurePeau(3523, PeauMat, 0,3).setUnlocalizedName("BottesPeau").setTextureName("medievallegend:BottesPeau");
    public static Item HeaumeOs = new ArmureOs(3524, OsMat, 0,0).setUnlocalizedName("HeaumeOs").setTextureName("medievallegend:HeaumeOs");
    public static Item PlastronOs = new ArmureOs(3525, OsMat, 0,1).setUnlocalizedName("PlastronOs").setTextureName("medievallegend:PlastronOs");
    public static Item JambesOs = new ArmureOs(3526, OsMat, 0,2).setUnlocalizedName("JambesOs").setTextureName("medievallegend:JambesOs");
    public static Item BottesOs = new ArmureOs(3527, OsMat, 0,3).setUnlocalizedName("BottesOs").setTextureName("medievallegend:BottesOs");
    public static Item HeaumeTissu = new ArmureTissu(3532, TissuMat, 0,0).setUnlocalizedName("HeaumeTissu").setTextureName("medievallegend:HeaumeTissu");
    public static Item PlastronTissu = new ArmureTissu(3533, TissuMat, 0,1).setUnlocalizedName("PlastronTissu").setTextureName("medievallegend:PlastronTissu");
    public static Item JambesTissu = new ArmureTissu(3534, TissuMat, 0,2).setUnlocalizedName("JambesTissu").setTextureName("medievallegend:JambesTissu");
    public static Item BottesTissu = new ArmureTissu(3535, TissuMat, 0,3).setUnlocalizedName("BottesTissu").setTextureName("medievallegend:BottesTissu");
    public static Item HeaumeCuir;
    public static Item PlastronCuir;
    public static Item JambesCuir;
    public static Item BottesCuir;
    public static Item HeaumeMailles;
    public static Item PlastronMailles;
    public static Item JambesMailles;
    public static Item BottesMailles;
    
    
    
    @EventHandler 
    public void initConfig(FMLPreInitializationEvent event) 
    { 
    	
    } 
 
    @EventHandler 
    public void load(FMLInitializationEvent event) 
    { 
    	//Craft suprimes
    	RemoveRecipe.removeRecipe(new ItemStack(Item.plateDiamond, 1));
    	RemoveRecipe.removeRecipe(new ItemStack(Item.bootsDiamond, 1));
    	RemoveRecipe.removeRecipe(new ItemStack(Item.legsDiamond, 1));
    	RemoveRecipe.removeRecipe(new ItemStack(Item.helmetDiamond, 1));
    	RemoveRecipe.removeRecipe(new ItemStack(Item.plateGold, 1));
    	RemoveRecipe.removeRecipe(new ItemStack(Item.bootsGold, 1));
    	RemoveRecipe.removeRecipe(new ItemStack(Item.legsGold, 1));
    	RemoveRecipe.removeRecipe(new ItemStack(Item.helmetGold, 1));
    	
    	//Armure de cuir
    	   Item.itemsList[Item.helmetLeather.itemID] = null;
    	   HeaumeCuir = new ArmureCuir(42, CuirMat, 0,0).setUnlocalizedName("helmetCloth");
    	   GameRegistry.registerItem(HeaumeCuir, "Casque de Cuir");
    	   Item.itemsList[Item.helmetLeather.itemID] = HeaumeCuir;
    	   Item.itemsList[Item.plateLeather.itemID] = null;
    	   PlastronCuir = new ArmureCuir(43, CuirMat, 0,1).setUnlocalizedName("chestplateCloth");
    	   GameRegistry.registerItem(PlastronCuir, "Gilet de Cuir");
    	   Item.itemsList[Item.plateLeather.itemID] = PlastronCuir;
    	   Item.itemsList[Item.legsLeather.itemID] = null;
    	   JambesCuir = new ArmureCuir(44, CuirMat, 0,2).setUnlocalizedName("leggingsCloth");
    	   GameRegistry.registerItem(JambesCuir, "Pantalon de Cuir");
    	   Item.itemsList[Item.legsLeather.itemID] = JambesCuir;
    	   Item.itemsList[Item.bootsLeather.itemID] = null;
    	   BottesCuir = new ArmureCuir(45, CuirMat, 0,3).setUnlocalizedName("bootsCloth");
    	   GameRegistry.registerItem(BottesCuir, "Bottes de Cuir");
    	   Item.itemsList[Item.bootsLeather.itemID] = BottesCuir;
    	//Armure de Mailles
    	   Item.itemsList[Item.helmetChain.itemID] = null;
    	   HeaumeMailles = new ArmureMailles(46, MaillesMat, 0,0).setUnlocalizedName("helmetChain").setTextureName("chainmail_helmet");
    	   GameRegistry.registerItem(HeaumeMailles, "Casque de Mailles");
    	   Item.itemsList[Item.helmetChain.itemID] = HeaumeMailles;
    	   Item.itemsList[Item.plateChain.itemID] = null;
    	   PlastronMailles = new ArmureMailles(47, MaillesMat, 0,1).setUnlocalizedName("chestplateChain").setTextureName("chainmail_chestplate");
    	   GameRegistry.registerItem(PlastronMailles, "Gilet de Mailles");
    	   Item.itemsList[Item.plateChain.itemID] = PlastronMailles;
    	   Item.itemsList[Item.legsChain.itemID] = null;
    	   JambesMailles = new ArmureMailles(48, MaillesMat, 0,2).setUnlocalizedName("leggingsChain").setTextureName("chainmail_leggings");
    	   GameRegistry.registerItem(JambesMailles, "Pantalon de Mailles");
    	   Item.itemsList[Item.legsChain.itemID] = JambesMailles;
    	   Item.itemsList[Item.bootsChain.itemID] = null;
    	   BottesMailles = new ArmureMailles(49, MaillesMat, 0,3).setUnlocalizedName("bootsChain").setTextureName("chainmail_boots");
    	   GameRegistry.registerItem(BottesMailles, "Bottes de Mailles");
    	   Item.itemsList[Item.bootsChain.itemID] = BottesMailles;
  
    	proxy.registerRender();
    	GameRegistry.registerWorldGenerator(new WorldGeneratorMod());
    	EntityRegistry.registerGlobalEntityID(EntityCerf.class, "Cerf", EntityRegistry.findGlobalUniqueEntityId(), 24, 30);
    	EntityRegistry.registerModEntity(EntityCerf.class, "Cerf", 250, this, 40, 1, true);
    	EntityRegistry.addSpawn(EntityCerf.class, 1, 4, 4, EnumCreatureType.creature);
    	LanguageRegistry.instance().addStringLocalization("entity.CerfEntity.name", "Cerf");
    }

 
    @EventHandler 
    public void afterLoad(FMLPostInitializationEvent event) 
    { 
    	//Items
    	LanguageRegistry.addName(Malt, "Malt");
    	GameRegistry.registerItem(Malt, "Malt");
    	GameRegistry.registerItem(Biere, "Biere");
    	LanguageRegistry.addName(Biere, "Biere");
    	GameRegistry.registerItem(GrainesMalt, "Graines de Malt");
    	LanguageRegistry.addName(GrainesMalt, "Graines de Malt");
        GameRegistry.registerItem(ItemLitPaille, "ItemLitPaille");
    	LanguageRegistry.addName(ItemLitPaille, "Lit De Paille");
    	GameRegistry.registerItem(LingotArgent, "LingotArgent");
    	LanguageRegistry.addName(LingotArgent, "Lingot d'Argent");
    	GameRegistry.registerItem(LingotAcier, "LingotAcier");
    	LanguageRegistry.addName(LingotAcier, "Lingot d'Acier");
    	GameRegistry.registerItem(LingotMithril, "LingotMithril");
    	LanguageRegistry.addName(LingotMithril, "Lingot de Mithril");
    	GameRegistry.registerItem(LingotCuivre, "LingotCuivre");
    	LanguageRegistry.addName(LingotCuivre, "Lingot de Cuivre");
    	GameRegistry.registerItem(Fourrure, "Fourrure");
    	LanguageRegistry.addName(Fourrure, "Fourrure");
    	GameRegistry.registerItem(Peau, "Peau");
    	LanguageRegistry.addName(Peau, "Peau");
    	GameRegistry.registerItem(PlaqueOs, "PlaqueOs");
    	LanguageRegistry.addName(PlaqueOs, "Plaque d'Os");
    	GameRegistry.registerItem(Tissu, "Tissu");
    	LanguageRegistry.addName(Tissu, "Tissu");
    	LanguageRegistry.addName(Maillon, "Maillon d'Acier");
    	LanguageRegistry.addName(Mailles, "Mailles");
    	LanguageRegistry.addName(Buche, "Buche");
    	
    	//Armures
    	
    	LanguageRegistry.addName(HeaumeArgent, "Heaume d'Argent");    	
    	LanguageRegistry.addName(PlastronArgent, "Plastron d'Argent");  	
    	LanguageRegistry.addName(JambesArgent, "Jambieres d'Argent");
    	LanguageRegistry.addName(BottesArgent, "Bottes d'Argent");
    	LanguageRegistry.addName(BottesAcier, "Bottes d'Acier");
    	LanguageRegistry.addName(HeaumeAcier, "Heaume d'Acier");
    	LanguageRegistry.addName(JambesAcier, "Jambieres d'Acier");
    	LanguageRegistry.addName(PlastronAcier, "Plastron d'Acier");
    	LanguageRegistry.addName(BottesMithril, "Bottes de Mithril");
    	LanguageRegistry.addName(HeaumeMithril, "Heaume de Mithril");
    	LanguageRegistry.addName(JambesMithril, "Jambieres de Mithril");
    	LanguageRegistry.addName(PlastronMithril, "Plastron de Mithril");
    	LanguageRegistry.addName(BottesCuivre, "Bottes de Cuivre");
    	LanguageRegistry.addName(HeaumeCuivre, "Heaume de Cuivre");
    	LanguageRegistry.addName(JambesCuivre, "Jambieres de Cuivre");
    	LanguageRegistry.addName(PlastronCuivre, "Plastron de Cuivre");
    	LanguageRegistry.addName(BottesFourrure, "Bottes de Fourrure");
    	LanguageRegistry.addName(HeaumeFourrure, "Casque de Fourrure");
    	LanguageRegistry.addName(JambesFourrure, "Pantalon de Fourrure");
    	LanguageRegistry.addName(PlastronFourrure, "Gilet de Fourrure");
    	LanguageRegistry.addName(BottesPeau, "Bottes de Peau");
    	LanguageRegistry.addName(HeaumePeau, "Casque de Peau");
    	LanguageRegistry.addName(JambesPeau, "Pantalon de Peau");
    	LanguageRegistry.addName(PlastronPeau, "Gilet de Peau");
    	LanguageRegistry.addName(BottesTissu, "Bottes de Tissu");
    	LanguageRegistry.addName(HeaumeTissu, "Casque de Tissu");
    	LanguageRegistry.addName(JambesTissu, "Pantalon de Tissu");
    	LanguageRegistry.addName(PlastronTissu, "Gilet de Tissu");
    	LanguageRegistry.addName(BottesOs, "Bottes d'Os");
    	LanguageRegistry.addName(HeaumeOs, "Heaume d'Os");
    	LanguageRegistry.addName(JambesOs, "Jambieres d'Os");
    	LanguageRegistry.addName(PlastronOs, "Plastron d'Os");

    	
    	
    	//Blocks
    	GameRegistry.registerBlock(LitPaille, "LitPaille");
    	GameRegistry.registerBlock(Caisse, "Caisse");
    	LanguageRegistry.addName(Caisse, "Caisse");
    	GameRegistry.registerBlock(EscaliersPaille, "Escaliers de Paille");
    	LanguageRegistry.addName(EscaliersPaille, "Escaliers de Paille");
    	GameRegistry.registerBlock(PlantMalt, "Plant de Malt");
    	GameRegistry.registerBlock(Lanterne, "Lanterne");
    	LanguageRegistry.addName(Lanterne, "Lanterne");
    	GameRegistry.registerBlock(Paille, "Paille");
    	GameRegistry.registerBlock(Torchis, "Torchis");
    	LanguageRegistry.addName(Torchis, "Torchis");
    	GameRegistry.registerBlock(MineraiCuivre, "MineraiCuivre");
    	LanguageRegistry.addName(MineraiCuivre, "Minerai de Cuivre");
    	GameRegistry.registerBlock(MineraiArgent, "MineraiArgent");
    	LanguageRegistry.addName(MineraiArgent, "Minerai d'Argent");
    	GameRegistry.registerBlock(MineraiMithril, "MineraiMithril");
    	LanguageRegistry.addName(MineraiMithril, "Minerai de Mithril");
    	GameRegistry.registerBlock(Table, "Table");
    	LanguageRegistry.addName(Table, "Table");
    	GameRegistry.registerBlock(BucheBloc, "BucheBloc");
    	LanguageRegistry.addName(BucheBloc, "Buche");
    	GameRegistry.registerBlock(Lucioles, "Lucioles");
    	LanguageRegistry.addName(Lucioles, "Lucioles");
    	
    	
    	
    	//Crafts
    	GameRegistry.addRecipe(new ItemStack (Biere, 1, 0), new Object[]{"XXX", 'X', Base.Malt});
        GameRegistry.addRecipe(new ItemStack (EscaliersPaille, 4, 0), new Object[]{"X  ", "XX ", "XXX", 'X', Block.hay});
        GameRegistry.addRecipe(new ItemStack (ItemLitPaille, 1, 0), new Object[]{"###", "XXX", '#', Block.hay, 'X', Block.planks});
        GameRegistry.addRecipe(new ItemStack (Caisse, 1, 0), new Object[]{"XXX", "XXX", "XXX", 'X', Block.planks});
        GameRegistry.addRecipe(new ItemStack (Torchis, 4, 0), new Object[]{" O ", "OXO", " O ", 'X', Block.hay, 'O', Block.dirt});
        GameRegistry.addRecipe(new ItemStack (PlaqueOs, 1, 0), new Object[]{"XXX", "XXX", "XXX", 'X', Item.bone});
        GameRegistry.addRecipe(new ItemStack (LingotAcier, 1, 0), new Object[]{"XXX", "OOO", 'X', Item.coal, 'O', Item.ingotIron});
        GameRegistry.addRecipe(new ItemStack (HeaumeAcier, 1, 0), new Object[]{"XXX", "X X", 'X', Base.LingotAcier});
        GameRegistry.addRecipe(new ItemStack (HeaumeArgent, 1, 0), new Object[]{"XXX", "X X", 'X', Base.LingotArgent});
        GameRegistry.addRecipe(new ItemStack (HeaumeMithril, 1, 0), new Object[]{"XXX", "X X", 'X', Base.LingotMithril});
        GameRegistry.addRecipe(new ItemStack (HeaumeCuivre, 1, 0), new Object[]{"XXX", "X X", 'X', Base.LingotCuivre});
        GameRegistry.addRecipe(new ItemStack (HeaumeFourrure, 1, 0), new Object[]{"XXX", "X X", 'X', Base.Fourrure});
        GameRegistry.addRecipe(new ItemStack (HeaumePeau, 1, 0), new Object[]{"XXX", "X X", 'X', Base.Peau});
        GameRegistry.addRecipe(new ItemStack (HeaumeTissu, 1, 0), new Object[]{"XXX", "X X", 'X', Base.Tissu});
        GameRegistry.addRecipe(new ItemStack (HeaumeOs, 1, 0), new Object[]{"XXX", "X X", 'X', Base.PlaqueOs});
        GameRegistry.addRecipe(new ItemStack (PlastronAcier, 1, 0), new Object[]{"X X", "XXX", "XXX", 'X', Base.LingotAcier});
        GameRegistry.addRecipe(new ItemStack (PlastronArgent, 1, 0), new Object[]{"X X", "XXX", "XXX", 'X', Base.LingotArgent});
        GameRegistry.addRecipe(new ItemStack (PlastronMithril, 1, 0), new Object[]{"X X", "XXX", "XXX", 'X', Base.LingotMithril});
        GameRegistry.addRecipe(new ItemStack (PlastronCuivre, 1, 0), new Object[]{"X X", "XXX", "XXX", 'X', Base.LingotCuivre});
        GameRegistry.addRecipe(new ItemStack (PlastronFourrure, 1, 0), new Object[]{"X X", "XXX", "XXX", 'X', Base.Fourrure});
        GameRegistry.addRecipe(new ItemStack (PlastronPeau, 1, 0), new Object[]{"X X", "XXX", "XXX", 'X', Base.Peau});
        GameRegistry.addRecipe(new ItemStack (PlastronTissu, 1, 0), new Object[]{"X X", "XXX", "XXX", 'X', Base.Tissu});
        GameRegistry.addRecipe(new ItemStack (PlastronOs, 1, 0), new Object[]{"X X", "XXX", "XXX", 'X', Base.PlaqueOs});
        GameRegistry.addRecipe(new ItemStack (JambesAcier, 1, 0), new Object[]{"XXX", "X X", "X X", 'X', Base.LingotAcier});
        GameRegistry.addRecipe(new ItemStack (JambesArgent, 1, 0), new Object[]{"XXX", "X X", "X X", 'X', Base.LingotArgent});
        GameRegistry.addRecipe(new ItemStack (JambesMithril, 1, 0), new Object[]{"XXX", "X X", "X X", 'X', Base.LingotMithril});
        GameRegistry.addRecipe(new ItemStack (JambesCuivre, 1, 0), new Object[]{"XXX", "X X", "X X", 'X', Base.LingotCuivre});
        GameRegistry.addRecipe(new ItemStack (JambesFourrure, 1, 0), new Object[]{"XXX", "X X", "X X", 'X', Base.Fourrure});
        GameRegistry.addRecipe(new ItemStack (JambesPeau, 1, 0), new Object[]{"XXX", "X X", "X X", 'X', Base.Peau});
        GameRegistry.addRecipe(new ItemStack (JambesTissu, 1, 0), new Object[]{"XXX", "X X", "X X", 'X', Base.Tissu});
        GameRegistry.addRecipe(new ItemStack (JambesOs, 1, 0), new Object[]{"XXX", "X X", "X X", 'X', Base.PlaqueOs});
        GameRegistry.addRecipe(new ItemStack (BottesAcier, 1, 0), new Object[]{"X X", "X X", 'X', Base.LingotAcier});
        GameRegistry.addRecipe(new ItemStack (BottesArgent, 1, 0), new Object[]{"X X", "X X", 'X', Base.LingotArgent});
        GameRegistry.addRecipe(new ItemStack (BottesMithril, 1, 0), new Object[]{"X X", "X X", 'X', Base.LingotMithril});
        GameRegistry.addRecipe(new ItemStack (BottesCuivre, 1, 0), new Object[]{"X X", "X X", 'X', Base.LingotCuivre});
        GameRegistry.addRecipe(new ItemStack (BottesFourrure, 1, 0), new Object[]{"X X", "X X", 'X', Base.Fourrure});
        GameRegistry.addRecipe(new ItemStack (BottesPeau, 1, 0), new Object[]{"X X", "X X", 'X', Base.Peau});
        GameRegistry.addRecipe(new ItemStack (BottesTissu, 1, 0), new Object[]{"X X", "X X", 'X', Base.Tissu});
        GameRegistry.addRecipe(new ItemStack (BottesOs, 1, 0), new Object[]{"X X", "X X", 'X', Base.PlaqueOs});
        GameRegistry.addRecipe(new ItemStack (Maillon, 1, 0), new Object[]{"X", 'X', Base.LingotAcier});
        GameRegistry.addRecipe(new ItemStack (Mailles, 1, 0), new Object[]{"XX", "XX", 'X', Base.Maillon});
        GameRegistry.addRecipe(new ItemStack (Item.helmetChain, 1, 0), new Object[]{"XXX", "X X", 'X', Base.Mailles});
        GameRegistry.addRecipe(new ItemStack (Item.plateChain, 1, 0), new Object[]{"X X", "XXX", "XXX", 'X', Base.Mailles});
        GameRegistry.addRecipe(new ItemStack (Item.legsChain, 1, 0), new Object[]{"XXX", "X X", "X X", 'X', Base.Mailles});
        GameRegistry.addRecipe(new ItemStack (Item.bootsChain, 1, 0), new Object[]{"X X", "X X", 'X', Base.Mailles});
        GameRegistry.addSmelting(MineraiCuivre.blockID, new ItemStack(LingotCuivre), 0.7F);
        GameRegistry.addSmelting(MineraiArgent.blockID, new ItemStack(LingotArgent), 0.7F);
        GameRegistry.addSmelting(MineraiMithril.blockID, new ItemStack(LingotMithril), 0.7F);
        
        MinecraftForge.addGrassSeed(new ItemStack(GrainesMalt), 10);
    }
    

    
}

     
