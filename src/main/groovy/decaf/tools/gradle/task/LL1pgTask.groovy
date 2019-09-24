package decaf.tools.gradle.task

import decaf.tools.ll1pg.Main
import org.gradle.api.DefaultTask
import org.gradle.api.file.ConfigurableFileTree
import org.gradle.api.file.FileVisitDetails
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction

class LL1pgTask extends DefaultTask {

    @InputDirectory
    @Optional
    File sourceDir = project.file('src/main/ll1pg')

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
        ConfigurableFileTree files = project.fileTree(dir: sourceDir, include: '**/*.spec')
        if (files.empty) System.err.println("No *.spec file found in src/main/ll1pg/, skip")
        files.visit { FileVisitDetails file ->
            if (file.isDirectory()) {
                System.err.println("Skip directory: " + file.file.path)
                return
            }

            // Call ll1pg
            Main.main(file.file.path, generateDir.path)
        }
    }
}
