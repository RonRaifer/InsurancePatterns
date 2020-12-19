package infrastructures.Factories;

import java.lang.reflect.InvocationTargetException;

public class GenericFactory<T> implements IFactory<T> {
    private Class<?> _clazz;

    public GenericFactory(Class<?> clazz) {
        super();
        _clazz = clazz;
    }

    @Override
    public T create() {
        try {
            return (T) _clazz.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            return null;
        }
    }

}