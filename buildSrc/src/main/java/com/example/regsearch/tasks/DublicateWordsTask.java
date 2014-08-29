
package com.example.regsearch.tasks;

import org.gradle.api.DefaultTask;
import org.gradle.api.Project;
import org.gradle.api.file.FileCollection;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.Optional;
import org.gradle.api.tasks.InputFile;
import org.gradle.api.tasks.SourceTask;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.io.IOException;
import java.lang.System;
import java.nio.charset.Charset;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import com.google.common.io.Files;

/**
 * Created by Cannibal on 26.8.2014 г..
 */
public class DublicateWordsTask extends DefaultTask {

    @InputFile
    private File source;

    @Optional
    private Charset encoding = Charset.forName("UTF-8");

    @TaskAction
    void search() {
        //Pattern regex = Pattern.compile("\\b([a-zA-Z]+) +\\1\\b");
        Pattern regex = Pattern.compile("(\\b([',.!?])\\b) \\1");
        for (File file : getProject().zipTree(source)) {
            try {
                Matcher regexMatcher = regex.matcher(Files.toString(file, encoding));
                while (regexMatcher.find()) {
                    System.out.println(regexMatcher.group());
                    System.out.println(file.getName());
                    System.out.println(file.getAbsolutePath());
                    System.out.println("------------------------");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public File getSource() {
        return source;
    }

    public void setSource(File source) {
        this.source = source;
    }

    public Charset getEncoding() {
        return encoding;
    }

    public void setEncoding(Charset encoding) {
        this.encoding = encoding;
    }
}

