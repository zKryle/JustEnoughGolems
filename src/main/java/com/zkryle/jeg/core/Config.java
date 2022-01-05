package com.zkryle.jeg.core;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.BooleanValue SHOULD_MAGMATIC_GOLEM_SPAWN;
    public static final ForgeConfigSpec.ConfigValue<Integer> MAGMATIC_GOLEM_ENTITY_WEIGHT;

    static{
        BUILDER.push( "JustEnoughGolems Config File!" );

        SHOULD_MAGMATIC_GOLEM_SPAWN = BUILDER.comment( "Determines if the Magmatic Golem should spawn in the nether!" )
                .define( "Magmatic Golem Should Spawn", true );
        MAGMATIC_GOLEM_ENTITY_WEIGHT = BUILDER.comment( "Sets the Magmatic Golem spawn weight in the nether!" )
                .define( "Magmatic Golem Spawn Weight", 3 );

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
