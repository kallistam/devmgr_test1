package cn.mirakyux.tools;

public class Git测试 {

    public static void main(String[] args) {
        String path = GitUtil.cloneRepository(
                "https://github.com/wuyunjiang/wayJS-Chinese-Document.git", "test");
        System.err.println(path);
        System.err.println(GitUtil.gitCheckout(path, "test"));

    }

}
