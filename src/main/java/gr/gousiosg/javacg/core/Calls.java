package gr.gousiosg.javacg.core;

import gr.gousiosg.javacg.core.inner.ClassVisitor;
import org.apache.bcel.classfile.ClassParser;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Calls {

    private final Consumer<String> classLogger;

    public Calls() {
        this.classLogger = s -> {};
    }

    public Calls(Consumer<String> classLogger) {
        this.classLogger = classLogger;
    }

    public Stream<MethodCall> extractFrom(JarFile jar) {
        Stream<JarEntry> entries = enumerationAsStream(jar.entries());

        Stream<MethodCall> methodCallStream = entries.
                flatMap(e -> visitJar(jar.getName(), e));
        return methodCallStream;
    }

    private Stream<MethodCall> visitJar(String jarFileName, JarEntry jarEntry) {

        try {
            if (jarEntry.isDirectory() || !jarEntry.getName().endsWith(".class"))
                return Stream.empty();

            ClassParser cp = new ClassParser(jarFileName, jarEntry.getName());
            return new ClassVisitor(classLogger ,cp.parse()).start().methodCalls().stream();

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static <T> Stream<T> enumerationAsStream(Enumeration<T> e) {
        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(
                        new Iterator<T>() {
                            public T next() {
                                return e.nextElement();
                            }

                            public boolean hasNext() {
                                return e.hasMoreElements();
                            }
                        },
                        Spliterator.ORDERED), false);
    }

}
