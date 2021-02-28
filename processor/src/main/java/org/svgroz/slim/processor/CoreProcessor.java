package org.svgroz.slim.processor;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class CoreProcessor extends AbstractProcessor {
    private final Set<Class<? extends Annotation>> supportedClasses = Set.of(Controller.class);

    private final Configuration templateConfig;

    public CoreProcessor() {
        this.templateConfig = new Configuration(Configuration.VERSION_2_3_31);
        this.templateConfig.setTemplateLoader(new ClassTemplateLoader(getClass(), "/templates"));
    }

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
            ctx.getImports().addAll(
                    Set.of(
                            HttpServlet.class.getName(),
                            ServletException.class.getName(),
                            HttpServletRequest.class.getName(),
                            HttpServletResponse.class.getName(),
                            IOException.class.getName(),
                            Optional.class.getName(),
                            Set.class.getName(),
                            SortedSet.class.getName(),
                            TreeSet.class.getName(),
                            BigDecimal.class.getName()
                    )
            );

            try {
                var writer = new StringWriter();
                templateConfig.getTemplate("HttpServletMapping.ftl").process(Map.of("ctx", ctx), writer);
                String classData = writer.toString();
                messager.printMessage(Diagnostic.Kind.OTHER, "\n" + classData);
                JavaFileObject classFile = filer.createSourceFile(ctx.getPackageName()+ "." + ctx.getClassName());
                Writer classWriter = classFile.openWriter();
                classWriter.write(classData);
                classWriter.flush();
                classWriter.close();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
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
