package org.svgroz.slim.processor;

import org.svgroz.slim.api.Controller;

import javax.annotation.processing.Messager;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.ModuleElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.AbstractElementVisitor9;
import javax.tools.Diagnostic;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class BasicVisitor extends AbstractElementVisitor9<Void, Void> {
    private final Messager messager;

    public BasicVisitor(final Messager messager) {
        this.messager = Objects.requireNonNull(messager);
    }

    @Override
    public Void visitModule(final ModuleElement t, final Void unused) {
        return null;
    }

    @Override
    public Void visitPackage(final PackageElement e, final Void unused) {
        messager.printMessage(Diagnostic.Kind.OTHER, "PackageElement is " + e.getQualifiedName());
        return null;
    }

    @Override
    public Void visitType(final TypeElement e, final Void unused) {
        if (e.getAnnotation(Controller.class) != null && !e.getKind().isInterface()) {
            throw new IllegalArgumentException("Only interfaces supported for @" + Controller.class.getName());
        }

        var className = e.getSimpleName().toString();
        var packageName = e.getEnclosingElement().accept(new ConcreteVisitor<>() {
            @Override
            public Optional<Object> visitPackage(final PackageElement e, final Object x) {
                return Optional.ofNullable(e.getQualifiedName());
            }
        }, null).orElseThrow(() -> new IllegalArgumentException("Could not find package of " + e.getQualifiedName()));

        messager.printMessage(Diagnostic.Kind.OTHER, "TypeElement is " + e.getQualifiedName());
        messager.printMessage(Diagnostic.Kind.OTHER, "Class name is " + className);
        messager.printMessage(Diagnostic.Kind.OTHER, "Package name is " + packageName);

        return null;
    }

    @Override
    public Void visitVariable(final VariableElement e, final Void unused) {
        messager.printMessage(Diagnostic.Kind.OTHER, "VariableElement is " + e.getSimpleName());
        return null;
    }

    @Override
    public Void visitExecutable(final ExecutableElement e, final Void unused) {
        messager.printMessage(Diagnostic.Kind.OTHER, "ExecutableElement is " + e.getSimpleName());
        e.getParameters().forEach(p -> p.accept(this, null));
        return null;
    }

    @Override
    public Void visitTypeParameter(final TypeParameterElement e, final Void unused) {
        messager.printMessage(Diagnostic.Kind.OTHER, "TypeParameterElement is " + e.getSimpleName());
        return null;
    }
}
