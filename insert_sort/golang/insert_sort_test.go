package golang

import (
	"fmt"
	"testing"
)

func TestInsertSort(t *testing.T) {
	arr := []int{4, 3, 5, 2, 9, 8}

	InsertSort(arr)

	fmt.Println(arr)
}
