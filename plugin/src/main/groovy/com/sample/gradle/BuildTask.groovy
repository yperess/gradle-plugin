package com.sample.gradle

import groovy.json.JsonSlurper
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.TaskAction

/**
 * Created by yperess on 1/26/17.
 */
public class BuildTask extends DefaultTask {

    @TaskAction
    def build() {
        println("building... '$project.pear.srcDir'")
        def manifestFile = new File(project.pear.srcDir, "manifest.json");
        if (!manifestFile.exists()) {
            throw new GradleException("Missing manifest.json in '$project.pear.srcDir'")
        }
        def manifest = new JsonSlurper().parseText(manifestFile.text);
        manifest.each {println it}
        validateString(manifest, 'title')
        validateString(manifest, 'description')
    }

    private static void validateExists(manifest, key) {
        if (!manifest.getAt(key)) {
            throw new GradleException("manifest.json missing field '$key'")
        }
    }
    private static void validateString(manifest, key) {
        validateExists(manifest, key)
        if (!(manifest[key] instanceof String)) {
            throw new GradleException("'$key' must be a string")
        }
    }
}
