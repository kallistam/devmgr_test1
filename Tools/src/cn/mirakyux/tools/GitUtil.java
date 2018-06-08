package cn.mirakyux.tools;

import java.io.File;
import org.eclipse.jgit.api.CheckoutCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;

public class GitUtil {

    private static String dir = "/";

    /**
     * git clone
     *
     * @param url uri
     * @param local projectName
     * @return path/error
     */
    public static String cloneRepository(String url, String local) {
        try {
            String path = dir + local + System.currentTimeMillis();
            Git.cloneRepository().setURI(url).setDirectory(new File(path)).call();
            System.err.println("clone success");

            return path;
        } catch (GitAPIException e) {
            System.err.println("== clone error ==> " + e.getMessage());
        }
        return "error";
    }

    public static String gitCheckout(String path, String version) {
        // File repoDir = new File(path);
        File RepoGitDir = new File(path + "/.git");
        if (!RepoGitDir.exists()) {
            System.err.println("Error! Not Exists : " + RepoGitDir.getAbsolutePath());
        } else {
            Repository repo = null;
            try {
                repo = new FileRepository(RepoGitDir.getAbsolutePath());
                Git git = new Git(repo);
                CheckoutCommand checkout = git.checkout();
                // checkout.setName(version);
                checkout.call();
                System.err.println("Checkout to " + version);

                PullCommand pullCmd = git.pull();
                pullCmd.call();

                System.err.println("Pulled from remote repository to local repository at "
                        + repo.getDirectory());
                return "checkout success";
            } catch (Exception e) {
                System.err.println(e.getMessage() + " : " + RepoGitDir.getAbsolutePath());
                return "checkout error";
            } finally {
                if (repo != null) {
                    repo.close();
                }
            }
        }
        return "checkout error";
    }
}
