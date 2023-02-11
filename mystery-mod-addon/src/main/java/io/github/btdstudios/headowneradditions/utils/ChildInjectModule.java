package io.github.btdstudios.headowneradditions.utils;

import com.google.inject.Binder;
import com.google.inject.Module;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ChildInjectModule implements Module {
    @Override
    public void configure(Binder binder) {
        binder.bind(IGameProfileUtils.class).to(findVersionClass("implementation.GameProfileUtils"));
    }

    @SuppressWarnings({"unchecked", "SameParameterValue"})
    private <T> Class<? extends T> findVersionClass(String classPath) {
        try {
            Class<?> versionClass = Class.forName("io.github.btdstudios.headowneradditions.version_specific." + classPath);
            return (Class<? extends T>)versionClass;
        } catch (ClassNotFoundException classNotFoundException) {
            throw new RuntimeException(classNotFoundException);
        }
    }

    public static ChildInjectModule create() {
        return new ChildInjectModule();
    }
}
