package gr.gousiosg.javacg.core;

public class MethodCall {


    private String callerClassName;
    private String callerMethod;
    private String callerArgumentList;
    private String invokationType;
    private String calleeClassName;
    private String calleeMethod;
    private String calleeArgumentList;


    public MethodCall(String callerClassName, String callerMethod, String callerArgumentList, String invokationType, String calleeClass, String calleeMethod, String calleeArgumentList) {

        this.callerClassName = callerClassName;
        this.callerMethod = callerMethod;
        this.callerArgumentList = callerArgumentList;
        this.invokationType = invokationType;
        this.calleeClassName = calleeClass;
        this.calleeMethod = calleeMethod;
        this.calleeArgumentList = calleeArgumentList;
    }

    @Override
    public String toString() {
        String format = "M:%s:%s(%s) (%s)%s:%s(%s)";
        return String.format(format, callerClassName, callerMethod, callerArgumentList, invokationType, calleeClassName, calleeMethod, calleeArgumentList);
    }

    public static final class From {

        private String className;
        private String name;
        private String argumentList;

        public From(String className, String name, String argumentList) {
            this.className = className;
            this.name = name;
            this.argumentList = argumentList;
        }

        public MethodCall callTo(String invokationType, String calleeClass, String calleeMethod, String arguments) {
            return new MethodCall(className, name, argumentList, invokationType, calleeClass, calleeMethod, arguments);
        }
    }
}
