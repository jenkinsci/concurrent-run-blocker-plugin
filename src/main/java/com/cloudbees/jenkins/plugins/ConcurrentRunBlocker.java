package com.cloudbees.jenkins.plugins;

import hudson.Extension;
import hudson.Util;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.Action;
import hudson.model.Queue.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author <a href="mailto:nicolas.deloof@gmail.com">Nicolas De Loof</a>
 */
@Extension
public class ConcurrentRunBlocker extends QueueDecisionHandler {

    @Override
    public boolean shouldSchedule(Task p, List<Action> actions) {
        if (p instanceof AbstractProject) {
            if (((AbstractProject) p).getProperty(ConcurrentRunBlockerJobProperty.class) != null) {

                // Check if same job is already running with same QueueAction (parameters)
                AbstractProject job  =(AbstractProject) p;
                for (Object b : job.getBuilds()) {
                    AbstractBuild build = (AbstractBuild) b;
                    if (!build.isBuilding()) continue;

                    for (QueueAction action: build.getActions(QueueAction.class)) {
                        if (action.shouldSchedule(actions) == false) {
                            // running job with same parameter
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
