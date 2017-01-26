package com.sample.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.internal.file.FileResolver

import javax.inject.Inject

/**
 * Created by yperess on 1/26/17.
 */
class PearPlugin implements Plugin<Project> {

    private final FileResolver mFileResolver;
    @Inject
    public PearPlugin(FileResolver fileResolver) {
        mFileResolver = fileResolver;
    }

    @Override
    void apply(final Project project) {
        project.extensions.create("pear", PearStruct)
        project.tasks.create("build", BuildTask) {
            description = "Generate .pear file for '$project.name'"
        }
    }
}
