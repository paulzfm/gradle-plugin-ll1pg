package decaf.tools.gradle.task

import org.gradle.api.DefaultTask
import org.gradle.api.file.FileVisitDetails
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction

class LL1ParserGenTask extends DefaultTask {

    @InputDirectory
    @Optional
    File source = project.file('src/main/ll1pg')

    @OutputDirectory
    @Optional
    File generateDir = project.file("$project.buildDir/generated-src/ll1pg")

    @TaskAction
    void generateAndTransform() throws Exception {
        project.delete(generateDir)
        project.mkdir(generateDir)
        generate()
    }

    private void generate() {
        project.fileTree(dir: source, include: '**/*.spec').visit { FileVisitDetails file ->
            if (file.isDirectory()) {
                return
            }
            // Call LL1-parser-gen
        }
    }
}
