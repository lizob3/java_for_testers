package by.lisakeyy.mantis.manager;

import by.lisakeyy.mantis.model.IssueData;
import by.lisakeyy.mantis.model.UserData;
import org.mantis.ApiClient;
import org.mantis.ApiException;
import org.mantis.Configuration;
import org.mantis.api.IssuesApi;
import org.mantis.api.UserApi;
import org.mantis.auth.ApiKeyAuth;
import org.mantis.model.AccessLevel;
import org.mantis.model.Identifier;
import org.mantis.model.Issue;
import org.mantis.model.User;

public class RestApiHelper extends HelperBase {

    private final ApiClient defaultClient;

    public RestApiHelper(ApplicationManager manager) {

        super(manager);
        defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath(String.format("%s/api/rest", manager.property("web.baseUrl")));
        ApiKeyAuth Authorization = (ApiKeyAuth) defaultClient.getAuthentication("Authorization");
        Authorization.setApiKey(manager.property("apiKey"));
    }

    public void createIssue(IssueData issueData) {
        Issue issue = new Issue(); // Issue | The issue to add.
        issue.setSummary(issueData.summary());
        issue.setDescription(issueData.description());
        var projectId = new Identifier();
        projectId.setId(issueData.project());
        issue.setProject(projectId);
        var categoryId = new Identifier();
        categoryId.setId(issueData.category());
        issue.setCategory(categoryId);

        IssuesApi apiInstance = new IssuesApi(defaultClient);
        try {
            apiInstance.issueAdd(issue);
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void startCreatingAccount(UserData userData) {
        User user = new User(); // User | The user to add.
        user.setUsername(userData.username());
        user.setEmail(userData.email());

        UserApi apiInstance = new UserApi(defaultClient);
        try {
            apiInstance.userAdd(user);
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }
}
