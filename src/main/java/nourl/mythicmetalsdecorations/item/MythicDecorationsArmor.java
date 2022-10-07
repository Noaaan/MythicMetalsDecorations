package nourl.mythicmetalsdecorations.item;

import io.wispforest.owo.registration.reflect.SimpleFieldProcessingSubject;

import java.lang.reflect.Field;

@SuppressWarnings("unused")
public class MythicDecorationsArmor implements SimpleFieldProcessingSubject<RegalSet> {

    public static final RegalSet HYDRARGYM = new RegalSet(MythicDecorationsArmorMaterials.HYDRARGYM);
    @Override
    public void processField(RegalSet regalSet, String name, Field field) {
        regalSet.register(name);
    }

    @Override
    public Class<RegalSet> getTargetFieldType() {
        return RegalSet.class;
    }
}
