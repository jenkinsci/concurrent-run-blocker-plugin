package com.cloudbees.jenkins.plugins;
import hudson.Launcher;
import hudson.Extension;
import hudson.model.*;
import hudson.util.FormValidation;
import hudson.tasks.Builder;
import hudson.tasks.BuildStepDescriptor;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.QueryParameter;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @author <a href="mailto:nicolas.deloof@gmail.com">Nicolas De Loof</a>
 */
public class ConcurrentRunBlockerJobProperty extends JobProperty<AbstractProject<?, ?>> {

    @DataBoundConstructor
    public ConcurrentRunBlockerJobProperty() {
    }

    @Extension
    public static final class DescriptorImpl extends JobPropertyDescriptor {

        @Override
        public String getDisplayName() {
            return "Block scheduling this job when same one (same parameters) is already being executed";
        }
    }
}

