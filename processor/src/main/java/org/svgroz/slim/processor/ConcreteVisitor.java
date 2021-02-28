package org.svgroz.slim.processor;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.ModuleElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.AbstractElementVisitor9;
import java.util.Optional;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class ConcreteVisitor<R, P> extends AbstractElementVisitor9<Optional<R>, P> {
    @Override
    public Optional<R> visitModule(final ModuleElement t, final P x) {
         return Optional.empty();
    }

    @Override
    public Optional<R> visitPackage(final PackageElement e, final P x) {
         return Optional.empty();
    }

    @Override
    public Optional<R> visitType(final TypeElement e, final P x) {
         return Optional.empty();
    }

    @Override
    public Optional<R> visitVariable(final VariableElement e, final P x) {
         return Optional.empty();
    }

    @Override
    public Optional<R> visitExecutable(final ExecutableElement e, final P x) {
         return Optional.empty();
    }

    @Override
    public Optional<R> visitTypeParameter(final TypeParameterElement e, final P x) {
         return Optional.empty();
    }
}
