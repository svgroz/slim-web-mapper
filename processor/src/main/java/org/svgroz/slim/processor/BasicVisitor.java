package org.svgroz.slim.processor;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.ModuleElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.AbstractElementVisitor9;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class BasicVisitor<R, P> extends AbstractElementVisitor9<R, P> {
    @Override
    public R visitModule(final ModuleElement t, final P p) {
        throw new UnsupportedOperationException();
    }

    @Override
    public R visitPackage(final PackageElement e, final P p) {
        throw new UnsupportedOperationException();
    }

    @Override
    public R visitType(final TypeElement e, final P p) {
        throw new UnsupportedOperationException();
    }

    @Override
    public R visitVariable(final VariableElement e, final P p) {
        throw new UnsupportedOperationException();
    }

    @Override
    public R visitExecutable(final ExecutableElement e, final P p) {
        throw new UnsupportedOperationException();
    }

    @Override
    public R visitTypeParameter(final TypeParameterElement e, final P p) {
        throw new UnsupportedOperationException();
    }
}
