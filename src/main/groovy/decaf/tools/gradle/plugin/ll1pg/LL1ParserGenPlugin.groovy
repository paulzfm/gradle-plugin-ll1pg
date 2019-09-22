package decaf.tools.gradle.plugin.ll1pg

import decaf.tools.gradle.task.LL1ParserGenTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class LL1ParserGenPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        project.with {
            apply plugin: 'java'
            tasks.create(name: 'll1pg', type: LL1ParserGenTask)
            tasks.compileJava.dependsOn tasks.ll1pg
            sourceSets.main.java.srcDirs += tasks.ll1pg.generateDir
        }
    }
}
