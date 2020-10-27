package golang

import (
	"fmt"
	"testing"
)

func TestBubbleSort(t *testing.T) {
	arr := []int{4, 3, 5, 2, 9, 8}

	BubbleSort(arr)

	fmt.Println(arr)
}
