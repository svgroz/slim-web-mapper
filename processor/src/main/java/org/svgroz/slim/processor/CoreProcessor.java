package org.svgroz.slim.processor;

import org.svgroz.slim.api.Controller;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import java.lang.annotation.Annotation;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class CoreProcessor extends AbstractProcessor {
    private final Set<Class<? extends Annotation>> supportedClasses = Set.of(Controller.class);

    private Types typeUtils;
    private Elements elementUtils;
    private Filer filer;
    private Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        typeUtils = processingEnv.getTypeUtils();
        elementUtils = processingEnv.getElementUtils();
        filer = processingEnv.getFiler();
        messager = processingEnv.getMessager();
    }

    @Override
    public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
        var visitor = new RootVisitor(messager);

        for (final Element element : roundEnv.getElementsAnnotatedWithAny(supportedClasses)) {
            var ctx = visitor.visit(element);
            messager.printMessage(Diagnostic.Kind.OTHER, ctx.toString());
        }

        return false;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return supportedClasses.stream()
                .map(Class::getName)
                .collect(Collectors.toSet());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_11;
    }
}
