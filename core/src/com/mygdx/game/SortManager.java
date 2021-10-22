package com.mygdx.game;

public class SortManager {

    private boolean sorting = false;
    private int[] arr;
    SortAlg alg;

    public SortManager(int[] init, SortAlg alg){
        this.arr = init.clone();
        this.alg = alg;
    }

    public SortManager(int[] init){
        this(init,SortAlg.BUBBLE);
    }

    public void setSort(SortAlg alg){
        this.alg = alg;
    }

    private int lastIndex = 0;
    public int[] step(){
        if(this.arr == null)
            return null;
        switch (alg){
            case BUBBLE:
                boolean swapped = false;
                do{
                    for(int i = lastIndex; i < this.arr.length - 1; i++){
                        if(arr[i] > arr[i+1]){
                           swapped = true;
                           int temp = arr[i];
                           arr[i] = arr[i+1];
                           arr[i+1] = temp;
                           lastIndex = i;
                           return arr;
                        }
                    }
                    lastIndex = 0;
                }while(swapped);
                break;
        }
        return arr;
    }


}
