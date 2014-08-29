package com.example.regsearch.plugins;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;
import com.example.regsearch.tasks.DublicateWordsTask;


public class RegexPlugin implements Plugin<Project> {
    public static final String DUBLICATE_WORDS_SEARCH = "findDublicates";

    @Override
    public void apply(final Project project) {
        addTasks(project);

    }

    private void addTasks(final Project project){
        Task findDublicates = project.getTasks().create(DUBLICATE_WORDS_SEARCH,DublicateWordsTask.class);
        findDublicates.setGroup("Check spelling");
        findDublicates.setDescription("Check for dublicate words");
    }

}
