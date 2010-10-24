package framework.factories;

import framework.enums.ModuloType;
import framework.interfaces.IModulo;

public abstract class ModuloFactory {
	
	public IModulo createModulo(ModuloType moduloType) {
        return factoryMethod(moduloType);
    }
	
	public abstract IModulo factoryMethod(ModuloType moduloType);
	
}
