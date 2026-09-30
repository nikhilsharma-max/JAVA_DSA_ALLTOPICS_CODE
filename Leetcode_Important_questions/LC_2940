class Solution {
    // Builds the segment tree using the max function and stores indices
    private void buildSegmentTree(int i, int l, int r, int[] segmentTree, int[] heights) {
        if (l == r) {
            segmentTree[i] = l; // Store the index
            return;
        }

        int mid = l + (r - l) / 2;
        buildSegmentTree(2 * i + 1, l, mid, segmentTree, heights);
        buildSegmentTree(2 * i + 2, mid + 1, r, segmentTree, heights);

        // Store the index of the maximum element
        segmentTree[i] = (heights[segmentTree[2 * i + 1]] >= heights[segmentTree[2 * i + 2]]) ?
                segmentTree[2 * i + 1] : segmentTree[2 * i + 2];
    }

    // Function to construct the segment tree
    private int[] constructST(int[] heights) {
        int n = heights.length;
        int[] segmentTree = new int[4 * n];
        buildSegmentTree(0, 0, n - 1, segmentTree, heights);
        return segmentTree;
    }

    // Function to query the segment tree for the index of the maximum value in range [start, end]
    private int querySegmentTree(int start, int end, int i, int l, int r, int[] segmentTree, int[] heights) {
        if (l > end || r < start) {
            return -1; // Return -1 for out-of-bound queries
        }

        if (l >= start && r <= end) {
            return segmentTree[i]; // Return the index of the maximum element
        }

        int mid = l + (r - l) / 2;
        int leftIndex = querySegmentTree(start, end, 2 * i + 1, l, mid, segmentTree, heights);
        int rightIndex = querySegmentTree(start, end, 2 * i + 2, mid + 1, r, segmentTree, heights);

        // Handle cases where one side is out of bounds
        if (leftIndex == -1) return rightIndex;
        if (rightIndex == -1) return leftIndex;

        // Return the index of the maximum element
        return (heights[leftIndex] >= heights[rightIndex]) ? leftIndex : rightIndex;
    }

    // Function to return the index of the maximum element in the range from a to b
    private int RMIQ(int[] st, int[] heights, int a, int b) {
        return querySegmentTree(a, b, 0, 0, heights.length - 1, st, heights);
    }

    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        int n = heights.length;
        int[] segmentTree = constructST(heights);
        int[] result = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {
            int alice = Math.min(queries[q][0], queries[q][1]);
            int bob = Math.max(queries[q][0], queries[q][1]);

            if (alice == bob || heights[bob] > heights[alice]) {
                result[q] = bob;
                continue;
            }

            int l = bob + 1, r = n - 1, resultIdx = Integer.MAX_VALUE;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                int idx = RMIQ(segmentTree, heights, l, mid);

                if (heights[idx] > Math.max(heights[alice], heights[bob])) {
                    r = mid - 1;
                    resultIdx = Math.min(resultIdx, idx);
                } else {
                    l = mid + 1;
                }
            }

            result[q] = (resultIdx == Integer.MAX_VALUE) ? -1 : resultIdx;
        }

        return result;
    }
}