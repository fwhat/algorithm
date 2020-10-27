package golang

import (
	"fmt"
	"testing"
)

func TestSelectSort(t *testing.T) {
	arr := []int{4, 3, 5, 2, 9, 8}

	SelectSort(arr)

	fmt.Println(arr)
}

func TestSwap(t *testing.T) {
	arr := []int{1, 3, 5, 2, 9}

	Swap(arr, 0, 1)

	fmt.Println(arr)
}
