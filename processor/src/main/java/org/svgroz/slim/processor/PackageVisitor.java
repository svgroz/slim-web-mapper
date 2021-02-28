package org.svgroz.slim.processor;

import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class PackageVisitor extends BasicVisitor<Optional<String>, Void>{
    @Override
    public Optional<String> visitPackage(final PackageElement e, final Void x) {
        return Optional.ofNullable(e.getQualifiedName()).map(Objects::toString);
    }

    @Override
    public Optional<String> visitType(final TypeElement e, final Void unused) {
        throw new IllegalArgumentException("Inner classes are not supported: " + e);
    }
}
