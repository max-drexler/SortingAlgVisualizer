package com.mygdx.game;

public class SortManager {
    private boolean sorting;
    private int[] arr;
    SortAlg alg;
    private int lastIndex;

    public SortManager(int[] init, SortAlg alg) {
        this.sorting = false;
        this.lastIndex = 0;
        this.arr = init;
        this.alg = alg;
    }

    public SortManager(int[] init) {
        this(init, SortAlg.BUBBLE);
    }

    public void setSort(SortAlg alg) {
        this.alg = alg;
    }

    public void setArray(int[] arr){
        this.arr = arr;
    }

    /**
     * Completes one step in sorting algorithm
     *
     * @return true if sorted, false if not
     */
    public boolean step() {
        if (this.arr == null) {
            return false;
        }
        switch (this.alg) {
            case BUBBLE:
               return this.bubbleSort();
        }
        return false;
    }

    private boolean bubbleSort(){
        boolean swapped;
        do {
            swapped = lastIndex > 0?true:false;
            for (int i = this.lastIndex; i < this.arr.length - 1; ++i) {
                if (this.arr[i] > this.arr[i + 1]) {
                    swapped = true;
                    this.lastIndex = i;
                    int temp = this.arr[i];
                    this.arr[i] = this.arr[i + 1];
                    this.arr[i + 1] = temp;
                    return false;
                }
            }
            this.lastIndex = 0;
        } while (swapped);
        return true;
    }
}
