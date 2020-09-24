package com.lsx.algorithm.tree;


/**
 * 给定一个二叉树，我们在树的节点上安装摄像头。
 *
 * 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。
 *
 * 计算监控树的所有节点所需的最小摄像头数量。
 *
 * 例子：
 * 输入：[0,0,null,0,null,0,null,null,0]
 * 输出：2
 * 解释：需要至少两个摄像头来监视树的所有节点。 上图显示了摄像头放置的有效位置之一。
 *
 * 提示：
 * 给定树的节点数的范围是 [1, 1000]。
 * 每个节点的值都是 0。
 */
public class CameraTree {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 递归
     * 需要三种变量：
     *  1，当前遍历的 root 节点。
     *  2，placeCam：root 处是否放相机。
     *  3，watched：root 是否被父亲或自己监控。
     *      - 因为递归是父亲调用儿子，对于当前节点，它只知道父亲和自己是否监控自己，不知道儿子有没有监控自己，所以，watched 只代表是否被父亲或自己监控。
     *
     * 分三种情况：
     * 1，当前节点 root 放了相机（当前子树的相机数，保底为1）
     *  - 左右儿子都没有放相机，都被父亲监控
     *      minCam(root.left, false, true) + minCam(root.right, false, true)
     *  - 左儿子放了相机，被监控，右儿子没有相机，被父亲监控
     *      minCam(root.left, true, true) + minCam(root.right, false, true)
     *  - 左儿子没有相机，被父亲监控，右儿子放了相机，被监控
     *      minCam(root.left, false, true) + minCam(root.right, true, true)
     * 2，当前节点 root 没有相机，但被父亲监控了
     *  - 两个儿子都放了相机，被监控
     *  - 左儿子放了相机，被监控，右儿子没有相机，没有被父亲和自己监控，但被自己儿子监控
     *  - 右儿子放了相机，被监控，左儿子没有相机，没有被父亲和自己监控，但被自己儿子监控
     *  - 两个儿子都没有相机，没有被父亲和自己监控，但被自己儿子监控
     * 3，当前节点 root 没有相机，也没有被父亲监控，是被儿子监控
     *  - 两个儿子都放了相机，被监控
     *  - 左儿子有相机，被监控，右儿子没有相机，没被父亲和自己监控，被自己儿子监控
     *  - 左儿子没有相机，没被父亲和自己监控，被自己儿子监控。右儿子有相机，被监控
     *
     * 特殊情况：递归的入口点
     * 整个树的根节点，它被监控，分两种情况：
     *  1，根节点有相机
     *  2，根节点没有相机，且根节点没有父亲，没有被父亲监控，但被儿子监控。
     *
     *  但是会在求一个子树的三种状态分三次调用去做，重复计算，用dp思想一次调用求出当前子树的三种状态下的minCam：
     *  1，withCam ：当前子树 root 有相机，情况下的minCam         0
     *  2，noCamWatchByDad：当前子树 root 没有相机，被父亲监控，情况下的minCam       1
     *  3，noCamWatchBySon ：当前子树 root 没有相机，被儿子监控，情况下的minCam      2
     *  三种状态放在一个容器返回给父调用，一次调用就得到当前子树的三种状态下的最优解。
     */
    public int minCameraCover(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] res = minCam(root);
        return Math.min(res[0], res[2]);
    }

    public int[] minCam(TreeNode root) {

        if (root == null) {
            // 结束点，null节点，返回给父节点自己有相机的情况，为无穷大，让该值失效
            return new int[]{1000, 0, 0};
        }

        //递归左右子树
        int[] left = minCam(root.left);
        int[] right = minCam(root.right);

        //withCam
        int withCam = 1 + min3(
                left[1] + right[1],
                left[0] + right[1],
                left[1] + right[0]
        );

        //noCamWatchByDad
        int noCamWatchByDad = min4(
                left[0] + right[0],
                left[0] + right[2],
                left[2] + right[0],
                left[2] + right[2]
        );

        //noCamWatchBySon
        int noCamWatchBySon = min3(
                left[0] + right[0],
                left[0] + right[2],
                left[2] + right[0]
        );

        return new int[]{withCam, noCamWatchByDad, noCamWatchBySon};
    }

    // 三个数，取最小
    public int min3(int a, int b, int c) {
        return Math.min(Math.min(a, b), Math.min(b, c));
    }

    //4个数取最小
    public int min4(int a, int b, int c, int d) {
        return Math.min(Math.min(a, b), Math.min(c, d));
    }
}
