package by.lisakeyy.mantis.manager;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;

import java.io.File;
import java.io.IOException;

public class JamesCliHelper extends HelperBase {

    public JamesCliHelper(ApplicationManager manager) {
        super(manager);
    }

    public void addUser(String email, String password) {
        CommandLine cmd = new CommandLine("java");
        cmd.addArgument("-cp");
        cmd.addArgument("\"james-server-jpa-app.lib/*\"");
        cmd.addArgument("org.apache.james.cli.ServerCmd");
        cmd.addArgument("AddUser");
        cmd.addArgument(email);
        cmd.addArgument(password);


        DefaultExecutor executor = new DefaultExecutor();
        executor.setWorkingDirectory(new File(manager.property("james.workingDir")));
        try {
            executor.execute(cmd);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
