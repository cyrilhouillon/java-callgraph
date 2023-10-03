package gr.gousiosg.javacg;

import gr.gousiosg.javacg.stat.JCallGraph;
import org.junit.Test;

public class LaunchStatic {

    @Test
    public void launchStatic() {
        JCallGraph.main(new String[]{"C:\\Users\\houil\\IdeaProjects\\java-callgraph\\target\\javacg-0.1-SNAPSHOT.jar"});
    }
}
